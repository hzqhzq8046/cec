package com.cec.commons.pojo;

import java.util.List;

public class PageBean<E> {
    private Integer totals;
    private List<E> rows;


    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
