package com.takaibun.plexmetadatamanager.http.req;

import lombok.Data;

/**
 * PageBase
 *
 * @author takaibun
 * @since 2024/3/2
 */
@Data
public class PageBase {
    private final Integer pageNum;

    private final Integer pageSize;

    public PageBase() {
        this.pageNum = 0;
        this.pageSize = 0;
    }

    public PageBase(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
