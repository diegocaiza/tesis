package paq_documentos;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import paq_clase_interfaz.interfaz_asignatura;

import paq_clase_interfaz.interfaz_cronograma;
import paq_clase_interfaz.interfaz_promocion;

@ManagedBean()
@ViewScoped
public class Vista_web_cronograma extends Vista_web_campostxt {

    Clase_reportes cls = new Clase_reportes();
    @EJB
    private interfaz_cronograma acciones;
    @EJB
    private interfaz_promocion comboprmocion;
    @EJB
    private interfaz_asignatura acciones_unidad;
    entidades_cronograma entidad = new entidades_cronograma();
    clase_funciones clsfunciones = new clase_funciones();
    //////////////
    private List<entidades_cronograma> filter;

    public List<entidades_cronograma> getFilter() {
        return filter;
    }

    public void setFilter(List<entidades_cronograma> filter) {
        this.filter = filter;
    }

    public entidades_cronograma getEntidad() {
        return entidad;
    }

    public void setEntidad(entidades_cronograma entidad) {
        this.entidad = entidad;
    }

    //////
    public ArrayList<entidades_cronograma> getLista1() {
        ArrayList listae = new ArrayList();
        crs = acciones.getLista_horario_documentos(txtpromocion);
        try {
            entidades_cronograma objart;
            while (crs.next()) {
                objart = new entidades_cronograma();
                objart.setCodigo(crs.getString("fd_codigo"));
                objart.setAsig_codigo(crs.getString("asig_codigo"));
                objart.setMod_codigo(crs.getString("mod_codigo"));
                objart.setFd_hora_i(crs.getString("fd_hora_i"));
                objart.setFd_fecha(crs.getDate("fd_fecha"));
                objart.setNombre_profesor(crs.getString("pr_nombres"));
                objart.setPr_cedula(crs.getString("pr_cedula"));
                objart.setNombre_mod(crs.getString("mod_nombre"));
                objart.setGrupo_modulo(crs.getString("fd_grupo_modulo"));
                objart.setNombre_programa_postgrado(crs.getString("pp_nombre"));
                objart.setNombre_promocion(crs.getString("pl_nombre"));
                objart.setCodigo_programa(crs.getString("pp_codigo"));
                listae.add(objart);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public List getListapromo() {
        listapromocion.clear();
        try {
            crs1 = comboprmocion.getListacombopromocion();
            while (crs1.next()) {
                listapromocion.add(new SelectItem(crs1.getString("prom_codigo"), crs1.getString("PRO_NOMBRE")));
            }
            crs1.close();
        } catch (SQLException ex) {
        }
        return listapromocion;
    }

    public List getListadirectores() {
        listadirectores.clear();
        try {
            crs2 = comboprmocion.getListacombodirectores();
            while (crs2.next()) {
                listadirectores.add(new SelectItem(crs2.getString("dep_director"), crs2.getString("dep_director")));
            }
            crs2.close();
        } catch (SQLException ex) {
        }
        return listadirectores;
    }

    public String devolvertabla() {
        return txtpromocion;
    }

    public void imprimir_reporte_carta_invitacion() {
        cls.reporte_carta_invitacion();
    }

    public void seleccionLista(SelectEvent event) {
        setNum_formulario("FORMULARIO No. 11");
        setTitulo("INVITACIÓN PARA DOCENTES DE LA ESPE");
        setTitulo1("DEPARTAMENTO DE CIENCIAS DE LA COMPUTACIÓN" + "\n" + "OFICIO No." + "\t" + "2013-048 -ESPE-e-02-mevast");
        setFecha_es(clsfunciones.devolver_fecha_actual());
        setNombre_ingeniero("Sr. Ing." + entidad.getNombre_profesor() + "," + " la Universidad de las Fuerzas Armadas - ESPE tiene el agrado de cursarle a usted la INVITACIÓN" + "\t" + "OFICIAL para que participe como docente en:");
        setUnidad_tematica("Unidad Temática:" + "\t" + entidad.getAsig_codigo() + ".");
        setAsignatura("Asignatura:" + "\t" + entidad.getNombre_mod() + ".");
        setNombre_programa_invitacion("Programa:" + " " + entidad.getNombre_programa_postgrado() + ".");
        setPromocion("Promoción:" + "\t" + entidad.getNombre_promocion() + ".");
        setFecha_clases("Se desarrollará el" + "\t" + clsfunciones.formato_fecha_letras(entidad.getFd_fecha()) + " " + "en el horario de" + "\t" + entidad.getFd_hora_i() + ".");
        setNota("NOTA: Este horario es fuera del horario de labores normales.");
        setValor("Valor de los honorarios:XXXXXXXXXXXXXXXXXXXXXXXXX  incluido retenciones de ley.");
        setRemitente("Ing." + director + "MSc." + "\n" + "Director del Departamento" + "\n" + "De Ciencias de la Computación");
        setTxtfotografia("newReport.jasper");



    }

    public void seleccionLista_aceptacion(SelectEvent event) {
        // entidad = (entidades_cronograma) tabla.getRowData();
        setNum_formularioa("FORMULARIO No. 12");
        setTituloa("CARTA DE ACEPTACIÓN");
//        setTitulo1("DEPARTAMENTO DE CIENCIAS DE LA COMPUTACIÓN");
////        setCodigo_oficio("2013-048 -ESPE-e-02-mevast");
        setFechaa(clsfunciones.devolver_fecha_actual());
        setSenores("Señores");
        setNombre_ingenieroa("Universidad de las Fuerzas Armadas - ESPE");
        setReferencia("En referencia al oficio No XXXXXXXXXXXXXXXXXX mediante el cual me invita a participar como docente/facilitador en:");
        setUnidad_tematicaa("Unidad Temática:" + "\t" + entidad.getAsig_codigo() + ".");
        setAsignaturaa("Asignatura:" + "\t" + entidad.getNombre_mod() + ".");
        setPrograma_postgradoa("Programa:" + " " + entidad.getNombre_programa_postgrado() + ".");
        setPromociona("Promoción:" + "\t" + entidad.getNombre_promocion() + ".");
        setFecha_clasesa("Que se desarrollará el" + " " + clsfunciones.formato_fecha_letras(entidad.getFd_fecha()) + " " + "en el horario de" + " " + entidad.getFd_hora_i() + "," + " fuera de mi horario de labores normales.");
        setValora("Valor de los honorarios:XXXXXXXXXXXXXXXXXXXXXXXXX incluido retenciones de ley.");
        setAceptaciona("Mediante el presente dejo constancia de mi agradecimiento y aceptación de la propuesta.");
        setRemitentea("Ing." + entidad.getNombre_profesor() + " PhD." + "\n" + "Docente Dcc");
        setTxtaceptacion("Carta_aceptacion.jasper");
    }

    public void seleccionLista_personal(SelectEvent event) {
        setNum_formulariocp("FORMULARIO No. 13");
        setTitulocp("CERTIFICADO");
        setNombre_ingenierocp("Para los fines consiguientes, Certifico que el Señor" + " " + entidad.getNombre_profesor() + ". Portador de la cédula de ciudadanía No. " + entidad.getPr_cedula() + ", labora en esta Institución de Educación Superior en calidad de XXXXXXXXX. del Departamento XXXXXXXXXXXXXXXXXXXXXXXX, de acuerdo al siguiente detalle:");
        setNota_personal("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        setAceptacioncp("Además informo que el horario de trabajo del mencionado profesional es: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        setPrograma_postgradocp("Es todo cuanto puedo certificar en forma legal, autorizando al interesado hacer uso del presente en forma y circunstancia que creyere conveniente.");
        setLugar("Sangolqui,");
        setFechacp(clsfunciones.devolver_fecha_actual());
        setRemitentecp("RESPONSABLE DE LA UNIDAD \nTALENTO HUMANO DOCENTE \n Nombres y apellidos");
        setTxtcertificado_personal("Certificado_personal.jasper");



    }

    public void seleccioncontrato(SelectEvent event) {
        setNum_formulariocont("FORMULARIO No. 14");
        setTitulocont("CONTRATO  Nro.****");
        setTitulocuerpo("Interviene en la celebración del presente contrato, por una parte, la Universidad de las Fuerzas Armadas - ESPE, legalmente representanta por el señor********************************************* en calidad de Vicerrector de Investigación y Vinculación con la Colectividad (titular o encargado) de la Universidad de las Fuerzas Armadas - ESPE, quien para efectos del presente contrato se denominará el Contratante, y por otra parte el señor****************************con cédula de nacionalidad ecuatoriana  Nro******************, quien en lo posterior se denominará el Contratado, partes legalmente capaces para contraer obligaciones, que convienen en celebrar el presente contrato, al tenor de las siguientes cláusulas:");
        setAntecendentes_contrato("PRIMERA.- ANTECEDENTES:");
        setCuerpoantecedentes_contrato("-	Orden de contrato emitida por parte del************************, mediante memorando Nro**********************de fecha********************, en el cual dispone la contratación.  \n -	   Certificación Presupuestaria Nro****************de fecha****************para el pago de Docentes del Programa MEVAST********************, partida presupuestaria**************************por Honorarios por contratos civiles de servicios con el valor de $ (números) (letras).");
        setDochabilitantes("SEGUNDA.- DOCUMENTOS HABILITANTES:");
        setCuerpohabilitantes("-	Documentos señalados en la cláusula primera.\n -	Copia de documentos personales");
        setObjeto("TERCERA.- OBJETO");
        setCuerpoobjeto_contrato("La Universidad de las Fuerzas Armadas - ESPE contrata los servicios lícitos y personales del señor************************************************, para que participe como docente del módulo *********************************************");
        setHonorarios("CUARTA.- HONORARIOS");
        setCuerpohonorarios("Por la prestación de servicios profesionales la Universidad de las Fuerzas Armadas - ESPE pagará al Contratado, la cantidad de (USD $  números ) (letras), sujetos a retenciones de ley, incluye (SI O NO )  I.V.A, valor que será cancelado al final del módulo.");
        setDuracion("QUINTA.- DURACIÓN:");
        setCuerpoduracion("El presente contrato está determinado a desarrollarse los días***************************. Con un total de********************* horas, tiempo en el cual el Contratado se compromete a participar como docente del módulo detallado en la cláusula tercera del presente contrato.");
        setConstancia("SEXTA.- CONSTANCIA:");
        setCuerpoconstancia_contrato("Conste por el presente, que la contratación se realiza por la capacidad, especialidad y experiencia del Contratado.");
        setTerminacion("SEPTIMA.- TERMINACION:");
        setCuerpoterminacion("El contrato terminará por cumplimiento del objeto y de la duración del mismo.\n El contrato podrá terminar también por las siguientes causas:\n Por renuncia del Contratado.\n Por mútuo acuerdo de las partes.\n Por las causales determinadas en la Ley.\n La Universidad de las Fuerzas Armadas - ESPE también dará por terminado este contrato unilateralmente, si el Contratado no cumpliere a satisfacción las actividades a él asignadas, resultantes de este instrumento, para cuyo efecto deberá preceder el trámite correspondiente.");
        setObligaciones("OCTAVA.- OBLIGACIONES TRIBUTARIAS:");
        setCuerpoobligaciones("El contratado se obliga a cumplir todas las obligaciones tributarias que las leyes ecuatorinas imponen, así como las que devengan del presente instrumento y la Universidad de las Fuerzas Armadas - ESPE actuará como agente de retención en los casos y montos que se determine en la Ley y reglamentos correspondientes.");
        setDefinicionterminos("NOVENA.- DEFINICIÓN E INTERPRETACION DE TÉRMINOS:");
        setCuerpoodefinicionterminos("La interpretación de términos será al tenor de la Ley y a la falta de definición legal se estará al significado técnico de los mismos y al significado natural y obvio, de conformidad con el objeto contractual y la intención de los Contratantes.");
        setControversias("DECIMA.- CONTROVERSIAS:");
        setCuerpocontroversias("En caso de suscitarse controversias en la aplicación e interpretación del presente contrato, las partes convienen en sujetar toda controversia, a la solución mediante trato directo y, para el caso de no llegar a ningún acuerdo en el plazo de quince días, se someterán a los dispuesto en la Ley de Arbitraje y Mediación, para lo cual las partes se obligan a buscar una solución con la intervención del Centro de Mediación de la Procuraduría General del Estado. En el caso de que las partes no lleguen a un acuerdo, acudirán a la vía judicial; para tal efecto se sujetan a los jueces competentes de la ciudad de Quito.");
        setNotificaciones("DECIMO PRIMERA.- NOTIFICACIONES:");
        setCuerponotificaciones("Las Notificaciones que sean necesarias realizar entre las partes durante la ejecución de este contrato se realizarán por escrito, el Contratado, en el lugar donde se desarrolla el objeto del contrato; y, a la Universidad de las Fuerzas Armadas - ESPE, en las oficinas del Rectorado de la Universidad de las Fuerzas Armadas - ESPE.");
        setRatificacion("DÉCIMO SEGUNDA.- RATIFICACIÓN:");
        setCuerporatificacion_contrato("Para constancia de todo cuanto queda estipulado, en fe de aceptación y conformidad, las partes suscriben el presente contrato, en tres ejemplares de igual tenor y valor, en Sangolquí a día/mes/ año.");
        setVicerrector_contrato("VICERRECTOS DE INVESTIGACIÓN\nVINCULACIÓN CON LA COLECTIVIDAD\nTITULAR O ENCARGADO");
        setDocenteruc_contrato("DOCENTE\nRUC.");
        setReporte_txtcontrato("Contrato.jasper");
        setReporte_txtcontrato_segunda_hoja("Contrato_segunda_hoja.jasper");
    }

    public void seleccion_informe_instructor(SelectEvent event) {
        setNum_formularioinf_instructor("FORMULARIO No. 15");
        setTitulo_inf("INFORME DEL INSTRUCTOR");
        setFecha_informe_instructor(clsfunciones.devolver_fecha_actual());
        setNombre_ingeniero_informe_instructor("NOMBRE DEL INSTRUCTOR: 	Ing. " + entidad.getNombre_profesor() + " Msc.");
        setPrograma_postgrado_informe_instructor("INSTITUCION: 		Universidad de las Fuerzas Armadas - ESPE/" + " " + entidad.getNombre_programa_postgrado() + ".");
        setNum_horas("No. de HORAS: " + numero_horas(entidad.getGrupo_modulo(), entidad.getPr_cedula()));
        setFecha_cursos_informe("FECHA DEL CURSO: " + fechas_por_grupos(entidad.getGrupo_modulo(), entidad.getPr_cedula()).toString());
        setN_asistentes_informe("Nro. ASISTENTES: " + total_estudiantes(txtpromocion));
        setN_aprobados_informe("No. APROBADOS: ");
        setAntecendentes_informe_instructor("I.	ANTECEDENTES");
        setCuerpoantecedentes_informe_instructor("Invitación formulada por el señor Director del Departamento de Ciencias de la Computación, al ingeniero " + entidad.getNombre_profesor() + ", para dictar la Unidad Tematica: " + unidad_tematica(entidad.getAsig_codigo()) + " de la Asignatura: " + entidad.getNombre_mod() + " en el Programa de " + programa_postgrado(entidad.getCodigo()) + ".");
        setDesarrollo_informe("II.	DESARROLLO");
        setCuerpoobjeto_informe("El tema se desarrolló a cabalidad conforme al syllabus establecido, en el tiempo determinado y con la entrega de notas y control de asistencias respectivo, las tareas y evaluaciones programadas también fueron cumplidas conforme lo detallado en la planificación del curso.");
        setConclusiones_informe("III.	CONCLUSIONES");
        setCuerpoconstancia_informe("El módulo fue dictado conforme al Plan de Contenidos del Programa Aprobado el mismo que fue estipulado por el Departamento de Ciencias de la Computación, cubriendo las expectativas existentes a satisfacción.");
        setRecomendaciones_informe("IV.	RECOMENDACIONES");
        setCuerporatificacio_informe("Se proceda al pago de los honorarios de acuerdo a la invitación formulada para el efecto.");
        setDocenteru_informe("Firma del Instructor\n Nombre:  Ing." + entidad.getNombre_profesor() + "MSc.\nCargo: Docente");
        setVicerrector_informe("Firma del Responsable de la Unidad\n Nombre: Ing. Fidel Castro MSc.\n Cargo:  Director DECC ");
        setReporte_informe_instructor("Informe_instructor.jasper");
    }

    public void seleccion_informe_cumplimiento_director(SelectEvent event) {
        setNum_formularioinf("FORMULARIO No. 16");
        setTituloinf("DEPARTAMENTO DE CIENCIAS DE LA COMPUTACIÓN\n INFORME DE CUMPLIMIENTO");
        setFecha__informe_cumplimiento_director(clsfunciones.devolver_fecha_actual());
        setNombre_ingeniero_informe_cumplimiento("MODULO/CLASES  DICTADAS:  " + entidad.getNombre_mod());
        setPrograma_postgrado("NOMBRE DEL DOCENTE:  Ing." + entidad.getNombre_profesor() + ".");
        setFecha_cursos("FECHAS DICTADAS:  " + fechas_por_grupos(entidad.getGrupo_modulo(), entidad.getPr_cedula()).toString());
        setN_asistentes("LUGAR: Universidad de las Fuerzas Armadas - ESPE");
        setN_aprobados("HORARIO: " + entidad.getFd_hora_i());
        setDesarrollo("DESARROLLO");
        setCuerpoobjeto("El proceso de selección del docente se realizó a través de la revisión de perfiles de docentes que constan en la Base de datos de la Unidad a fin de identificar los candidatos que reúnan el perfil y sean especializados en la temática, con los que se tomó contacto, para verificar la disponibilidad de tiempo y el interés para dictar el curso, siendo seleccionado por su formación y experiencia en el tema  al Sr. Ing. " + entidad.getNombre_profesor() + ", quien dictó La Unidad Temática de " + entidad.getNombre_mod() + " el " + fechas_por_grupos(entidad.getGrupo_modulo(), entidad.getPr_cedula()).toString() + "." + "\n\nEl conocimiento y experiencia trasmitidos por el docente en los temas tratados, fueron un aporte importante en la formación de los estudiantes\n\nEl Docente cumplió a cabalidad con los requerimientos de entrega de notas y control de asistencias respectivo, cuya documentación se archiva en la Secretaría del Programa.");
        setConclusiones("CONCLUSIÓN:");
        setCuerpoconstancia("Después de realizar el análisis de la documentación entregada por el docente, se concluye que el Ing. " + entidad.getNombre_profesor() + "., ha cumplido satisfactoriamente con el desarrollo de la asignatura " + entidad.getNombre_mod() + ", en el horario de " + entidad.getFd_hora_i() + ", el mismo que fue programado por la Universidad de las Fuerzas Armadas - ESPE.");
        setRecomendaciones("RECOMENDACIONES:");
        setCuerporatificacion("El docente ha desarrollado sin ninguna novedad, según lo acordado y es procedente al pago correspondiente de  $ xxxxxxxxx USD (monto en letras)");
        setDocenteruc("Ing. " + coordinador_programa(entidad.getCodigo_programa()) + " MSc.\nCOORDINADOR  MEVAST");
        setVicerrector("Ing. Fidel Castro D. MSc.\nDIRECTOR  DECC");
        setReporte_informe("Informe_cumplimiento_director.jasper");
    }

    public String numero_horas(String grupomodulo, String cedula) {
        try {
            crsnumero = acciones.numero_horas_por_grupo(grupomodulo, cedula);
            while (crsnumero.next()) {

                numero_total_horas = crsnumero.getString("total");
            }
            return numero_total_horas;
        } catch (SQLException ex) {
            Logger.getLogger(Vista_web_cronograma.class.getName()).log(Level.SEVERE, null, ex);
            return "cero";
        }

    }

    public List fechas_por_grupos(String grupomodulo, String cedula) {
        ArrayList<String> listae = new ArrayList<String>();
        fechasPor_grupos = acciones.fechas_cronogramas_por_grupo(grupomodulo, cedula);
        try {
            entidades_cronograma objart;
            while (fechasPor_grupos.next()) {
                objart = new entidades_cronograma();
                listae.add(fechasPor_grupos.getString("fd_fecha"));
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return listae;
    }

    public String total_estudiantes(String promocion) {
//        ArrayList<String> listae = new ArrayList<String>();
        total_estudiantes = acciones.total_estudiantes_por_promocion(promocion);
        try {
            while (total_estudiantes.next()) {
                total_estudiantes_por_promocion = total_estudiantes.getString("total");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return total_estudiantes_por_promocion;
    }

    public String devolveremitente() {
        setRemitente("Ing. " + director + " MSc." + "\n" + "Director del Departamento" + "\n" + "De Ciencias de la Computación");
        return director;
    }

    public String unidad_tematica(String cod) {
        nombre_unidad = acciones_unidad.nombre_asignatura(cod);
        try {
            while (nombre_unidad.next()) {
                nombre_generico = nombre_unidad.getString("asig_nombre");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return nombre_generico;
    }

    public String programa_postgrado(String cod) {
        nombre_prog = acciones_unidad.nombre_programa(cod);
        try {
            while (nombre_prog.next()) {
                nombre_programa = nombre_prog.getString("nombre");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return nombre_programa;
    }

    public String coordinador_programa(String cod) {
        coordinadores = acciones_unidad.nombre_coordinador(cod);
        try {
            while (coordinadores.next()) {
                nombre_coordinadores = coordinadores.getString("nombres");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return nombre_coordinadores;
    }

    public void limpiar() {
        num_formulario = "";
    }
}
