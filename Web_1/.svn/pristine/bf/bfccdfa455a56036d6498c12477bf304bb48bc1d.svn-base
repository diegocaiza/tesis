package paqv_vista;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import paq_asistsoft.Asistsoft;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_tipo_informacion;
import paq_entidades.entiv_tipo_informacion;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_departamento extends Vista_campostxt implements Serializable {

    @EJB
    private interfaz_tipo_informacion acciones;
    entiv_tipo_informacion entidad = new entiv_tipo_informacion();
    entiv_variables v = new entiv_variables();
    Validaciones valida = new Validaciones();
    Asistsoft rev = new Asistsoft();
    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarC;
    private List<entiv_tipo_informacion> filter;

    public entiv_tipo_informacion getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_tipo_informacion entidad) {
        this.entidad = entidad;
    }

    public List<entiv_tipo_informacion> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_tipo_informacion> filter) {
        this.filter = filter;
    }

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

    public void insertar_departamento() {
        if (valida.campos_departamento(txtnombres, txtextra) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar_departamento(txtnombres.toUpperCase(), txtextra.toUpperCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            rev.registro_actividad(getMostrarC(), "departamento", "insertar", txtnombres);
            limpiar();
        }
    }

     public void eliminar(String cod) {
        acciones.eliminar_departamento(cod);
       
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            rev.registro_actividad(getMostrarC(), "departamento", "eliminar", txtcodigomod);
            limpiar();
        }
    }

    public void modificar_departamento() {
        if (valida.campos_departamento(txtextra, txtnombres) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar_departamento(txtcodigomod, txtnombres.toUpperCase(), txtextra.toUpperCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            rev.registro_actividad(getMostrarC(), "departamento", "modificar", txtcodigomod + txtnombresmod);
            limpiar();
        }
    }

    public ArrayList<entiv_tipo_informacion> getLista1_departamento() {
        ArrayList listae = new ArrayList();
        crs0 = acciones.getLista_departamento();
        try {
            entiv_tipo_informacion objart;
            while (crs0.next()) {
                objart = new entiv_tipo_informacion();
                objart.setCodigo(crs0.getString("dep_codigo"));
                objart.setNombre(crs0.getString("dep_nombre"));
                objart.setPp_nombre(crs0.getString("dep_director"));
                listae.add(objart);
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar datos" + e.getMessage());
        }
        return listae;
    }

    public void seleccionardepartamento(SelectEvent event) {
        this.txtcodigomod = entidad.getCodigo();
        this.txtnombres = entidad.getNombre();
        this.txtextra = entidad.getPp_nombre();
    }

    public void limpiar() {
        txtcodigomod = "";
        txtnombres = "";
        txtextra="";
    }
}
