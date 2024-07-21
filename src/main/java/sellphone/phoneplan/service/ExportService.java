package sellphone.phoneplan.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import sellphone.phoneplan.model.PhonePlanBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportService {

    public byte[] exportPhonePlansToExcel(List<PhonePlanBean> phonePlans) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Phone Plans");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Plan ID", "Plan Name", "Tel Company", "Contract Type", "Contract Duration",
                            "Generation", "Data Usage", "Data Transfer Rate", "IntraNet Call", "InterNet Call",
                            "Local Call", "Discount", "Gift"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Fill data
        int rowNum = 1;
        for (PhonePlanBean plan : phonePlans) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(plan.getPlanID());
            row.createCell(1).setCellValue(plan.getPlanName());
            row.createCell(2).setCellValue(plan.getTelCompany());
            row.createCell(3).setCellValue(plan.getContractType());
            row.createCell(4).setCellValue(plan.getContractDuration());
            row.createCell(5).setCellValue(plan.getGeneration());
            row.createCell(6).setCellValue(plan.getDataUsage());
            row.createCell(7).setCellValue(plan.getDataTransferRate());
            row.createCell(8).setCellValue(plan.getIntraNetCall());
            row.createCell(9).setCellValue(plan.getInterNetCall());
            row.createCell(10).setCellValue(plan.getLocalCall());
            row.createCell(11).setCellValue(plan.getDiscount());
            row.createCell(12).setCellValue(plan.getGift());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }
}
