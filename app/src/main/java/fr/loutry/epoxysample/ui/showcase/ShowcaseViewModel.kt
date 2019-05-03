package fr.loutry.epoxysample.ui.showcase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.loutry.epoxysample.domain.usecase.ShowcaseUseCase
import fr.loutry.epoxysample.ui.showcase.mappers.ShowcaseUiMapper
import fr.loutry.epoxysample.ui.showcase.models.ShowcaseUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowcaseViewModel(showcaseUseCase: ShowcaseUseCase) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private val _uiState = MutableLiveData<ShowcaseUiModel>()
    val uiState: LiveData<ShowcaseUiModel>
        get() = _uiState

    init {
        showcaseUseCase()
            .subscribeOn(Schedulers.io())
            .map { domainModel -> ShowcaseUiMapper(domainModel) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { uiModel -> _uiState.value = uiModel },
                { throwable -> Log.e("hello", "something went wrong :'(", throwable) })
            .autoDispose()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private fun Disposable.autoDispose() {
        disposables.add(this)
    }
}
