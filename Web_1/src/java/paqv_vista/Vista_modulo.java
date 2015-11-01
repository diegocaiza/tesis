package paqv_vista;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import paq_clase_interfaz.interfaz_modulo;
import paq_entidades.entiv_mo_as_uni;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import paq_clase_interfaz.interfaz_programa;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import paq_asistsoft.Asistsoft;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_tipo_informacion;

@ManagedBean()
@ViewScoped
public class Vista_modulo extends Vista_campostxt {

    CachedRowSetImpl crs = null;
    CachedRowSetImpl crs2 = null;
    CachedRowSetImpl crs3 = null;
    @EJB
    private interfaz_modulo acciones;
    @EJB
    private interfaz_programa comboprograma;
    @EJB
    private interfaz_tipo_informacion combotipo;
    entiv_mo_as_uni entidad = new entiv_mo_as_uni();
    Validaciones valida = new Validaciones();
    public List listaprograma2 = new ArrayList();
    public List listatipo = new ArrayList();
    List l1 = null;
    Asistsoft rev = new Asistsoft();
    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarC;
    private List<entiv_mo_as_uni> filter;

    public entiv_mo_as_uni getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_mo_as_uni entidad) {
        this.entidad = entidad;
    }

    public List<entiv_mo_as_uni> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_mo_as_uni> filter) {
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
        programa_por_coordinador(mostrarC);
        return mostrarC;
    }

    public void setMostrarC(String mostrarC) {
        this.mostrarC = mostrarC;
    }

    public void insertar() {
        if (valida.campos4_1(txtnombresmod, txttipomod, txtcreditos, txtcodigo_programa) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            if (valida.validar_numeros_enteros(txtcreditos) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_numeros_creditos, ""));

            } else if (valida.validar_numeros_enteros(txtcreditos) == true) {
                acciones.insertar(txtnombresmod.toUpperCase(), txtcreditos, txttipomod, txtcodigo_programa);
                mensaje = acciones.getmensajei().substring(0, 9);
                if (codigo_oracle_duplicado.equals(mensaje)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado_modulo, ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                    rev.registro_actividad(getMostrarC(), "Módulo", "insertar", txtnombresmod + "," + txttipomod + "," + txtcreditos + "," + txtcodigo_programa);
                    limpiar();
                }

            }
        }
    }

    public void eliminar(String cod) {
        acciones.eliminar(cod);
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            rev.registro_actividad(getMostrarC(), "Módulo", "eliminar", cod);

            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos4_1(txtnombresmod, txttipomod, txtcreditos, txtcodigo_programa) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            if (valida.validar_numeros_enteros(txtcreditos) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_numeros_creditos, ""));

            } else if (valida.validar_numeros_enteros(txtcreditos) == true) {
                acciones.actualizar(txtcodigomod, txtnombresmod.toUpperCase(), txtcreditos, txttipomod, txtcodigo_programa);
                mensaje = acciones.getmensajei().substring(0, 9);
                if (codigo_oracle_duplicado.equals(mensaje)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado_actualizar_modulo, ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                    rev.registro_actividad(getMostrarC(), "Módulo", "modificar", txtcodigomod + "," + txtnombresmod.toUpperCase() + "," + txtcreditos + "," + txttipomod + "," + txtcodigo_programa);
                    limpiar();
                }
            }
        }
    }

    public ArrayList<entiv_mo_as_uni> getLista1() {
        ArrayList listae = new ArrayList();
        crs = acciones.getLista();

        try {
            entiv_mo_as_uni objart;
            while (crs.next()) {
                objart = new entiv_mo_as_uni();
                objart.setCodigo(crs.getString("mod_codigo"));
                objart.setNombre(crs.getString("mod_nombre"));
                objart.setCreditos(crs.getInt("mod_numerocreditos"));
                objart.setTipo(crs.getString("ti_codigo"));
                objart.setPp_codigo(crs.getString("pp_codigo"));
                listae.add(objart);

            }
        } catch (Exception e) {
            System.out.println(mensaje_error_lista + e.getMessage());
        }
        return listae;
    }

    public ArrayList<entiv_mo_as_uni> getLista2() {
        ArrayList listae = new ArrayList();
        crs2 = acciones.getLista_tabla(getMostrarC());
        try {
            entiv_mo_as_uni objart;
            while (crs2.next()) {
                objart = new entiv_mo_as_uni();
                objart.setCodigo(crs2.getString("mod_codigo"));
                objart.setNombre(crs2.getString("mod_nombre"));
                objart.setCreditos(crs2.getInt("mod_numerocreditos"));
                objart.setTipo(crs2.getString("ti_codigo"));
                objart.setPp_codigo(crs2.getString("pp_codigo"));
                objart.setPp_nombre(crs2.getString("pp_nombre"));
                objart.setTipo_nombre(crs2.getString("ti_descripcion"));
                listae.add(objart);
            }
        } catch (Exception e) {
            System.out.println(mensaje_error_lista + e.getMessage());
        }
        return listae;
    }

    public void seleccionarModulo(SelectEvent event) {
        this.txtcodigomod = entidad.getCodigo();
        this.txtnombresmod = entidad.getNombre();
        this.txttipomod = entidad.getTipo();
        this.txtcreditos = entidad.getCreditos();
        this.txtcodigo_programa = entidad.getPp_codigo();
    }

    public void limpiar() {
        txtcodigomod = "";
        txtnombresmod = "";
        txtcreditos = null;
        txttipomod = "";
    }

    public List getListaprograma() {
        listaprograma2.clear();
        try {
            crs3 = comboprograma.getcomboprograma();
            while (crs3.next()) {
                listaprograma2.add(new SelectItem(crs3.getString("pp_codigo"), crs3.getString("pp_nombre")));
            }
            crs3.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }
        return listaprograma2;
    }

    public List getListaprograma_por_coordinador() {
        listaprograma2.clear();
        try {
            crs3 = comboprograma.getcomboprograma_por_coordinador(getMostrarC());
            while (crs3.next()) {
                listaprograma2.add(new SelectItem(crs3.getString("pp_codigo"), crs3.getString("pp_nombre")));
            }
            crs3.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }
        return listaprograma2;
    }

    public void programa_por_coordinador(String cedula) {
        listaprograma2.clear();
        try {
            crs3 = comboprograma.getcomboprograma_por_coordinador(cedula);
            while (crs3.next()) {
                txtcodigo_programa = crs3.getString("pp_codigo");
                txtnombres1 = crs3.getString("pp_nombre");
            }
            crs3.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }

    }

    public List getListatipo() {
        listatipo.clear();
        try {
            crs3 = combotipo.getListacomboinformacion();
            while (crs3.next()) {
                listatipo.add(new SelectItem(crs3.getString("ti_codigo"), crs3.getString("ti_descripcion")));
            }
            crs3.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }
        return listatipo;
    }

    public String devolver() {
        return txtcodigo_programa;
    }
}
