package io.nebula.poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by 徐步龙 on 2017-09-06.
 */
public abstract class ExcelWriter implements AutoCloseable {
    private final Workbook workbook;

    public ExcelWriter() {
        this.workbook = createWorkbook();
    }

    protected abstract Workbook createWorkbook();

    public Sheet getOrCreate(String name) {
        Sheet sheet = workbook.getSheet(name);
        if (null != sheet) {
            return sheet;
        }
        return workbook.createSheet(name);
    }

    public void writeHeader(String sheetName, String... headers) {
        HeaderWriter headerWriter = new HeaderWriter();
        List<String> list = Arrays.asList(headers);
        write(sheetName, headerWriter, Arrays.asList(list));
    }

    public void writeHeader(String sheetName, List<String> headers) {
        HeaderWriter headerWriter = new HeaderWriter();
        write(sheetName, headerWriter, Arrays.asList(headers));
    }

    public <T> void write(String sheetName, DataWriter<T> dataWriter, Collection<T> dataSet) {
        Sheet sheet = getOrCreate(sheetName);
        int index = sheet.getPhysicalNumberOfRows();
        for (T data : dataSet) {
            if (index == 0) {
                Row row = sheet.getRow(index);
                if (row == null) {
                    row = sheet.createRow(index++);
                }
                dataWriter.write(new RowWriter(row), data);
            } else {
                Row row = sheet.createRow(index++);
                dataWriter.write(new RowWriter(row), data);
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (null != workbook) {
            workbook.close();
        }
    }

    public void saveAs(OutputStream outputStream) {
        try {
            this.workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class HeaderWriter implements DataWriter<List<String>> {
        @Override
        public void write(RowWriter writer, List<String> data) {
            for (int i = 0; i < data.size(); i++) {
                writer.write(i, data.get(i));
            }
        }
    }
}
