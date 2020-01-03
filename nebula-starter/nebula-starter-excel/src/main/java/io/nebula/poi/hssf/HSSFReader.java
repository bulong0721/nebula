package io.nebula.poi.hssf;

import io.nebula.poi.ExcelReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public class HSSFReader extends ExcelReader {

    public HSSFReader(String filePath, int columnCount) throws IOException {
        super(filePath, columnCount);
    }

    public HSSFReader(InputStream inputStream, int columnCount) throws IOException {
        super(inputStream, columnCount);
    }

    public HSSFReader(File file, int columnCount) throws IOException {
        super(file, columnCount);
    }

    @Override
    protected Workbook createWorkbook(InputStream is) throws IOException {
        return new HSSFWorkbook(is);
    }
}
