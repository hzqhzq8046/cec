package com.cec.commons.pojo;

import java.util.List;

public class ClockBean<E> {
    private List<E> clocked;

    private Integer totals;

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }


    public List<E> getClocked() {
        return clocked;
    }

    public void setClocked(List<E> clocked) {
        this.clocked = clocked;
    }
}
