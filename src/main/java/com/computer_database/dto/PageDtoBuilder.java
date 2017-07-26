package com.computer_database.dto;

import java.util.List;

public class PageDtoBuilder<T> {
    private int pageCurrent;
    private int pageTotal;
    private int limit;
    private List<T> datas;

    /**
     * @param pageCurrent pageCurrent
     * @return this for builder
     */
    public PageDtoBuilder setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
        return this;
    }

    /**
     * @param pageTotal pageTotal
     * @return this for builder
     */
    public PageDtoBuilder setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
        return this;
    }

    /**
     * @param limit limit
     * @return this for builder
     */
    public PageDtoBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * @param datas datas
     * @return this for builder
     */
    public PageDtoBuilder setDatas(List<T> datas) {
        this.datas = datas;
        return this;
    }

    /**
     * @return builder
     */
    public PageDto createPageDto() {
        return new PageDto(datas, pageCurrent, pageTotal, limit);
    }
}