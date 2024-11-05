package com.example.javaknowledge2.test.FileTest.elxs;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExample {
    public static void main(String[] args) {
        // Excel文件路径
        String filePath = "F:\\document\\停机坪托盘机加物料.xlsx";

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // 获取第一个Sheet
            // 获取第一行的第一列
            Cell cell1 = sheet.getRow(0).getCell(0);
            System.out.println("第一行第一列的值: " + cell1.getStringCellValue());

            // 假设我们想在最后一行后添加新数据
            Row lastRow = sheet.getLastRowNum() == -1? null : sheet.getRow(sheet.getLastRowNum());
            int lastRowNum = lastRow == null ? 0 : lastRow.getRowNum();

            // 创建一个新行，在最后一行之后
            Row newRow = sheet.createRow(lastRowNum + 1);

            // 在新行中添加一些数据
            Cell cell = newRow.createCell(0);
            cell.setCellValue("新数据1");
            cell = newRow.createCell(1);
            cell.setCellValue("新数据2");

            // 如果需要，可以将更新后的workbook写回文件
            // 注意：这里为了示例，我们写入一个新文件，避免覆盖原文件
            String newFilePath = "F:\\document\\停机坪托盘机加物料_updated_example.xlsx";
            try (FileOutputStream fos = new FileOutputStream(newFilePath)) {
                workbook.write(fos);
            }

            System.out.println("数据已添加并保存到新文件: " + newFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
