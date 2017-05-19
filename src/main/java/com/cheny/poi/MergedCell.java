package com.cheny.poi;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 合并的单元格
 * <p>Filename: com.cheny.poi.MergedCell.java</p>
 * <p>Date: 2017-05-19 10:59.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class MergedCell {


    public MergedCell(int firstRow,int firstColumn,int lastRow,int lastColumn){
        this.r0 = firstRow;
        this.c0 = firstColumn;
        this.r1 = lastRow;
        this.c1 = lastColumn;
    }

    /*开始行*/
    private int r0;
    /*开始列*/
    private int c0;

    /*结束行*/
    private int r1;
    /*结束列*/
    private int c1;

    private String value;


    public boolean isMergedCell(){
        return !(r0 == c0 && r0 == r1 && r0 == c1);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MergedCell)) return false;

        MergedCell that = (MergedCell) o;

        return new EqualsBuilder()
                .append(r0, that.r0)
                .append(c0, that.c0)
                .append(r1, that.r1)
                .append(c1, that.c1)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(r0)
                .append(c0)
                .append(r1)
                .append(c1)
                .toHashCode();
    }
    @Override
    public String toString() {
        return "["+r0+":"+c0+","+r1+":"+c1+"]" + " = " + value+"";
    }
}
