package paqv_vista;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_paralelo;
import paq_entidades.entiv_paralelo;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_parale extends Vista_campostxt {

    @EJB
    private interfaz_paralelo acciones;
    @EJB
    private interfaz_paralelo lista;
    entiv_paralelo entidad = new entiv_paralelo();
    entiv_variables v = new entiv_variables();
    Validaciones valida = new Validaciones();
    private List<entiv_paralelo> filter;

    public List<entiv_paralelo> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_paralelo> filter) {
        this.filter = filter;
    }
    

    public void insertar() {
        if (valida.campos(txtnombres) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar(txtnombres.toUpperCase());
            if (codigo_oracle_duplicado.equals(acciones.getmensajei().substring(0, 9))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado, ""));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                limpiar();
            }
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
        if (valida.campos2(txtcodigo, txtnombres) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txtnombres.toUpperCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public ArrayList<entiv_paralelo> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = lista.getLista();
        try {
            entiv_paralelo objart;
            while (v.crs.next()) {
                objart = new entiv_paralelo();
                objart.setCodigo(v.crs.getString("pl_codigo"));
                objart.setNombre(v.crs.getString("pl_nombre"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista(String cod, String nom) throws ParseException {
//        entidad = (entiv_paralelo) tabla.getRowData();
        this.txtcodigo = cod;
        this.txtnombres = nom;
    }

    public void limpiar() {
        txtcodigo = "";
        txtnombres = "";
    }
}
