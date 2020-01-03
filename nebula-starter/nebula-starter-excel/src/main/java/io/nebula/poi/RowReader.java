package io.nebula.poi;

import lombok.Getter;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 徐步龙 on 2017-10-26.
 */
public class RowReader {
    private static final String S_ERR_NUMBER = "第%d列原输入值:【%s】不是正确数值格式;";
    @Getter
    private Row row;
    @Getter
    private List<String> errors;
    private final boolean recordError;

    public RowReader(Row row, boolean recordError) {
        this.row = row;
        this.recordError = recordError;
    }

    public Double getDouble(int colIndex) {
        return getDouble(colIndex, 0D,false);
    }

    public Double getDouble(int colIndex, Double defaultValue, boolean checkFlag) {
        String value = null;
        try {
            value = getString(colIndex);
            boolean result = checkEmpty(value);
            if(!checkFlag){
                if(result){
                    return null;
                }
            }
            return Double.valueOf(value.trim());
        } catch (Exception ex) {
            if(checkFlag || value!=null){
                addError(S_ERR_NUMBER, colIndex, value);
            }
            return defaultValue;
        }
    }

    public Integer getInteger(int colIndex) {
        return getInteger(colIndex, 0, false);
    }

    public Integer getInteger(int colIndex, Integer defaultValue,boolean checkFlag) {
        String value = null;
        try {
            value = getString(colIndex);
            boolean result = checkEmpty(value);
            if(!checkFlag){
                if(result){
                    return null;
                }
            }
            return Integer.valueOf(value.trim());
        } catch (Exception ex) {
            if(checkFlag || (!checkFlag && value!=null)) {
                addError(S_ERR_NUMBER, colIndex, value);
            }
            return defaultValue;
        }
    }

    public Long getLong(int colIndex) {
        return getLong(colIndex, 0L,false);
    }

    public Long getLong(int colIndex, Long defaultValue,boolean checkFlag) {
        String value = null;
        try {
            value = getString(colIndex);
            boolean result = checkEmpty(value);
            if(!checkFlag){
                if(result){
                    return null;
                }
            }
            return Long.valueOf(value.trim());
        } catch (Exception ex) {
            if(checkFlag || (!checkFlag && value!=null)) {
                addError(S_ERR_NUMBER, colIndex, value);
            }
            return defaultValue;
        }
    }

    public BigDecimal getBigDecimal(int colIndex) {
        return getBigDecimal(colIndex, BigDecimal.ZERO,false);
    }

    public BigDecimal getBigDecimal(int colIndex, BigDecimal defaultValue,boolean checkFlag) {
        String value = null;
        try {
            value = getString(colIndex);
            boolean result = checkEmpty(value);
            if(!checkFlag){
                if(result){
                    return null;
                }
            }
            return new BigDecimal(value.trim());
        } catch (Exception ex) {
            if(checkFlag || (!checkFlag && value!=null)) {
                addError(S_ERR_NUMBER, colIndex, value);
            }
            return defaultValue;
        }
    }

    public Date getDate(int colIndex) {
        return getDate(colIndex, null);
    }

    public Date getDate(int colIndex, Date defaultValue) {
        try {
            Cell cell = getCell(colIndex);
            if (cell == null) {
                return null;
            }
            return cell.getDateCellValue();
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public Boolean getBoolean(int colIndex) {
        return getBoolean(colIndex, false);
    }

    public Boolean getBoolean(int colIndex, Boolean defaultValue) {
        try {
            return getCell(colIndex).getBooleanCellValue();
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public String getString(int colIndex) {
        return getString(colIndex, null);
    }

    public String getString(int colIndex, String defaultValue) {
        Cell cell = getCell(colIndex);
        if (null != cell) {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').trim();
        }
        return null;
    }

    private Cell getCell(int columnIndex) {
        return row.getCell(columnIndex);
    }

    public void addError(String template, int colIndex, String value) {
        if (!recordError) {
            return;
        }
        if (null == errors) {
            errors = Lists.newArrayList();
        }
        errors.add(String.format(template, colIndex + 1, value==null?"空":value));
    }

    public boolean checkEmpty(String checkValue){
        boolean result=false;
        if(null!=checkValue && "".equals(checkValue.trim())){
            result = true;
        }
        return result;
    }
}
