package paqv_vista;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import paq_clase_interfaz.interfaz_secuencias;

@ManagedBean()
public class Vista_secuencia extends Vista_campostxt {

    @EJB
    private interfaz_secuencias insertar;
    @EJB
    private interfaz_secuencias mens;

    public void insertar() {
        insertar.insertar(txtnombres, txtsalto, txtinicio);
        mensaje = mens.getmensajei();
    }
}
