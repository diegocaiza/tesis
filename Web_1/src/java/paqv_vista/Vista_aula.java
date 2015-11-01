package paqv_vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import paq_asistsoft.Asistsoft;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_aula;
import paq_clase_interfaz.interfaz_tipo_aula;
import paq_entidades.entiv_aula;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_aula extends Vista_campostxt {

    @EJB
    private interfaz_aula acciones;
    @EJB
    private interfaz_tipo_aula combotipo;
    entiv_variables v = new entiv_variables();
    entiv_aula entidad = new entiv_aula();
    Validaciones valida = new Validaciones();
    private List<entiv_aula> filter;

    public entiv_aula getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_aula entidad) {
        this.entidad = entidad;
    }

    public List<entiv_aula> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_aula> filter) {
        this.filter = filter;
    }
    Asistsoft rev = new Asistsoft();
    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarC;

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
        if (valida.campos6_1(txttipomod, txtnumero, txtcaracteristcas, txtcapacidad, txtbloque, txtpiso) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar(txttipomod, txtnumero, txtcapacidad, txtcaracteristcas.toUpperCase(), txtbloque, txtpiso, txtnumero + txtbloque + txtpiso);
            mensaje = acciones.getmensajei().substring(0, 9);
            if (codigo_oracle_duplicado.equals(mensaje)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado_insertar_aula, ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                rev.registro_actividad(getMostrarC(), "Aula", "insertar", txttipomod + "," + txtnumero + "," + txtcaracteristcas + "," + txtcapacidad + "," + txtbloque + "," + txtpiso);
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
            rev.registro_actividad(getMostrarC(), "Aula", "eliminar", cod);

            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos6_1(txttipomod, txtnumero, txtcaracteristcas, txtcapacidad, txtbloque, txtpiso) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txttipomod, txtnumero, txtcapacidad, txtcaracteristcas.toUpperCase(), txtbloque, txtpiso, txtnumero + txtbloque + txtpiso);
            mensaje = acciones.getmensajei().substring(0, 9);
            if (codigo_oracle_duplicado.equals(mensaje)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado_aula, ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                rev.registro_actividad(getMostrarC(), "Aula", "modificar", txtcodigo + "," + txttipomod + "," + txtnumero + "," + txtcaracteristcas + "," + txtcapacidad + "," + txtbloque + "," + txtpiso);
                limpiar();
            }
        }
    }

    public void limpiar() {
        txtcodigo = "";
        txtnumero = "";
        txtcapacidad = null;
        txtcaracteristcas = "";
        txtbloque = "";
        txtpiso = "";
        txttipomod = "";
    }

    public ArrayList<entiv_aula> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista();
        try {
            entiv_aula objart;
            while (v.crs.next()) {
                objart = new entiv_aula();
                objart.setAu_codigo(v.crs.getString("au_codigo"));
                objart.setTa_codigo(v.crs.getString("ta_codigo"));
                objart.setAu_numero(v.crs.getString("au_numero"));
                objart.setAu_capacidad(v.crs.getInt("au_capacidad"));
                objart.setAu_caracteristicas(v.crs.getString("au_caracteristicas"));
                objart.setAu_bloque(v.crs.getString("au_bloque"));
                objart.setAu_piso(v.crs.getString("au_piso"));
                objart.setTa_nombre(v.crs.getString("ta_nombre"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionarAula(SelectEvent event) {
        this.txtcodigo = entidad.getAu_codigo();
        this.txttipomod = entidad.getTa_codigo();
        this.txtcapacidad = entidad.getAu_capacidad();
        this.txtcaracteristcas = entidad.getAu_caracteristicas();
        this.txtbloque = entidad.getAu_bloque();
        this.txtpiso = entidad.getAu_piso();
        this.txtnumero = entidad.getAu_numero();
    }

    public List getListaaula() {
        listageneral.clear();
        try {
            v.crs3 = combotipo.getListacomboinformacion();
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("ta_codigo"), v.crs3.getString("ta_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }
}
