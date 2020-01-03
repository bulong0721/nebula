package io.nebula.poi;

import io.nebula.poi.hssf.HSSFReader;
import io.nebula.poi.hssf.HSSFWriter;
import io.nebula.poi.sxssf.SXSSFReader;
import io.nebula.poi.sxssf.SXSSFWriter;
import io.nebula.poi.xssf.XSSFReader;
import io.nebula.poi.xssf.XSSFWriter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public class ExcelUtil {

    public enum Version {
        HSSF, XSSF, SXSSF, Unknown
    }

    public static Version getVersion(String path) {
        if (path.endsWith(".xls")) {
            return Version.HSSF;
        } else if (path.endsWith(".xlsx")) {
            return Version.XSSF;
        }
        return Version.Unknown;
    }

    public static ExcelReader getReader(File file, int colCount) throws IOException {
        String path = file.getPath();
        return getReader(path, colCount);
    }

    public static ExcelReader getReader(String path, int colCount) throws IOException {
        Version ver = getVersion(path);
        return getReader(ver, path, colCount);
    }

    public static ExcelReader getReader(Version ver, String path, int colCount) throws IOException {
        switch (ver) {
            case SXSSF:
                return new SXSSFReader(path, colCount);
            case XSSF:
                return new XSSFReader(path, colCount);
            case HSSF:
                return new HSSFReader(path, colCount);
            default:
                throw new IOException("不支持的Excel文件");
        }
    }

    public static ExcelReader getReader(Version ver, File file, int colCount) throws IOException {
        switch (ver) {
            case SXSSF:
                return new SXSSFReader(file, colCount);
            case XSSF:
                return new XSSFReader(file, colCount);
            case HSSF:
                return new HSSFReader(file, colCount);
            default:
                throw new IOException("不支持的Excel文件");
        }
    }

    public static ExcelReader getReader(Version ver, InputStream in, int colCount) throws IOException {
        switch (ver) {
            case SXSSF:
                return new SXSSFReader(in, colCount);
            case XSSF:
                return new XSSFReader(in, colCount);
            case HSSF:
                return new HSSFReader(in, colCount);
            default:
                throw new IOException("不支持的Excel文件");
        }
    }

    public static ExcelWriter getWriter(Version ver) {
        switch (ver) {
            case SXSSF:
                return new SXSSFWriter();
            case XSSF:
                return new XSSFWriter();
            case HSSF:
                return new HSSFWriter();
            default:
                return new SXSSFWriter();
        }
    }
}
