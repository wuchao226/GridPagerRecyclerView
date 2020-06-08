package com.wuc.gridpagerrecyclerview.snap;


/**
 * @author : wuchao5
 * @date : 2020/6/4 19:13
 * @desciption :  RecycleView 状态和页数变化的回调
 */
public interface PageGridChangeListener {

    /**
     * 回调当前位置，当前页数（从1开始），总页数
     *
     * @param position    当前位置
     * @param currentPage 当前页数（从1开始）
     * @param totalPage   总页数
     */
    void onPageSelected(int position, int currentPage, int totalPage);

    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @seeRecyclerView#SCROLL_STATE_IDLE
     * @seeRecyclerView#SCROLL_STATE_DRAGGING
     * @seeRecyclerView#SCROLL_STATE_SETTLING
     */
    void onPageScrollStateChanged(int state);
}
