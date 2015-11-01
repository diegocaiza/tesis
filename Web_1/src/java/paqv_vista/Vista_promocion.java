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
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_paralelo;
import paq_clase_interfaz.interfaz_programa;
import paq_clase_interfaz.interfaz_promocion;
import paq_entidades.entiv_promocion;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_promocion extends Vista_campostxt {

    @EJB
    private interfaz_promocion acciones;
    @EJB
    private interfaz_programa comboprograma;
    @EJB
    private interfaz_paralelo comboparalelo;
    entiv_promocion entidad = new entiv_promocion();
    Validaciones valida = new Validaciones();
    entiv_variables v = new entiv_variables();
    fun_calculo fu = new fun_calculo();

    public entiv_promocion getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_promocion entidad) {
        this.entidad = entidad;
    }

    public void insertar() {
        if (valida.campos_promocion(txtcodigo_programa, txtcodigoparalelo, txtnombres, txtfechainicio, txtfechafin) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            x = fu.fecha_mayor(txtfechainicio, txtfechafin);
            if (x == true) {
                acciones.insertar(txtcodigo.concat(txtcodigoparalelo), txtcodigo_programa, txtcodigoparalelo, fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), txtnombres.toUpperCase(), 0);
                mensaje = acciones.getmensajei().substring(0, 9);
                if (codigo_oracle_duplicado.equals(mensaje)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado, ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                    limpiar();
                }
//                else {
                //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje_error_lista, ""));
//                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_fecha_mayor, ""));

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
        if (valida.campos_promocion(txtcodigo_programa, txtcodigoparalelo, txtnombres,  txtfechainicio, txtfechafin) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            x = fu.fecha_mayor(txtfechainicio, txtfechafin);
            if (x == true) {
                acciones.actualizar_promocion(txtcodigo, txtcodigoparalelo, fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), txtnombres.toUpperCase(), 0);
                mensaje = acciones.getmensajei().substring(0, 9);
                if (txtextra.equals(txtcodigo)) {
//                    acciones.actualizar(txtcodigo, txtcodigo_programa, txtcodigoparalelo, fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), txtnombres, txtestudiantes);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                    limpiar();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cambio_codigo_principal, ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_fecha_mayor, ""));
            }
        }
    }

    public String devolver() {
        limpiar();
        return txtcodigo_programa;
    }

    public void habilitar_clave() {
        deshabilitar_clave_compuesta = "false";
    }

    public ArrayList<entiv_promocion> getLista2() {

        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista(txtcodigo_programa);
        try {
            entiv_promocion objart;
            while (v.crs.next()) {
                objart = new entiv_promocion();
                objart.setProm_codigo(v.crs.getString("prom_codigo"));
                objart.setPp_nombre(v.crs.getString("pp_nombre"));
                objart.setPp_codigo(v.crs.getString("pp_codigo"));
                objart.setPp_estado(v.crs.getString("pp_estado"));
                objart.setPl_codigo(v.crs.getString("pl_codigo"));
                objart.setProm_fechainicio(v.crs.getDate("prom_fechainicio"));
                objart.setPro_fechafin(v.crs.getDate("pro_fechafin"));
                objart.setPro_nombre(v.crs.getString("pro_nombre"));
                objart.setPl_nombre(v.crs.getString("pl_nombre"));
                objart.setNumestudiantes(v.crs.getInt("pro_numero_estudiantes"));
                objart.setPp_total_real_estudiantes(v.crs.getString("total"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista(String cod, String extra, String codpro, String ced, String codpara, Date fi, Date ff, String nom, Integer es) {
        deshabilitar_clave();
        this.txtcodigo = cod;
        this.txtextra = extra;
        this.txtcodigo_programa = codpro;
        this.txtcedula = ced;
        this.txtcodigoparalelo = codpara;
        this.txtfechainicio = fi;
        this.txtfechafin = ff;
        this.txtnombres = nom;
        this.txtestudiantes = es;
//        this.txttotal_estudiantes = devolver_total_estudiantes_por_promocion();
//        entidad = (entiv_promocion) tabla.getRowData();
//        this.txtcodigo = entidad.getProm_codigo();
//        this.txtextra = entidad.getProm_codigo();
//        this.txtcodigo_programa = entidad.getPp_codigo();
//        this.txtcedula = entidad.getPr_cedula();
//        this.txtcodigoparalelo = entidad.getPl_codigo();
//        this.txtfechainicio = entidad.getProm_fechainicio();
//        this.txtfechafin = entidad.getPro_fechafin();
//        this.txtnombres = entidad.getPro_nombre();
//        this.txtestudiantes = entidad.getNumestudiantes();
//        this.txttotal_estudiantes = devolver_total_estudiantes_por_promocion();

    }

    public void seleccionarPromocion(SelectEvent event) {
        deshabilitar_clave();
        this.txtcodigo = entidad.getProm_codigo();
        this.txtextra = entidad.getProm_codigo();
        this.txtcodigo_programa = entidad.getPp_codigo();
        this.txtcedula = entidad.getPr_cedula();
        this.txtcodigoparalelo = entidad.getPl_codigo();
        this.txtfechainicio = entidad.getProm_fechainicio();
        this.txtfechafin = entidad.getPro_fechafin();
        this.txtnombres = entidad.getPro_nombre();
        this.txtestudiantes = entidad.getNumestudiantes();
        //this.txttotal_estudiantes = devolver_total_estudiantes_por_promocion();
        this.txttotal_estudiantes=entidad.getPp_total_real_estudiantes();
        setDeshabilitar_lista( "true");
    }
    
//    public String devolver_total_estudiantes_por_promocion() {
//        v.crs7 = acciones.total_alumnos_promocion(txtcodigo);
//        if (v.crs7.size() > 0) {
//            try {
//                while (v.crs7.next()) {
//                    txtnumero = (v.crs7.getString("total"));
//                }
//                return txtnumero;
//            } catch (SQLException ex) {
//                return null;
//            }
//        } else {
//            return mensaje_no_existen_estudiantes;
//        }
//    }

    public void limpiar() {
        habilitar_clave();
        this.txtcodigo = "";
        this.txtcedula = "";
        this.txtcodigoparalelo = "";
        this.txtfechainicio = null;
        this.txtfechafin = null;
        this.txtnombres = "";
        this.txtestudiantes = null;
        this.txttotal_estudiantes = null;
         setDeshabilitar_lista( "false");
    }

    public List getListaprograma() {
        v.listaprograma2.clear();
        try {
            v.crs2 = comboprograma.getcomboprograma();
            while (v.crs2.next()) {
                v.listaprograma2.add(new SelectItem(v.crs2.getString("pp_codigo"), v.crs2.getString("pp_nombre")));
            }
            v.crs2.close();
        } catch (SQLException ex) {
        }
        return v.listaprograma2;
    }

    public List getListaparalelo() {
        v.listaparalelo.clear();
        try {
            v.crs3 = comboparalelo.getListacomboparalelo();
            while (v.crs3.next()) {
                v.listaparalelo.add(new SelectItem(v.crs3.getString("pl_codigo"), v.crs3.getString("pl_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return v.listaparalelo;
    }

    public void deshabilitar_clave() {
        deshabilitar_clave_compuesta = "true";
    }
}
