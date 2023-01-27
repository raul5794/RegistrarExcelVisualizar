package com.grabar.excel.model;




import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@IdClass(HojaPK.class)
@Table(name = "guia_tarja")
public class Hoja {
    @Id
    @NotEmpty(message = "No puede estar Vacio")
    @Size(min = 1,max = 4,message = "El periodo debe tener como máximo 4 digitos")
    private String periodo;
    @Id
    @NotEmpty(message = "No puede estar Vacio")
    @Size(min = 1,max = 8,message = "El periodo debe tener como máximo 8 digitos")
    private String manifiesto;
    @Id
    @NotEmpty(message = "No puede estar Vacio")
    private String awb;
    @Id
    @NotEmpty(message = "No puede estar Vacio")
    private String hawb;
    private String consignatario;

    private Double pesosB;
    private String piezas;

    private String piezasR;

    public Hoja(String awb, String hawb, String consignatario, String periodo, String manifiesto, Double pesosB, String piezas, String piezasR) {
        this.awb = awb;
        this.hawb = hawb;
        this.consignatario = consignatario;
        this.periodo = periodo;
        this.manifiesto = manifiesto;
        this.pesosB = pesosB;
        this.piezas = piezas;
        this.piezasR = piezasR;
    }

    public Hoja() {
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public String getHawb() {
        return hawb;
    }

    public void setHawb(String hawb) {
        this.hawb = hawb;
    }

    public String getConsignatario() {
        return consignatario;
    }

    public void setConsignatario(String consignatario) {
        this.consignatario = consignatario;
    }

    public Double getPesosB() {
        return pesosB;
    }

    public void setPesosB(Double pesosB) {
        this.pesosB = pesosB;
    }

    public String getPiezas() {
        return piezas;
    }

    public void setPiezas(String piezas) {
        this.piezas = piezas;
    }

    public String getPiezasR() {
        return piezasR;
    }

    public void setPiezasR(String piezasR) {
        this.piezasR = piezasR;
    }
}
