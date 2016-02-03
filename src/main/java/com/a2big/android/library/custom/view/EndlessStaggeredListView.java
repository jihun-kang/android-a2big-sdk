package com.a2big.android.library.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

/**
 * Created by a2big on 15. 6. 28..
 */
public class EndlessStaggeredListView extends ListView {

    private final static String TAG = "EndlessStaggeredGridView";



    private boolean mIsLoading;
    private ProgressBar progressBar;

    public interface OnLoadMoreListener {
        public boolean onLoadMore();
    }
    private OnLoadMoreListener onLoadMoreListener;

    //make jh
    public interface OnSelectItemListener {
        public boolean onSelectItem(int position);
    }
    private OnSelectItemListener onSelectItemListener;       //make jh


    public EndlessStaggeredListView(Context context) {
        super(context);
        init();

    }

    public EndlessStaggeredListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EndlessStaggeredListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    //make jh
    public void setOnSelectItemListener(OnSelectItemListener onSelectItemListener){
        this.onSelectItemListener = onSelectItemListener;
    }


    public void loadMoreCompleat() {
        mIsLoading = false;
        progressBar.setVisibility(View.GONE);
    }

    private void init() {
        mIsLoading = false;

        progressBar = new ProgressBar(getContext(), null,
                android.R.attr.progressBarStyle);
        LinearLayout.LayoutParams progressBarParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(progressBarParams);
        progressBar.setPadding(6, 6, 6, 6);
        progressBar.setVisibility(View.GONE);
       // progressBar.setVisibility(View.VISIBLE);

        LinearLayout footerLinearLayout = new LinearLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        footerLinearLayout.setGravity(Gravity.CENTER);
        footerLinearLayout.setLayoutParams(layoutParams);
        footerLinearLayout.addView(progressBar);

        addFooterView(footerLinearLayout);

        super.setOnScrollListener(new ELScrollChangedListener());

    }


    private class ELScrollChangedListener implements OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {

            boolean loadMore;
            loadMore = (0 != totalItemCount)
                    && ((firstVisibleItem + visibleItemCount) >= (totalItemCount));
            Log.e("JH","debug 1111");
            if (false == mIsLoading && true == loadMore) {
                Log.e("JH", "debug 2222");

                //jh
                if( null != onSelectItemListener){
                    onSelectItemListener.onSelectItem(firstVisibleItem);
                }

                ////
                if (null != onLoadMoreListener) {
                    Log.e("JH","debug 3333");

                    if (onLoadMoreListener.onLoadMore()) {
                        mIsLoading = true;
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

}
