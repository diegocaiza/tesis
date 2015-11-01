package paqv_vista;

import com.sun.rowset.CachedRowSetImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_promocion;
import paq_clase_interfaz.interfaz_session;
import paq_clase_interfaz.interfaz_tipo_usuario;
import paq_clase_interfaz.interfaz_usuario;
import paq_entidades.entiv_usuario;
import paq_entidades.entiv_variables;

@ManagedBean()
@SessionScoped
public class Vista_usuario extends Vista_campostxt {

    @EJB
    private interfaz_usuario acciones;
    @EJB
    private interfaz_session login;
    @EJB
    private interfaz_tipo_usuario combotipo;
    @EJB
    private interfaz_promocion combopromocion;
    Validaciones valida = new Validaciones();
    entiv_variables v = new entiv_variables();
    entiv_usuario entidad = new entiv_usuario();
    private List<entiv_usuario> filter;

    public List<entiv_usuario> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_usuario> filter) {
        this.filter = filter;
    }

    public entiv_usuario getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_usuario entidad) {
        this.entidad = entidad;
    }

    public void insertar() {
        if (valida.campos_usuario(txtnombres, txtapellidos, txtcedula, txtipoUsuario, txtpassw, txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            if (valida.validar_cedula(txtcedula) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_cedula, ""));
            } else {
                  acciones.insertar(txtnombres.toUpperCase(), txtapellidos.toUpperCase(), txtcedula, txtipoUsuario, txtpassw, txtcorreo.toLowerCase(), "usuario");
                if (codigo_oracle_duplicado.equals(acciones.getmensajei().substring(0, 9))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado, ""));
                } else if (codigo_oracle_duplicado != mensaje) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                    limpiar();
                }
            }
        }
    }
