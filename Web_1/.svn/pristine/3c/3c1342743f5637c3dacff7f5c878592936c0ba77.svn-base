package paq_calculo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import paq_entidades.entiv_variables;

public class fun_calculo {

    Random rnd = new Random();
    int numero_aleatorio;
    public Date fecha_actual = new Date();
    String mensaje = "";
    Date fechaMayor = null;
    Date fechaMenor = null;
    Transport t = null;
    public Calendar ahoraCal = Calendar.getInstance();
    Integer num;
    Integer mes = null;
    String dia = "";
    String palabrames = "";
    entiv_variables v = new entiv_variables();
    Date hoy = new Date();
    //Validación cédula
    public String cedula = "";
    public String mensaje1 = "";

    public Date devolver_fecha_actual() {
        Date fecha_actual = new Date();
        return fecha_actual;
    }

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Boolean fecha_mayor(Date fecha1, Date fecha2) {
        if (fecha1.compareTo(fecha2) > 0) {

            fechaMenor = fecha2;
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public String formato_fecha_hora(Date fecha) {
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        return ft.format(fecha);
    }

    public String formato_fecha(Date fecha) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(fecha);
    }

    public String formato_fecha2(Date fecha) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(fecha);
    }

    public String formato_fecha_hora2(Date fecha) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft.format(fecha);
    }

    public int getDayOfTheWeek(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return (cal.get(Calendar.DAY_OF_WEEK) - 1);
    }

    public String mes() {

        if (ahoraCal.get(Calendar.MONTH) == Calendar.JANUARY) {
            palabrames = "Enero";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.FEBRUARY) {
            palabrames = "Febrero";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.MARCH) {
            palabrames = "Marzo";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.APRIL) {
            palabrames = "Abril";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.MAY) {
            palabrames = "Mayo";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.JUNE) {
            palabrames = "Junio";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.JULY) {
            palabrames = "Julio";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.AUGUST) {
            palabrames = "Agosto";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.SEPTEMBER) {
            palabrames = "Septiembre";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.OCTOBER) {
            palabrames = "Octubre";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.NOVEMBER) {
            palabrames = "Noviembre";
        } else if (ahoraCal.get(Calendar.MONTH) == Calendar.DECEMBER) {
            palabrames = "Diciembre";
        } else {
            palabrames = "sin mes";
        }
        return palabrames;
    }

    public String getDayOfTheWeek2() {

        ahoraCal.setTime(hoy);
        num = (ahoraCal.get(Calendar.DAY_OF_WEEK));
        if (num == 1) {
            dia = "Domingo";

        } else if (num == 2) {
            dia = "Lunes";
        } else if (num == 3) {
            dia = "Martes";
        } else if (num == 4) {
            dia = "Miercoles";
        } else if (num == 5) {
            dia = "Jueves";
        } else if (num == 6) {
            dia = "Viernes";
        } else if (num == 7) {
            dia = "Sabado";
        } else {
            dia = "sin dia";
        }

        return dia;
    }

    public static Timestamp dateToTimeStamp(Date fecha) {
        return new Timestamp(fecha.getTime());
    }

    public static Date fechaHoraSistema() {
        return new Date(System.currentTimeMillis());
    }

    private static Date timestampToDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    public String enviar_correo(String correoorigen, String clave, String tema, String cuerpo_mensaje, List ia) {
        Transport t = null;
        int i = 0;
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", correoorigen);
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            for (i = 0; i < ia.size(); i++) {
                System.out.println(ia.get(i));
                // Construimos el mensaje
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("yo@yo.com"));
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ia.get(i).toString()));
                message.setSubject(tema);
                message.setText("Fecha:" + hoy + " \n Estimado:" + cuerpo_mensaje);
                t = session.getTransport("smtp");
                // Transport t = session.getTransport("smtp");
                t.connect(correoorigen, clave);
                t.sendMessage(message, message.getAllRecipients());
            }
            // Cierre.
            t.close();
            mensaje = "Mensaje enviado con éxito, revise su correo electrónico";
        } catch (Exception e) {
            mensaje = "Existe un error en la validación de datos. Revise sus datos";
        }
        return mensaje;
    }

    public String enviar_correo_copia(String correoorigen, String clave, String tema, String cuerpo_mensaje, List ia) {
        Transport t = null;
        int i = 0;
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", correoorigen);
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            for (i = 0; i < ia.size(); i++) {
                System.out.println(ia.get(i));
                // Construimos el mensaje

                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ia.get(i).toString()));

            }
            message.setSubject(tema);
            message.setText("Fecha:" + hoy + " \n Estimado:" + cuerpo_mensaje);
            t = session.getTransport("smtp");
            // Transport t = session.getTransport("smtp");
            t.connect(correoorigen, clave);
            t.sendMessage(message, message.getAllRecipients());
            // Cierre.
            t.close();
            mensaje = "Mensaje enviado con éxito, revise su correo electrónico";
        } catch (Exception e) {
            mensaje = "Existe un error en la validación de datos. Revise sus datos";
        }
        return mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    //Validación cédula
    public static final int NUMERO_DE_PROVINCIAS = 24;

    public boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {
            if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
                mensaje1 = "La Cédula ingresada es incorrecta debe tener 10 dígitos y no debe contener caracteres";
                return false;
            }
            int prov = Integer.parseInt(cedula.substring(0, 2));

            if (!((prov > 0) && (prov <= NUMERO_DE_PROVINCIAS))) {
                return false;
            }

            if (cedula.equals("2222222222")) {
                return false;
            }

            if (cedula.length() == 10) {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            mensaje1 = "Una excepción ocurrió en el proceso de validación";
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            mensaje1 = "La Cédula ingresada es Incorrecta";

        }
        return cedulaCorrecta;
    }

    public String numeros_aleatorios() {
        return String.valueOf(rnd.nextInt(100) * 189762);
    }
}
