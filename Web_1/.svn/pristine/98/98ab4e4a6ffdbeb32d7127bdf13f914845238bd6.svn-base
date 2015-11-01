package paq_documentos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class clase_funciones {

    public Date devolver_fecha_actual() {
        Date fecha_hoy = new Date();
        return fecha_hoy;
    }

    public String formato_fecha_letras(Date fecha) {
        if (fecha == null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
            String fecha_letras = formateador.format(devolver_fecha_actual());
            return fecha_letras;
        } else {
            SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
            String fecha_letras = formateador.format(fecha);
            return fecha_letras;
        }
    }
}
