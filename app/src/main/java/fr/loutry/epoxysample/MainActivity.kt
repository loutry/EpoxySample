package fr.loutry.epoxysample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.loutry.epoxysample.ui.showcase.ShowcaseFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShowcaseFragment.newInstance())
                .commitNow()
        }
    }
}
