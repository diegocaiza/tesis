package paq_reportes_sistema;

import paq_reportes_sistema.Vista_reportes;
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
import paq_clase_interfaz.interfaz_modulo;
import paq_clase_interfaz.interfaz_programa;
import paq_clase_interfaz.interfaz_reportes;
import paq_entidades.entiv_mo_as_uni;
import paq_entidades.entiv_variables;
import paqv_vista.Vista_campostxt;

@ManagedBean()
public class Vista_reporte_modulos_asignaturas extends Vista_campostxt {

    entiv_variables v = new entiv_variables();
    Validaciones val = new Validaciones();
    fun_calculo fu = new fun_calculo();
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
    private interfaz_programa comboprograma;
    @EJB
    private interfaz_modulo acciones_modulo;

    public Connection devolver_conexion() {
        return acciones.conexion_reportes();
    }

    public void imprimir_reporte_modulos() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_modulos_pdf(txtcodigo, "reporte_solo_modulos.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_modulos_excel(txtcodigo, "reporte_solo_modulos.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();

    }

    public List getListaprograma() {
        v.listaprograma2.clear();
        try {
            v.crs2 = comboprograma.getcomboprograma();
            while (v.crs2.next()) {
                v.listaprograma2.add(new SelectItem(v.crs2.getString("pp_codigo"), v.crs2.getString("pp_nombre")));
            }
            v.crs2.close();
        } catch (SQLException ex) {
        }
        return v.listaprograma2;
    }

    public String devolvertabla_e() {
        return txtcodigo;
    }

    public String devolver_codigo_programa() {
        return txtcodigo_programa;
    }

    public ArrayList<entiv_mo_as_uni> getLista1() {
        ArrayList listae = new ArrayList();
        v.crs = acciones_modulo.lista_modulos_por_programa(txtcodigo);

        try {
            entiv_mo_as_uni objart;
            while (v.crs.next()) {
                objart = new entiv_mo_as_uni();
                objart.setNombre(v.crs.getString("mod_nombre"));
                objart.setCreditos(v.crs.getInt("mod_numerocreditos"));
                objart.setTipo(v.crs.getString("ti_descripcion"));

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
        v.crs2 = acciones_modulo.lista_asignaturas_por_programa(txtcodigo_programa);

        try {
            entiv_mo_as_uni objart;
            while (v.crs2.next()) {
                objart = new entiv_mo_as_uni();
                objart.setNombre(v.crs2.getString("asig_nombre"));
                objart.setCreditos(v.crs2.getInt("asig_numerocreditos"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public void reporte_modulos_excel(String codigo, String reporte_jasper) {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("cod_programa", codigo);
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

    public void reporte_modulos_pdf(String cod_programa, String reporte_jasper) {
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
        parametros.put("cod_programa", cod_programa);
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
    //////////////////////////////////////////////////////////////////////

    public void imprimir_reporte_asignaturas() {
        if ("pdf".equals(val.formato_reporte(txtipo))) {
            reporte_modulos_pdf(txtcodigo_programa, "reporte_asignaturas.jasper");
        } else if ("excel".equals(val.formato_reporte(txtipo))) {
            reporte_modulos_excel(txtcodigo_programa, "reporte_asignaturas.jasper");
        } else {
            mensaje = "Escoja un Formato..";
        }
        acciones.cerrar_conexion_reportes();
    }

    public void reporte_asignaturas_excel(String reporte_jasper) {
        Connection cc = devolver_conexion();
        String fichero = "C:\\temp\\reporte.xls";
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
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

    public void reporte_asignaturas_pdf(String reporte_jasper) {
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
