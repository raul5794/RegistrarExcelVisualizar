package com.grabar.excel.service;

import com.grabar.excel.model.Hoja;
import com.grabar.excel.model.HojaPK;
import com.grabar.excel.repository.HojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HojaService {

    @Autowired
    HojaRepository hojaRepository;


    public Optional<Hoja> listarUno(HojaPK hojaPK) {
        return hojaRepository.findById(hojaPK);
    }

    public Hoja grabarHoja(Hoja hoja) {
        return hojaRepository.save(hoja);
    }

    public void grabarHojas(List<Hoja> lstHoja) {
        hojaRepository.saveAll(lstHoja);
    }

    public void eliminarHoja(Hoja hoja) {
        hojaRepository.delete(hoja);
    }

    public Hoja actualizarHojaParcial(Hoja hoja) {
        return hojaRepository.save(hoja);
    }

    public List<Hoja> listarAwb(String awb,String periodo,String manifiesto){
        return hojaRepository.findByAwbAndPeriodoAndManifiesto(awb,periodo,manifiesto);
    }

    public Long  eliminarAwb(String awb,String periodo,String manifiesto){
        return hojaRepository.deleteByAwbAndPeriodoAndManifiesto(awb,periodo,manifiesto);
    }


}
