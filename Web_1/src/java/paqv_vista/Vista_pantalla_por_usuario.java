package paqv_vista;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import paq_clase_interfaz.interfaz_pantallas_por_tipo_usuario;
import paq_clase_interfaz.interfaz_tipo_usuario;
import paq_entidades.entiv_pantalla_por_usuario;
import paq_entidades.entiv_variables;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_pantalla;

@ManagedBean()
@SessionScoped
public class Vista_pantalla_por_usuario extends Vista_campostxt {

    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistau;
    public String mostrarnick = "";
    private String actual;
    private String input1;
    private String input2;
    public String mostrarClave = "";
    Validaciones valida = new Validaciones();

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getMostrarClave() {
        mostrarClave = vistau.getTxtpassw();
        return mostrarClave;
    }

    public void setMostrarClave(String mostrarClave) {
        this.mostrarClave = mostrarClave;
    }

    public String getMostrarnick() {
        mostrarnick = vistau.getTxtnick();

        return mostrarnick;
    }

    public void setMostrarnick(String mostrarnick) {
        this.mostrarnick = mostrarnick;
    }

    public Vista_usuario getVistau() {
        return vistau;
    }

    public void setVistau(Vista_usuario vistau) {
        this.vistau = vistau;
    }
    @EJB
    private interfaz_pantallas_por_tipo_usuario acciones;
    @EJB
    private interfaz_tipo_usuario combotipo;
    @EJB
    private interfaz_pantalla combopantalla;
    entiv_variables v = new entiv_variables();
    entiv_pantalla_por_usuario entidad = new entiv_pantalla_por_usuario();
    private List<entiv_pantalla_por_usuario> filter;

    public entiv_pantalla_por_usuario getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_pantalla_por_usuario entidad) {
        this.entidad = entidad;
    }

    public List<entiv_pantalla_por_usuario> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_pantalla_por_usuario> filter) {
        this.filter = filter;
    }

    public void insertar() {
        if (valida.campos2(txtpantalla, txtipoUsuario) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar(txtpantalla, txtipoUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
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
        if (valida.campos2(txtpantalla, txtipoUsuario) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txtpantalla, txtipoUsuario);
            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
            limpiar();
        }
    }

    public void limpiar() {
        txtpantalla = "";
        txtipoUsuario = "";
        txtcodigo = "";
    }

    public ArrayList<entiv_pantalla_por_usuario> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista();

        try {
            entiv_pantalla_por_usuario objart;
            while (v.crs.next()) {
                objart = new entiv_pantalla_por_usuario();
                objart.setPpu_codigo(v.crs.getString("ppu_codigo"));
                objart.setP_codigo(v.crs.getString("p_codigo"));
                objart.setTu_codigo(v.crs.getString("tu_codigo"));
                objart.setTu_nombre(v.crs.getString("tu_nombre"));
                objart.setP_nombre(v.crs.getString("p_nombre"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista(SelectEvent event) {
        this.txtcodigo = entidad.getPpu_codigo();
        this.txtipoUsuario = entidad.getTu_codigo();
        this.txtpantalla = entidad.getP_codigo();
    }

    public List getListapantalla() {
        listapantalla.clear();
        try {
            v.crs2 = combopantalla.getListacomboinformacion();
            while (v.crs2.next()) {
                listapantalla.add(new SelectItem(v.crs2.getString("p_codigo"), v.crs2.getString("p_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listapantalla;
    }

    public List getListatipousuario() {
        listageneral.clear();
        try {
            v.crs3 = combotipo.getListacomboinformacion();
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("tu_codigo"), v.crs3.getString("tu_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }

    public boolean verificarPermiso(String nick, String url) throws SQLException {
        return acciones.verificarPermiso(getMostrarnick(), url);
    }
    //verificar

    public boolean verificarPermisoCoordinador(String cedula) throws SQLException {
        return acciones.verificarPermisoCoordinador(getMostrarnick());
    }

    public void limpiar_cambio_clave() {
        actual = "";
        input1 = "";
        input2 = "";
    }

    public void modificarClave() throws Exception {
        if (valida.campos3(actual, input1, input2) == false) {
            mensaje = "Se requieren datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
        } else if ((actual.equals(getMostrarClave())) && (input1 != null && input1.length() > 6 && (input1.equals(input2)))) {
            acciones.actualizarClave(input1, txtrespuesta, getMostrarnick());
            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
        } else {
            if (!actual.equals(getMostrarClave())) {
                mensaje = "Contraseña actual incorrecta";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
            } else {
                mensaje = "La nueva contraseña debe tener más de 6 caracteres y los dos campos deben ser Idénticos ";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
            }

        }

    }
}
