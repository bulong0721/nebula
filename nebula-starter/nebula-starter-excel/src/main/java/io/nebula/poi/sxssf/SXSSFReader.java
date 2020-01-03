package io.nebula.poi.sxssf;

import io.nebula.poi.ExcelReader;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public class SXSSFReader extends ExcelReader {

    public SXSSFReader(String filePath, int columnCount) throws IOException {
        super(filePath, columnCount);
    }

    public SXSSFReader(InputStream inputStream, int columnCount) throws IOException {
        super(inputStream, columnCount);
    }

    public SXSSFReader(File file, int columnCount) throws IOException {
        super(file, columnCount);
    }

    @Override
    protected Workbook createWorkbook(InputStream is) throws IOException {
        return new SXSSFWorkbook(new XSSFWorkbook(is));
    }
}
