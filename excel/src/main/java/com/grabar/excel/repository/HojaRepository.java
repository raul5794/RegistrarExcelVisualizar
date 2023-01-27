package com.grabar.excel.repository;

import com.grabar.excel.model.Hoja;
import com.grabar.excel.model.HojaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HojaRepository extends JpaRepository<Hoja, HojaPK> {

    public List<Hoja> findByAwbAndPeriodoAndManifiesto(String awb,String periodo,String manifiesto);

    public Long  deleteByAwbAndPeriodoAndManifiesto(String awb,String periodo,String manifiesto);
}
