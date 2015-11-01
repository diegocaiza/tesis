package paqv_vista;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_cronograma;
import paq_clase_interfaz.interfaz_login;
import paq_clase_interfaz.interfaz_timbre;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_timbre extends Vista_campostxt {

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
        cronograma_por_profesor(mostrarC);
        return mostrarC;
    }

    public void setMostrarC(String mostrarC) {
        this.mostrarC = mostrarC;
    }

    public void cronograma_por_profesor(String cedula) {
       
        try {
            v.crs9 = devolverfecha.cronograma_por_profesor(cedula);
            while (v.crs9.next()) {
              txtcuerpo=v.crs9.getString("pro_nombre");
            }
            v.crs9.close();
        } catch (SQLException ex) {
            System.out.println(mensaje_error_lista + ex.getMessage());
        }

    }
    Date fecha2 = null;
    Integer di = null;
    fun_calculo fu = new fun_calculo();
    Calendar ahoraCal = Calendar.getInstance();
    entiv_variables v = new entiv_variables();
    @EJB
    private interfaz_timbre acciones;
    @EJB
    private interfaz_cronograma devolverfecha;
    @EJB
    private interfaz_login devolver;

    public void insertar() {
        txtfechainicio = new Date();
        txtdia = fu.getDayOfTheWeek2();
        txtmes = fu.mes();
        txthorainicio = new Date();
        txthorafin = new Date();
        acciones.insertar(getMostrarC(), fu.formato_fecha_hora2(txthorainicio), "", fu.formato_fecha2(txtfechainicio), txtdia, txtmes, opcion);
        mensaje = acciones.getmensajei();
    }

    public void modificar() {
        acciones.actualizarsalida(getMostrarC(), opcion);
        mensaje = acciones.getmensajei();
    }

    public String opciontimbre() {
        if (opcion.equals("1")) {
            mensaje = comprobar_in();
        } else if (opcion.equals("2")) {
            mensaje = comprobar_out();
        } else {
            mensaje = "error al timbrado";
        }
        return mensaje;
    }

    public String comprobar_in() {

        v.crs1 = acciones.comprobarentrada(getMostrarC(), fu.formato_fecha2(new Date()), opcion);
        if (v.crs1.size() > 0) {

            mensaje = "Ya existe un registro de entrada con esta credencial";
        } else {
            insertar();
//        mensaje = "Su asistencia se registro satisfactoriamente¡¡";
            mensaje = "";

        }
        return mensaje;
    }

    public String comprobar_out() {
        boolean x;
        v.crs2 = acciones.comprobarsalida(getMostrarC(), fu.formato_fecha2(new Date()), opcion);
        if (v.crs2.size() > 0) {
            mensaje = "Ya existe un registro de salida con esta credencial";

        } else {
            modificar();
            mensaje = "Su salida se registro satisfactoriamente!!";

        }
        return mensaje;
    }

    public Boolean devolverfechacronograma() {
        boolean x;
        v.crs5 = devolverfecha.getdevolverfechaprofesor(getMostrarC(), fu.formato_fecha2(new Date()));
        if (v.crs5.size() > 0) {
            //mensaje = "fecha encontrada en:'"+ fu.formato_fecha2(new Date()) +"' sin problemas para timbrar";
            insertar_codigo_cronograma(v.crs5);
            x = true;
        } else {
            // mensaje = "No puede timbrar";
            x = false;
        }
        return x;

    }

    public void insertar_codigo_cronograma(CachedRowSetImpl lista) {
        try {
            while (lista.next()) {
                acciones.insertar_cronogramas_dictados(lista.getString("fd_codigo"));

            }
        } catch (SQLException ex) {
            System.out.print("Error al recuperar los datos.");
        }
    }

    public String devolver() {
        boolean x;
        v.crs6 = devolver.getvalidar(getMostrarC());
        if (v.crs6.size() > 0) {
            x = devolverfechacronograma();
            if (x == true) {
                //insertar();

                mensaje = opciontimbre();
                return "Asistencia_estudiante";

            } else {
                mensaje = "No tiene un cronograma asignado!";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));

            }
        } else {
            mensaje = "Usted no se encuentra en la lista de profesores. Llene sus datos personales!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));

        }
        return mensaje;
    }
}
