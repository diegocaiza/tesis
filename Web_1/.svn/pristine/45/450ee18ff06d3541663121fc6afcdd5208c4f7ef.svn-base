package Reportes_sistema;

import net.sf.jasperreports.engine.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import paq_clase_interfaz.interfaz_reportes;

@ManagedBean()
@ViewScoped
public class Vista_reportes1 {
    Connection conexion;
    @EJB
    private interfaz_reportes acciones;
    
    public Connection devolver_conexion()
    {
        return acciones.conexion_reportes();
    }
    
    public void imprimir_reporte() throws JRException
    {
        JasperReport reporte = (JasperReport) JRLoader.loadObject("Reportes_sistema/ventas.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, devolver_conexion());
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
        exporter.exportReport();
    }
    
    
}
