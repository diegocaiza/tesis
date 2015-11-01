package paqv_vista;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_asignatura;
import paq_clase_interfaz.interfaz_modulo;
import paq_clase_interfaz.interfaz_programa;
import paq_clase_interfaz.interfaz_tipo_informacion;
import paq_clase_interfaz.interfaz_unidad_tematica;
import paq_entidades.entiv_mo_as_uni;
import paq_entidades.entiv_variables;

@ManagedBean()
@ViewScoped
public class Vista_unidad_tem extends Vista_campostxt {

    @EJB
    private interfaz_unidad_tematica acciones;
    @EJB
    private interfaz_unidad_tematica lista;
    @EJB
    private interfaz_modulo combomodulo;
    @EJB
    private interfaz_asignatura comboasignatura;
    @EJB
    private interfaz_programa comboprograma;
    @EJB
    private interfaz_tipo_informacion combotipo;
    entiv_mo_as_uni entidad = new entiv_mo_as_uni();
    Validaciones valida = new Validaciones();
    public List listaprograma2 = new ArrayList();
    public List listamodulo = new ArrayList();
    public List listasignatura = new ArrayList();
    public List listatipo = new ArrayList();
    entiv_variables v = new entiv_variables();

    public void insertar() {
        if (valida.campos3(txtnombres, txtdescripcion, txtcodigoasignatura) == false) {
            mensaje = "Se requieren datos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
        } else {
            acciones.insertar(txtnombres, txtcreditos, txtdescripcion, txtcodigoasignatura);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void eliminar() {
        entidad = (entiv_mo_as_uni) tabla.getRowData();
        this.txtcodigo = entidad.getCodigo();
        acciones.eliminar(txtcodigo);
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos3(txtnombres, txtdescripcion, txtcodigoasignatura) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.actualizar(txtcodigo, txtnombres, txtcreditos, txtdescripcion, txtcodigoasignatura);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void limpiar() {
        txtnombresmod = "";
        txtcreditos = null;
        txtdescripcion = "";
    }

    public ArrayList<entiv_mo_as_uni> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones.getLista();
        try {
            entiv_mo_as_uni objart;
            while (v.crs.next()) {
                objart = new entiv_mo_as_uni();
                objart.setCodigo(v.crs.getString("utem_codigo"));
                objart.setNombre(v.crs.getString("utem_nombre"));
                objart.setCreditos(v.crs.getInt("utem_numerocreditos"));
                objart.setTipo(v.crs.getString("ti_codigo"));
                objart.setPp_codigo(v.crs.getString("asig_codigo"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public ArrayList<entiv_mo_as_uni> getLista2() {
        ArrayList listae = new ArrayList();
        v.crs1 = lista.getListaactualizada(txtcodigoasignatura);
        try {
            entiv_mo_as_uni objart;
            while (v.crs1.next()) {
                objart = new entiv_mo_as_uni();
                objart.setCodigo(v.crs1.getString("utem_codigo"));
                objart.setNombre(v.crs1.getString("utem_nombre"));
                objart.setCreditos(v.crs1.getInt("utem_numerocreditos"));
                objart.setTipo(v.crs1.getString("ti_codigo"));
                objart.setPp_codigo(v.crs1.getString("asig_codigo"));
                objart.setTipo_nombre(v.crs1.getString("ti_descripcion"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void seleccionLista() throws ParseException {
        entidad = (entiv_mo_as_uni) tabla.getRowData();
        this.txtcodigo = entidad.getCodigo();
        this.txtnombres = entidad.getNombre();
        this.txtdescripcion = entidad.getTipo();
        this.txtcreditos = entidad.getCreditos();
        this.txtcodigoasignatura = entidad.getPp_codigo();
    }

    public List getListaprograma() {
        listaprograma.clear();
        try {
            v.crs2 = comboprograma.getcomboprograma();
            while (v.crs2.next()) {
                listaprograma.add(new SelectItem(v.crs2.getString("pp_codigo"), v.crs2.getString("pp_nombre")));
            }
            v.crs2.close();
        } catch (SQLException ex) {
        }
        return listaprograma;
    }

    public List getListamodulo() {
        listamodulo.clear();
        try {
            v.crs3 = combomodulo.getListacombomodulo(txtcodigo_programa);
            while (v.crs3.next()) {
                listamodulo.add(new SelectItem(v.crs3.getString("mod_codigo"), v.crs3.getString("mod_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
        }
        return listamodulo;
    }

    public List getListasignatura() {
        listasignatura.clear();
        try {
            v.crs4 = comboasignatura.getListacomboasignatura(txtcodigomod);
            while (v.crs4.next()) {
                listasignatura.add(new SelectItem(v.crs4.getString("asig_codigo"), v.crs4.getString("asig_nombre")));
            }
            v.crs4.close();
        } catch (SQLException ex) {
        }
        return listasignatura;
    }

    public List getListatipo() {
        listatipo.clear();
        try {
            v.crs5 = combotipo.getListacomboinformacion();
            while (v.crs5.next()) {
                listatipo.add(new SelectItem(v.crs5.getString("ti_codigo"), v.crs5.getString("ti_descripcion")));
            }
            v.crs5.close();
        } catch (SQLException ex) {
        }
        return listatipo;
    }

    public String devolver() {
        return txtcodigo_programa;
    }

    public String devolve2() {
        return txtcodigomod;
    }

    public String devolver3() {
        return txtcodigoasignatura;
    }

    public String devolver4() {
        txtcodigoasignatura = "x";
        return txtcodigoasignatura;
    }

    public String devolver5() {
        txtcodigomod = "x";
        return txtcodigomod;
    }
}
