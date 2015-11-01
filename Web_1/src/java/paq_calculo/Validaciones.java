package paq_calculo;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import paq_entidades.entiv_variables;

public class Validaciones {

    entiv_variables v = new entiv_variables();
    fun_calculo f = new fun_calculo();

    public Boolean campos(String a) {
        if ("".equals(a)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos_listbox(String a) {
        if (a == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos2(String a, String b) {
        if (a == null || "".equals(b)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos_departamento(String a, String b) {
        if ("".equals(a) || "".equals(b)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos_departamentoo(String a, String b, String c) {
        if ("".equals(a) || "".equals(b) || c == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos2p(String a, String b) {
        if ("".equals(a) || b == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos3_lista_desplegable(String a, String b, String c) {
        if ("".equals(a) || b == null || c == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos_listadesplegable(String a, String b) {
        if ("".equals(a) || b == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos2_1(String a, Date b) {
        if ("".equals(a) || b == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos3(String a, String b, String c) {
        if ("".equals(a) || "".equals(b) || "".equals(c)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos3_1(String a, String b, String c, Integer d, Date e, Date f) {
        if (a == null || b == null || "".equals(c) || d == null || e == null || f == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }
public Boolean campos_promocion(String a, String b, String c, Date e, Date f) {
        if (a == null || b == null || "".equals(c) || e == null || f == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }
    public Boolean campos_solicitud(String a, String b, String c, String d) {
        if ("".equals(a) || "".equals(b) || c == null || "".equals(d)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos4(String a, String b, String c, String d) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(d)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos4_lista_desplegable(String a, String b, String c, String d) {
        if ("".equals(a) || "".equals(b) || c == null || d == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos4_1(String a, String b, Integer c, String d) {
        if ("".equals(a) || b == null || c == null || d == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos_4(String a, String b, String c, Date d) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos5(String a, String b, String c, String d, String hi) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null || "".equals(hi)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos5_1(String a, String b, String c, String d, String hi, String gen) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null || "".equals(hi) || gen == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos4_2(String a, String b, String c, Integer d) {
        if ("".equals(a) || b == null || c == null || d == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean validar_numeros_enteros(Integer numero) {
        if (numero < 1 || numero > 6) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean campos_asignatura(String a, String b, String c, Integer d) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos6(String a, String b, String c, String d, String hi, String hf) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null || "".equals(hi) || "".equals(hf)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos_cronograma(String a, String b, String c, String d, Date hi, Date hf) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null || hi == null || hf == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos6_2(String a, String b, String c, String d, String hi, String hf) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null || hi == null || "".equals(hf)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos6_1(String a, String b, String c, Integer d, String hi, String hf) {
        if (a == null || "".equals(b) || "".equals(c) || d == null || "".equals(hi) || "".equals(hf)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }
    //public void actualizar(String al_cedula,String al_correo,String al_id,String al_direccion_trabajo, String al_direccion_casa, String al_telefono1,String al_telefono2,String al_especialidad,String al_fecha_nacimiento,String al_fecha_graduacion_pregrado,String al_cursos,String al_nacionalidad,String al_genero) {
    //9 y 10

    public Boolean campos13A(String a, String b, String c, String d, String f, String g, String h, String k, Date l, Date m, String n, String o, String p) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(d) || "".equals(f) || "".equals(g) || "".equals(h) || "".equals(k) || l == null || m == null || "".equals(n) || "".equals(o) || p == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos7(String a, String b, String c, String d, String f, String g, String h) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null || "".equals(f) || "".equals(g) || "".equals(h)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos_usuario(String a, String b, String c, String d, String f, String g) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || d == null || "".equals(f) || "".equals(g)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos7_usuario(String a, String b, String c, String f, String g) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(f) || "".equals(g)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos8(String a, String b, String c, String d, String f, String g, String h, String k) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(d) || "".equals(f) || "".equals(g) || "".equals(h) || "".equals(k)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos9(String a, String b, String c, String d, String f, String g, String h, String k, String l) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(d) || "".equals(f) || "".equals(g) || "".equals(h) || "".equals(k) || "".equals(l)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos10(String a, String b, String c, String d, String f, String g, String h, String k, String l, String m) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(d) || "".equals(f) || "".equals(g) || "".equals(h) || "".equals(k) || "".equals(l) || "".equals(l)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos8_1(String a, String b, String c, String d, String f, String g, String h, Date k) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(d) || "".equals(f) || "".equals(g) || "".equals(h) || k == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos_actualizar_cronograma(String a, String b, String c, String d, String f, Date g, Date h, Date k) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(d) || "".equals(f) || g == null || h == null || k == null) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos12(String a, String b, String c, String d, String f, String g, String h, String k, String l, String m, String n, String o) {
        if ("".equals(a) || "".equals(b) || "".equals(c) || "".equals(d) || "".equals(f) || "".equals(g) || "".equals(h) || "".equals(k) || "".equals(l) || "".equals(m) || "".equals(n) || "".equals(o)) {
            v.x = false;
        } else {
            v.x = true;
        }
        return v.x;
    }

    public Boolean campos16(String a, String b, String c, String d, String f, String g, String h, String p, Date q, Date k, String l, String m, String n, String o, String r, String s) {

        if (a == null || "".equals(b) || "".equals(c) || "".equals(d) || "".equals(f) || "".equals(g) || "".equals(h) || "".equals(p) || q == null || k == null || "".equals(l) || "".equals(m) || "".equals(n) || "".equals(o) || "".equals(r) || s == null) {
            v.x = false;

        } else {
            v.x = true;
        }
        return v.x;
    }

    public String formato_reporte(String formato) {
        if ("pdf".equals(formato)) {
            return "pdf";
        } else if ("excel".equals(formato)) {
            return "excel";
        } else {
            return null;
        }
    }

    public boolean validacion_correo(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        //pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            v.x = true;
        } else {
            v.x = false;
        }
        return v.x;
    }

    public boolean validar_cedula(String cedula) {
        if (f.validadorDeCedula(cedula)) {
            v.x = true;

        } else {
            v.x = false;

        }
        return v.x;
    }
}

//"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@ [A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";