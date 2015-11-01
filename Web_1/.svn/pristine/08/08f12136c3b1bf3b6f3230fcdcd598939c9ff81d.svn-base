package paq_asistsoft;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class Asistsoft {
    Date fechasistema = new Date();
    public void registro_actividad(String cedula, String pantalla,String accion, String campo) {
       
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("c:/Asistsoft.txt", true);
            pw = new PrintWriter(fichero);

//            for (int i = 0; i < 10; i++) {
                pw.println("Cédula: " + cedula + " Fecha: " + fechasistema + " Pantalla: " + pantalla + " Acción: " +accion + " Campos " +campo);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
