package paqv_vista;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_tipo_usuario;
import paq_entidades.entiv_tipo_usuario;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_tipo_usuario extends Vista_campostxt {

    @EJB
    private interfaz_tipo_usuario acciones;
    Validaciones valida = new Validaciones();
    entiv_tipo_usuario entidad = new entiv_tipo_usuario();
    entiv_variables v = new entiv_variables();
    private List<entiv_tipo_usuario> filter;

    public List<entiv_tipo_usuario> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_tipo_usuario> filter) {
        this.filter = filter;
    }

    public entiv_tipo_usuario getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_tipo_usuario entidad) {
        this.entidad = entidad;
    }

    public void insertar() {
        if (valida.campos(txtnombres) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar(txtnombres.toUpperCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void eliminar(String cod) {
        acciones.eliminar(cod);
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos(txtnombres) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txtnombres.toUpperCase());
            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
            limpiar();
        }
    }

    public ArrayList<entiv_tipo_usuario> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista();
        try {
            entiv_tipo_usuario objart;
            while (v.crs.next()) {
                objart = new entiv_tipo_usuario();
                objart.setTu_codigo(v.crs.getString("tu_codigo"));
                objart.setTu_nombre(v.crs.getString("tu_nombre"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista(SelectEvent event) {
        this.txtcodigo = entidad.getTu_codigo();
        this.txtnombres = entidad.getTu_nombre();
    }

    public void limpiar() {
        txtcodigo = "";
        txtnombres = "";
    }
}
