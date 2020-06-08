package com.wuc.gridpagerrecyclerview.snap.transform;

/**
 * @author : wuchao5
 * @date : 2020/6/5 14:58
 * @desciption :
 */
public class OneRowDataTransform<T> extends AbsRowDataTransform<T> {

    private static final int ROW = 1;

    public OneRowDataTransform(int column) {
        super(ROW, column);
    }

    @Override
    protected int transformIndex(int index, int row, int column) {
        //First Order
        return index;
    }

}
