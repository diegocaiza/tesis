package paqv_vista;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import paq_calculo.Validaciones;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_usuario;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_formateoclave extends Vista_campostxt {

    @EJB
    private interfaz_usuario acciones;
    Validaciones valida = new Validaciones();
    entiv_variables v = new entiv_variables();
    fun_calculo fu = new fun_calculo();

    public void formatear() {
        if (valida.campos2(txtcedula, txtrespuesta) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
          comprobar_pregunta();
        }
    }

    public void limpiar() {
        txtcodigo = "";
        txtpregunta = "";
        txtcedula = "";
    }

    public String regresar() {
        return "index";
    }

    public void comprobar_pregunta() {
        v.crs1 = acciones.devolver_datos_formatear_clave(txtcedula, txtrespuesta);
        if (v.crs1.size() > 0) {
            txtcodigo = fu.numeros_aleatorios();
            acciones.formatear_clave(txtcedula, txtrespuesta, txtcodigo);
            mensaje = acciones.getmensajei().substring(0, 9);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La Cédula o la respuesta secreta son incorrectas, Consulte al Administrador", ""));

        }

    }
}
