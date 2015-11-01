package paqv_vista;

import java.sql.SQLException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import paq_clase_interfaz.interfaz_alumno;
import paq_clase_interfaz.interfaz_tomalistaalumnos;
import paq_entidades.entiv_alumno;
import paq_entidades.entiv_variables;
import java.text.ParseException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import paq_calculo.Validaciones;
import paq_calculo.fun_calculo;

@ManagedBean()
@ViewScoped
public class Vista_tomalistaalumno extends entiv_alumno {

    @ManagedProperty("#{vista_usuario}")
    private Vista_usuario vistaUsuario;
    public String mostrarC;
    private ArrayList<entiv_alumno> alumnos;
    private entiv_alumno[] selected;

    public entiv_alumno[] getSelected() {
        return selected;
    }

    public void setSelected(entiv_alumno[] selected) {
        this.selected = selected;
    }

    public Vista_usuario getVistaUsuario() {
        return vistaUsuario;
    }

    public void setVistaUsuario(Vista_usuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }

    public String getMostrarC() {

        mostrarC = vistaUsuario.getTxtnick();
        Devolver_timbre(mostrarC);
        return mostrarC;
    }

    public void setMostrarC(String mostrarC) {
        this.mostrarC = mostrarC;
    }
    Integer to = null;
    private int Pruebas_tomalistaalumno;
    public String txtcodigo = "";
    Integer txtestado1 = null;
    public String txtnombres = "";
    Integer txtestado2 = null;
    public String hora_e = "";
    public String hora_s = "";
    public String txtobservacion = "";
    Validaciones val = new Validaciones();
    Vista_campostxt campos = new Vista_campostxt();
    public HtmlDataTable tabla;
    private entiv_alumno entidad;
    ArrayList listae = new ArrayList();
    ArrayList listae2 = new ArrayList();
    @EJB
    private interfaz_alumno listar;
    @EJB
    private interfaz_tomalistaalumnos acciones;
    entiv_variables v = new entiv_variables();
    private ArrayList<entiv_alumno> lista = new ArrayList<entiv_alumno>() {
    };
    fun_calculo fu = new fun_calculo();

    public Vista_tomalistaalumno() {
        entidad = new entiv_alumno();
    }

    public void cargarLista() {
        entidad = new entiv_alumno(txtcodigo, txtestado1, txtestado2, txtobservacion);
        this.lista.add(entidad);
        to = lista.size();
    }

