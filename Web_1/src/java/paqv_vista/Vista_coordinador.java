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
import paq_clase_interfaz.interfaz_coordinador;
import paq_clase_interfaz.interfaz_profesor;
import paq_clase_interfaz.interfaz_solicitud;
import paq_entidades.entiv_persona;

@ManagedBean()
@ViewScoped
public class Vista_coordinador extends Vista_campostxt {
//
//    @ManagedProperty("#{vista_usuario}")
//    private Vista_usuario vistaUsuario;
//    public String mostrarCedula;
//
//    public Vista_usuario getVistaUsuario() {
//        return vistaUsuario;
//    }
//
//    public void setVistaUsuario(Vista_usuario vistaUsuario) {
//        this.vistaUsuario = vistaUsuario;
//    }
//
//    public String getMostrarCedula() {
//        mostrarCedula=vistaUsuario.getTxtnick();
//        return mostrarCedula;
//    }
//
//    public void setMostrarCedula(String mostrarCedula) {
//        this.mostrarCedula = mostrarCedula;
//    }

    @EJB
    private interfaz_coordinador acciones;
    @EJB
    private interfaz_solicitud solicitud;
    @EJB
    private interfaz_profesor comboprofesor;
    entiv_persona entidad = new entiv_persona();
    Validaciones valida = new Validaciones();
    
    private List<entiv_persona> filter;

    public List<entiv_persona> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_persona> filter) {
        this.filter = filter;
    }
    
    
    

    public void insertar() {
        if (valida.campos_listbox(txtcedula) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));
        } else {
            acciones.insertar(txtcedula);
            mensaje = acciones.getmensajei().substring(0, 9);
            if (codigo_oracle_duplicado.equals(mensaje)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_coordinador_repetido, ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                limpiar();
            }
        }
    }

    public void eliminar(String cod) {
//        entidad = (entiv_persona) tabla.getRowData();
//        this.txtcodigo = entidad.getPe_codigo();
        acciones.eliminar(cod);
        if (codigo_oracle_relacionado.equals(acciones.getmensajei().substring(0, 9))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_dato_relacionado, ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
            limpiar();
        }
    }

    public void modificar() {
        if (valida.campos_listbox(txtcedula) == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_requerir_datos, ""));

        } else {
            acciones.actualizar(txtcodigo, txtcedula);
            mensaje = acciones.getmensajei().substring(0, 9);
            if (codigo_oracle_duplicado.equals(mensaje)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_coordinador_repetido, ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, acciones.getmensajei(), ""));
                limpiar();
            }
        }
    }

    public void limpiar() {
        txtcodigo = "";
        txtcedula = "";
        listacoordinador=null;
    }

//    public ArrayList<entiv_solicitud> correo(){
//       listagen.clear();
//        crs = acciones.correo(getMostrarCedula());
//        try {
//            entiv_solicitud objart;
//            while (crs.next()) {
//                objart = new entiv_solicitud();
//                objart.setSoli_asunto(crs.getString("soli_asunto"));
//                objart.setSoli_fecha(crs.getDate("soli_fecha"));
//                objart.setSoli_redaccion(crs.getString("soli_redaccion"));
//                listagen.add(objart);
//            }
//        } catch (Exception e) {
//            javax.swing.JOptionPane.showMessageDialog(null,
//                    e.getMessage());
//        }
//        return listagen;
//    
//    
//    }
    public ArrayList<entiv_persona> getLista1() {
            listagen.clear();
            crs = acciones.tabla_cordinador();
            try {
                entiv_persona objart;
                while (crs.next()) {
                    objart = new entiv_persona();
                    objart.setPe_nombres(crs.getString("pr_nombres"));
                    objart.setPe_cedula(crs.getString("pr_cedula"));
                    objart.setPe_codigo(crs.getString("cop_codigo"));
                    listagen.add(objart);
                }
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        e.getMessage());
            }
            return listagen;
                }

    public void seleccionLista() throws ParseException {
        entidad = (entiv_persona) tabla.getRowData();
        this.txtcodigo = entidad.getPe_codigo();
        this.txtcedula = entidad.getPe_cedula();
    }

    public List getListacoordinador() {
        listageneral.clear();
        try {
            crs0 = comboprofesor.getcombocoordinador();
            while (crs0.next()) {
                listageneral.add(new SelectItem(crs0.getString("pr_cedula"), crs0.getString("pr_nombres")));
            }
            crs0.close();
        } catch (SQLException ex) {
        }
        return listageneral;
    }
}
