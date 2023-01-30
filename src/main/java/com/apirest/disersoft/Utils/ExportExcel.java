package com.apirest.disersoft.Utils;

import com.apirest.disersoft.Entity.EntityExcel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.IOException;
import java.util.List;

public class ExportExcel {
    private List <EntityExcel> studentList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExportExcel(List<EntityExcel> studentList) {
        this.studentList = studentList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "V_master", style);
        createCell(row, 1, "V_battery", style);
        createCell(row, 2, "master", style);
        createCell(row, 3, "battery", style);
        createCell(row, 4, "charger", style);
        createCell(row, 5, "nvr", style);
        createCell(row, 6, "switchh", style);
        createCell(row, 7, "iot", style);
        createCell(row, 8, "fan", style);
        createCell(row, 9, "status", style);
        createCell(row, 10, "Pwm", style);
        createCell(row, 11, "temp", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (EntityExcel record: studentList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.V_master, style);
            createCell(row, columnCount++, record.V_battery, style);
            createCell(row, columnCount++, record.master, style);
            createCell(row, columnCount++, record.battery, style);
            createCell(row, columnCount++, record.charger, style);
            createCell(row, columnCount++, record.nvr, style);
            createCell(row, columnCount++, record.switchh, style);
            createCell(row, columnCount++, record.iot, style);
            createCell(row, columnCount++, record.fan, style);
            createCell(row, columnCount++, record.status, style);
            createCell(row, columnCount++, record.Pwm, style);
            createCell(row, columnCount++, record.temp, style);
        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
