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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
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
import paq_clase_interfaz.interfaz_alumno;
import paq_clase_interfaz.interfaz_profesor;
import paq_clase_interfaz.interfaz_promocion;
import paq_clase_interfaz.interfaz_reportes;
import paq_entidades.entiv_alumno;
import paq_entidades.entiv_profesor;
import paq_entidades.entiv_variables;
import paqv_vista.Vista_campostxt;
import paqv_vista.Vista_cronograma;

@ManagedBean()
public class Vista_datos_reporte extends Vista_campostxt {

    entiv_variables v = new entiv_variables();
    Validaciones val = new Validaciones();
    public FacesContext ctx = FacesContext.getCurrentInstance();
    public String mostrarC;
    public ServletOutputStream out = null;
    public FileInputStream fis = null;
    public String fileName = null;
    public JasperReport reporte = null;
    public int read = 0;
    public byte[] bytes = new byte[1000];
    @ManagedProperty("#{vista_cronograma}")
    private Vista_cronograma vistacronograma;

    public Vista_cronograma getVistacronograma() {
        return vistacronograma;
    }

    public void setVistacronograma(Vista_cronograma vistacronograma) {
        this.vistacronograma = vistacronograma;
    }
    @EJB
    private interfaz_profesor lis_profesor;
    @EJB
    private interfaz_alumno lis_alumno;
    @EJB
    private interfaz_reportes acciones;

    public String devolvertabla_e() {
        return txtpromocion;
    }

    public Connection devolver_conexion() {
        return acciones.conexion_reportes();
    }

    public String devolvertabla_p() {
        return txtpromocion;
    }

    public ArrayList<entiv_alumno> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = lis_alumno.getLista_por_promocion(vistacronograma.txtpromocion);
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

    public void imprimir_reporte_profesor() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_pdf(vistacronograma.txtpromocion, "Docente.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_xls(vistacronograma.txtpromocion, "Docente.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();

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
                Logger.getLogger(Vista_datos_reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JRException ex) {
            System.out.println("ERROR en JRException: " + ex);
        }
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

    public void imprimir_reporte_estudiante() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            imprimir_reporte_estudiantes_pdf();
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_alum_xls();
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();

    }

    public void reporte_alum_xls() {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("COD_PROMOCION", vistacronograma.txtpromocion);
        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("estudiante.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, cc);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fichero);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.exportReport();
            try {
                abrirxls(fichero);
            } catch (IOException ex) {
                Logger.getLogger(Vista_datos_reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JRException ex) {
            System.out.println("ERROR en JRException: " + ex);
        }
    }

    public void imprimir_reporte_estudiantes_pdf() {
        Connection cc = devolver_conexion();
        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Vista_datos_reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("COD_PROMOCION", vistacronograma.txtpromocion);

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("estudiante.jasper"));
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

    public ArrayList<entiv_profesor> getLista2() {
        ArrayList listaa = new ArrayList();
        v.crs = lis_profesor.getLista_por_promocion_p(vistacronograma.txtpromocion);
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

    public void imprimir_reporte_estadistico() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_pdf(vistacronograma.txtpromocion, "estadisticas.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_profe_alum_xls(vistacronograma.txtpromocion, "estadisticas.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();

    }

    public void abrirxls(String fichero) throws IOException {
        File ficheroXLS = new File(fichero);
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(ficheroXLS);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vista_datos_reporte.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Vista_datos_reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while ((read = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            } catch (IOException ex) {
                Logger.getLogger(Vista_datos_reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.flush();
            out.close();
            System.out.println("\nDescargado\n");
            ctx.responseComplete();
        }
    }
}
