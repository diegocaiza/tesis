package paqv_vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_programa;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_coordinador;
import paq_entidades.entiv_programa_postgrado;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_programa_postgrado extends Vista_campostxt {

    @EJB
    private interfaz_programa acciones;
    @EJB
    private interfaz_coordinador acciones_coordinador;
    entiv_variables v = new entiv_variables();
    Validaciones valida = new Validaciones();
    public List alListaNombres = new ArrayList();
    public List listanombresaula = new ArrayList();
    entiv_programa_postgrado entidad = new entiv_programa_postgrado();
    fun_calculo fu = new fun_calculo();
    private List<entiv_programa_postgrado> filter;

    public entiv_programa_postgrado getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_programa_postgrado entidad) {
        this.entidad = entidad;
    }

    public List<entiv_programa_postgrado> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_programa_postgrado> filter) {
        this.filter = filter;
    }

    public void insertar() {
        if (valida.campos_departamentoo(txtnombres, txtsoli_asunto, txtcedula) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            txtsoli_fecha_respuesta = new Date();
            acciones.inser(txtnombres.toUpperCase(), txtcedula, "ACTIVO", txtsoli_asunto, fu.formato_fecha2(txtsoli_fecha_respuesta));
            if (codigo_oracle_no_duplicidad.equals(acciones.getmensajei().substring(0, 9))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_no_duplicidad, ""));
            } else if (codigo_oracle_no_duplicidad != acciones.getmensajei().substring(0, 9)) {
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

    public void activar_programa(String cod) {
        acciones.activar_programa(cod);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje_activar_programa, ""));
    }

    public void desactivar_programa(String cod) {
        acciones.desactivar_programa(cod);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje_desactivar_programa, ""));
    }

    public void modificar() {
        if (valida.campos_departamentoo(txtsoli_asunto, txtnombres, txtcedula) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txtnombres.toUpperCase(), txtcedula,  txtsoli_asunto);
            if (codigo_oracle_no_duplicidad.equals(acciones.getmensajei().substring(0, 9))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_no_duplicidad, ""));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                limpiar();
            }
        }
    }

    public ArrayList<entiv_programa_postgrado> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista();
        try {
            entiv_programa_postgrado objart;
            while (v.crs.next()) {
                objart = new entiv_programa_postgrado();
                objart.setCodigo(v.crs.getString("pp_codigo"));
                objart.setNombre(v.crs.getString("pp_nombre"));
                objart.setEatado(v.crs.getString("pp_estado"));
                objart.setFin(v.crs.getDate("pp_fecha_creacion"));
                objart.setModalidad(v.crs.getString("pp_norma"));
                objart.setCoordinador(v.crs.getString("pr_nombres"));
                objart.setAu_codigo(v.crs.getString("pr_cedula"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void limpiar() {
        txtcodigo = "";
        txtnombres = "";
        txtcedula = "";
        txtestado = "";
        txtsoli_asunto = "";
    }

    public void seleccionarPrograma(SelectEvent event) {
        this.txtcodigo = entidad.getCodigo();
        this.txtnombres = entidad.getNombre();
        this.txtcedula = entidad.getAu_codigo();
        this.txtestado = entidad.getEatado();
        this.txtsoli_asunto = entidad.getModalidad();
    }

    public List getListacoordinador() {
        listageneral.clear();
        try {
            v.crs3 = acciones_coordinador.listacoordinador();
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("pr_cedula"), v.crs3.getString("pr_nombres")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }
}
