package io.nebula.poi.xssf;

import io.nebula.poi.ExcelWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public class XSSFWriter extends ExcelWriter {

    @Override
    protected Workbook createWorkbook() {
        return new XSSFWorkbook();
    }
}
