package com.dabbler.tools.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2021/12/9
 */
@Slf4j
public class ExcelUtils {



    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     * @param inputStream 读取文件的输入流
     * @param fileType 文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 获取工作簿
     * @param filePath
     * @return
     * @throws IOException
     */
    public static Iterator<Sheet> getSheetIterator(String filePath)throws IOException {
        InputStream inputStream = FileHelper.getInputStream(filePath);
        String suffix = StringUtils.substring(filePath,StringUtils.lastIndexOf(filePath,'.')+1);
        Workbook workbook = getWorkbook(inputStream,suffix);
        log.info("获取sheet个数：{}",workbook.getNumberOfSheets());
        return workbook.sheetIterator();
    }
}
