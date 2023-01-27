package com.grabar.excel.controller;


import com.grabar.excel.model.Hoja;
import com.grabar.excel.model.HojaDTO;
import com.grabar.excel.model.HojaPK;
import com.grabar.excel.service.HojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HojaController {

    @Autowired
    HojaService hojaService;


    @GetMapping("/guias/{periodo}/{manifiesto}/{awb}")
    public ResponseEntity<Iterable<Hoja>> getHojasAwb(@PathVariable @Min(1) @Max(4) String periodo,
                                                      @PathVariable @Min(1) @Max(8) String manifiesto, @PathVariable String awb) {
         try {
            Iterable<Hoja> lstHoja = hojaService.listarAwb(awb,periodo,manifiesto);

            return new ResponseEntity<>(lstHoja, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/guia/{periodo}/{manifiesto}/{awb}/{hawb}")
    public ResponseEntity<Hoja> getHoja(@PathVariable String awb, @PathVariable String hawb,@PathVariable String periodo,@PathVariable String manifiesto) {
        HojaPK hojaPK = new HojaPK(periodo,hawb, awb,manifiesto);
        try {
            Optional<Hoja> hoja = hojaService.listarUno(hojaPK);
            return new ResponseEntity<>(hoja.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping("/guia")
    public ResponseEntity<String> saveHoja(@RequestBody @Valid Hoja hoja) {
        HojaPK hojaPK = new HojaPK(hoja.getPeriodo(),hoja.getHawb(), hoja.getAwb(),hoja.getManifiesto());
        try {
            if (hojaService.listarUno(hojaPK).isEmpty()) {
                hoja = hojaService.grabarHoja(hoja);
                HttpHeaders responseHeaders = new HttpHeaders();
                URI newHojaUri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{periodo}/{manifiesto}/{awb}/{hawb}")
                        .buildAndExpand(hoja.getPeriodo(),hoja.getManifiesto(),hoja.getAwb(), hoja.getHawb()).toUri();
                responseHeaders.setLocation(newHojaUri);
                return new ResponseEntity<>("Se creo el recurso", responseHeaders,HttpStatus.CREATED);
            } else {

                return new ResponseEntity<>("El recurso ya existe", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/guia/{periodo}/{manifiesto}/{awb}/{hawb}")
    public ResponseEntity<?> editarHoja(@Valid @RequestBody Hoja hoja, @PathVariable Map<String,String> pathVarMap) {
        HojaPK hojaPK = constructorHojaPK(pathVarMap);
        try {
            if (!hojaService.listarUno(hojaPK).isEmpty()) {
                hoja = hojaService.grabarHoja(hoja);
                return new ResponseEntity<>("Se actualizó", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("El recurso no existe", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/guia/{periodo}/{manifiesto}/{awb}/{hawb}")
    public ResponseEntity<?> editarHojaParcial(@RequestBody HojaDTO hojaDTO, @PathVariable Map<String,String> pathVarMap){
        HojaPK hojaPK = constructorHojaPK(pathVarMap);
        try{if(!hojaService.listarUno(hojaPK).isEmpty()){

            Optional<Hoja> hoja = hojaService.listarUno(hojaPK);
            hoja.get().setPiezasR(hojaDTO.getPiezasR());
            hojaService.grabarHoja(hoja.get());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("El recurso no existe", HttpStatus.BAD_REQUEST);
        }

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public HojaPK constructorHojaPK(Map<String,String> var){
        return new HojaPK(var.get("periodo"),var.get("hawb"),var.get("awb"),var.get("manifiesto"));
    }
}
