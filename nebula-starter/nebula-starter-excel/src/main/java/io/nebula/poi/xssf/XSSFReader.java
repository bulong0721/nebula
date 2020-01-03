package io.nebula.poi.xssf;

import io.nebula.poi.ExcelReader;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public class XSSFReader extends ExcelReader {

    public XSSFReader(String filePath, int columnCount) throws IOException {
        super(filePath, columnCount);
    }

    public XSSFReader(InputStream inputStream, int columnCount) throws IOException {
        super(inputStream, columnCount);
    }

    public XSSFReader(File file, int columnCount) throws IOException {
        super(file, columnCount);
    }

    @Override
    protected Workbook createWorkbook(InputStream is) throws IOException {
        return new XSSFWorkbook(is);
    }
}
