package com.wuc.gridpagerrecyclerview.snap;

import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : wuchao5
 * @date : 2020/6/4 19:15
 * @desciption :
 */
public class PagerGridSnapHelper extends PagerSnapHelper {

    @NonNull
    private final PagerGridDelegate delegate;
    /**
     * 是否允许翻页的滑动
     */
    private boolean canPageScroll = false;

    public PagerGridSnapHelper() {
        this(Gravity.CENTER, false, null);
    }

    public PagerGridSnapHelper(int gravity) {
        this(gravity, false, null);
    }

    public PagerGridSnapHelper(int gravity, boolean enableSnapLastItem) {
        this(gravity, enableSnapLastItem, null);
    }

    public PagerGridSnapHelper(int gravity, boolean enableSnapLastItem,
                               @Nullable SnapListener snapListener) {
        delegate = new PagerGridDelegate(gravity, enableSnapLastItem, snapListener);

    }

    public void setCanPageScroll(boolean canPageScroll) {
        this.canPageScroll = canPageScroll;
    }

    public void setColumn(int column) {
        delegate.setColumn(column);
    }

    /**
     * 用于将滚动工具和 Recycler 绑定
     *
     * @param recyclerView RecyclerView
     * @throws IllegalStateException 状态异常
     */
    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView)
            throws IllegalStateException {
        delegate.attachToRecyclerView(recyclerView);
        super.attachToRecyclerView(recyclerView);
    }

    /**
     * 计算需要滚动的向量，用于页面自动回滚对齐
     *
     * @param layoutManager 布局管理器
     * @param targetView    目标控件
     * @return 需要滚动的距离
     */
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        return delegate.calculateDistanceToFinalSnap(layoutManager, targetView);
    }

    /**
     * 获得需要对齐的View，对于分页布局来说，就是页面第一个
     *
     * @param layoutManager 布局管理器
     * @return 目标控件
     */
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return delegate.findSnapView(layoutManager);
    }

    /**
     * 获取目标控件的位置下标
     * (获取滚动后第一个View的下标)
     *
     * @param layoutManager 布局管理器
     * @param velocityX     X 轴滚动速率
     * @param velocityY     Y 轴滚动速率
     * @return 目标控件的下标
     */
    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX,
                                      int velocityY) {
        if (!canPageScroll) {
            return super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
        } else {
            return delegate.findTargetSnapPositionByPage(layoutManager, velocityX, velocityY);
        }
    }

    /**
     * Enable snapping of the last item that's snappable.
     * The default value is false, because you can't see the last item completely
     * if this is enabled.
     *
     * @param snap true if you want to enable snapping of the last snappable item
     */
    public void enableLastItemSnap(boolean snap) {
        delegate.enableLastItemSnap(snap);
    }

    public interface SnapListener {
        void onSnap(int position);
    }

}
