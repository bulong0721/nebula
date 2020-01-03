package io.nebula.poi;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.nebula.util.StringUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public abstract class ExcelReader implements AutoCloseable {
    private final HeaderReader headerReader;
    private final Workbook workbook;
    private final InputStream inputStream;
    private final int columnCount;

    public ExcelReader(InputStream inputStream, int columnCount) throws IOException {
        this.headerReader = new HeaderReader(columnCount);
        this.inputStream = inputStream;
        this.workbook = createWorkbook(inputStream);
        this.columnCount = columnCount;
    }

    public ExcelReader(File file, int columnCount) throws IOException {
        this(new FileInputStream(file), columnCount);
    }

    public ExcelReader(String path, int columnCount) throws IOException {
        this(new FileInputStream(path), columnCount);
    }

    protected abstract Workbook createWorkbook(InputStream is) throws IOException;

    Sheet getSheet(String sheetName) {
        if (Strings.isNullOrEmpty(sheetName)) {
            return workbook.getSheetAt(0);
        }
        return workbook.getSheet(sheetName);
    }

    public boolean isEmpty() {
        return isEmpty(null);
    }

    public boolean isEmpty(String sheetName) {
        Sheet sheet = getSheet(sheetName);
        return (null == sheet) || (sheet.getLastRowNum() <= 0);
    }

    public int size() {
        return size(null);
    }

    public int size(String sheetName) {
        Sheet sheet = getSheet(sheetName);
        return sheet.getPhysicalNumberOfRows();
    }

    public String[] readHeader() {
        return readHeader(null);
    }

    public String[] readHeader(String sheetName) {
        if (isEmpty(sheetName)) {
            return new String[0];
        }
        List<String[]> result = read(sheetName, this.headerReader, 0, 1);
        return result.get(0);
    }

    public <T> List<T> readContent(DataReader<T> dataReader) {
        return readContent(null, dataReader);
    }

    public <T> List<T> readContent(String sheetName, DataReader<T> dataReader) {
        if (isEmpty(sheetName)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return read(sheetName, dataReader, 1, size(sheetName));
    }

    private <T> List<T> read(String sheetName, DataReader<T> dataReader, int begin, int end) {
        List<T> result = Lists.newArrayListWithCapacity(end - begin);
        Sheet sheet = getSheet(sheetName);
        for (int i = begin; i < end; i++) {
            Row row = sheet.getRow(i);
            if (null != row) {
                RowReader rowReader = new RowReader(row, false);
                result.add(dataReader.read(rowReader));
            }
        }
        return result;
    }

    public <T> Map<T, List<String>> readContentWithErrors(DataReader<T> dataReader) {
        return readContentWithErrors(null, dataReader);
    }

    public <T> Map<T, List<String>> readContentWithErrors(String sheetName, DataReader<T> dataReader) {
        if (isEmpty(sheetName)) {
            return Maps.newHashMap();
        }
        return readWithErrors(sheetName, dataReader, 1, size(sheetName));
    }

    private <T> Map<T, List<String>> readWithErrors(String sheetName, DataReader<T> dataReader, int begin, int end) {
        Map<T, List<String>> result = Maps.newLinkedHashMapWithExpectedSize(end - begin);
        Sheet sheet = getSheet(sheetName);
        for (int i = begin; i < end; i++) {
            Row row = sheet.getRow(i);
            if (null != row) {
                RowReader rowReader = new RowReader(row, true);
                result.put(dataReader.read(rowReader), rowReader.getErrors());
            }
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (null != inputStream) {
            inputStream.close();
        }
        if (null == workbook) {
            workbook.close();
        }
    }

    static class HeaderReader implements DataReader<String[]> {
        private final int columnCount;
        private String[] result;

        HeaderReader(int columnCount) {
            this.columnCount = columnCount;
        }

        @Override
        public String[] read(RowReader reader) {
            if (null != result) {
                return result;
            }
            if (columnCount <= 0) {
                return new String[0];
            } else {
                result = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    String readerString = reader.getString(i);
                    if (StringUtil.isNotBlank(readerString)) {
                        result[i] = readerString;
                    }
                }
            }
            return result;
        }
    }
}
