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
import paq_clase_interfaz.interfaz_pantalla;
import paq_entidades.entiv_pantalla;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_pantalla extends Vista_campostxt {

    @EJB
    private interfaz_pantalla acciones;
    Validaciones valida = new Validaciones();
    entiv_pantalla entidad = new entiv_pantalla();
    entiv_variables v = new entiv_variables();
    private List<entiv_pantalla> filter;

    public List<entiv_pantalla> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_pantalla> filter) {
        this.filter = filter;
    }

    public entiv_pantalla getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_pantalla entidad) {
        this.entidad = entidad;
    }
    

    public void insertar() {
        if (valida.campos2(txtnombres, txturl) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar(txtnombres, txturl);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void eliminar(String cod) {
        acciones.eliminar(cod);
        mensaje = acciones.getmensajei();
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos3(txtcodigo, txtnombres, txturl) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txtnombres, txturl);
            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
            limpiar();
        }
    }

    public ArrayList<entiv_pantalla> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista();
        try {
            entiv_pantalla objart;
            while (v.crs.next()) {
                objart = new entiv_pantalla();
                objart.setP_codigo(v.crs.getString("p_codigo"));
                objart.setP_nombre(v.crs.getString("p_nombre"));
                objart.setP_url(v.crs.getString("p_url"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista(SelectEvent event){
        this.txtcodigo = entidad.getP_codigo();
        this.txtnombres = entidad.getP_nombre();
        this.txturl = entidad.getP_url();
    }

    public void limpiar() {
        txtcodigo = "";
        txtnombres = "";
        txturl = "";
    }
}
