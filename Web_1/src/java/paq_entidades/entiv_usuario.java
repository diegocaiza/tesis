package paq_entidades;

public class entiv_usuario extends entiv_persona {

    public String u_nick = "";
    public String tu_codigo = "";
    public String tipo = "";
    public String u_password = "";
    public String u_promocion = "";
    public String u_extra = "";
    public String mensaje1 = "";

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getU_extra() {
        return u_extra;
    }

    public void setU_extra(String u_extra) {
        this.u_extra = u_extra;
    }

    public String getU_promocion() {
        return u_promocion;
    }

    public void setU_promocion(String u_promocion) {
        this.u_promocion = u_promocion;
    }

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getU_nick() {
        return u_nick;
    }

    public void setU_nick(String u_nick) {
        this.u_nick = u_nick;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getTu_codigo() {
        return tu_codigo;
    }

    public void setTu_codigo(String tu_codigo) {
        this.tu_codigo = tu_codigo;
    }
}
