package com.wuc.gridpagerrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.wuc.gridpagerrecyclerview.DataSourceUtils.ItemData
import com.wuc.gridpagerrecyclerview.indicator.OnPageChangeListener
import com.wuc.gridpagerrecyclerview.snap.PagerGridSnapHelper
import com.wuc.gridpagerrecyclerview.snap.transform.TwoRowDataTransform
import com.wuc.gridpagerrecyclerview.snap.utils.GridPagerUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //https://github.com/hanhailong/GridPagerSnapHelper/blob/master/README.md
        //https://github.com/whosea/GravitySnapRecycleView/blob/master/README.md

        //行
        val row = 2
        //列
        val column = 3
        // 水平分页布局管理器
        val layoutManager = GridLayoutManager(this, row, GridLayoutManager.HORIZONTAL, false)
        recycler_view_second.layoutManager = layoutManager
        // 设置滚动辅助工具
        val snapHelper =
            PagerGridSnapHelper(Gravity.CENTER)
        snapHelper.setColumn(column)
        snapHelper.attachToRecyclerView(recycler_view_second)
        snapHelper.setCanPageScroll(true)

        val screenWidth: Int = ScreenUtils.getScreenWidth(this)
        val itemWidth: Int = screenWidth / column

        //数据源并且转换
        var dataList = DataSourceUtils.getDataSource()
        dataList = GridPagerUtils.transformAndFillEmptyData(
            TwoRowDataTransform<ItemData>(column), dataList
        )

        //适配器
        val adapter = RecyclerViewAdapter(this, dataList, itemWidth)
        recycler_view_second.adapter = adapter

        //线性指示器
        line_page_indicator.setRecyclerView(recycler_view_second)
        line_page_indicator.setPageColumn(column)

        //圆点指示器
        circle_page_indicator.setRecyclerView(recycler_view_second)
        circle_page_indicator.setPageColumn(column)

        //加入Indicator监听
        circle_page_indicator.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageSelected(position: Int) {
                Log.e("ssss", "position-->$position")
            }
        })

    }


}
