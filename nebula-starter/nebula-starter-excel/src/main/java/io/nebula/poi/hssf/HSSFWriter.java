package io.nebula.poi.hssf;

import io.nebula.poi.ExcelWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public class HSSFWriter extends ExcelWriter {

    @Override
    protected Workbook createWorkbook() {
        return new HSSFWorkbook();
    }
}