public void modificar_usuario() {
        if (valida.campos7_usuario(txtnombres, txtapellidos, txtcedula,  txtpassw, txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            acciones.actualizar_usuario(txtnombres.toUpperCase(), txtapellidos.toUpperCase(), txtcedula, txtpassw, txtcorreo.toLowerCase());
            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
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
        if (valida.campos7(txtnombres, txtapellidos, txtcedula, txtipoUsuario, txtpassw, txtcorreo, txtextra) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            acciones.actualizar(txtnombres.toUpperCase(), txtapellidos.toUpperCase(), txtcedula, txtipoUsuario, txtpassw, txtcorreo.toLowerCase(), txtextra);
            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
            limpiar();
        }
    }

    public void reseteo_clave() {
          buscar_usuario_reseteo();
        if (valida.campos(txtcedula) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por favor ingrese un número de cédula", ""));
        } else{
            acciones.reseteo_clave(txtcedula, "Espe.2013");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Clave reseteada con éxito", ""));
            limpiar();
              }
    
    }

    public void buscar_usuario() {
        v.crs1 = acciones.buscar_us(txtcedula);
        if (v.crs1.size() > 0) {
            datos_usuario(v.crs1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje_encontrado, ""));

        } else if (v.crs1.size() < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_no_encontrado, ""));
            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_general, ""));
        }
    }

    public void datos_usuario(CachedRowSetImpl lista) {
        try {
            while (lista.next()) {
                setTxtipoUsuario(lista.getString("tu_codigo"));
                setTxtextra(lista.getString("u_extrap"));
                setTxtnombres(lista.getString("u_nombre"));
                setTxtapellidos(lista.getString("u_apellido"));
                setTxtpassw(lista.getString("u_password"));
                setTxtcorreo(lista.getString("u_correo"));
            }
        } catch (SQLException ex) {
            System.out.print("Error al recuperar los datos.");
        }
    }

    public void limpiar() {
        habilitar_campo_nick();
        txtnombres = "";
        txtapellidos = "";
        txtcedula = "";
        txtipoUsuario = "";
        txtpassw = "";
        txtcorreo = "";
        txtextra = null;
        txtestado = "";
    }

    public void cancelar() {
        txtnick = "";
        txtpassw = "";
    }

    public ArrayList<entiv_usuario> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista();
        try {
            entiv_usuario objart;
            while (v.crs.next()) {
                objart = new entiv_usuario();
                objart.setPe_nombres(v.crs.getString("u_nombre"));
                objart.setPe_apellidos(v.crs.getString("u_apellido"));
                objart.setPe_cedula(v.crs.getString("u_nick"));
                objart.setTu_codigo(v.crs.getString("tu_codigo"));
                objart.setTipo(v.crs.getString("tu_nombre"));
                objart.setU_password(v.crs.getString("u_password"));
                objart.setPe_email(v.crs.getString("u_correo"));
                objart.setU_extra(v.crs.getString("u_extraP"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public ArrayList<entiv_usuario> getListadministradores() {
        ArrayList listae = new ArrayList();
        v.crs5 = acciones.getListadministradores();
        try {
            entiv_usuario objart;
            while (v.crs5.next()) {
                objart = new entiv_usuario();
                objart.setPe_nombres(v.crs5.getString("u_nombre"));
                objart.setPe_apellidos(v.crs5.getString("u_apellido"));
                objart.setPe_cedula(v.crs5.getString("u_nick"));
                objart.setTu_codigo(v.crs5.getString("tu_codigo"));
                objart.setTipo(v.crs5.getString("tu_nombre"));
                objart.setU_password(v.crs5.getString("u_password"));
                objart.setPe_email(v.crs5.getString("u_correo"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista(SelectEvent event) {
        deshabilitar_campo_nick();
        this.txtnombres = entidad.getPe_nombres();
        this.txtapellidos = entidad.getPe_apellidos();
        this.txtcedula = entidad.getPe_cedula();
        this.txtipoUsuario = entidad.getTu_codigo();
        this.txtipo = entidad.getTipo();
        this.txtpassw = entidad.getU_password();
        this.txtcorreo = entidad.getPe_email();
        this.txtextra = entidad.getU_extra();
    }

    public List getLista_perfil() {
        listageneral.clear();
        try {
            v.crs3 = combotipo.combo_lista_tipos_usuarios();
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("tu_codigo"), v.crs3.getString("tu_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }

    public List getLista_perfil_administradores() {
        listageneral.clear();
        try {
            v.crs3 = combotipo.combo_lista_administradores();
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("tu_codigo"), v.crs3.getString("tu_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }

    public String login() throws SQLException, IOException {
        if (loginAccion()) {
            // return "correcto";
            //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido",this.txtnick));
//            FacesContext context = FacesContext.getCurrentInstance();
//                String path = ((ServletContext)context.getExternalContext().getContext()).getContextPath();
//                context.getExternalContext().redirect(path+"/faces/inicio.xhtml");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", txtnick);
            return "correcto";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje_error_credenciales, "Credenciales Incorrectas!"));
            // return "incorrecto";
            return "incorrecto";
        }
    }

    //
    //
    private boolean loginAccion() throws SQLException {
        ArrayList listae = new ArrayList();
        v.crs = login.getLista(txtnick, txtpassw);
        try {
            while (v.crs.next()) {
                txtipoUsuario = v.crs.getString("tu_codigo");
                txtnombres = v.crs.getString("u_nombre");
                txtnick = v.crs.getString("u_nick");
                txtpassw = v.crs.getString("u_password");
                txtapellidos = v.crs.getString("u_apellido");
                txtextra = v.crs.getString("u_extraP");
                txtcorreo = v.crs.getString("u_correo");
                txtnombre_completo = v.crs.getString("u_nombres");
                txt_nombre_promocion = v.crs.getString("promo_nombre");
                return true;
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return false;
    }

    public String cerrarSesion() {

        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();


        //probar 
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        session.removeAttribute("LoginBean");
//        return "cerrar";
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            ExternalContext externalContext = context.getExternalContext();

            Object session = externalContext.getSession(false);

            HttpSession httpSession = (HttpSession) session;

            httpSession.invalidate();
            cerrarSesion();
            System.out.println("sesion cerrada");
        } catch (Exception ex) {
            System.out.println("error");
        }
        return "index";
    }

    public void logout() {
        ExternalContext ctx =
                FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath =
                ((ServletContext) ctx.getContext()).getContextPath();

        try {

            ((HttpSession) ctx.getSession(false)).invalidate();


            ctx.redirect(ctxPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List getListaPromocion() {
        listageneral.clear();
        try {
            v.crs3 = combopromocion.getListacombopromocion();
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("prom_codigo"), v.crs3.getString("pro_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }

    public List getListadepartamento() {
        listageneral.clear();
        try {
            v.crs3 = combopromocion.getListacombodepartamento();
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("dep_codigo"), v.crs3.getString("dep_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }

    public void deshabilitar_campo_nick() {
        deshabilitar = "true";
    }

    public void habilitar_campo_nick() {
        deshabilitar = "false";
    }

    public String formateo() {
        return "Formateo_clave";
    }

    public void buscar_usuario_reseteo() {
        if (valida.campos(txtcedula) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por favor ingrese un número de cédula", ""));
            limpiar();
        } else {
            v.crs6 = acciones.getdevolver_usuario_reseteo(txtcedula);
            if (v.crs6.size() > 0) {
                try {
                    while (v.crs6.next()) {
                        txtnombres = v.crs6.getString("nombres");
                        txtcorreo = v.crs6.getString("u_correo");
                        txtestado = v.crs6.getString("u_estado");
                    }
                } catch (SQLException ex) {
                    System.out.println("Error al recorrer la lista");
                }
                try {
                    v.crs6.close();
                } catch (SQLException ex) {
                    System.out.println("Error al limpiar la lista");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario No encontrado", ""));
                limpiar();
            }
        }
    }
    public void seleccionar_codigo_modulo(String cod) {
        txtcodigomod = cod;
      

    }
}
