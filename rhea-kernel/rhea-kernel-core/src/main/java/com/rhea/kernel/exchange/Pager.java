package com.rhea.kernel.exchange;

import com.github.pagehelper.PageRowBounds;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xubulong
 * @version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager {
    private int pageIndex;
    private int pageSize = 10;

    public PageRowBounds toRowBounds() {
        int offset = (pageIndex > 0 ? pageIndex - 1 : 0) * pageSize;
        return new PageRowBounds(offset, pageSize);
    }
}
