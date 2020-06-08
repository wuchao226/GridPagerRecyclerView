package com.wuc.gridpagerrecyclerview.snap.transform;

/**
 * @author : wuchao5
 * @date : 2020/6/5 14:46
 * @desciption :
 */
public class VerticalDataTransform<T> extends AbsRowDataTransform<T> {

    public VerticalDataTransform(int row, int column) {
        super(row, column);
    }

    @Override
    protected int transformIndex(int index, int row, int column) {
        return index;
    }
}
