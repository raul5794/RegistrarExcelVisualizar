package com.grabar.excel.service;


import com.grabar.excel.model.Hoja;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class ExcelService {

    @Autowired
    HojaService hojaService;
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"HAWB", "CONSIGNATARIO", "MAWB", "PESOS B", "PIEZAS", "PIEZAS RECIBIDAS"};
    static String SHEET = "SearchResults";

    public List<Hoja> grabarExcel(InputStream inputStream,String manifiesto,String periodo) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Hoja> lstHoja = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Hoja hoja = new Hoja();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    DataFormatter formatter = new DataFormatter();
                    switch (cellIdx) {
                        case 0:

                            hoja.setHawb(formatter.formatCellValue(currentCell));
                            break;

                        case 1:
                            hoja.setConsignatario(formatter.formatCellValue(currentCell));
                            break;

                        case 2:

                            hoja.setAwb(formatter.formatCellValue(currentCell));
                            break;

                        case 3:
                           hoja.setPesosB(currentCell.getNumericCellValue());
                            break;
                        case 4:
                            hoja.setPiezas(formatter.formatCellValue(currentCell));
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }
                if(hoja.getAwb()!=null){
                    hoja.setPeriodo(periodo);
                    hoja.setManifiesto(manifiesto);
                lstHoja.add(hoja);}
            }
            hojaService.grabarHojas(lstHoja);
            return  lstHoja;
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }

    }

}
