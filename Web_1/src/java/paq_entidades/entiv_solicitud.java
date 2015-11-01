package paq_entidades;

import java.util.Date;

public class entiv_solicitud extends entiv_persona {

    public String soli_codigo = "";
    public String soli_redaccion = "";
    public String soli_asunto = "";
    public Date soli_fecha;
    public String soli_estado = "";
    public String soli_contestacion = "";
    public Date soli_fecha_respuesta;
    public String nombres = "";

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getSoli_fecha_respuesta() {
        return soli_fecha_respuesta;
    }

    public void setSoli_fecha_respuesta(Date soli_fecha_respuesta) {
        this.soli_fecha_respuesta = soli_fecha_respuesta;
    }

    public String getSoli_estado() {
        return soli_estado;
    }

    public void setSoli_estado(String soli_estado) {
        this.soli_estado = soli_estado;
    }

    public String getSoli_contestacion() {
        return soli_contestacion;
    }

    public void setSoli_contestacion(String soli_contestacion) {
        this.soli_contestacion = soli_contestacion;
    }

    public String getSoli_codigo() {
        return soli_codigo;
    }

    public void setSoli_codigo(String soli_codigo) {
        this.soli_codigo = soli_codigo;
    }

    public String getSoli_redaccion() {
        return soli_redaccion;
    }

    public void setSoli_redaccion(String soli_redaccion) {
        this.soli_redaccion = soli_redaccion;
    }

    public String getSoli_asunto() {
        return soli_asunto;
    }

    public void setSoli_asunto(String soli_asunto) {
        this.soli_asunto = soli_asunto;
    }

    public Date getSoli_fecha() {
        return soli_fecha;
    }

    public void setSoli_fecha(Date soli_fecha) {
        this.soli_fecha = soli_fecha;
    }
}
