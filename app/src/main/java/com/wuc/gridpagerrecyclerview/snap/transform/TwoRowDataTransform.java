package com.wuc.gridpagerrecyclerview.snap.transform;

/**
 * @author : wuchao5
 * @date : 2020/6/5 14:46
 * @desciption :
 */
public class TwoRowDataTransform<T> extends AbsRowDataTransform<T> {

    private static final int ROW = 2;

    public TwoRowDataTransform(int column) {
        super(ROW, column);
    }

    @Override
    protected int transformIndex(int index, int row, int column) {
        //页数
        int pageCount = row * column;
        //第几页
        int pre = index / pageCount;
        int divisor = index % pageCount;

        int transformIndex;
        if (divisor % row == 0) {//even
            transformIndex = divisor / row;
        } else {//odd
            transformIndex = column + divisor / row;
        }

        //this is very important
        transformIndex += pre * pageCount;
        return transformIndex;
    }

}
