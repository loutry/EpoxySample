package fr.loutry.epoxysample.ui.common;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

/**
 * A {@link androidx.recyclerview.widget.LinearLayoutManager} implementation which override
 * its child measurement to enforce the number of items displayed on screen at a given time
 * with an always visible sneak pick of next items.
 */
public abstract class RowLayoutManager extends LinearLayoutManager {

    private Resources mResources;
    private int mLastKnownWidth;
    private int mItemSize;
    private RowDecoration mDecor;

    public RowLayoutManager(Context context) {
        super(context, LinearLayoutManager.HORIZONTAL, false);
        init(context);
    }

    private void init(@NonNull Context context) {
        mResources = context.getResources();
        mDecor = new RowDecoration(context, getItemPaddingRes());
    }

    /**
     * Get an res array of dimen breakpoints. Breakpoints array is to read as such:
     * from 0dp to breakpoints[0] dimen, display one item (index +1),
     * from breakpoints[0] dimen to breakpoints[1] dimen, display two items (index +1), etc.
     * NB: value are expected to be ordered ASC.
     *
     * @return array res of dimen breakpoints
     */
    protected abstract int getBreakpointsArrayRes();

    protected abstract int getItemPaddingRes();

    @Override
    public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        int targetMeasureSpec = getOrientation() == HORIZONTAL ? widthSpec : heightSpec;
        if (mLastKnownWidth == 0 || mLastKnownWidth != View.MeasureSpec.getSize(targetMeasureSpec)) {
            mLastKnownWidth = View.MeasureSpec.getSize(targetMeasureSpec);
            resolveItemSize();
            alignFirsItemToAnchor();
        }
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);

        view.addItemDecoration(mDecor);

        SnapHelper snappingSnap = new GravitySnapHelper(getSnapGravity(), false);
        snappingSnap.attachToRecyclerView(view);
    }

    @Override
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        view.removeItemDecoration(mDecor);
        super.onDetachedFromWindow(view, recycler);
    }

    @Override
    public void measureChild(@NonNull View child, int widthUsed, int heightUsed) {
        overrideChildLayoutParams(child);
        super.measureChild(child, widthUsed, heightUsed);
    }

    @Override
    public void measureChildWithMargins(@NonNull View child, int widthUsed, int heightUsed) {
        overrideChildLayoutParams(child);
        super.measureChildWithMargins(child, widthUsed, heightUsed);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        SavedState state = new SavedState();
        if (getChildCount() > 0) {
            boolean didLayoutFromEnd = getStackFromEnd() ^ getReverseLayout();
            state.mStackFromEnd = didLayoutFromEnd;
            if (didLayoutFromEnd) {
                state.mAnchorPosition = findLastCompletelyVisibleItemPosition();
            } else {
                state.mAnchorPosition = findFirstCompletelyVisibleItemPosition();
            }
        }
        return state;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            setStackFromEnd(savedState.mStackFromEnd);
            setReverseLayout(savedState.mReverseLayout);
            scrollToPositionWithOffset(savedState.mAnchorPosition, 0);
        }
    }

    private int getItemPadding(Resources resources) {
        return resources.getDimensionPixelSize(getItemPaddingRes());
    }

    /**
     * Get column number for given {@param fullWidth}.
     *
     * @param resources resources
     * @param fullWidth recyclerView width
     * @return column number for given {@param fullWidth}
     */
    private int getColumnNumber(Resources resources, int fullWidth) {
        int valuesArr = getBreakpointsArrayRes();
        TypedArray values = resources.obtainTypedArray(valuesArr);
        int i = 0;
        int columnNumber = 1;
        while (fullWidth > values.getDimension(i + 1, 0) && i < values.length() - 1) {
            i++;
            columnNumber++;
        }
        values.recycle();
        return columnNumber;
    }

    private void overrideChildLayoutParams(View child) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        if (lp != null) {
            lp.width = getOrientation() == RecyclerView.HORIZONTAL ? mItemSize : lp.width;
            lp.height = getOrientation() == RecyclerView.VERTICAL ? mItemSize : lp.height;
        }
    }

    private void resolveItemSize() {
        int columnNumber = getColumnNumber(mResources, mLastKnownWidth);
        int itemPadding = getItemPadding(mResources);

        int availableSpace = mLastKnownWidth - getPaddingLeft() - getPaddingRight();
        mItemSize = columnNumber > 0 ? (int) Math.floor(availableSpace / columnNumber) - itemPadding : 0;
    }

    private void alignFirsItemToAnchor() {
        int firstCompletelyVisibleItem = findFirstCompletelyVisibleItemPosition();
        if (firstCompletelyVisibleItem >= 0) {
            scrollToPositionWithOffset(findFirstCompletelyVisibleItemPosition(), 0);
        }
    }

    /**
     * Get snap gravity.
     *
     * @return snap gravity
     */
    private int getSnapGravity() {
        if (isStartAnchor()) {
            return Gravity.START;
        } else if (isEndAnchor()) {
            return Gravity.END;
        } else if (isTopAnchor()) {
            return Gravity.TOP;
        } else if (isBottomAnchor()) {
            return Gravity.BOTTOM;
        } else {
            return Gravity.START;
        }
    }

    private boolean isStartAnchor() {
        return getOrientation() == HORIZONTAL && !getReverseLayout();
    }

    private boolean isEndAnchor() {
        return getOrientation() == HORIZONTAL && getReverseLayout();
    }

    private boolean isTopAnchor() {
        return getOrientation() == RecyclerView.VERTICAL && !getReverseLayout();
    }

    private boolean isBottomAnchor() {
        return getOrientation() == RecyclerView.VERTICAL && getReverseLayout();
    }

    public static class SavedState implements Parcelable {

        public static final Creator<SavedState> CREATOR =
                new Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };

        int mAnchorPosition;
        boolean mStackFromEnd;
        boolean mReverseLayout;

        SavedState() {

        }

        SavedState(Parcel in) {
            mAnchorPosition = in.readInt();
            mStackFromEnd = in.readInt() == 1;
            mReverseLayout = in.readInt() == 1;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(mAnchorPosition);
            dest.writeInt(mStackFromEnd ? 1 : 0);
            dest.writeInt(mReverseLayout ? 1 : 0);
        }
    }
}