    public void modificarLista() {
        entidad.setCodigo(this.txtcodigo);
        entidad.setEstado1(this.txtestado1);
        entidad.setEstado2(this.txtestado2);
        entidad.setObservacion(this.txtobservacion);
        try {
            this.lista.set(Pruebas_tomalistaalumno, entidad);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void seleccionLista() throws ParseException {
        entidad = (entiv_alumno) tabla.getRowData();
        this.txtcodigo = entidad.getCodigo();
        this.txtestado1 = 1;
        this.txtestado2 = entidad.getEstado2();
        this.txtobservacion = "sin observación";
        cargarLista();
    }

    public void seleccionLista2() throws ParseException {
        entidad = (entiv_alumno) tabla.getRowData();
        this.txtcodigo = entidad.getCodigo();
        this.txtestado1 = entidad.getEstado1();
        this.txtestado2 = 1;
        this.txtobservacion = entidad.getObservacion();
        cargarLista();
    }

    public void seleccionLista3(String cod, Integer estado1, Integer estado2,String observacion,String nombres){
        this.txtcodigo = cod;
        this.txtestado1 = estado1;
        this.txtestado2 = estado2;
        this.txtobservacion = observacion;
        this.txtnombres=nombres;
    }

    public void modificar() {
        if (val.campos(txtcodigo) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, campos.mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txtestado1, txtestado2, txtobservacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
            
        }

    }

    public void modificar_registro_asistencias() {
        if (val.campos2(txtcodigo, txtnombres) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Primero debe buscar un registro!", ""));
        } else {
            acciones.modificar_registro_asistencia(txtcodigo, txtestado1, txtestado2, txtobservacion);
            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
            limpiar();
        }
    }

    public void buscar_registro() {
        mensaje = "";
        if (val.campos2_1(txtcedula, txtfecha) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, campos.mensaje_requerir_datos + " " + "cedula o fecha!", ""));
        } else {
            v.crs4 = acciones.buscar(txtcedula, fu.formato_fecha(txtfecha));
            if (v.crs4.size() > 0) {
                try {
                    while (v.crs4.next()) {
                        txtcodigo = (v.crs4.getString("asis_codigo"));
                        txtnombres = (v.crs4.getString("al_nombres"));
                        txtestado1 = (v.crs4.getInt("asis_estado1"));
                        txtestado2 = (v.crs4.getInt("asis_estado2"));
                        txtobservacion = (v.crs4.getString("asis_observacion"));
                    }
                } catch (SQLException ex) {
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, campos.mensaje_sin_registros, ""));

            }
        }
    }

    public void limpiar() {
        txtnombres = "";
        txtfecha = null;
        txtcodigo = "";
        txtestado1 = null;
        txtestado2 = null;
        txtobservacion = "";
    }

    public ArrayList<entiv_alumno> getLista() {
        return lista;
    }

    public void setLista(ArrayList<entiv_alumno> lista) {
        this.lista = lista;
    }

    public HtmlDataTable getTabla() {
        return tabla;
    }

    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
    }

    public void guardar() {
        try {
            for (entiv_alumno tmpObjeto : getSelected()) {

                acciones.actualizar(tmpObjeto.getCodigo(), 1, tmpObjeto.getEstado2(), tmpObjeto.getObservacion());
                mensaje = acciones.getmensajei();

            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));

        } catch (Exception e) {

            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));

        }
        limpiar();
        lista.clear();
    }

    public void guardar_salida() {
        try {
            for (entiv_alumno tmpObjeto : getSelected()) {

                acciones.actualizar(tmpObjeto.getCodigo(), tmpObjeto.getEstado1(), 1, tmpObjeto.getObservacion());
                mensaje = acciones.getmensajei();

            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));

        } catch (Exception e) {

            mensaje = acciones.getmensajei();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));

        }
        limpiar();
        lista.clear();
    }

    public ArrayList<entiv_alumno> getAlumnos() {
        alumnos = new ArrayList<entiv_alumno>();
        this.alumnos = getLista1();
        return alumnos;
    }

    public ArrayList<entiv_alumno> getLista1() {
        comprobar_in();
        listae2.clear();
        v.crs3 = acciones.cargarlistaalumnos(getMostrarC());
        try {
            entiv_alumno objart;
            while (v.crs3.next()) {
                objart = new entiv_alumno();
                objart.setPe_apellidos(v.crs3.getString("al_apellidos"));
                objart.setCodigo(v.crs3.getString("al_cedula"));
                objart.setEstado1(v.crs3.getInt("ASIS_ESTADO1"));
                objart.setEstado2(v.crs3.getInt("ASIS_ESTADO2"));
                objart.setObservacion(v.crs3.getString("ASIS_observacion"));
//                objart.setOrden(v.crs3.getString("orden"));
                listae2.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae2;
    }

    public void Devolver_timbre(String cedula) {
        v.crs2 = acciones.devolver_timbre_profesor(cedula);
        try {
            while (v.crs2.next()) {
                hora_e = (v.crs2.getString("t_entrada2"));
                hora_s = (v.crs2.getString("t_salida2"));
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
            System.out.println("Error al obtener registro");
        }
    }

    public String devolver() {
        return getMostrarC();
    }

    public void insertar() {
        acciones.insertar(devolver(), fu.mes(), fu.getDayOfTheWeek2());
        mensaje = acciones.getmensajei();
    }

    public void comprobar_in() {

        Devolver_timbre(getMostrarC());
        v.crs1 = acciones.comprobarlistaasistencia(getMostrarC());

        if (v.crs1.size() == 0) {
            insertar();
            mensaje = "Alumnos recién enviados";

        } else if (v.crs1.size() > 0) {
           // mensaje = "Alumnos ya enviados a la otra tabla";
        } else {
            mensaje = "Error al ver alumnos";
        }

    }

    public ArrayList getListadevolverasistencias() {
        listae2.clear();
        v.crs3 = acciones.cargarlistaalumnos(getMostrarC());
        try {
            entiv_alumno objart;
            while (v.crs3.next()) {
                objart = new entiv_alumno();
                objart.setPe_apellidos(v.crs3.getString("AL_APELLIDOS"));
                objart.setCodigo(v.crs3.getString("al_codigo"));
                objart.setEstado1(v.crs3.getInt("ASIS_ESTADO1"));
                objart.setEstado2(v.crs3.getInt("ASIS_ESTADO2"));
                objart.setObservacion(v.crs3.getString("ASIS_observacion"));
                listae2.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae2;
    }

    public String getTxtcodigo() {
        return txtcodigo;
    }

    public void setTxtcodigo(String txtcodigo) {
        this.txtcodigo = txtcodigo;
    }

    public Integer getTxtestado1() {
        return txtestado1;
    }

    public void setTxtestado1(Integer txtestado1) {
        this.txtestado1 = txtestado1;
    }

    public Integer getTxtestado2() {
        return txtestado2;
    }

    public void setTxtestado2(Integer txtestado2) {
        this.txtestado2 = txtestado2;
    }

    public String getTxtobservacion() {
        return txtobservacion;
    }

    public void setTxtobservacion(String txtobservacion) {
        this.txtobservacion = txtobservacion;
    }

    public String getTxtnombres() {
        return txtnombres;
    }

    public void setTxtnombres(String txtnombres) {
        this.txtnombres = txtnombres;
    }

    public String getHora_e() {
        return hora_e;
    }

    public void setHora_e(String hora_e) {
        this.hora_e = hora_e;
    }

    public String getHora_s() {
        return hora_s;
    }

    public void setHora_s(String hora_s) {
        this.hora_s = hora_s;
    }
}
