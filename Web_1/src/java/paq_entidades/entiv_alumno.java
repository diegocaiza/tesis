package paq_entidades;

import java.util.Date;

public class entiv_alumno extends entiv_persona {

    public String codigo_promocion = "";
    public Date fecha_graduacion_pregrado;
    Integer estado1 = null;
    Integer estado2 = null;
    public String codigo = "";
    public String observacion = "";
    public String txtcedula = "";
    public Date txtfecha = null;
    public String mensaje = "";
    public String orden="";

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getTxtfecha() {
        return txtfecha;
    }

    public void setTxtfecha(Date txtfecha) {
        this.txtfecha = txtfecha;
    }

    public String getCodigo_promocion() {
        return codigo_promocion;
    }

    public void setCodigo_promocion(String codigo_promocion) {
        this.codigo_promocion = codigo_promocion;
    }

    public Date getFecha_graduacion_pregrado() {
        return fecha_graduacion_pregrado;
    }

    public void setFecha_graduacion_pregrado(Date fecha_graduacion_pregrado) {
        this.fecha_graduacion_pregrado = fecha_graduacion_pregrado;
    }

    public entiv_alumno() {
    }

    public entiv_alumno(String codigo, Integer es1, Integer es2, String obs) {
        this.codigo = codigo;
        this.estado1 = es1;
        this.estado2 = es2;
        this.observacion = obs;
    }

    public String getTxtcedula() {
        return txtcedula;
    }

    public void setTxtcedula(String txtcedula) {
        this.txtcedula = txtcedula;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getEstado1() {
        return estado1;
    }

    public void setEstado1(Integer estado1) {
        this.estado1 = estado1;
    }

    public Integer getEstado2() {
        return estado2;
    }

    public void setEstado2(Integer estado2) {
        this.estado2 = estado2;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
