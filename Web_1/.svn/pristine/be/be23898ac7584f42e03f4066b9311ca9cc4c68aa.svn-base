package paqv_vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import paq_clase_interfaz.interfaz_usuario;
import paq_calculo.Validaciones;
import paq_clase_interfaz.interfaz_alumno;

@ManagedBean()
@ViewScoped
public class Vista_lectura_excel extends Vista_campostxt {

    Envioadjunto env = new Envioadjunto();
    String a = "";
    String b = "";
    String c = "";
    String d = "";
    String e = "";
    private StreamedContent file;  
    public Vista_lectura_excel(){
    InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/lectura27.pdf");  
        file = new DefaultStreamedContent(stream, "doc/pdf", "lectura27.pdf");  
    }
    Validaciones v = new Validaciones();

    public StreamedContent getFile() {
        return file;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
    boolean antiguo = false;
    @EJB
    private interfaz_usuario accion1;
    @EJB
    private interfaz_alumno accion2;

    public void Leer_Archivo_Excel(String Nombre_Archivo) {

        List Lista_Datos_Celda = new ArrayList();

        if (Nombre_Archivo.contains(".xlsx")) {
            GENERAR_XLSX(Nombre_Archivo, Lista_Datos_Celda);
            antiguo = false;
        } else if (Nombre_Archivo.contains(".xls")) {
            GENERAR_XLS(Nombre_Archivo, Lista_Datos_Celda);
            antiguo = true;
        }
        Imprimir_Consola2(Lista_Datos_Celda);
    }

    private void GENERAR_XLSX(String Nombre_Archivo, List Lista_Datos_Celda) {
        try {
            FileInputStream fileInputStream = new FileInputStream(
                    Nombre_Archivo);

            XSSFWorkbook Libro_trabajo = new XSSFWorkbook(fileInputStream);

            XSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);

            Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();

            while (Iterador_de_Fila.hasNext()) {

                XSSFRow Fila_hssf = (XSSFRow) Iterador_de_Fila.next();

                Iterator iterador = Fila_hssf.cellIterator();

                List Lista_celda_temporal = new ArrayList();

                while (iterador.hasNext()) {
                    XSSFCell Celda_hssf = (XSSFCell) iterador.next();
                    Lista_celda_temporal.add(Celda_hssf);
                }
                Lista_Datos_Celda.add(Lista_celda_temporal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void GENERAR_XLS(String Nombre_Archivo, List Lista_Datos_Celda) {
        try {
            FileInputStream fileInputStream = new FileInputStream(
                    Nombre_Archivo);
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            HSSFWorkbook Libro_trabajo = new HSSFWorkbook(fsFileSystem);
            HSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
            Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
            while (Iterador_de_Fila.hasNext()) {
                HSSFRow Fila_hssf = (HSSFRow) Iterador_de_Fila.next();
                Iterator iterador = Fila_hssf.cellIterator();
                List Lista_celda_temporal = new ArrayList();
                while (iterador.hasNext()) {
                    HSSFCell Celda_hssf = (HSSFCell) iterador.next();
                    Lista_celda_temporal.add(Celda_hssf);
                }
                Lista_Datos_Celda.add(Lista_celda_temporal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Imprimir_Consola2(List Datos_celdas) {
        String Valor_de_celda;

        for (int i = 1; i < Datos_celdas.size(); i++) {
            List Lista_celda_temporal_1 = (List) Datos_celdas.get(i);
            for (int j = 0; j < Lista_celda_temporal_1.size(); j++) {
                if (antiguo) {
                    HSSFCell hssfCell = (HSSFCell) Lista_celda_temporal_1.get(j);
                    if (j == 0) {
                        setA(hssfCell.toString());
                    } else if (j == 1) {
                        setB(hssfCell.toString());
                    } else if (j == 2) {
                        if (hssfCell.getCellType() == 1) {
                            setC(hssfCell.toString());
                        } else {
                            setC("x");
                        }

                    } else if (j == 3) {
                        setD(hssfCell.toString());
                    } else if (j == 4) {
                        setE(hssfCell.toString());
                        if ("x".equals(getC())) {
                            FacesMessage msg = new FacesMessage("Error", "Revise el Formato de su archivo" + "El Arvhivo debe estar en formato de texto");
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                        } else {
                            if (v.validar_cedula(getC()) == false) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_cedula + getC(), ""));
                            } else if (v.validar_cedula(getC()) == true) {
                                accion1.insertar(getA(), getB(), getC(), "tu862", "Espe.2013", getD(), getE());
                                accion2.insertarAlumno(getC(), getE(), getA(), getB(), getD(), "Activo");
                                if (codigo_fk_no_encontrado.equals(accion1.getmensajei().substring(0, 9))) {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_fk_no_encontrado + getC(), ""));
                                }
                                if (codigo_oracle_duplicado.equals(accion1.getmensajei().substring(0, 9))) {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje_error_dato_duplicado + getC(), ""));
                                }
                            }
                        }

                    }
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, accion1.getmensajei(), ""));
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            env.copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            String fileName = "C:" + File.separator + "temp" + File.separator
                    + event.getFile().getFileName();
            Leer_Archivo_Excel(fileName);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error", event.getFile().getFileName() + " El Archivo debe tener estar en formato (.xls) o (.xlsx)");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
