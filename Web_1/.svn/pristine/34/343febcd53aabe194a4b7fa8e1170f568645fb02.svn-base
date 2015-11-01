package paq_documentos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


@ManagedBean()

public class Clase_reportes {
 public FacesContext ctx = FacesContext.getCurrentInstance();
    public JasperReport reporte = null;
    public FileInputStream fis = null;
    public String fileName = null;
    public ServletOutputStream out = null;
    public int read = 0;
    public byte[] bytes = new byte[1000];
    @ManagedProperty("#{vista_web_cronograma}")
   
    private Vista_web_cronograma vistacronograma;

    public Vista_web_cronograma getVistacronograma() {
        return vistacronograma;
    }

    public void setVistacronograma(Vista_web_cronograma vistacronograma) {
        this.vistacronograma = vistacronograma;
    }
 clase_funciones clf=new clase_funciones();
    public void reporte_carta_invitacion() {
        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
    }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("num_formulario", vistacronograma.getNum_formulario());
        parametros.put("titulo", vistacronograma.getTitulo());
        parametros.put("num_oficio", vistacronograma.getTitulo1());
        parametros.put("fecha",clf.formato_fecha_letras(vistacronograma.getFecha_es()));
        parametros.put("nombre_ingeniero", vistacronograma.getNombre_ingeniero());
        parametros.put("unidad", vistacronograma.getUnidad_tematica());
        parametros.put("asignatura", vistacronograma.getAsignatura());
        parametros.put("programa", vistacronograma.getNombre_programa_invitacion());
        parametros.put("promocion", vistacronograma.getPromocion());
        parametros.put("fecha_clases", vistacronograma.getFecha_clases());
        parametros.put("nota", vistacronograma.getNota());
        parametros.put("valor", vistacronograma.getValor());
        parametros.put("remitente", vistacronograma.getRemitente());

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource( vistacronograma.getTxtfotografia()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
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

    public void reporte_carta_aceptacion() {

        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("num_formulario",vistacronograma.getNum_formularioa());
        parametros.put("titulo", vistacronograma.getTituloa());
        parametros.put("fecha", clf.formato_fecha_letras( vistacronograma.getFechaa()));
        parametros.put("señores", vistacronograma.getSenores());
        parametros.put("nombre_ingeniero", vistacronograma.getNombre_ingenieroa());
        parametros.put("referencia", vistacronograma.getReferencia());
        parametros.put("unidad", vistacronograma.getUnidad_tematicaa());
        parametros.put("asignatura", vistacronograma.getAsignaturaa());
        parametros.put("programa", vistacronograma.getPrograma_postgradoa());
        parametros.put("promocion", vistacronograma.getPromociona());
        parametros.put("fecha_clases", vistacronograma.getFecha_clasesa());
        parametros.put("nota", vistacronograma.getNombre_ingenieroa());
        parametros.put("valor", vistacronograma.getValora());
        parametros.put("aceptacion", vistacronograma.getAceptaciona());
        parametros.put("remitente", vistacronograma.getRemitentea());

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(vistacronograma.getTxtaceptacion()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
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

    public void reporte_certificado_personal() {

        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("num_formulario", vistacronograma.getNum_formulariocp());
        parametros.put("titulo", vistacronograma.getTitulocp());
        parametros.put("señores", vistacronograma.getNombre_ingenierocp());
        parametros.put("nota", vistacronograma.getNota_personal());
        parametros.put("aceptacion", vistacronograma.getAceptacioncp());
        parametros.put("programa", vistacronograma.getPrograma_postgradocp());
        parametros.put("asignatura", vistacronograma.getLugar());
        parametros.put("fecha",clf.formato_fecha_letras( vistacronograma.getFechacp()));
        parametros.put("remitente", vistacronograma.getRemitentecp());

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(vistacronograma.getTxtcertificado_personal()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
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
    public void reporte_contrato() {

        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("num_formulario", vistacronograma.getNum_formulariocont());
        parametros.put("titulo", vistacronograma.getTitulocont());
        parametros.put("señores", vistacronograma.getTitulocuerpo());
        parametros.put("antecedentes", vistacronograma.getAntecendentes_contrato());
        parametros.put("cuerpoantece", vistacronograma.getCuerpoantecedentes_contrato());
        parametros.put("dochabilitantes", vistacronograma.getDochabilitantes());
        parametros.put("cuerpohabilitantes", vistacronograma.getCuerpohabilitantes());
        parametros.put("objeto",vistacronograma.getObjeto());
        parametros.put("cuerpobjeto", vistacronograma.getCuerpoobjeto_contrato());
        parametros.put("hoorarios", vistacronograma.getHonorarios());
        parametros.put("cuerpohonorarios", vistacronograma.getCuerpohonorarios());
        parametros.put("duracion", vistacronograma.getDuracion());
        parametros.put("cuerpoduracion", vistacronograma.getCuerpoduracion());
        parametros.put("constancia",vistacronograma.getConstancia());
        parametros.put("cuerpoconst", vistacronograma.getCuerpoconstancia_contrato());

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(vistacronograma.getReporte_txtcontrato()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
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
     public void reporte_contrato_segunda_hoja() {

        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("terminacion", vistacronograma.getTerminacion());
        parametros.put("cuerptermi", vistacronograma.getCuerpoterminacion());
        parametros.put("obligaciones", vistacronograma.getObligaciones());
        parametros.put("cuerpobliga", vistacronograma.getCuerpoobligaciones());
        parametros.put("defterminos", vistacronograma.getDefinicionterminos());
        parametros.put("cuerpdefterminos", vistacronograma.getCuerpoodefinicionterminos());
        parametros.put("controversias", vistacronograma.getControversias());
        parametros.put("cuerpcontroversia",vistacronograma.getCuerpocontroversias());
        parametros.put("notificaciones", vistacronograma.getNotificaciones());
        parametros.put("cuerponotificaciones", vistacronograma.getCuerponotificaciones());
        parametros.put("ratificacion", vistacronograma.getRatificacion());
        parametros.put("cuerpratifi", vistacronograma.getCuerporatificacion_contrato());
        parametros.put("vicerrector", vistacronograma.getVicerrector_contrato());
        parametros.put("docenteruc",vistacronograma.getDocenteruc_contrato());

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(vistacronograma.getReporte_txtcontrato_segunda_hoja()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
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
     public void informe_instructor() {

        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("num_formulario", vistacronograma.getNum_formularioinf_instructor());
        parametros.put("titulo", vistacronograma.getTitulo_inf());
        parametros.put("fecha",clf.formato_fecha_letras( vistacronograma.getFecha_informe_instructor()));
        parametros.put("nombre_ingeniero", vistacronograma.getNombre_ingeniero_informe_instructor());
        parametros.put("señores", vistacronograma.getPrograma_postgrado_informe_instructor());
        parametros.put("referencia", vistacronograma.getNum_horas());
        parametros.put("fecha_clases", vistacronograma.getFecha_cursos_informe());
        parametros.put("valor",vistacronograma.getN_asistentes_informe());
        parametros.put("aceptacion", vistacronograma.getN_aprobados_informe());
        parametros.put("unidad", vistacronograma.getAntecendentes_informe_instructor());
        parametros.put("asignatura", vistacronograma.getCuerpoantecedentes_informe_instructor());
        parametros.put("programa", vistacronograma.getDesarrollo_informe());
        parametros.put("promocion", vistacronograma.getCuerpoobjeto_informe());
        parametros.put("conclusiones", vistacronograma.getConclusiones_informe());
        parametros.put("cuerpoconclu", vistacronograma.getCuerpoconstancia_informe());
        parametros.put("recomendaciones", vistacronograma.getRecomendaciones_informe());
        parametros.put("cuerporecomenda", vistacronograma.getCuerporatificacio_informe());
        parametros.put("remitente", vistacronograma.getVicerrector_informe());
        parametros.put("firma_instru",vistacronograma.getDocenteru_informe());

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(vistacronograma.getReporte_informe_instructor()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
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
     public void informe_cumplimiento() {

        File ficheroPDF = new File("C:\\temp\\reporte.pdf");
        ficheroPDF.delete();
        try {
            ficheroPDF.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.clear();
        parametros.put("num_formulario", vistacronograma.getNum_formularioinf());
        parametros.put("titulo", vistacronograma.getTituloinf());
        parametros.put("fecha",clf.formato_fecha_letras( vistacronograma.getFecha__informe_cumplimiento_director()));
        parametros.put("modulos_dictados", vistacronograma.getNombre_ingeniero_informe_cumplimiento());
        parametros.put("nombre_ingeniero", vistacronograma.getPrograma_postgrado());
        
//        parametros.put("señores", vistacronograma.getPrograma_postgrado_informe_cumplimiento());
//        parametros.put("referencia", vistacronograma.getNum_horas());
        parametros.put("fecha_clases", vistacronograma.getFecha_cursos());
        parametros.put("valor",vistacronograma.getN_asistentes());
        parametros.put("aceptacion", vistacronograma.getN_aprobados());
        parametros.put("programa", vistacronograma.getDesarrollo());
        parametros.put("promocion", vistacronograma.getCuerpoobjeto());
        parametros.put("conclusiones", vistacronograma.getConclusiones());
        parametros.put("cuerpoconclu", vistacronograma.getCuerpoconstancia());
        parametros.put("recomendaciones", vistacronograma.getRecomendaciones());
        parametros.put("cuerporecomenda", vistacronograma.getCuerporatificacion());
        parametros.put("remitente", vistacronograma.getVicerrector());
        parametros.put("firma_instru",vistacronograma.getDocenteruc());

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource(vistacronograma.getReporte_informe()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
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
