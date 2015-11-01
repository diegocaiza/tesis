package paqv_vista;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.text.ParseException;
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
import paq_clase_interfaz.interfaz_asignatura;
import paq_clase_interfaz.interfaz_aula;
import paq_clase_interfaz.interfaz_cronograma;
import paq_clase_interfaz.interfaz_modulo;
import paq_clase_interfaz.interfaz_profesor;
import paq_clase_interfaz.interfaz_promocion;
import paq_clase_interfaz.interfaz_unidad_tematica;
import paq_entidades.entiv_aula;
import paq_entidades.entiv_cronograma;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_cronograma extends Vista_campostxt {

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
    @EJB
    private interfaz_cronograma acciones;
    @EJB
    private interfaz_unidad_tematica combounidad;
    @EJB
    private interfaz_modulo combomodulo;
    @EJB
    private interfaz_asignatura comboasignatura;
    @EJB
    private interfaz_promocion comboprmocion;
    @EJB
    private interfaz_aula comboaula;
    @EJB
    private interfaz_profesor comboprofesor;
    @EJB
    private interfaz_alumno acciones_alumno;
    ArrayList lista = new ArrayList();
    entiv_cronograma entidad = new entiv_cronograma();
    Validaciones valida = new Validaciones();
    entiv_variables v = new entiv_variables();
    fun_calculo fu = new fun_calculo();
    Vista_envio_emails enviomails = new Vista_envio_emails();
    Integer di;
    public String fechaactual = "";
    private List<entiv_cronograma> filter;

    public List<entiv_cronograma> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_cronograma> filter) {
        this.filter = filter;
    }

    public entiv_cronograma getEntidad() {
        return entidad;
    }

    public void setEntidad(entiv_cronograma entidad) {
        this.entidad = entidad;
    }

    public void insertar() {
        
        Date fec = null;
        v.listacomprobaraula.clear();
        try {
            v.crs6 = acciones.getcomprobaraula(txtaula, fu.formato_fecha(txtfechainicio));
            if (v.crs6.size() > 0) {
                try {
                    while (v.crs6.next()) {
                        fec = v.crs6.getDate("fd_fecha");
                        txtcodigo_fecha_dia = v.crs6.getString("fd_codigo");
                    }
                    v.crs6.close();
                } catch (SQLException ex) {
                }
                mensaje = "No se puede asignar esta aula,consulte el código: '" + txtcodigo_fecha_dia + "'";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));

            } else {
                insertar2();
                limpiar1();
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));

        }

    }

    public Date devolver_fecha_final_promocion() {
        v.crs7 = comboprmocion.fecha_final_promocion(txtpromocion);
        if (v.crs7.size() > 0) {
            try {
                while (v.crs7.next()) {
                    txtfecha_fin_promocion = v.crs7.getDate("fecha");
                }
                v.crs7.close();
                return txtfecha_fin_promocion;
            } catch (SQLException ex) {

                return null;
            }

        } else {

            return null;
        }
    }

    public void insertar2() {
        Date fec = null;
        v.listacomprobarprofesor.clear();
        v.crs7 = acciones.getcomprobarprofesor(txtprofesor, fu.formato_fecha(txtfechainicio));
        if (v.crs7.size() > 0) {
            try {
                while (v.crs7.next()) {
                    fec = v.crs7.getDate("fd_fecha");
                    txtcodigo_fecha_dia = v.crs7.getString("fd_codigo");
                }
                v.crs7.close();
            } catch (SQLException ex) {
            }

            mensaje = "No se puede asignar este profesor,consulte el código: '" + txtcodigo_fecha_dia + "'";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));

        } else {

            insertar_cronograma();
        }
    }

    public void insertar_cronograma() {
        if (valida.campos_cronograma(txtaula, txtprofesor, txtmodulo, fu.formato_fecha(txtfechainicio), fecha_inicio, fecha_fin) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));

        } else {
            x = fu.fecha_mayor(fu.fecha_actual, devolver_fecha_final_promocion());
            if (x == true) {
                di = fu.getDayOfTheWeek(txtfechainicio);
                txtdia = di.toString();
                acciones.insertar(txtaula, txtprofesor, txtunidad, txtasignatuta, txtmodulo, txtpromocion, txtdia, fu.formato_fecha_hora(fecha_inicio), fu.formato_fecha_hora(fecha_fin), fu.formato_fecha(txtfechainicio));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Esta Promocion ya Finalizo el:" + devolver_fecha_final_promocion(), ""));
            }
        }
        if ("Transacción Exitosa".equals(acciones.getmensajei())) {
            horario_clases(txtpromocion, fu.formato_fecha(txtfechainicio));

        } else {
            System.out.println("No se enviaron los mensajes");
        }
    }

    public void eliminar(String cod) {
        acciones.eliminar(cod);
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_curso_dictado, ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public String buscar() {
        setTxtcodigo_dinamico("");
        return getTxtcodigo_dinamico();
    }

    public void modificar() {
        
        if (valida.campos_actualizar_cronograma(txtaula, txtprofesor, txtasignatuta, txtmodulo, txtpromocion, fecha_inicio, fecha_fin, txtfechainicio) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));

        } else {
            try {
                di = fu.getDayOfTheWeek(txtfechainicio);
                txtdia = di.toString();
                buscar_codigo_dictado(txtcodigo);
                if (buscar_codigo_dictado(txtcodigo).size() > 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede modificar, éste curso está siendo dictado", ""));

                } else {
                    acciones.actualizar(txtcodigo, txtaula, txtprofesor, txtunidad, txtasignatuta, txtmodulo, txtpromocion, txtdia, fu.formato_fecha_hora(fecha_inicio), fu.formato_fecha_hora(fecha_fin), fu.formato_fecha(txtfechainicio));
                    horario_clases_modificado(txtcodigo, txtpromocion);
                    limpiar();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                }

            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar", ""));
            }
        }
    }

    public ArrayList<entiv_cronograma> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista_cronograma(txtpromocion, txtcodigo_dinamico);
        try {
            entiv_cronograma objart;
            while (v.crs.next()) {
                objart = new entiv_cronograma();
                objart.setCodigo(v.crs.getString("fd_codigo"));
                objart.setAu_codigo(v.crs.getString("au_codigo"));
                objart.setPr_cedula(v.crs.getString("pr_cedula"));
                objart.setUtem_codigo(v.crs.getString("utem_codigo"));
                objart.setAsig_codigo(v.crs.getString("asig_codigo"));
                objart.setMod_codigo(v.crs.getString("mod_codigo"));
                objart.setProm_codigo(v.crs.getString("prom_codigo"));
                objart.setFd_dia(v.crs.getString("fd_dia"));
                objart.setFd_hora_inicio(v.crs.getDate("fd_hora_i"));
                objart.setFd_hora_final(v.crs.getDate("fd_hora_f"));
                objart.setFd_fecha(v.crs.getDate("fd_fecha"));
                objart.setBloque(v.crs.getString("au_bloque"));
                objart.setNumero(v.crs.getString("au_numero"));
                objart.setPiso(v.crs.getString("au_piso"));
                objart.setNombre_profesor(v.crs.getString("pr_nombres"));
                objart.setNombre_mod(v.crs.getString("mod_nombre"));
                objart.setGrupo_modulo(v.crs.getString("fd_grupo_modulo"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public ArrayList<entiv_cronograma> getLista_cronogramapor_profesor() {
        lista.clear();
        buscar_cronograma();
        try {
            entiv_cronograma objart;
            while (v.crs3.next()) {
                objart = new entiv_cronograma();
                objart.setFd_dia(v.crs3.getString("fd_codigo"));
                objart.setProm_codigo(v.crs3.getString("pro_nombre"));
                objart.setUtem_codigo(v.crs3.getString("pp_nombre"));
                objart.setNombre_mod(v.crs3.getString("mod_nombre"));
                objart.setCreditos(v.crs3.getInt("mod_numerocreditos"));
                objart.setFd_fecha2(v.crs3.getString("fd_fecha"));
                objart.setFd_hora_i(v.crs3.getString("fd_hora_i"));
                objart.setFd_hora_f(v.crs3.getString("fd_hora_f"));
                objart.setNumero(v.crs3.getString("au_numero"));
                objart.setBloque(v.crs3.getString("au_bloque"));
                objart.setPiso(v.crs3.getString("au_piso"));
                objart.setNombre_profesor(v.crs3.getString("pr_nombres"));
                txtnombres = objart.getNombre_profesor();
                lista.add(objart);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de datos" + e.getMessage());
        }
        return lista;
    }

    public ArrayList<entiv_cronograma> getLista_cronogramapor_profesor_automatico() {
        lista.clear();
        buscar_cronograma_profesor();
        try {
            entiv_cronograma objart;
            while (v.crs3.next()) {
                objart = new entiv_cronograma();
                objart.setFd_dia(v.crs3.getString("fd_codigo"));
                objart.setProm_codigo(v.crs3.getString("pro_nombre"));
                objart.setUtem_codigo(v.crs3.getString("pp_nombre"));
                objart.setNombre_mod(v.crs3.getString("mod_nombre"));
                objart.setCreditos(v.crs3.getInt("mod_numerocreditos"));
                objart.setFd_fecha2(v.crs3.getString("fd_fecha"));
                objart.setFd_hora_i(v.crs3.getString("fd_hora_i"));
                objart.setFd_hora_f(v.crs3.getString("fd_hora_f"));
                objart.setNumero(v.crs3.getString("au_numero"));
                objart.setBloque(v.crs3.getString("au_bloque"));
                objart.setPiso(v.crs3.getString("au_piso"));
                objart.setNombre_profesor(v.crs3.getString("pr_nombres"));
                txtnombres = objart.getNombre_profesor();
                lista.add(objart);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de datos" + e.getMessage());
        }
        return lista;
    }
//cambio

    public void buscar_cronograma() {
        v.fecha_fin.add(Calendar.DAY_OF_MONTH, +20);
        v.crs3 = acciones.getdevolver_cronograma_por_profesor(txtcedula, fu.formato_fecha(v.fecha_inicio), fu.formato_fecha(v.fecha_fin.getTime()));
        if (v.crs3.size() > 0) {
            mensaje = "Sus fechas de cronograma son:";
//            return v.crs3;
        } else if (v.crs3.size() < 1) {
            mensaje = "No existen cronograma en estas 2 semanas o su cédula no es la correcta!";
            txtnombres = "";
//            return null;
        } else {
            txtnombres = "";
//            return null;

        }

    }

    public void buscar_cronograma_profesor() {
        v.fecha_fin.add(Calendar.DAY_OF_MONTH, +20);
        v.crs3 = acciones.getdevolver_cronograma_por_profesor(getMostrarC(), fu.formato_fecha(v.fecha_inicio), fu.formato_fecha(v.fecha_fin.getTime()));
        if (v.crs3.size() > 0) {
            mensaje = "Sus fechas de cronograma son:";
//            return v.crs3;
        } else if (v.crs3.size() < 1) {
            mensaje = "No existen cronogramas asgnados en estas 2 próximas semanas";
            txtnombres = "";
//            return null;
        } else {
            txtnombres = "";
//            return null;
        }

    }

    public void seleccionarCronograma(SelectEvent event) {
        this.txtcodigo = entidad.getCodigo();
        this.txtaula = entidad.getAu_codigo();
        this.txtprofesor = entidad.getPr_cedula();
        this.txtunidad = entidad.getUtem_codigo();
        this.txtasignatuta = entidad.getAsig_codigo();
        this.txtpromocion = entidad.getProm_codigo();
        this.txtmodulo = entidad.getMod_codigo();
        this.txtdia = entidad.getFd_dia();
        this.fecha_inicio = entidad.getFd_hora_inicio();
        this.fecha_fin = entidad.getFd_hora_final();
        this.txtfechainicio = entidad.getFd_fecha();
        this.txtgrupo_modulo = entidad.getGrupo_modulo();
        setDeshabilitar_lista("true");
    }

    public CachedRowSetImpl buscar_codigo_dictado(String codigo) {
        v.crs10 = comboaula.busqueda_curso_dictado(codigo);
        return v.crs10;
    }

    public void seleccionLista_grupo_modulo() {
        entidad = (entiv_cronograma) tabla.getRowData();
        this.txtgrupo_modulo = entidad.getGrupo_modulo();
    }

    public List getListaprofe() {
        v.listaprofesor.clear();
        try {
            v.crs6 = comboprofesor.getListacomboprofesor();
            while (v.crs6.next()) {
                v.listaprofesor.add(new SelectItem(v.crs6.getString("pr_cedula"), v.crs6.getString("pr_nombres")));
            }
            v.crs6.close();
        } catch (SQLException ex) {
        }
        return v.listaprofesor;
    }

    public List getListaaula() {
        v.listaaula.clear();
        try {
            v.crs2 = comboaula.getListacomboaula();
            while (v.crs2.next()) {
                v.listaaula.add(new SelectItem(v.crs2.getString("au_codigo"), v.crs2.getString("au_nombre")));
            }
            v.crs2.close();
        } catch (SQLException ex) {
        }
        return v.listaaula;
    }

    public List getListapromo() {
        v.listapromocion.clear();
        try {
            v.crs1 = comboprmocion.getLista_nombres_promocion(getMostrarC());
            while (v.crs1.next()) {
                v.listapromocion.add(new SelectItem(v.crs1.getString("prom_codigo"), v.crs1.getString("PRO_NOMBRE")));
            }
            v.crs1.close();
        } catch (SQLException ex) {
        }
        return v.listapromocion;
    }

    public List getListamodulo() {
        v.listamodulo.clear();
        try {
            v.crs3 = combomodulo.getListacombomoduloporpromocion(txtpromocion);

            while (v.crs3.next()) {
                v.listamodulo.add(new SelectItem(v.crs3.getString("mod_codigo"), v.crs3.getString("mod_nombre")));
            }

            v.crs3.close();
        } catch (SQLException ex) {
        }

        return v.listamodulo;

    }

    public List getListasignatura() {
        v.listaasignatura.clear();
        try {
            v.crs4 = comboasignatura.getListaactualizada(txtmodulo);
            while (v.crs4.next()) {
                v.listaasignatura.add(new SelectItem(v.crs4.getString("asig_codigo"), v.crs4.getString("asig_nombre")));
            }
            v.crs4.close();
        } catch (SQLException ex) {
        }

        return v.listaasignatura;


    }

    public List getListaunidad() {
        v.listaunidad.clear();
        try {
            v.crs5 = combounidad.getListacomboporasignatura(txtasignatuta);
            while (v.crs5.next()) {
                v.listaunidad.add(new SelectItem(v.crs5.getString("UTEM_codigo"), v.crs5.getString("UTEM_nombre")));
            }
            v.crs5.close();
        } catch (SQLException ex) {
        }
        return v.listaunidad;
    }

    public String devolver() {
        limpiar();
        return txtpromocion;

    }

    public String devolvertabla() {
        return txtpromocion;
    }

    public String devolve2() {
        return txtmodulo;
    }

    public String devolver3() {

        return txtasignatuta;
    }

    public String devolver4() {
        txtmodulo = "x";
        return txtmodulo;
    }

    public String devolver5() {
        txtasignatuta = "x";
        return txtasignatuta;
    }

    public String devolver_mensaje() {

        return mensaje;
    }

    public void limpiar1() {
        txtaula = "";
        txtprofesor = "";
        txthorainicio2 = "";
        txthorafin2 = "";
        txtfechainicio = null;

    }

    public void limpiar() {
        txtcodigo_dinamico = "";
        txtcodigo = "";
        txtaula = "";
        txtprofesor = "";
        txtunidad = "";
        txtasignatuta = "";
        txtmodulo = "";
        txthorainicio2 = "";
        txthorafin2 = "";
        txtfechainicio = null;
        setDeshabilitar_lista("false");

    }

    public void limpiar_datos() {
        txtcedula = "";
        txtnombres = "";
        mensaje = "";
    }

    public String getFechaactual() {
        Date fec = new Date();
        fechaactual = fu.formato_fecha2(fec);
        return fechaactual;
    }

    public void setFechaactual(String fechaactual) {

        this.fechaactual = fechaactual;
    }

    public void verfecha() {

        mensaje = getFechaactual();
    }

    public void reporte() {
        seleccionLista_grupo_modulo();
    }

    public List enviar_cronograma_automatico(String promo) {
        v.listacorreos.clear();
        try {
            v.crs4 = acciones_alumno.getLista_correos_por_alumno(promo);
            while (v.crs4.next()) {
                v.listacorreos.add(v.crs4.getString("al_correo"));
            }
            v.crs4.close();
            return v.listacorreos;

        } catch (SQLException ex) {
            System.out.println("Error al obtener la lista de correos");
            return null;
        }
    }

    public void horario_clases(String promocion, String fecha) {
        lista_correos_electronicos = enviar_cronograma_automatico(promocion);
        lista.clear();
        v.crs8 = acciones.devolver_cronograma(promocion, fecha);
        try {
            entiv_cronograma objart = null;
            while (v.crs8.next()) {
                objart = new entiv_cronograma();
                objart.setProm_codigo(v.crs8.getString("pro_nombre"));
                objart.setUtem_codigo(v.crs8.getString("pp_nombre"));
                objart.setNombre_mod(v.crs8.getString("mod_nombre"));
                objart.setFd_fecha2(v.crs8.getString("fd_fecha"));
                objart.setFd_hora_i(v.crs8.getString("fd_hora_i"));
                objart.setFd_hora_f(v.crs8.getString("fd_hora_f"));
                objart.setNumero(v.crs8.getString("au_numero"));
                objart.setBloque(v.crs8.getString("au_bloque"));
                mensaje_correo_electronico = "\n Programa: " + objart.getUtem_codigo() + "\n Pomocion: " + objart.getProm_codigo() + "\n Modulo: " + objart.getNombre_mod() + "\n Fecha: " + objart.getFd_fecha2() + "\n Hora inicial:" + objart.getFd_hora_i() + "\t Hora final: " + objart.getFd_hora_f() + "\n Aula: " + objart.getNumero() + "\t Bloque: " + objart.getBloque() + "\nNota: Para confirmar su horario este pendiente de su correo o ingrese al sistema de consultas de horarios de clases.";

            }
            enviomails.enviarcorreos_automaticos(promocion, "Horario Clases:" + objart.getUtem_codigo(), mensaje_correo_electronico, lista_correos_electronicos);
            //  System.out.println(mensaje_correo_electronico);

        } catch (Exception e) {
            System.out.println("Error al obtener la lista de datos" + e.getMessage());
        }
    }

    public void horario_clases_modificado(String codigo, String promocion) {
        lista_correos_electronicos_modificacion = enviar_cronograma_automatico(promocion);
        lista.clear();
        v.crs9 = acciones.devolver_cronograma_modificado(codigo);
        try {
            entiv_cronograma objart = null;
            while (v.crs9.next()) {
                objart = new entiv_cronograma();
                objart.setProm_codigo(v.crs9.getString("pro_nombre"));
                objart.setUtem_codigo(v.crs9.getString("pp_nombre"));
                objart.setNombre_mod(v.crs9.getString("mod_nombre"));
                objart.setFd_fecha2(v.crs9.getString("fd_fecha"));
                objart.setFd_hora_i(v.crs9.getString("fd_hora_i"));
                objart.setFd_hora_f(v.crs9.getString("fd_hora_f"));
                objart.setNumero(v.crs9.getString("au_numero"));
                objart.setBloque(v.crs9.getString("au_bloque"));
                mensaje_correo_electronico = "\n Programa: " + objart.getUtem_codigo() + "\n Pomoción: " + objart.getProm_codigo() + "\n Módulo: " + objart.getNombre_mod() + "\n Fecha: " + objart.getFd_fecha2() + "\n Hora inicial:" + objart.getFd_hora_i() + "\t Hora final: " + objart.getFd_hora_f() + "\n Aula: " + objart.getNumero() + "\t Bloque: " + objart.getBloque() + "\nNota: Para confirmar su horario esté pendiente de su correo o ingrese al sistema de consultas de horarios de clases.";

            }
            enviomails.enviarcorreos_automaticos(promocion, "Modificación del Horario del Clases:" + objart.getUtem_codigo(), mensaje_correo_electronico, lista_correos_electronicos_modificacion);


        } catch (Exception e) {
            System.out.println("Error al obtener la lista de datos" + e.getMessage());
        }
    }

    //aula
    public ArrayList<entiv_aula> getAula() {
        ArrayList listae = new ArrayList();
        v.crs = comboaula.getListacomboaula();
        try {
            entiv_aula objart;
            while (v.crs.next()) {
                objart = new entiv_aula();
                objart.setAu_codigo(v.crs.getString("au_codigo"));
                objart.setAu_bloque(v.crs.getNString("au_bloque"));
                objart.setAu_capacidad(v.crs.getInt("au_capacidad"));
                objart.setAu_caracteristicas(v.crs.getString("au_caracteristicas"));
                objart.setAu_numero(v.crs.getString("au_nombre"));
                objart.setAu_piso(v.crs.getString("au_piso"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public List getLista_profesor_timbraron() {
        v.listaprofesor_timbraron.clear();
        try {
            v.crs2 = comboprofesor.lista_profesores_timbraron(getMostrarC());
            while (v.crs2.next()) {
                v.listaprofesor_timbraron.add(new SelectItem(v.crs2.getString("pr_cedula"), v.crs2.getString("pr_nombres")));
            }
            v.crs2.close();
        } catch (SQLException ex) {
        }
        return v.listaprofesor_timbraron;
    }
}
