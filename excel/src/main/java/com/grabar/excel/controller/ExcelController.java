package com.grabar.excel.controller;

import com.grabar.excel.model.Hoja;
import com.grabar.excel.service.ExcelService;
import com.grabar.excel.service.HojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @Autowired
    HojaService hojaService;

    @PostMapping("/grabar")
    public ResponseEntity<?> grabarExcel(@RequestParam(name = "file") @NotEmpty MultipartFile multipartFile,
                                         @RequestParam @Min(1) @Max(4) String periodo,
                                         @RequestParam @Min(1) @Max(8) String manifiesto) {
        try {

            List<Hoja> lstHoja = excelService.grabarExcel(multipartFile.getInputStream(),manifiesto,periodo);
            return new ResponseEntity(lstHoja, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @DeleteMapping("/eliminarExcel/{periodo}/{manifiesto}/{awb}")
    public ResponseEntity<?> eliminarExcel(@PathVariable String periodo,@PathVariable String manifiesto,@PathVariable String awb) {
        try {
            Long cant = hojaService.eliminarAwb(awb,periodo,manifiesto);
            return new ResponseEntity<>("Se eliminaron: " + cant + " registros.", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
