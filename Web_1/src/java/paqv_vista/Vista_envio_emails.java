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
import paq_calculo.Validaciones;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_alumno;
import paq_clase_interfaz.interfaz_promocion;
import paq_clase_interfaz.interfaz_tipo_usuario;
import paq_clase_interfaz.interfaz_usuario;
import paq_entidades.entiv_persona;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_envio_emails extends entiv_persona {

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
    String txtemail = null;
    String txtcodigo_correo = null;
    String txttema = null;
    String txtcuerpo = null;
    String txtemail_usuario = null;
    String txtcodigo_correo_usuario = null;
    String txttema_usuario = null;
    String txtcuerpo_usuario = null;
    fun_calculo fu = new fun_calculo();
    Validaciones val = new Validaciones();
    entiv_variables v = new entiv_variables();
    public String txtcod = null;
    private List<Vista_envio_emails> usuarios;
    private Vista_envio_emails[] selectedusuarios;
    @EJB
    private interfaz_tipo_usuario acciones_tipo_usuario;
    @EJB
    private interfaz_usuario acciones_usuario;
    @EJB
    private interfaz_promocion acciones_promocion;
    @EJB
    private interfaz_alumno acciones_alumno;
    private List<entiv_persona> filter;

    public List<entiv_persona> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_persona> filter) {
        this.filter = filter;
    }
    

    public Vista_envio_emails() {
        usuarios = new ArrayList<Vista_envio_emails>();

    }

    public List getListatipousuario() {
        v.listatipo.clear();
        try {
            v.crs = acciones_tipo_usuario.getLista();
            while (v.crs.next()) {
                v.listatipo.add(new SelectItem(v.crs.getString("tu_codigo"), v.crs.getString("tu_nombre")));
            }
            v.crs.close();
        } catch (SQLException ex) {
        }
        return v.listatipo;
    }

    public List getLista_promocion() {
        v.listapromocion.clear();
        try {
            v.crs = acciones_promocion.getLista_nombres_promocion(getMostrarC());
            while (v.crs.next()) {
                v.listapromocion.add(new SelectItem(v.crs.getString("prom_codigo"), v.crs.getString("pro_nombre")));
            }
            v.crs.close();
        } catch (SQLException ex) {
        }
        return v.listapromocion;
    }

    public List getLista_promocion_admin() {
        v.listapromocion.clear();
        try {
            v.crs = acciones_promocion.getListacombopromocion();
            while (v.crs.next()) {
                v.listapromocion.add(new SelectItem(v.crs.getString("prom_codigo"), v.crs.getString("pro_nombre")));
            }
            v.crs.close();
        } catch (SQLException ex) {
        }
        return v.listapromocion;
    }

    public List getLista_nombres_usuarios() {
        v.listaprofesor.clear();
        try {
            v.crs2 = acciones_usuario.getLista_nombres_completos();
            while (v.crs2.next()) {
                v.listaprofesor.add(new SelectItem(v.crs2.getString("u_nick"), v.crs2.getString("u_nombre")));
            }
            v.crs2.close();
        } catch (SQLException ex) {
            System.out.println("Error al recuperar datos");
        }
        return v.listaprofesor;
    }

    public List getListacorreos() {
        v.listacorreos.clear();
        try {
            v.crs4 = acciones_usuario.getlista_correos_alumnos_por_promocion(txtcod);
            while (v.crs4.next()) {
                v.listacorreos.add(v.crs4.getString("u_correo"));
            }
            v.crs4.close();
        } catch (SQLException ex) {
        }
        return v.listacorreos;
    }

    public void enviarcorreos() {
        if (val.campos4(txtemail, txtcodigo_correo, txttema, txtcuerpo) == false) {
            v.mensaje = "Se requieren datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, v.mensaje, ""));

        } else {
            pe_observacion = fu.enviar_correo(txtemail, txtcodigo_correo, txttema, txtcuerpo, getListacorreos());

            if ("Mensaje enviado con éxito, revise su correo electrónico".equals(pe_observacion)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, pe_observacion, ""));
                limpiar();
            } else if ("Existe un error en la validacion de datos. Revise sus datos".equals(pe_observacion)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, pe_observacion, ""));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al cargar las direcciones electrónicas", ""));
            }
        }
    }

    public void enviarcorreos_por_usuario() {
        getListacorreos().clear();
        v.listacorreos.clear();
        if (val.campos5(pe_cedula, txtemail_usuario, txtcodigo_correo_usuario, txttema_usuario, txtcuerpo_usuario) == false) {
            v.mensaje = "Se requieren datos!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, v.mensaje, ""));

        } else {
            v.listacorreos.add(pe_email);
            pe_observacion = fu.enviar_correo(txtemail_usuario, txtcodigo_correo_usuario, txttema_usuario, txtcuerpo_usuario, v.listacorreos);

            if ("Mensaje enviado con éxito, revise su correo electrónico".equals(pe_observacion)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, pe_observacion, ""));
                limpiar();
            } else if ("Existe un error en la validación de datos. Revise sus datos".equals(pe_observacion)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, pe_observacion, ""));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al cargar las direcciones electrónicas", ""));
            }
        }
    }

    public void limpiar() {
        txtemail = "";
        txtcodigo_correo = "";
        txttema = "";
        txtcuerpo = "";
        pe_apellidos = "";
        pe_nombres = "";
        pe_email = "";
    }

    public ArrayList<entiv_persona> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs1 = acciones_usuario.getLista_por_promocion(txtcod);

        try {
            entiv_persona objart;
            while (v.crs1.next()) {
                objart = new entiv_persona();
                objart.setPe_codigo(v.crs1.getString("u_nick"));
                objart.setPe_observacion(v.crs1.getString("tu_nombre"));
                // objart.setPe_passw(v.crs1.getString("u_password"));
                objart.setPe_nombres(v.crs1.getString("u_nombre"));
                objart.setPe_apellidos(v.crs1.getString("u_apellido"));
                objart.setPe_email(v.crs1.getString("u_correo"));

                listae.add(objart);
            }
        } catch (Exception e) {
            System.out.println("Error al recorrer la lista");
        }
        return listae;
    }

    public void buscar_usuario() {
        v.crs6 = acciones_usuario.getdevolver_usuario(pe_cedula);
        if (v.crs6.size() > 0) {
            try {
                while (v.crs6.next()) {
                    pe_nombres = v.crs6.getString("u_nombre");
                    pe_apellidos = v.crs6.getString("u_apellido");
                    pe_email = v.crs6.getString("u_correo");
                    pe_passw = v.crs6.getString("u_nick");
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

    public List<Vista_envio_emails> getUsuarios() {
        return usuarios;
    }

    public Vista_envio_emails[] getSelectedusuarios() {
        return selectedusuarios;
    }

    public void setSelectedusuarios(Vista_envio_emails[] selectedusuarios) {
        this.selectedusuarios = selectedusuarios;
    }

    public String getTxtcod() {
        return txtcod;
    }

    public void setTxtcod(String txtcod) {
        this.txtcod = txtcod;
    }

    public String devolver_txtcod() {

        return getTxtcod();
    }

    public String getTxtemail() {
        return txtemail;
    }

    public void setTxtemail(String txtemail) {
        this.txtemail = txtemail;
    }

    public String getTxttema() {
        return txttema;
    }

    public void setTxttema(String txttema) {
        this.txttema = txttema;
    }

    public String getTxtcuerpo() {
        return txtcuerpo;
    }

    public void setTxtcuerpo(String txtcuerpo) {
        this.txtcuerpo = txtcuerpo;
    }

    public String getTxtcodigo_correo() {
        return txtcodigo_correo;
    }

    public void setTxtcodigo_correo(String txtcodigo_correo) {
        this.txtcodigo_correo = txtcodigo_correo;
    }

    public String getTxtemail_usuario() {
        return txtemail_usuario;
    }

    public void setTxtemail_usuario(String txtemail_usuario) {
        this.txtemail_usuario = txtemail_usuario;
    }

    public String getTxtcodigo_correo_usuario() {
        return txtcodigo_correo_usuario;
    }

    public void setTxtcodigo_correo_usuario(String txtcodigo_correo_usuario) {
        this.txtcodigo_correo_usuario = txtcodigo_correo_usuario;
    }

    public String getTxttema_usuario() {
        return txttema_usuario;
    }

    public void setTxttema_usuario(String txttema_usuario) {
        this.txttema_usuario = txttema_usuario;
    }

    public String getTxtcuerpo_usuario() {
        return txtcuerpo_usuario;
    }

    public void setTxtcuerpo_usuario(String txtcuerpo_usuario) {
        this.txtcuerpo_usuario = txtcuerpo_usuario;
    }

    public void imprimi_lista() {

        if (getSelectedusuarios().length >= 1) {
            System.out.println("reultados:" + getSelectedusuarios().length);
        } else if (getSelectedusuarios().length <= 0) {
            System.out.println("ningun");
        } else {
            System.out.println("error");
        }
    }

   
    public void enviarcorreos_automaticos(String promocion, String asunto, String cuerpo, List lista) {

//          List listacorreos;
//          listacorreos=getListacorreos_automaticos(promocion);
//        if (val.campos4(txtemail, txtcodigo_correo, txttema, txtcuerpo) == false) {
//            v.mensaje = "Se requieren datos";
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, v.mensaje, ""));
//
//        } else {
        pe_observacion = fu.enviar_correo_copia("programapostgrado@gmail.com", "espe.2013", asunto, cuerpo, lista);

        if ("Mensaje enviado con éxito, revise su correo electrónico".equals(pe_observacion)) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, pe_observacion, ""));
//                limpiar();
            System.out.println("exitoso");

        } else if ("Existe un error en la validación de datos. Revise sus datos".equals(pe_observacion)) {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, pe_observacion, ""));
            System.out.println("error1");
        } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al cargar las direcciones electronicas", ""));
            System.out.println("error2");
        }
//        }
    }
}
