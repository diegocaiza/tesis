package paqv_vista;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import paq_asistsoft.Asistsoft;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_tipo_aula;
import paq_entidades.entiv_tipo_aula;
import paq_entidades.entiv_variables;
import java.io.Serializable;
import java.util.List;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean()
@ViewScoped
public class Vista_tipo_aulas extends Vista_campostxt implements Serializable {

    @EJB
    private interfaz_tipo_aula acciones;
    entiv_tipo_aula entidad = new entiv_tipo_aula();
    Asistsoft auditoria = new Asistsoft();
    entiv_variables v = new entiv_variables();
    Validaciones valida = new Validaciones();
    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarC;
    //cambiar
    private List<entiv_tipo_aula> filter;

    public List<entiv_tipo_aula> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_tipo_aula> filter) {
        this.filter = filter;
    }
    ////////////////////////////////////////////////    
    entiv_tipo_aula aulaSeleccionada;

    public Vista_tipo_aulas() {
        aulaSeleccionada = new entiv_tipo_aula();
    }

    public void seleccionListaPrueba(SelectEvent event) {
        this.txtcodigo = aulaSeleccionada.getCodigo();
        this.txtnombres = aulaSeleccionada.getNombre();
    }

    public entiv_tipo_aula getAulaSeleccionada() {
        return aulaSeleccionada;
    }

    public void setAulaSeleccionada(entiv_tipo_aula aulaSeleccionada) {
        this.aulaSeleccionada = aulaSeleccionada;
    }

    ////////////////////////////////////////////////
    public Vista_usuario getVistaUsuario() {
        return vistaUsuario;
    }

    public void setVistaUsuario(Vista_usuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }

    public String getMostrarC() {
        mostrarC = vistaUsuario.getTxtnick();
        return mostrarC;
    }

    public void setMostrarC(String mostrarC) {
        this.mostrarC = mostrarC;
    }

    public void insertar() {
        if ("".equals(txtnombres)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar(txtnombres.toUpperCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            auditoria.registro_actividad(getMostrarC(), "tipo_aula", "insertar", txtnombres);
            limpiar();

        }
    }

    public void eliminar(String cod) {
        acciones.eliminar(cod);
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            auditoria.registro_actividad(getMostrarC(), "tipo_aula", "eliminar", cod);
            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos2(txtcodigo, txtnombres) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txtnombres.toUpperCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            auditoria.registro_actividad(getMostrarC(), "tipo_aula", "modificar", txtcodigo + txtnombres);
            limpiar();
        }
    }

    public void modificarCelda(String cod, String nom) {
        if (valida.campos2(cod, nom) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(cod, nom.toUpperCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            auditoria.registro_actividad(getMostrarC(), "tipo_aula", "modificar", txtcodigo + txtnombres);
            limpiar();
        }
    }

    public ArrayList<entiv_tipo_aula> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista();
        try {
            entiv_tipo_aula objart;
            while (v.crs.next()) {
                objart = new entiv_tipo_aula();
                objart.setCodigo(v.crs.getString("ta_codigo"));
                objart.setNombre(v.crs.getString("ta_nombre"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista(String cod, String nom) {
        this.txtcodigo = cod;
        this.txtnombres = nom;
    }

    public void editarCelda(CellEditEvent event) {


//        Object oldValue = event.getOldValue();  
//     Object newValue = event.getNewValue(); 

        entiv_tipo_aula editedLesson = (entiv_tipo_aula) event.getNewValue();

    }

    public void limpiar() {
        txtcodigo = "";
        txtnombres = "";
    }
}
