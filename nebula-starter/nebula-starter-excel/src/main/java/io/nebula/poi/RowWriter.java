package io.nebula.poi;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 徐步龙 on 2017-09-06.
 */
public class RowWriter {
    @Getter
    private final Row row;

    public RowWriter(Row row) {
        assert row != null;
        this.row = row;
    }

    public Cell write(int index, boolean value) {
        Cell cell = createCell(index);
        cell.setCellValue(value);
        return cell;
    }

    public Cell write(int index, double value) {
        Cell cell = createCell(index);
        cell.setCellValue(value);
        return cell;
    }

    public Cell write(int index, int value) {
        Cell cell = createCell(index);
        cell.setCellValue(value);
        return cell;
    }

    public Cell write(int index, Date value) {
        Cell cell = createCell(index);
        cell.setCellValue(value);
        return cell;
    }

    public Cell write(int index, String value) {
        Cell cell = createCell(index);
        cell.setCellValue(value);
        return cell;
    }

    public Cell write(int index, long value) {
        Cell cell = createCell(index);
        cell.setCellValue(value);
        return cell;
    }

    public Cell write(int index, BigDecimal value) {
        Cell cell = createCell(index);
        cell.setCellValue(value!=null?value.toString():null);
        return cell;
    }

    Cell createCell(int columnIndex) {
        return row.createCell(columnIndex);
    }
}
