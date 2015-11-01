<%-- Document : Reportes Created on : 08-ene-2009, 20:30:51 Author : Geniz --%>

<!DOCTYPE html>
/*importamos las librerías de JasperReports*/ 
<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.sql.*" %> 



<% /*
     * Parametros para realizar la conexión
     */ Connection conexion;
    Class.forName("oracle.jdbc.OracleDriver").newInstance();
    conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "diego"); /*
     * Establecemos la ruta del reporte
     */ File reportFile = new File(application.getRealPath("reportes/usuario.jasper")); /*
     * No enviamos parámetros porque nuestro reporte no los necesita asi que
     * escriba cualquier cadena de texto ya que solo seguiremos el formato del
     * método runReportToPdf
     */ Map parameters = new HashMap();
    String nombrep = "codigoaula";
    String c1=request.getParameter("campo1");

    parameters.put(nombrep, c1); /*
     * Enviamos la ruta del reporte, los parámetros y la conexión(objeto
     * Connection)
     */ byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion); /*
     * Indicamos que la respuesta va a ser en formato PDF
     */ response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream ouputStream = response.getOutputStream();
    ouputStream.write(bytes, 0, bytes.length); /*
     * Limpiamos y cerramos flujos de salida
     */ ouputStream.flush();
    ouputStream.close();%>
