package paqv_vista;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.util.ArrayList;
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
import paq_clase_interfaz.interfaz_profesor;
import paq_clase_interfaz.interfaz_usuario;
import paq_entidades.entiv_profesor;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_profesor extends Vista_campostxt {

    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarNombre;
    public String mostrarApellido;
    public String mostrarCedula;
    private List<entiv_profesor> filter;

    public List<entiv_profesor> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_profesor> filter) {
        this.filter = filter;
    }

    public Vista_usuario getVistaUsuario() {
        return vistaUsuario;
    }

    public void setVistaUsuario(Vista_usuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }

    public String getMostrarNombre() {
        mostrarNombre = vistaUsuario.getTxtnombres();
        return mostrarNombre;
    }

    public void setMostrarNombre(String mostrarNombre) {
        this.mostrarNombre = mostrarNombre;
    }

    public String getMostrarApellido() {
        mostrarApellido = vistaUsuario.getTxtapellidos();
        return mostrarApellido;
    }

    public void setMostrarApellido(String mostrarApellido) {
        this.mostrarApellido = mostrarApellido;
    }

    public String getMostrarCedula() {
        mostrarCedula = vistaUsuario.getTxtnick();
        devolver_datos_profesor(mostrarCedula);
        return mostrarCedula;
    }

    public void setMostrarCedula(String mostrarCedula) {
        this.mostrarCedula = mostrarCedula;
    }
    @EJB
    private interfaz_profesor acciones;
    @EJB
    private interfaz_usuario usuarioA;
    ArrayList listae = new ArrayList();
    String[] co = null;
    entiv_variables v = new entiv_variables();
    entiv_profesor entidad = new entiv_profesor();
    Validaciones valida = new Validaciones();
    fun_calculo fu = new fun_calculo();

    public entiv_profesor getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_profesor entidad) {
        this.entidad = entidad;
    }

    public void insertar() {
        if (valida.campos10(getMostrarCedula(), getMostrarNombre(), txtespecialidad, txttelefono1, txttelefono2, txtdireccion_casa, txtdireccion_trabajo, txtcorreo, txtnacionalidad, getMostrarApellido()) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            acciones.insertar(getMostrarCedula(), getMostrarNombre(), txtespecialidad.toUpperCase(), txttelefono1, txttelefono2, txtdireccion_casa.toUpperCase(), txtdireccion_trabajo.toUpperCase(), txtcorreo.toLowerCase(), txtnacionalidad.toUpperCase(), getMostrarApellido(), "", "");
            mensaje = acciones.getMensajei().substring(0, 9);
            if (codigo_oracle_duplicado.equals(mensaje)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_que_consta_bbd, ""));
            } else if (codigo_oracle_duplicado != mensaje) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getMensajei(), ""));
                limpiar();
            }
        }

    }

    public void devolver_datos_profesor(String cedula) {
        v.crs3 = acciones.datos_profesor(cedula);
        try {
            entiv_profesor objart;
            if (v.crs3.size() > 0) {
                habilitar_boton_modificar = "false";
                deshabilitar_boton_insertar = "true";
            } else {
                habilitar_boton_modificar = "true";
            }
            while (v.crs3.next()) {
                objart = new entiv_profesor();
                txtespecialidad = v.crs3.getString("pr_especialidad");
                txttelefono1 = v.crs3.getString("pr_telefono1");
                txttelefono2 = v.crs3.getString("pr_telefono2");
                txtdireccion_casa = v.crs3.getString("pr_direccion_casa");
                txtdireccion_trabajo = v.crs3.getString("pr_direccion_trabajo");
                txtnacionalidad = v.crs3.getString("pr_nacionalidad");
                txtcorreo = v.crs3.getString("pr_correo");
            }

        } catch (Exception e) {
            System.out.println("Error al obtener la lista de datos" + e.getMessage());
        }
    }

    public void validar_cedula_campo() {
        if (fu.validadorDeCedula(txtcedula)) {
            setTxt_validar_cedula("Cédula  de identidad correcta");

        } else {
            setTxt_validar_cedula("Cédula de identidad incorrecta");

        }

    }

    public void insertarAdmin() {
        if (valida.campos6_2(txtcedula, txtnombres, txtapellidos, txtipo, txtcontrato, txtemail) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtemail) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            if (valida.validar_cedula(txtcedula) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_cedula, ""));
            } else {
                acciones.insertar(txtcedula, txtnombres.toUpperCase(), txtespecialidad.toUpperCase(), txttelefono1, txttelefono2, txtdireccion_casa.toUpperCase(), txtdireccion_trabajo.toUpperCase(), txtemail.toLowerCase(), txtnacionalidad.toUpperCase(), txtapellidos.toUpperCase(), txtipo, txtcontrato);
                mensaje = acciones.getMensajei().substring(0, 9);
                if (codigo_oracle_duplicado.equals(mensaje)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado, ""));
                } else {
                    acciones.insertarUsuario(txtnombres.toUpperCase(), txtapellidos.toUpperCase(), txtcedula, txtipoUsuario, txtpassw, txtemail.toLowerCase());
                    mensaje = acciones.getMensajei().substring(0, 9);
                    if (codigo_oracle_duplicado.equals(mensaje)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesor registrado correctamente y ya se encuentra registrado como usuario", ""));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesor registrado correctamente", ""));
                    }
                    limpiar();
                }
            }
        }
    }

    public void eliminar(String cod) {
        acciones.eliminar(cod);
        if (codigo_oracle_relacionado.equals(acciones.getMensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));
        } else {
            usuarioA.eliminar(cod);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getMensajei(), ""));
            limpiar();
        }

    }

    public void modificar() {
        if (valida.campos6_2(txtcedula, txtnombres, txtapellidos, txtipo, txtcontrato, txtemail) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtemail) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            acciones.actualizar(txtcedula, txtnombres.toUpperCase(), txtespecialidad.toUpperCase(), txttelefono1, txttelefono2, txtdireccion_casa.toUpperCase(), txtdireccion_trabajo.toUpperCase(), txtemail.toLowerCase(), txtnacionalidad.toUpperCase(), txtapellidos.toUpperCase(), txtipo, txtcontrato);
            usuarioA.actualizarP(txtcedula, txtnombres.toUpperCase(), txtapellidos.toUpperCase(), txtemail.toLowerCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getMensajei(),"" ));
            limpiar();
        }
    }

    public void modificar_p() {
        if (valida.campos8(mostrarCedula, txtcorreo, txtespecialidad, txttelefono1, txttelefono2, txtdireccion_casa, txtdireccion_trabajo, txtnacionalidad) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            acciones.actualizar_p(mostrarCedula, txtcorreo.toLowerCase(), txtespecialidad.toUpperCase(), txttelefono1, txttelefono2, txtdireccion_casa.toUpperCase(), txtdireccion_trabajo.toUpperCase(), txtnacionalidad.toUpperCase());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getMensajei(), ""));
            limpiar();
        }
    }

    public void buscar_profesor() {
        deshabilitar_campo_cedula();
        v.crs2 = acciones.buscar_prof(txtcedula);
        if (v.crs2.size() > 0) {
            datos_profesor(v.crs2);
            buscar_profesor_asignado_coordinador();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje_encontrado, ""));

        } else if (v.crs2.size() < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_no_encontrado, ""));
            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_general, ""));
        }


    }

    public void buscar_profesor_asignado_coordinador() {
        v.crs6 = acciones.buscar_prof_asignado_coordinador(txtcedula);
        if (v.crs6.size() > 0) {
            deshabilitar_tipo_contrato();
        } else if (v.crs6.size() < 1) {
            habilitar_tipo_contrato();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_general, ""));
        }


    }

    public Boolean profesor_asignado_coordinador() {
        v.crs6 = acciones.buscar_prof_asignado_coordinador(txtcedula);
        if (v.crs6.size() > 0) {
            return true;

        } else {
            return false;
        }


    }

    public void datos_profesor(CachedRowSetImpl lista) {
        try {
            while (lista.next()) {
                setTxtipo(lista.getString("pr_tipo"));
                setTxtcontrato(lista.getString("pr_contrato"));
                setTxtapellidos(lista.getString("pr_apellidos"));
                setTxtemail(lista.getString("pr_correo"));
                setTxtnacionalidad(lista.getString("pr_nacionalidad"));
                setTxtdireccion_trabajo(lista.getString("pr_direccion_trabajo"));
                setTxtdireccion_casa(lista.getString("pr_direccion_casa"));
                setTxttelefono1(lista.getString("pr_telefono1"));
                setTxttelefono2(lista.getString("pr_telefono2"));
                setTxtespecialidad(lista.getString("pr_especialidad"));
                setTxtnombres(lista.getString("pr_nombres"));

            }
        } catch (SQLException ex) {
            System.out.print("Error al recuperar los datos.");
        }
    }

    public void bloquear_profesor() {
        if (!"".equals(txtcedula)) {
            if (profesor_asignado_coordinador() == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_profesor_coordinador, ""));
            } else {
                acciones.bloquear_profesor(txtcedula);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getMensajei() + " , " + mensaje_profesor_bloqueado, ""));
                limpiar();
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje_fk_no_encontrado1, ""));
        }

    }

    public void desbloquear_profesor() {
        if (!"".equals(txtcedula)) {
            acciones.desbloquear_profesor(txtcedula);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getMensajei() + " , " + mensaje_profesor_desbloqueado, ""));
            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje_fk_no_encontrado1, ""));
        }

    }

    public void limpiar() {
        habilitar_campo_cedula();
        habilitar_tipo_contrato();
        txtcedula = "";
        txtnombres = "";
        txtapellidos = "";
        txtespecialidad = "";
        txttelefono1 = "";
        txttelefono2 = "";
        txtdireccion_casa = "";
        txtdireccion_trabajo = "";
        txtemail = "";
        txtnacionalidad = "";
        txtipo = "";
        txtcontrato = "";
    }

    public ArrayList<entiv_profesor> getLista1() {
        ArrayList listaa = new ArrayList();
        v.crs = acciones.getListaa();
        try {
            entiv_profesor objart;
            while (v.crs.next()) {
                objart = new entiv_profesor();
                objart.setPe_cedula(v.crs.getString("pr_cedula"));
                objart.setPe_nombres(v.crs.getString("pr_nombres"));
                objart.setPe_apellidos(v.crs.getString("pr_apellidos"));
                objart.setPe_especialidad(v.crs.getString("pr_especialidad"));
                objart.setPe_telefono1(v.crs.getString("pr_telefono1"));
                objart.setPe_telefono2(v.crs.getString("pr_telefono2"));
                objart.setPe_direccion_casa(v.crs.getString("pr_direccion_casa"));
                objart.setPe_direccion_trabajo(v.crs.getString("pr_direccion_trabajo"));
                objart.setPe_email(v.crs.getString("pr_correo"));
                objart.setPe_nacionalidad(v.crs.getString("pr_nacionalidad"));
                objart.setPr_tipo(v.crs.getString("pr_tipo"));
                objart.setPr_contrato(v.crs.getString("pr_contrato"));
                objart.setPe_observacion(v.crs.getString("pr_estado"));
                listaa.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listaa;
    }

    public void seleccionarProfesor(SelectEvent event) {
        deshabilitar_campo_cedula();
        this.txtcedula = entidad.getPe_cedula();
        this.txtnombres = entidad.getPe_nombres();
        this.txtapellidos = entidad.getPe_apellidos();
        this.txtespecialidad = entidad.getPe_especialidad();
        this.txttelefono1 = entidad.getPe_telefono1();
        this.txttelefono2 = entidad.getPe_telefono2();
        this.txtdireccion_casa = entidad.getPe_direccion_casa();
        this.txtdireccion_trabajo = entidad.getPe_direccion_trabajo();
        this.txtemail = entidad.getPe_email();
        this.txtnacionalidad = entidad.getPe_nacionalidad();
        this.txtipo = entidad.getPr_tipo();
        this.txtcontrato = entidad.getPr_contrato();
        buscar_profesor_asignado_coordinador();
    }

    public ArrayList<entiv_profesor> getLista_para_enviar_correos() {
        v.crs = acciones.getLista();
        try {
            entiv_profesor objart;
            while (v.crs.next()) {
                objart = new entiv_profesor();
                objart.setPe_email(v.crs.getString("pr_correo"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public List getListacorreos() {
        listageneral.clear();
        try {
            v.crs3 = acciones.getLista();
            while (v.crs3.next()) {
                listageneral.add(v.crs3.getString("pr_correo"));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }

    public void enviarcorreos() {

        for (int i = 0; i < getListacorreos().size(); i++) {
            System.out.println(getListacorreos().get(i));
        }

        fu.enviar_correo(txtemail, txtcodigo, txttema, txtcuerpo, getListacorreos());

    }

    public void deshabilitar_campo_cedula() {
        deshabilitar = "true";

    }

    public void habilitar_campo_cedula() {
        deshabilitar = "false";
    }

    public void deshabilitar_tipo_contrato() {
        txtlistacontrato = "true";
        txtlistatipo = "true";

    }

    public void habilitar_tipo_contrato() {
        txtlistacontrato = "false";
        txtlistatipo = "false";
    }

    public void insertar_admin() {
        v.crs7 = acciones.buscar_alumno(txtcedula);
        if (v.crs7.size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_profesor_alumno, ""));
        } else {
            insertarAdmin();
        }
    }
}
