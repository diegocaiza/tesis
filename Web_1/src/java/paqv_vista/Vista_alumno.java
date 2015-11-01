package paqv_vista;

import com.sun.rowset.CachedRowSetImpl;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import paq_calculo.Validaciones;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_alumno;
import paq_clase_interfaz.interfaz_promocion;
import paq_clase_interfaz.interfaz_solicitud;
import paq_clase_interfaz.interfaz_usuario;
import paq_entidades.entiv_alumno;
import paq_entidades.entiv_cronograma;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_alumno extends Vista_campostxt {

    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarNombre;
    public String mostrarApellido;
    public String mostrarCedula;
    public String mostrarPromocion;
    public String mostrarNPromocion;
    public String txtcod = null;
    public String mostrarC;
    ArrayList lista = new ArrayList();
    private List<entiv_alumno> filter;

    public List<entiv_alumno> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_alumno> filter) {
        this.filter = filter;
    }

    public String getMostrarC() {
        mostrarC = vistaUsuario.getTxtnick();
        return mostrarC;
    }

    public void setMostrarC(String mostrarC) {
        this.mostrarC = mostrarC;
    }

    public String getMostrarNPromocion() {
        mostrarNPromocion = vistaUsuario.getTxt_nombre_promocion();
        return mostrarNPromocion;
    }

    public void setMostrarNPromocion(String mostrarNPromocion) {
        this.mostrarNPromocion = mostrarNPromocion;
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

    public String getMostrarPromocion() {
        mostrarPromocion = vistaUsuario.getTxtextra();
        return mostrarPromocion;
    }

    public void setMostrarPromocion(String mostrarPromocion) {
        this.mostrarPromocion = mostrarPromocion;
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
        devolver_datos_alumno(mostrarCedula);
        return mostrarCedula;
    }

    public void setMostrarCedula(String mostrarCedula) {
        this.mostrarCedula = mostrarCedula;
    }
    @EJB
    private interfaz_alumno acciones;
    @EJB
    private interfaz_usuario usuarioA;
    @EJB
    private interfaz_promocion combopromocion;
    @EJB
    private interfaz_solicitud acciones_solicitud;
    entiv_variables v = new entiv_variables();
    entiv_alumno entidad = new entiv_alumno();
    Validaciones valida = new Validaciones();
    fun_calculo fu = new fun_calculo();

    public entiv_alumno getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_alumno entidad) {
        this.entidad = entidad;
    }

    public void insertar() throws IOException {
        if (valida.campos16(getMostrarPromocion(), getMostrarNombre(), getMostrarCedula(), txtdireccion_trabajo, txtdireccion_casa, txttelefono1, txttelefono2, txtespecialidad, txtfecha_nacimiento, txtfecha_graduacion_pregrado, txtcursos, txtnacionalidad, getMostrarApellido(), txtcorreo, txtid, txtgenero) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            acciones.insertar(getMostrarPromocion(), getMostrarNombre().toUpperCase(), getMostrarCedula(), txtdireccion_trabajo.toUpperCase(), txtdireccion_casa.toUpperCase(), txttelefono1, txttelefono2, txtespecialidad.toUpperCase(), fu.formato_fecha(txtfecha_nacimiento), fu.formato_fecha(txtfecha_graduacion_pregrado), txtcursos.toUpperCase(), txtnacionalidad.toUpperCase(), getMostrarApellido().toUpperCase(), txtcorreo.toLowerCase(), txtid.toUpperCase(), txtgenero);
            if (codigo_oracle_duplicado.equals(acciones.getmensajei().substring(0, 9))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_que_consta_bbd, ""));
            } else if (codigo_oracle_duplicado != mensaje) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                limpiar();
            }
        }
    }

    public void devolver_datos_alumno(String cedula) {
        v.crs3 = acciones.datos_alumno(cedula);
        try {
            entiv_alumno objart;
            if (v.crs3.size() > 0) {
                habilitar_boton_modificar = "false";
                deshabilitar_boton_insertar = "true";
            } else {
                habilitar_boton_modificar = "true";
            }

            while (v.crs3.next()) {
                objart = new entiv_alumno();
                txtid = v.crs3.getString("al_id");
                txtdireccion_trabajo = v.crs3.getString("al_direccion_trabajo");
                txtdireccion_casa = v.crs3.getString("al_direccion_casa");
                txttelefono1 = v.crs3.getString("al_telefono1");
                txttelefono2 = v.crs3.getString("al_telefono2");
                txtespecialidad = v.crs3.getString("al_especialidad");
                txtfecha_nacimiento = v.crs3.getDate("al_fecha_nacimiento");
                txtfecha_graduacion_pregrado = v.crs3.getDate("al_fecha_graduacion_pregrado");
                txtcursos = v.crs3.getString("al_cursos");
                txtnacionalidad = v.crs3.getString("al_nacionalidad");
                txtgenero = v.crs3.getString("al_genero");
                txtcorreo = v.crs3.getString("al_correo");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de datos" + e.getMessage());
        }
    }

    public void insertarAdmin() {
        if (valida.campos5_1(txtapellidos, txtnombres, txtcedula, txtcod, txtcorreo, txtgenero) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            if (valida.validar_cedula(txtcedula) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_cedula, ""));
            } else {
                acciones.insertarAdmin(txtcod, txtnombres.toUpperCase(), txtcedula, txtdireccion_trabajo.toUpperCase(), txtdireccion_casa.toUpperCase(), txttelefono1, txttelefono2, txtespecialidad.toUpperCase(), fu.formato_fecha(fu.fecha_actual), fu.formato_fecha(fu.fecha_actual), txtcursos.toUpperCase(), txtnacionalidad.toUpperCase(), txtapellidos.toUpperCase(), txtcorreo.toLowerCase(), txtid.toUpperCase(), txtgenero);
                mensaje = acciones.getmensajei().substring(0, 9);
                if (codigo_oracle_duplicado.equals(mensaje)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado, ""));
                } else {
                    acciones.insertarUsuario(txtnombres.toUpperCase(), txtapellidos.toUpperCase(), txtcedula, txtipoUsuario, txtpassw, txtcorreo.toLowerCase(), txtcod);
                    mensaje = acciones.getmensajei().substring(0, 9);
                    if (codigo_oracle_duplicado.equals(mensaje)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alumno registrado correctamente y ya se encuentra registrado como usuario", ""));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alumno registrado correctamente", ""));
                    }
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
            usuarioA.eliminar(cod);
            limpiar();
        }
    }

    public void bloquear_alumno() {
        if (!"".equals(txtcedula)) {
            acciones.bloquear_alumno(txtcedula);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei() + " , " + mensaje_alumno_bloqueado, ""));
            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje_fk_no_encontrado1, ""));

        }

    }

    public void desbloquear_alumno() {
        if (!"".equals(txtcedula)) {
            acciones.desbloquear_alumno(txtcedula);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei() + " , " + mensaje_alumno_desbloqueado, ""));
            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje_fk_no_encontrado1, ""));

        }
    }

    public void modificar1() {
        if (valida.campos5_1(txtapellidos, txtnombres, txtcedula, txtcod, txtcorreo, txtgenero) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            acciones.actualizar1(txtcedula, txtcod, txtnombres.toUpperCase(), txtapellidos.toUpperCase(), txtdireccion_trabajo.toUpperCase(), txtdireccion_casa.toUpperCase(), txttelefono1, txttelefono2, txtespecialidad.toUpperCase(), fu.formato_fecha(txtfecha_nacimiento), fu.formato_fecha(txtfecha_graduacion_pregrado), txtcursos.toUpperCase(), txtnacionalidad.toUpperCase(), txtcorreo.toLowerCase(), txtid.toUpperCase(), txtgenero);
            mensaje = acciones.getmensajei().substring(0, 9);
            usuarioA.modificar(txtcedula, txtnombres.toUpperCase(), txtapellidos.toUpperCase(), txtcorreo.toLowerCase(), txtcod);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos13A(mostrarCedula, txtcorreo, txtid, txtdireccion_trabajo, txtdireccion_casa, txttelefono1, txttelefono2, txtespecialidad, txtfecha_nacimiento, txtfecha_graduacion_pregrado, txtcursos, txtnacionalidad, txtgenero) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else if (valida.validacion_correo(txtcorreo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_correo_incorrecto, ""));
        } else {
            acciones.actualizar(mostrarCedula, txtcorreo.toLowerCase(), txtid.toUpperCase(), txtdireccion_trabajo.toUpperCase(), txtdireccion_casa.toUpperCase(), txttelefono1, txttelefono2, txtespecialidad.toUpperCase(), fu.formato_fecha(txtfecha_nacimiento), fu.formato_fecha(txtfecha_graduacion_pregrado), txtcursos.toUpperCase(), txtnacionalidad.toUpperCase(), txtgenero);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            txtsoli_fecha = new Date();
            acciones_solicitud.insertar(mostrarCedula, "Revisar los datos de este alumno que modificó sus datos.", mostrarNombre + " " + mostrarApellido + " " + "Actualización de datos", fu.formato_fecha_hora2(txtsoli_fecha), estado1);
            limpiar();
        }
    }

    public void buscar_alumno() {
        deshabilitar_campo_cedula();
        v.crs2 = acciones.buscar_al(txtcedula);
        if (v.crs2.size() > 0) {
            datos_alumno(v.crs2);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje_encontrado, ""));

        } else if (v.crs2.size() < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_no_encontrado, ""));
            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_general, ""));
        }


    }

    public void datos_alumno(CachedRowSetImpl lista) {
        try {
            while (lista.next()) {
                setTxtid(lista.getString("al_id"));
                setTxtcod(lista.getString("prom_codigo"));
                setTxtnombres(lista.getString("al_nombres"));
                setTxtapellidos(lista.getString("al_apellidos"));
                setTxtdireccion_trabajo(lista.getString("al_direccion_trabajo"));
                setTxtdireccion_casa(lista.getString("al_direccion_casa"));
                setTxttelefono1(lista.getString("al_telefono1"));
                setTxttelefono2(lista.getString("al_telefono2"));
                setTxtespecialidad(lista.getString("al_especialidad"));
                setTxtfecha_graduacion_pregrado(lista.getDate("al_fecha_nacimiento"));
                setTxtfecha_nacimiento(lista.getDate("al_fecha_nacimiento"));
                setTxtcursos(lista.getString("al_cursos"));
                setTxtnacionalidad(lista.getString("al_nacionalidad"));
                setTxtcorreo(lista.getString("al_correo"));
                setTxtgenero(lista.getString("al_genero"));
            }
        } catch (SQLException ex) {
            System.out.print("Error al recuperar los datos.");
        }
    }

    public void limpiar() {
        habilitar_campo_cedula();
        txtnombres = "";
        txtcedula = "";
        txtdireccion_trabajo = "";
        txtdireccion_casa = "";
        txttelefono1 = "";
        txttelefono2 = "";
        txtespecialidad = "";
        txtcursos = "";
        txtfotografia = "";
        txtnacionalidad = "";
        txtapellidos = "";
        txtfecha_nacimiento = null;
        txtfecha_graduacion_pregrado = null;
        txtcorreo = "";
        txtid = "";
        txtgenero = "";
    }

    public ArrayList<entiv_alumno> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista_por_promocion(txtcod);
        try {
            entiv_alumno objart;
            while (v.crs.next()) {
                objart = new entiv_alumno();
                objart.setCodigo_promocion(v.crs.getString("prom_codigo"));
                objart.setPe_nombres(v.crs.getString("al_nombres"));
                objart.setPe_apellidos(v.crs.getString("al_apellidos"));
                objart.setPe_cedula(v.crs.getString("al_cedula"));
                objart.setPe_direccion_trabajo(v.crs.getString("al_direccion_trabajo"));
                objart.setPe_direccion_casa(v.crs.getString("al_direccion_casa"));
                objart.setPe_telefono1(v.crs.getString("al_telefono1"));
                objart.setPe_telefono2(v.crs.getString("al_telefono2"));
                objart.setPe_especialidad(v.crs.getString("al_especialidad"));
                objart.setPe_fecha_nacimiento(v.crs.getDate("al_fecha_nacimiento"));
                objart.setFecha_graduacion_pregrado(v.crs.getDate("al_fecha_graduacion_pregrado"));
                objart.setPe_cursos(v.crs.getString("al_cursos"));
                objart.setPe_nacionalidad(v.crs.getString("al_nacionalidad"));
                objart.setPe_email(v.crs.getString("al_correo"));
                objart.setPe_id(v.crs.getString("al_id"));
                objart.setGenero(v.crs.getString("al_genero"));
                objart.setObservacion(v.crs.getString("al_estado"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionarAlumno(SelectEvent event) {
        deshabilitar_campo_cedula();
        this.txtpromocion = entidad.getCodigo_promocion();
        this.txtnombres = entidad.getPe_nombres();
        this.txtapellidos = entidad.getPe_apellidos();
        this.txtcedula = entidad.getPe_cedula();
        this.txtdireccion_trabajo = entidad.getPe_direccion_trabajo();
        this.txtdireccion_casa = entidad.getPe_direccion_casa();
        this.txttelefono1 = entidad.getPe_telefono1();
        this.txttelefono2 = entidad.getPe_telefono2();
        this.txtespecialidad = entidad.getPe_especialidad();
        this.txtfecha_nacimiento = entidad.getPe_fecha_nacimiento();
        this.txtfecha_graduacion_pregrado = entidad.getFecha_graduacion_pregrado();
        this.txtcursos = entidad.getPe_cursos();
        this.txtnacionalidad = entidad.getPe_nacionalidad();
        this.txtcorreo = entidad.getPe_email();
        this.txtid = entidad.getPe_id();
        this.txtgenero = entidad.getGenero();
    }

    public void deshabilitar_campo_cedula() {
        deshabilitar = "true";
    }

    public void habilitar_campo_cedula() {
        deshabilitar = "false";
    }

    public List getListaPromocionC() {
        listageneral.clear();
        try {
            v.crs3 = combopromocion.getLista_nombres_promocion(getMostrarCedula());
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("prom_codigo"), v.crs3.getString("pro_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listageneral;
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

    public void buscar_cronograma() {
        v.fecha_fin.add(Calendar.DAY_OF_MONTH, +20);
        v.crs1 = acciones.devolver_cronograma_alumno(txtcedula, fu.formato_fecha(v.fecha_inicio), fu.formato_fecha(v.fecha_fin.getTime()));
        if (v.crs1.size() > 0) {
            mensaje = "Sus fechas de cronograma son:";

        } else if (v.crs1.size() < 1) {
            mensaje = "No existen cronograma en estas 2 semanas siguientes o la cédula no es la correcta!";
            limpiar_datos();

        } else {
            limpiar_datos();

        }


    }

    public void buscar_cronograma_automatico() {
//        devolver_datos(getMostrarC()) ;
        v.fecha_fin.add(Calendar.DAY_OF_MONTH, +20);
        v.crs1 = acciones.devolver_cronograma_alumno(getMostrarC(), fu.formato_fecha(v.fecha_inicio), fu.formato_fecha(v.fecha_fin.getTime()));
        if (v.crs1.size() > 0) {
            mensaje = "Sus fechas de cronograma son:";

        } else if (v.crs1.size() < 1) {
            mensaje = "Si el cuadro de abajo no tiene datos que mostrar, usted no tiene cronograma en estas 2 semanas siguientes";
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
            limpiar_datos();

        } else {
            limpiar_datos();


        }


    }

    public void limpiar_datos() {
        txtapellidos = "";
        txt_nombre_promocion = "";
        txtpantalla = "";
        txtunido = "";

    }

    public ArrayList<entiv_cronograma> getLista_cronogramapor_alumno() {
        lista.clear();
        buscar_cronograma();
        try {
            entiv_cronograma objart;
            while (v.crs1.next()) {
                objart = new entiv_cronograma();
                objart.setProm_codigo(v.crs1.getString("pro_nombre"));
                objart.setUtem_codigo(v.crs1.getString("pp_nombre"));
                objart.setNombre_mod(v.crs1.getString("mod_nombre"));
                objart.setCreditos(v.crs1.getInt("mod_numerocreditos"));
                objart.setFd_fecha2(v.crs1.getString("fd_fecha"));
                objart.setFd_hora_i(v.crs1.getString("fd_hora_i"));
                objart.setFd_hora_f(v.crs1.getString("fd_hora_f"));
                objart.setNumero(v.crs1.getString("au_numero"));
                objart.setBloque(v.crs1.getString("au_bloque"));
                objart.setPiso(v.crs1.getString("au_piso"));
                objart.setAsig_codigo(v.crs1.getString("pl_nombre"));
                objart.setNombre_profesor(v.crs1.getString("pr_nombres"));
                objart.setNombre_asig(v.crs1.getString("al_nombres"));
                txtapellidos = objart.getNombre_asig();
                txt_nombre_promocion = objart.getProm_codigo();
                txtpantalla = objart.getUtem_codigo();
                txtunido = objart.getAsig_codigo();
                lista.add(objart);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de datos" + e.getMessage());
        }
        return lista;
    }

    public ArrayList<entiv_cronograma> getLista_cronogramapor_alumno_automatico() {
        lista.clear();
        buscar_cronograma_automatico();

        try {
            entiv_cronograma objart;
            while (v.crs1.next()) {
                objart = new entiv_cronograma();
                objart.setProm_codigo(v.crs1.getString("pro_nombre"));
                objart.setUtem_codigo(v.crs1.getString("pp_nombre"));
                objart.setNombre_mod(v.crs1.getString("mod_nombre"));
                objart.setCreditos(v.crs1.getInt("mod_numerocreditos"));
                objart.setFd_fecha2(v.crs1.getString("fd_fecha"));
                objart.setFd_hora_i(v.crs1.getString("fd_hora_i"));
                objart.setFd_hora_f(v.crs1.getString("fd_hora_f"));
                objart.setNumero(v.crs1.getString("au_numero"));
                objart.setBloque(v.crs1.getString("au_bloque"));
                objart.setPiso(v.crs1.getString("au_piso"));
                objart.setAsig_codigo(v.crs1.getString("pl_nombre"));
                objart.setNombre_profesor(v.crs1.getString("pr_nombres"));
                objart.setNombre_asig(v.crs1.getString("al_nombres"));
                txtapellidos = objart.getNombre_asig();
                txt_nombre_promocion = objart.getProm_codigo();
                txtpantalla = objart.getUtem_codigo();
                txtunido = objart.getAsig_codigo();
                lista.add(objart);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de datos" + e.getMessage());
        }

        return lista;
    }
    public void validar_cedula_campo() {
        if (fu.validadorDeCedula(txtcedula)) {
          setTxt_validar_cedula( "Cédula  de identidad correcta");

        } else {
            setTxt_validar_cedula( "Cédula de identidad incorrecta");

        }
       
    }
    public void insertar_alumno_admin() {
        v.crs7 = acciones.buscar_profesor(txtcedula);
        if (v.crs7.size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_alumno_profesor, ""));
        } else {
            insertarAdmin();
        }
    }
//    public void devolver_datos(String cedula) {
//        v.crs4 = acciones.devolver_programa_promocion(cedula);
//        if (v.crs4.size() > 0) {
//            datos_programa_promocion(v.crs4);
//
//        } else if (v.crs4.size() < 1) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usted no se encuentra en ninguna Promoción, por favor llene sus datos personales para constar en una Promoción", ""));
//
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_general, ""));
//        }
//
//    }
//    public void datos_programa_promocion(CachedRowSetImpl lista) {
//        try {
//            while (lista.next()) {
////                setTxt_nombre_promocion(lista.getString("pro_nombre"));
////                setTxtunidad(lista.getString("pl_nombre"));
////                setTxtcontrato(lista.getString("pp_nombre"));
//               txt_nombre_promocion=(lista.getString("pro_nombre"));
//               txtunidad=(lista.getString("pl_nombre"));
//                txtcontrato=(lista.getString("pp_nombre"));
//
//            }
//        } catch (SQLException ex) {
//            System.out.print("Error al recuperar los datos.");
//        }
//    }
}
