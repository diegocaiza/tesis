package paq_reportes_sistema;

import java.io.IOException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import paq_clase_interfaz.interfaz_reportes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.save.JRPdfSaveContributor.*;
import net.sf.jasperreports.view.JRViewer.*;
import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import paq_calculo.Validaciones;
import paq_calculo.fun_calculo;
import paq_clase_interfaz.interfaz_alumno;
import paq_clase_interfaz.interfaz_profesor;
import paq_clase_interfaz.interfaz_promocion;
import paq_entidades.entiv_alumno;
import paq_entidades.entiv_profesor;
import paq_entidades.entiv_variables;
import paqv_vista.Vista_campostxt;

@ManagedBean()
public class Vista_reportes extends Vista_campostxt{

    fun_calculo fu = new fun_calculo();
    entiv_variables v = new entiv_variables();
    entiv_alumno alum = new entiv_alumno();
    Validaciones val = new Validaciones();
    Connection conn = null;
    ServletOutputStream out = null;
    byte[] bytes = new byte[1000];
    int read = 0;
    FacesContext ctx = FacesContext.getCurrentInstance();
    String fileName = null;
    FileInputStream fis = null;
    HttpServletResponse response = null;
    Connection conexion;
    JasperReport reporte = null;
    
    @EJB
    private interfaz_reportes acciones;
    @EJB
    private interfaz_promocion promocion_acciones;
    @EJB
    private interfaz_alumno lis_alumno;
    @EJB
    private interfaz_profesor lis_profesor;

    private List<entiv_alumno> filter;

    public List<entiv_alumno> getFilter() {
        return filter;
    }

    public void setFilter(List<entiv_alumno> filter) {
        this.filter = filter;
    }
    
    

    public Connection devolver_conexion() {
        return acciones.conexion_reportes();
    }

    public List getListapromocion() {
        listageneral.clear();
        try {
            v.crs1 = promocion_acciones.getListacombopromocion();
            while (v.crs1.next()) {
                listageneral.add(new SelectItem(v.crs1.getString("prom_codigo"), v.crs1.getString("pro_nombre")));
            }
            v.crs1.close();
        } catch (SQLException ex) {
            mensaje = promocion_acciones.getmensajei();
        }
        return listageneral;
    }

    //
    public List getListapromocion_e() {
        listageneral.clear();
        try {
            v.crs1 = promocion_acciones.getListacombopromocion();
            while (v.crs1.next()) {
                listageneral.add(new SelectItem(v.crs1.getString("prom_codigo"), v.crs1.getString("pro_nombre")));
            }
            v.crs1.close();
        } catch (SQLException ex) {
            mensaje = promocion_acciones.getmensajei();
        }
        return listageneral;
    }

    public List getListapromocion2() {
        listaprograma.clear();
        try {
            v.crs3 = promocion_acciones.getListacombopromocion();
            while (v.crs3.next()) {
                listaprograma.add(new SelectItem(v.crs3.getString("prom_codigo"), v.crs3.getString("pro_nombre")));
            }
            v.crs3.close();
        } catch (SQLException ex) {
            mensaje = promocion_acciones.getmensajei();
        }
        return listaprograma;
    }

    public void imprimir_reporte_estadistico() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_pdf(txtpromocion, "estadisticas.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_xls(txtpromocion, "estadisticas.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();

    }

    public void imprimir_reporte_profesor() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_pdf(txtpromocion, "Docente.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_xls(txtpromocion, "Docente.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();

    }

    public void imprimir_reporte_estudiante() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_pdf(txtpromocion, "estudiante.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_xls(txtpromocion, "estudiante.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();

    }

    public void imprimir_reporte() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_asistencia_profesor_pdf(fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), txtpromocion, "timbre_profesor_bien.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_asistencia_profesor_excel(fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), txtpromocion, "timbre_profesor_bien.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();
    }

    public void imprimir_reporte_cronogramamodulos() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_cronogramamodulos_tres_parametros_pdf(fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), txtpromocion, "Cronogramamodulosagrupadomodulos.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_cronogramamodulos_tres_parametros_excel(fu.formato_fecha(txtfechainicio), fu.formato_fecha(txtfechafin), txtpromocion, "Cronogramamodulosagrupadomodulos.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();
    }

    public void limpiar() {
        txtfechainicio = null;
        txtfechafin = null;
        txtpromocion = "";
    }

    public void abrirxls(String fichero) throws IOException {
        File ficheroXLS = new File(fichero);
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(ficheroXLS);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vista_reportes.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Vista_reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while ((read = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            } catch (IOException ex) {
                Logger.getLogger(Vista_reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.flush();
            out.close();
            System.out.println("\nDescargado\n");
            ctx.responseComplete();
        }
    }

    public void reporte_tres_parametros_excel(String a, String b, String c, String reporte_jasper) {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("fecha_i", a);
        parametros.put("fecha_f", b);
        parametros.put("cod_promocion", c);
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

    public void reporte_asistencia_profesor_excel(String a, String b, String c, String reporte_jasper) {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("fecha_i", a);
        parametros.put("fecha_f", b);
        parametros.put("cod_promocion", c);
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

    public void reporte_cronogramamodulos_tres_parametros_excel(String a, String b, String c, String reporte_jasper) {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("fechai", a);
        parametros.put("fechaf", b);
        parametros.put("cod_promocion", c);
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

    public void reporte_profe_alum_xls(String a, String reporte_jasper) {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("COD_PROMOCION", a);
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

    public void reporte_tres_parametros_pdf(String a, String b, String c, String reporte_jasper) {
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
        parametros.put("cod_promocion", c);
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

    public void reporte_asistencia_profesor_pdf(String a, String b, String c, String reporte_jasper) {
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
        parametros.put("cod_promocion", c);
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

    public void reporte_cronogramamodulos_tres_parametros_pdf(String a, String b, String c, String reporte_jasper) {
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
        parametros.put("fechai", a);
        parametros.put("fechaf", b);
        parametros.put("cod_promocion", c);
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

    public void reporte_profe_alum_pdf(String a, String reporte_jasper) {
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

        parametros.put("COD_PROMOCION", a);
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

    public void imprimir_reporte_prueba() {



        reporte_asistencias_estudiantes_pdf("modu434pro201", "asistencia_forma1.jasper");
//        reporte_asistencias_estudiantes_excel(txtgrupo_modulo, "asistencia_forma1.jasper");
//        // txtgrupo_modulo="10";
////        if ("pdf".equals(val.formato_reporte(txtipo))) {
//
////        } else if ("excel".equals(val.formato_reporte(txtipo))) {
////            reporte_cronogramasignaturas_tres_parametros_excel(txtgrupo_modulo, "asistencia_forma1.jasper");
////        } else {
////            mensaje = "Escoja un Formato..";
////        }
//        acciones.cerrar_conexion_reportes();

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

    public String devolvertabla_e() {
        return txtpromocion;
    }

    //lista de estudiantes
    public ArrayList<entiv_alumno> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = lis_alumno.getLista_por_promocion(txtpromocion);
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

    public String devolvertabla_p() {
        return txtpromocion;
    }

    public ArrayList<entiv_profesor> getLista2() {
        ArrayList listaa = new ArrayList();
        v.crs = lis_profesor.getLista_por_promocion_p(txtpromocion);
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
                listaa.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listaa;
    }
    
    
     //pruebas
    public ArrayList<entiv_alumno> getListaa() {
        ArrayList listae = new ArrayList();
        v.crs = lis_alumno.getlistaa();
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
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }
    
    
    
}
