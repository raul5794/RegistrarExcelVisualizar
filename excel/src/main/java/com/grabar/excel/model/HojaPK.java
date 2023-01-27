package com.grabar.excel.model;


import java.io.Serializable;
import java.util.Objects;


public class HojaPK implements Serializable {

    private String periodo;
    private String hawb;

private String awb;
    private String manifiesto;

    public HojaPK() {
    }

    public HojaPK(String periodo, String hawb, String awb, String manifiesto) {
        this.periodo = periodo;
        this.hawb = hawb;
        this.awb = awb;
        this.manifiesto = manifiesto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HojaPK hojaPK = (HojaPK) o;
        return Objects.equals(periodo, hojaPK.periodo) && Objects.equals(hawb, hojaPK.hawb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodo, hawb);
    }
}
