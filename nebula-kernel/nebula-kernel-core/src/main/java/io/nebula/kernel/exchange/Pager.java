package io.nebula.kernel.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/11/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager {
    private int pageIndex;
    private int pageSize = 10;

}
