package paqv_vista;

import com.sun.rowset.CachedRowSetImpl;
import paq_clase_interfaz.interfaz_programa;
import paq_entidades.entiv_mo_as_uni;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import paq_asistsoft.Asistsoft;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_asignatura;
import paq_clase_interfaz.interfaz_modulo;
import paq_clase_interfaz.interfaz_tipo_informacion;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_asignatura extends Vista_campostxt {

    public CachedRowSetImpl crs4 = null;
    @EJB
    private interfaz_asignatura acciones;
    @EJB
    private interfaz_modulo combomodulo;
    @EJB
    private interfaz_programa comboprograma;
    @EJB
    private interfaz_tipo_informacion combotipo;
    entiv_mo_as_uni entidad = new entiv_mo_as_uni();
    Validaciones valida = new Validaciones();
    public List listaprograma2 = new ArrayList();
    public List listamodulo = new ArrayList();
    public List listatipo = new ArrayList();
    entiv_variables v = new entiv_variables();
    Asistsoft rev = new Asistsoft();
    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarC;
    public String mostrar_modulo;
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
        return mostrarC;
    }

    public void setMostrarC(String mostrarC) {
        this.mostrarC = mostrarC;
    }

    public String getMostrar_modulo() {
        mostrar_modulo = vistaUsuario.getTxtcodigomod();
        return mostrar_modulo;
    }

    public void insertar() {
        if (valida.campos_asignatura(txtnombresmod, txtdescripcion, getMostrar_modulo(), txtcreditos) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validar_numeros_enteros(txtcreditos) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_numeros_creditos, ""));

        } else if (valida.validar_numeros_enteros(txtcreditos) == true) {
            if (comparar_numero_creditos(getMostrar_modulo(), txtcreditos) == true) {
                acciones.insertar(txtnombresmod.toUpperCase(), txtcreditos, txtdescripcion, getMostrar_modulo());
                mensaje = acciones.getmensajei().substring(0, 9);
                if (codigo_oracle_duplicado.equals(mensaje)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado_asignatura, ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                    rev.registro_actividad(getMostrarC(), "Asignatura", "insertar", txtnombresmod.toUpperCase() + "," + txtcreditos + "," + txtdescripcion + "," + getMostrar_modulo());
                    limpiar();
                }
            } else if (comparar_numero_creditos(getMostrar_modulo(), txtcreditos) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya se ha completado el número de créditos para éste módulo", ""));

            }
        }
    }

    public void eliminar(String cod) {
        acciones.eliminar(cod);
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            rev.registro_actividad(getMostrarC(), "Asignatura", "eliminar", cod);
            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos4_2(txtnombresmod, txtdescripcion, getMostrar_modulo(), txtcreditos) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
         } else if (valida.validar_numeros_enteros(txtcreditos) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_numeros_creditos, ""));

        } else if (valida.validar_numeros_enteros(txtcreditos) == true) {
            if (getTxtcreditos_comparacion() >= txtcreditos) {

                acciones.actualizar(txtcodigoasignatura, txtnombresmod.toUpperCase(), txtcreditos, txtdescripcion, getMostrar_modulo());
                mensaje = acciones.getmensajei().substring(0, 9);
                if (codigo_oracle_duplicado.equals(mensaje)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado, ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                    rev.registro_actividad(getMostrarC(), "Asignatura", "modificar", txtcodigoasignatura + "," + txtnombresmod.toUpperCase() + "," + txtcreditos + "," + txtdescripcion + "," + getMostrar_modulo());
                    limpiar();
                }
            } else {
                if (comparar_numero_creditos(txtcodigomod, (txtcreditos - getTxtcreditos_comparacion())) == true) {
                    acciones.actualizar(txtcodigoasignatura, txtnombresmod.toUpperCase(), txtcreditos, txtdescripcion, getMostrar_modulo());
                    mensaje = acciones.getmensajei().substring(0, 9);
                    if (codigo_oracle_duplicado.equals(mensaje)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado_actualizar_asignatura, ""));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                        rev.registro_actividad(getMostrarC(), "Asignatura", "modificar", txtcodigoasignatura + "," + txtnombresmod.toUpperCase() + "," + txtcreditos + "," + txtdescripcion + "," + getMostrar_modulo());
                        limpiar();

                    }
                } else if (comparar_numero_creditos(getMostrar_modulo(), txtcreditos) == false) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Ya se ha completado el número de créditos con el módulo", ""));

                }


            }
        }
    }

    public ArrayList<entiv_mo_as_uni> getLista2() {
        ArrayList listae = new ArrayList();
        crs4 = acciones.getLista_tabla(getMostrar_modulo());
        try {
            entiv_mo_as_uni objart;
            while (crs4.next()) {
                objart = new entiv_mo_as_uni();
                objart.setCodigo(crs4.getString("asig_codigo"));
                objart.setNombre(crs4.getString("asig_nombre"));
                objart.setCreditos(crs4.getInt("asig_numerocreditos"));
                objart.setTipo(crs4.getString("ti_codigo"));
                objart.setPp_codigo(crs4.getString("mod_codigo"));
                objart.setModulo_nombre(crs4.getString("mod_nombre"));
                objart.setTipo_nombre(crs4.getString("ti_descripcion"));

                listae.add(objart);
            }
        } catch (Exception e) {
            System.out.println(mensaje_error_lista + e.getMessage());
        }
        return listae;
    }

    public void seleccionarAsignatura(SelectEvent event) {
        this.txtcodigoasignatura = entidad.getCodigo();
        this.txtnombresmod = entidad.getNombre();
        this.txtdescripcion = entidad.getTipo();
        this.txtcreditos = entidad.getCreditos();
        this.txtcodigomod = entidad.getPp_codigo();
        setTxtcreditos_comparacion(entidad.getCreditos());
    }

    public void limpiar() {
        txtcodigoasignatura = "";
        txtnombresmod = "";
        txtcreditos = null;
        txtdescripcion = "";
    }

    public void limpiar1() {
        txtcodigoasignatura = "";
        txtnombresmod = "";
        txtcreditos = null;
        txtdescripcion = "";
        txtcodigomod = "";
    }

    public List getListaprograma() {
        listaprograma2.clear();
        try {
            v.crs2 = comboprograma.getcomboprograma();
            while (v.crs2.next()) {
                listaprograma2.add(new SelectItem(v.crs2.getString("pp_codigo"), v.crs2.getString("pp_nombre")));
            }
            v.crs2.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }
        return listaprograma2;
    }

    public List getListaprograma_por_coordinador() {

        listapantalla.clear();
        try {
            v.crs = comboprograma.getcomboprograma_por_coordinador(getMostrarC());
            while (v.crs.next()) {
                listapantalla.add(new SelectItem(v.crs.getString("pp_codigo"), v.crs.getString("pp_nombre")));
            }
            v.crs.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }
        return listapantalla;
    }

    public List getListamodulo() {
        listamodulo.clear();
        try {
            v.crs3 = combomodulo.getListacombomodulo(txtcodigo_programa);
            while (v.crs3.next()) {
                listamodulo.add(new SelectItem(v.crs3.getString("mod_codigo"), v.crs3.getString("mod_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }
        return listamodulo;

    }

    public List getListatipo() {
        listatipo.clear();
        try {
            v.crs1 = combotipo.getListacomboinformacion();
            while (v.crs1.next()) {
                listatipo.add(new SelectItem(v.crs1.getString("ti_codigo"), v.crs1.getString("ti_descripcion")));
            }
            v.crs1.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }
        return listatipo;
    }

    public String devolver() {
        return txtcodigo_programa;
    }

    public String devolver2() {
        return txtcodigomod;
    }

    public String devolver3() {
        txtcodigomod = "x";
        return txtcodigomod;
    }

    public Boolean comparar_numero_creditos(String modulo, Integer a) {

        v.crs6 = acciones.lista_creditos(modulo);

        try {
            while (v.crs6.next()) {

                creditos_asignaturas = v.crs6.getInt("ASIG_NUMEROCREDITOS");
            }
            v.crs6.close();
        } catch (SQLException ex) {
        }
        if (numero_credito_modulo(modulo) >= (a + creditos_asignaturas)) {
            return true;
        } else {
            return false;
        }
    }

    public Integer numero_credito_modulo(String cod) {

        v.crs4 = acciones.num_credito_modulo(cod);

        try {
            while (v.crs4.next()) {
                creditos_modulos = v.crs4.getInt("mod_numerocreditos");

            }
            v.crs4.close();
        } catch (SQLException ex) {
        }
        return creditos_modulos;
    }
}
