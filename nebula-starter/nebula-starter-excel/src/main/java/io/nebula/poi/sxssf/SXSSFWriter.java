package io.nebula.poi.sxssf;

import io.nebula.poi.ExcelWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public class SXSSFWriter extends ExcelWriter {

    @Override
    protected Workbook createWorkbook() {
        return new SXSSFWorkbook();
    }
}
