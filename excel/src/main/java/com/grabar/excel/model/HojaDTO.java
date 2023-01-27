package com.grabar.excel.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class HojaDTO {
    private String periodo;
    private String hawb;

    @NotEmpty(message = "No puede estar Vacio")
    private String piezasR;

    private String awb;
    private String manifiesto;

    public HojaDTO(String periodo, String hawb, String piezasR, String awb, String manifiesto) {
        this.periodo = periodo;
        this.hawb = hawb;
        this.piezasR = piezasR;
        this.awb = awb;
        this.manifiesto = manifiesto;
    }

    public HojaDTO() {
    }

    public String getManifiesto() {
        return manifiesto;
    }

    public void setManifiesto(String manifiesto) {
        this.manifiesto = manifiesto;
    }

    public String getAwb() {
        return awb;
    }

    public void setAwb(String awb) {
        this.awb = awb;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb;
    }

    public String getPiezasR() {
        return piezasR;
    }

    public void setPiezasR(String piezasR) {
        this.piezasR = piezasR;
    }
}
