package com.wuc.gridpagerrecyclerview.snap.transform;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wuchao5
 * @date : 2020/6/5 14:46
 * @desciption : 数据转换类，类似倒置矩阵，把最后一页的空数据也补上
 */
public abstract class AbsRowDataTransform<T> {

    private static final int DEFAULT_ROW = 1;
    private static final int DEFAULT_COLUMN = 1;

    private int mRow = DEFAULT_ROW;
    private int mColumn = DEFAULT_COLUMN;

    public AbsRowDataTransform(int row, int column) {
        if (row <= 0 || column <= 0)
            throw new IllegalArgumentException("row or column must be not null");

        this.mRow = row;
        this.mColumn = column;
    }

    public List<T> transform(List<T> dataList) {
        List<T> destList = new ArrayList<T>();

        //页数
        int pageSize = mRow * mColumn;
        //总数量
        int size = dataList.size();
        //转换后的总数量，包括一页空的数据
        int afterTransformSize;
        if (size < pageSize) {
            afterTransformSize = pageSize;
        } else if (size % pageSize == 0) {
            afterTransformSize = size;
        } else {
            afterTransformSize = (size / pageSize + 1) * pageSize;
        }
        //开始遍历位置，类似置换矩阵
        for (int i = 0; i < afterTransformSize; i++) {
            int index = transformIndex(i, mRow, mColumn);
            if (index >= 0 && index < size) {
                destList.add(dataList.get(index));
            } else {
                destList.add(null);
            }
        }

        return destList;
    }

    protected abstract int transformIndex(int index, int row, int column);

}
