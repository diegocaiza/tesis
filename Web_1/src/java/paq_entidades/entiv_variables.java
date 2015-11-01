package paq_entidades;

import com.sun.rowset.CachedRowSetImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class entiv_variables {

    public CachedRowSetImpl crs = null;
    public CachedRowSetImpl crs2 = null;
    public CachedRowSetImpl crs3 = null;
    public CachedRowSetImpl crs1 = null;
    public CachedRowSetImpl crs4 = null;
    public CachedRowSetImpl crs5 = null;
    public CachedRowSetImpl crs6 = null;
    public CachedRowSetImpl crs7 = null;
    public CachedRowSetImpl crs8 = null;
    public CachedRowSetImpl crs9 = null;
     public CachedRowSetImpl crs10 = null;
    public List listaprograma2 = new ArrayList();
    public List listamodulo = new ArrayList();
    public List listacorreos = new ArrayList();
    public List listatipo = new ArrayList();
    public List listacoordinador = new ArrayList();
    public List listaparalelo = new ArrayList();
    public List listaasignatura = new ArrayList();
    public List listaunidad = new ArrayList();
    public List listapromocion = new ArrayList();
    public List listaaula = new ArrayList();
    public List listaprofesor = new ArrayList();
    public List listaprofesor_timbraron = new ArrayList();
    public List listacomprobaraula = new ArrayList();
    public List listacomprobarprofesor = new ArrayList();
    public List listafechas = new ArrayList();
    public ArrayList listafechatimbres = new ArrayList();
    public ArrayList lista = new ArrayList();
    public Integer dia = null;
    public Integer mes = null;
    public String mensaje = "";
    public String[] correos;
    public boolean x;
    public Calendar fecha_fin = Calendar.getInstance();
    public Date fecha_inicio = new Date();
}
