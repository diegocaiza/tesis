package paq_reportes_sistema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import paq_calculo.Validaciones;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_cronograma;
import paq_clase_interfaz.interfaz_profesor;
import paq_clase_interfaz.interfaz_promocion;
import paq_clase_interfaz.interfaz_reportes;
import paq_entidades.entiv_cronograma;
import paq_entidades.entiv_variables;
import paqv_vista.Vista_campostxt;
import paqv_vista.Vista_cronograma;

@ManagedBean()
public class Vista_reporte_asistencias extends Vista_campostxt {

    Validaciones val = new Validaciones();
    fun_calculo fu = new fun_calculo();
    entiv_variables v = new entiv_variables();
    Connection conn = null;
    ServletOutputStream out = null;
    byte[] bytes = new byte[1000];
    int read = 0;
    FacesContext ctx = FacesContext.getCurrentInstance();
    String fileName = null;
    FileInputStream fis = null;
    HttpServletResponse response = null;
    JasperReport reporte = null;
    @EJB
    private interfaz_reportes acciones;
    @EJB
    private interfaz_promocion comboprmocion;
    @EJB
    private interfaz_cronograma acciones_cronograma;
    @ManagedProperty("#{vista_cronograma}")
    private Vista_cronograma vistacronograma;

    public Vista_cronograma getVistacronograma() {
        return vistacronograma;
    }

    public void setVistacronograma(Vista_cronograma vistacronograma) {
        this.vistacronograma = vistacronograma;
    }

    public Connection devolver_conexion() {
        return acciones.conexion_reportes();
    }

    public List getLista_grupo_modulo() {
        v.listamodulo.clear();
        try {
            v.crs2 = comboprmocion.lista_grupos_modulos();
            while (v.crs2.next()) {
                v.listamodulo.add(new SelectItem(v.crs2.getString("FD_GRUPO_MODULO"), v.crs2.getString("mod_nombre")));
            }
            v.crs2.close();
        } catch (SQLException ex) {
        }
        return v.listamodulo;
    }

    public void imprimir_reporte() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_asistencias_profesores_pdf(fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), vistacronograma.txtcedula, "timbre_profesor_bien.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_asistencia_profesor_excel(fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), vistacronograma.txtcedula, "timbre_profesor_bien.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();
    }

    public void imprimir() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_asistencias_estudiantes_pdf(txtgrupo_modulo, "asistencia_forma1.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_asistencia_estudiante_excel(txtgrupo_modulo, "asistencia_forma1.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();
    }

    public void reporte_asistencia_profesor_excel(String a, String b, String c, String reporte_jasper) {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("fecha_i", a);
        parametros.put("fecha_f", b);
        parametros.put("cedula", c);
        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(reporte_jasper));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, cc);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fichero);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.exportReport();
            try {
                abrirxls(fichero);
            } catch (IOException ex) {
                Logger.getLogger(Vista_reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JRException ex) {
            System.out.println("ERROR en JRException: " + ex);
        }
    }

    public void reporte_asistencias_profesores_pdf(String a, String b, String c, String reporte_jasper) {
        Connection cc = devolver_conexion();
        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo!");
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("fecha_i", a);
        parametros.put("fecha_f", b);
        parametros.put("cedula", c);

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(reporte_jasper));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, cc);
            JasperExportManager.exportReportToPdfFile(jasperPrint, ficheroPDF.toString());
        } catch (JRException ex) {
            System.out.println("ERROR AL GENERAR EN PDF: " + ex);
        }
        try {
            fis = new FileInputStream(ficheroPDF);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR en fileinputexception: " + ex);
        }
        fileName = ficheroPDF.getName();
        String contentType = "application/pdf";
        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        try {
            out = response.getOutputStream();
        } catch (IOException ex) {
            System.out.println("ERROR en ioexception1: " + ex);
        }
        try {
            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException ex) {
            System.out.println("ERROR en ioexception2: " + ex);
        }
        try {
            out.flush();
        } catch (IOException ex) {
            System.out.println("ERROR en ioexception3: " + ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            System.out.println("ERROR en ioexception4: " + ex);
        }
        System.out.println("\nDescargado\n");
        ctx.responseComplete();
    }

    public void reporte_asistencia_estudiante_excel(String a, String reporte_jasper) {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("grupo_modulo", a);
        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(reporte_jasper));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, cc);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fichero);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.exportReport();
            try {
                abrirxls(fichero);
            } catch (IOException ex) {
                Logger.getLogger(Vista_reporte_asistencias.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JRException ex) {
            System.out.println("ERROR en JRException: " + ex);
        }
    }

    public void reporte_asistencias_estudiantes_pdf(String a, String reporte_jasper) {
        Connection cc = devolver_conexion();
        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo!");
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("grupo_modulo", a);

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(reporte_jasper));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, cc);
            JasperExportManager.exportReportToPdfFile(jasperPrint, ficheroPDF.toString());
        } catch (JRException ex) {
            System.out.println("ERROR AL GENERAR EN PDF: " + ex);
        }
        try {
            fis = new FileInputStream(ficheroPDF);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR en fileinputexception: " + ex);
        }
        fileName = ficheroPDF.getName();
        String contentType = "application/pdf";
        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        try {
            out = response.getOutputStream();
        } catch (IOException ex) {
            System.out.println("ERROR en ioexception1: " + ex);
        }
        try {
            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException ex) {
            System.out.println("ERROR en ioexception2: " + ex);
        }
        try {
            out.flush();
        } catch (IOException ex) {
            System.out.println("ERROR en ioexception3: " + ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            System.out.println("ERROR en ioexception4: " + ex);
        }
        System.out.println("\nDescargado\n");
        ctx.responseComplete();
    }

    public ArrayList<entiv_cronograma> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones_cronograma.getLista_comprobar_cronogramas_dictados(vistacronograma.txtpromocion);
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
                objart.setFd_hora_i(v.crs.getString("fd_hora_i"));
                objart.setFd_hora_f(v.crs.getString("fd_hora_f"));
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

    public ArrayList<entiv_cronograma> getLista_profesores() {
        ArrayList listae = new ArrayList();
        v.crs = acciones_cronograma.listar_cronograma_por_profesor(vistacronograma.txtcedula);
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
                objart.setFd_hora_i(v.crs.getString("fd_hora_i"));
                objart.setFd_hora_f(v.crs.getString("fd_hora_f"));
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

    public void abrirxls(String fichero) throws IOException {
        File ficheroXLS = new File(fichero);
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(ficheroXLS);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vista_reporte_asistencias.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = new byte[1000];
        int read = 0;
        if (!ctx.getResponseComplete()) {
            String fileName = ficheroXLS.getName();
            String contentType = "application/vnd.ms-excel";
            HttpServletResponse response =
                    (HttpServletResponse) ctx.getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition",
                    "attachment;filename=\"" + fileName + "\"");
            ServletOutputStream out = null;
            try {
                out = response.getOutputStream();
            } catch (IOException ex) {
                Logger.getLogger(Vista_reporte_asistencias.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while ((read = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            } catch (IOException ex) {
                Logger.getLogger(Vista_reporte_asistencias.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.flush();
            out.close();
            System.out.println("\nDescargado\n");
            ctx.responseComplete();
        }
    }
}
