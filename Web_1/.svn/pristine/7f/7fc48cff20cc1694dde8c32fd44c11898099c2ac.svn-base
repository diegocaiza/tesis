package paqv_vista;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import paq_calculo.Validaciones;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_solicitud;
import paq_entidades.entiv_solicitud;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_solicitud extends Vista_campostxt implements Serializable {

    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarNick;

    public Vista_usuario getVistaUsuario() {
        return vistaUsuario;
    }

    public void setVistaUsuario(Vista_usuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }

    public String getMostrarNick() {
        mostrarNick = vistaUsuario.getTxtnick();
        return mostrarNick;
    }

    public void setMostrarNick(String mostrarNick) {
        this.mostrarNick = mostrarNick;
    }
    @EJB
    private interfaz_solicitud acciones;
    Validaciones valida = new Validaciones();
    entiv_solicitud entidad = new entiv_solicitud();
    entiv_variables v = new entiv_variables();
    fun_calculo fu = new fun_calculo();
    Date fecha1 = new Date();
    private List<entiv_solicitud> filter;

    public entiv_solicitud getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_solicitud entidad) {
        this.entidad = entidad;
    }

    public List<entiv_solicitud> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_solicitud> filter) {
        this.filter = filter;
    }

    public void seleccionarSolicitud(SelectEvent event) {
        this.txtcodigo = entidad.getSoli_codigo();
        this.txtsoli_fecha = entidad.getSoli_fecha();
        this.txtcedula = entidad.getPe_cedula();
        this.txtnombres = entidad.getNombres();
        this.txtsoli_asunto = entidad.getSoli_asunto();
        this.txtsoli_redaccion = entidad.getSoli_redaccion();
        this.estado1 = entidad.getSoli_estado();
        this.txtsoli_fecha_respuesta = entidad.getSoli_fecha_respuesta();
        this.txtdescripcion = entidad.getGenero();

    }

    public void insertar() {
        txtsoli_fecha = new Date();
        if (valida.campos_solicitud(getMostrarNick(), txtsoli_redaccion, txtsoli_asunto, fu.formato_fecha_hora2(txtsoli_fecha)) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar(mostrarNick, txtsoli_redaccion, txtsoli_asunto, fu.formato_fecha_hora2(txtsoli_fecha), estado1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void eliminar(String cod) {
        acciones.eliminar(cod);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei() + " , " + mensaje_eliminacion_buzon, ""));
        limpiar();

    }
 public void eliminar_correo_coordinador(String cod) {
        acciones.eliminar_correo_coordinador(cod);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei() + " , " + mensaje_eliminacion_buzon, ""));
        limpiar();

    }
    public void eliminar() {
        entidad = (entiv_solicitud) tabla.getRowData();
        this.txtcodigo = entidad.getSoli_codigo();
        acciones.eliminar(txtcodigo);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
        limpiar();
    }

    public void modificar() {
        txtsoli_fecha_respuesta = new Date();
        if (valida.campos4(getMostrarNick(), txtsoli_redaccion, txtsoli_asunto, fu.formato_fecha_hora2(txtsoli_fecha_respuesta)) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, estado1, fu.formato_fecha_hora2(txtsoli_fecha_respuesta),txtdescripcion);
            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
            limpiar();
        }
    }

    public ArrayList<entiv_solicitud> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista(getMostrarNick());
        try {
            entiv_solicitud objart;
            while (v.crs.next()) {
                objart = new entiv_solicitud();
                objart.setSoli_codigo(v.crs.getString("soli_codigo"));
                objart.setPe_cedula(v.crs.getString("al_cedula"));
                objart.setSoli_redaccion(v.crs.getString("soli_redaccion"));
                objart.setSoli_asunto(v.crs.getString("soli_asunto"));
                objart.setSoli_fecha(v.crs.getDate("soli_fecha"));
                objart.setSoli_estado(v.crs.getString("soli_estado"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista() throws ParseException {
        entidad = (entiv_solicitud) tabla.getRowData();
        this.txtcodigo = entidad.getSoli_codigo();
        this.txtcedula = entidad.getPe_cedula();
        this.txtsoli_redaccion = entidad.getSoli_redaccion();
        this.txtsoli_asunto = entidad.getSoli_asunto();
        this.txtsoli_fecha = entidad.getSoli_fecha();
        this.txtsoli_fecha_respuesta = entidad.getSoli_fecha_respuesta();
        this.txtnombres = entidad.getNombres();
    }

    public void limpiar() {
        txtcodigo = "";
        txtcedula = "";
        txtsoli_redaccion = "";
        txtsoli_asunto = "";
        txtnombres = "";
        txtsoli_fecha = null;
        estado1 = "";
    }

    public ArrayList<entiv_solicitud> getCorreo() {
        ArrayList listae1 = new ArrayList();
        v.crs4 = acciones.getCorreo(getMostrarNick());
        try {
            entiv_solicitud objart;
            while (v.crs4.next()) {
                objart = new entiv_solicitud();
                objart.setGenero(v.crs4.getString("soli_contestado"));
                objart.setSoli_codigo(v.crs4.getString("soli_codigo"));
                objart.setPe_cedula(v.crs4.getString("al_cedula"));
                objart.setPe_observacion(v.crs4.getString("pro_nombre"));
                objart.setSoli_redaccion(v.crs4.getString("soli_redaccion"));
                objart.setSoli_asunto(v.crs4.getString("soli_asunto"));
                objart.setSoli_fecha(v.crs4.getDate("soli_fecha"));
                objart.setSoli_fecha_respuesta(v.crs4.getDate("soli_fecha_respuesta"));
                objart.setNombres(v.crs4.getString("al_nombres"));
                objart.setSoli_estado(v.crs4.getString("soli_estado"));
                listae1.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae1;
    }
}
