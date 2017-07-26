package com.computer_database.model;

import java.util.List;

public class Page<T> {
    List<T> datas;
    private int pageCurrent;
    private int pageTotal;
    private int limit;

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Page)) {
            return false;
        }

        Page<?> page = (Page<?>) o;

        if (pageCurrent != page.pageCurrent) {
            return false;
        }
        if (pageTotal != page.pageTotal) {
            return false;
        }
        if (limit != page.limit) {
            return false;
        }
        return datas.equals(page.datas);
    }

    @Override
    public int hashCode() {
        int result = pageCurrent;
        result = 31 * result + pageTotal;
        result = 31 * result + limit;
        result = 31 * result + datas.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageCurrent=" + pageCurrent +
                ", pageTotal=" + pageTotal +
                ", limit=" + limit +
                ", datas=" + datas +
                '}';
    }
}
