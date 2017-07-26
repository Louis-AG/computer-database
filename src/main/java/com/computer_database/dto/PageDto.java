package com.computer_database.dto;

import java.util.List;

public class PageDto<T> {
    List<T> datas;
    private int pageCurrent;
    private int pageTotal;
    private int limit;

    /**
     * @param datas       datas
     * @param pageCurrent pageCurrent
     * @param pageTotal   pageTotal
     * @param limit       limit
     */
    public PageDto(List<T> datas, int pageCurrent, int pageTotal, int limit) {
        this.datas = datas;
        this.pageCurrent = pageCurrent;
        this.pageTotal = pageTotal;
        this.limit = limit;
    }

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
        if (!(o instanceof PageDto)) {
            return false;
        }

        PageDto<?> pageDto = (PageDto<?>) o;

        if (pageCurrent != pageDto.pageCurrent) {
            return false;
        }
        if (pageTotal != pageDto.pageTotal) {
            return false;
        }
        if (limit != pageDto.limit) {
            return false;
        }
        return datas.equals(pageDto.datas);
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
        return "PageDto{" +
                "pageCurrent=" + pageCurrent +
                ", pageTotal=" + pageTotal +
                ", limit=" + limit +
                ", datas=" + datas +
                '}';
    }
}
