package paq_documentos;
import java.util.Date;
public class entidades_cronograma {

    boolean x;
    public String codigo = "";
    public String codigo_programa = "";
    public String au_codigo = "";
    public String pr_cedula = "";
    public String utem_codigo = "";
    public String asig_codigo = "";
    public String mod_codigo = "";
    public String prom_codigo = "";
    public String fd_dia = "";
    public Date fd_hora_inicio;
    public Date fd_hora_final;
    public Date fd_fecha = null;
    public String fd_fecha2 = "";
    public String fd_hora_i = "";
    public String fd_hora_f = "";
    public String piso = "";
    public String bloque = "";
    public String numero = "";
    public String nombre_profesor = "";
    public String nombre_mod = "";
    public String nombre_asig = "";
    public String nombre_programa_postgrado = "";
    public String nombre_uni = "";
    public Integer creditos;
    public String estado_fecha = "";
    public String grupo_modulo = "";
    public String nombre_promocion = "";

    Date dt = new Date();

    public String getEstado_fecha() {
        x = fecha_mayor(getFd_fecha(), dt);
        if (x == true) {
            return "Dictado";
        } else {
            return "En Proceso";
        }

    }
    public boolean xx;
    Date fechaMenor = null;

    public Boolean fecha_mayor(Date fecha1, Date fecha2) {
        if (fecha1.compareTo(fecha2) > 0) {

            fechaMenor = fecha2;
            xx = false;
        } else {
            xx = true;
        }
        return xx;
    }

    public String getCodigo_programa() {
        return codigo_programa;
    }

    public void setCodigo_programa(String codigo_programa) {
        this.codigo_programa = codigo_programa;
    }

    public String getNombre_promocion() {
        return nombre_promocion;
    }

    public void setNombre_promocion(String nombre_promocion) {
        this.nombre_promocion = nombre_promocion;
    }

    public String getGrupo_modulo() {
        return grupo_modulo;
    }

    public void setGrupo_modulo(String grupo_modulo) {
        this.grupo_modulo = grupo_modulo;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getNombre_mod() {
        return nombre_mod;
    }

    public void setNombre_mod(String nombre_mod) {
        this.nombre_mod = nombre_mod;
    }

    public String getNombre_asig() {
        return nombre_asig;
    }

    public String getNombre_programa_postgrado() {
        return nombre_programa_postgrado;
    }

    public void setNombre_programa_postgrado(String nombre_programa_postgrado) {
        this.nombre_programa_postgrado = nombre_programa_postgrado;
    }

    public void setNombre_asig(String nombre_asig) {
        this.nombre_asig = nombre_asig;
    }

    public String getNombre_uni() {
        return nombre_uni;
    }

    public void setNombre_uni(String nombre_uni) {
        this.nombre_uni = nombre_uni;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getFd_fecha2() {
        return fd_fecha2;
    }

    public void setFd_fecha2(String fd_fecha2) {
        this.fd_fecha2 = fd_fecha2;
    }

    public String getFd_hora_i() {
        return fd_hora_i;
    }

    public void setFd_hora_i(String fd_hora_i) {
        this.fd_hora_i = fd_hora_i;
    }

    public String getFd_hora_f() {
        return fd_hora_f;
    }

    public void setFd_hora_f(String fd_hora_f) {
        this.fd_hora_f = fd_hora_f;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAu_codigo() {
        return au_codigo;
    }

    public void setAu_codigo(String au_codigo) {
        this.au_codigo = au_codigo;
    }

    public String getPr_cedula() {
        return pr_cedula;
    }

    public void setPr_cedula(String pr_cedula) {
        this.pr_cedula = pr_cedula;
    }

    public String getUtem_codigo() {
        return utem_codigo;
    }

    public void setUtem_codigo(String utem_codigo) {
        this.utem_codigo = utem_codigo;
    }

    public String getAsig_codigo() {
        return asig_codigo;
    }

    public void setAsig_codigo(String asig_codigo) {
        this.asig_codigo = asig_codigo;
    }

    public String getMod_codigo() {
        return mod_codigo;
    }

    public void setMod_codigo(String mod_codigo) {
        this.mod_codigo = mod_codigo;
    }

    public String getProm_codigo() {
        return prom_codigo;
    }

    public void setProm_codigo(String prom_codigo) {
        this.prom_codigo = prom_codigo;
    }

    public String getFd_dia() {
        return fd_dia;
    }

    public void setFd_dia(String fd_dia) {
        this.fd_dia = fd_dia;
    }

    public Date getFd_hora_inicio() {
        return fd_hora_inicio;
    }

    public void setFd_hora_inicio(Date fd_hora_inicio) {
        this.fd_hora_inicio = fd_hora_inicio;
    }

    public Date getFd_hora_final() {
        return fd_hora_final;
    }

    public void setFd_hora_final(Date fd_hora_final) {
        this.fd_hora_final = fd_hora_final;
    }

    public Date getFd_fecha() {
        return fd_fecha;
    }

    public void setFd_fecha(Date fd_fecha) {
        this.fd_fecha = fd_fecha;
    }
}
