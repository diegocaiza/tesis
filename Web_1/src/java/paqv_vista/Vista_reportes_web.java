package paqv_vista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_promocion;
import paq_clase_interfaz.interfaz_reportes;
import paq_entidades.entiv_variables;
import paq_reportes_sistema.Vista_reportes;

@ManagedBean()
@ViewScoped
public class Vista_reportes_web extends Vista_campostxt {

    Vista_reportes reportes = new Vista_reportes();
    fun_calculo fu = new fun_calculo();
    entiv_variables v = new entiv_variables();
    @EJB
    private interfaz_promocion acciones;
    @EJB
    private interfaz_reportes reportes_con;

    public Connection devolver_conexion() {
        return reportes_con.conexion_reportes();
    }

    public List getListapromocion() {
        listageneral.clear();
        try {
            v.crs3 = acciones.getListacombopromocion();
            while (v.crs3.next()) {
                listageneral.add(new SelectItem(v.crs3.getString("prom_codigo"), v.crs3.getString("pro_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
            mensaje = acciones.getmensajei();
        }
        return listageneral;
    }

    public void reporte_asistencia_profesor_pdf() {
    }
}
