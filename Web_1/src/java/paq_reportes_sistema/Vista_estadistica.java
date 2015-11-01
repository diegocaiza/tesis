package paq_reportes_sistema;

import paq_reportes_sistema.Vista_reportes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
import paq_clase_interfaz.interfaz_promocion;
import paq_clase_interfaz.interfaz_reportes;
import paq_entidades.entiv_variables;
import paqv_vista.Vista_campostxt;

@ManagedBean
public class Vista_estadistica extends Vista_campostxt {

    fun_calculo fu = new fun_calculo();
    entiv_variables v = new entiv_variables();
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
    private interfaz_promocion promo;

    public Connection devolver_conexion() {
        return acciones.conexion_reportes();
    }

    public List getListapromocion() {
        listageneral.clear();
        try {
            v.crs1 = promo.getListacombopromocion();
            while (v.crs1.next()) {
                listageneral.add(new SelectItem(v.crs1.getString("prom_codigo"), v.crs1.getString("pro_nombre")));
            }
            v.crs1.close();
        } catch (SQLException ex) {
            mensaje = promo.getmensajei();
        }
        return listageneral;
    }

    public void imprimir_reporte_estadistica() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_estadistica_pdf(txtpromocion, "estadisticas.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_estadistica_xls(txtpromocion, "estadisticas.jasper");
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

    public void reporte_estadistica_xls(String a, String reporte_jasper) {
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

    public void reporte_estadistica_pdf(String a, String reporte_jasper) {
        Connection cc = devolver_conexion();

        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
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
}
