// 代码生成时间: 2025-08-01 09:50:40
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelGenerator {
    // 生成Excel文件的方法
    public void generateExcel(String[] headers, String[][] data, String filename) {
        try {
            // 创建工作簿
            Workbook workbook = new XSSFWorkbook();
            // 创建工作表
            Sheet sheet = workbook.createSheet("Data Sheet");
            // 创建单元格样式
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // 创建标题行
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // 创建数据行
            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < data[i].length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(data[i][j]);
                }
            }

            // 写入文件
            FileOutputStream outputStream = new FileOutputStream(filename);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            System.out.println("Excel file created at: " + new Date());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error occurred while creating the Excel file.");
        }
    }

    // 主方法，用于测试Excel生成器
    public static void main(String[] args) {
        ExcelGenerator generator = new ExcelGenerator();
        String[] headers = {"Header1", "Header2", "Header3"};
        String[][] data = {
            {"Data11", "Data12", "Data13"},
            {"Data21", "Data22", "Data23"},
            {"Data31", "Data32", "Data33"}
        };
        generator.generateExcel(headers, data, "example.xlsx");
    }
}
