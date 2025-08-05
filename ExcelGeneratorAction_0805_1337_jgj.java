// 代码生成时间: 2025-08-05 13:37:23
package com.example.struts.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Action class for Excel Generation
 *
 * This class provides functionality to generate an Excel file.
 */
@ParentPackage("struts")
@Namespace("/excel")
public class ExcelGeneratorAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;

    /**
     * Method to generate an Excel file and download it.
     *
     * @return String the result of the action
     */
    @Action(value = "generateExcel", results = {
            @Result(name = "success", type = "stream", params = {
                    "contentType", "application/vnd.ms-excel",
                    "inputName", "excelStream",
                    "bufferSize", "4096",
                    "contentDisposition", "attachment;filename="${excelFileName}"
            }),
            @Result(name = "input", location = "input.jsp")
    })
    public String generateExcel() {
        try {
            // Create a new Excel workbook
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Generated Excel Sheet");
            // Add sample data to the sheet
            sheet.createRow(0).createCell(0).setCellValue("Column 1");
            sheet.createRow(0).createCell(1).setCellValue("Column 2");
            sheet.createRow(1).createCell(0).setCellValue("Data 1");
            sheet.createRow(1).createCell(1).setCellValue("Data 2");

            // Get the output stream to write the workbook
            OutputStream excelStream = response.getOutputStream();
            workbook.write(excelStream);
            excelStream.close();

            return SUCCESS;
        } catch (IOException e) {
            addFieldError("excelFile", "Error in generating Excel file: " + e.getMessage());
            return INPUT;
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    // Getter and setter for excelFileName
    private String excelFileName;
    public String getExcelFileName() {
        return excelFileName;
    }
    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
}