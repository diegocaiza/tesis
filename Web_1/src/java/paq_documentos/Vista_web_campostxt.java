package paq_documentos;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.html.HtmlDataTable;

public class Vista_web_campostxt {

    public List listapromocion = new ArrayList();
    public List listadirectores = new ArrayList();
    public CachedRowSetImpl crs = null;
    public CachedRowSetImpl crs1 = null;
    public CachedRowSetImpl crs2 = null;
    public CachedRowSetImpl crsnumero = null;
    public CachedRowSetImpl fechasPor_grupos = null;
    public CachedRowSetImpl total_estudiantes = null;
    public CachedRowSetImpl nombre_unidad=null;
    public CachedRowSetImpl nombre_prog=null;
    public CachedRowSetImpl coordinadores=null;
    public String total_estudiantes_por_promocion = "";
    public String nombre_generico = "";
    public String nombre_programa = "";
    public String nombre_coordinadores = "";
    public HtmlDataTable tabla;
    public String txtnombres = "";
    public String txtnombre_completo = "";
    public String txtapellidos = "";
    public String txtcedula = "";
    public String txtcodigo = "";
    public String txtfotografia = "";
    public String txtaceptacion = "";
    public String txtcertificado_personal = "";
    public String reporte_txtcontrato = "";
    public String reporte_txtcontrato_segunda_hoja = "";
    public String reporte_informe = "";
     public String reporte_informe_instructor = "";
    public String txtitulo = "";
    public String txtid = "";
    public String txtipo = "";
    public String director = "";
    public String txtcontrato = "";
    public String txtcodigo_fecha_dia = "";
    public String txtcodigo_dinamico = "";
    public String txtsoli_redaccion = "";
    public String txtsoli_asunto = "";
    public String txtgenero = "";
    public String txttotal_estudiantes = "";
    public Date txtsoli_fecha;
    public Date txtsoli_fecha_respuesta;
    public String txt_nombre_promocion = "";
    public String txtgrupo_modulo = "";
    public String txtcursos = "";
    public String txtnacionalidad = "";
    public Date txtfecha_nacimiento;
    public Date txtfechainicio;
    public Date txtfechafin;
    public Date txthorainicio = null;
    public Date txthorafin = null;
    public String txthorainicio2 = "";
    public String txthorafin2 = "";
    public String txtdescripcion;
    public Date txtfecharegistro;
    public String txtcodigo_programa = "";
    public String txtnombresmod = "";
    public String txtcodigomod = "";
    public Integer txtcreditos;
    public String txttipomod = "";
    public String txtcodpromocion = "";
    public Integer txtestudiantes;
    public String txtnumero = "";
    public String txtbloque = "";
    public String txtpiso = "";
    public String txtcodigoasignatura = "";
    public String txtaula = "";
    public String txtasignatuta = "";
    public String txtmodulo = "";
    public String txtpromocion = "";
    public String txtunidad = "";
    public String txtprofesor = "";
    public String txtdia = "";
    public String txttema = "";
    public String txtcuerpo = "";
    ArrayList listagen = new ArrayList();
    public List listaprograma = new ArrayList();
    public List listageneral = new ArrayList();
    public String titulo;
    public String nombre_programa_invitacion = "";
    //////////////informe////
    public String num_formularioinf;
    public String num_formularioinf_instructor;
    public String tituloinf;
    public String titulo_inf;
    public String institucion;
    public String num_horas;
    public String fecha_cursos;
    public String fecha_cursos_informe;
    public String n_asistentes;
    public String n_asistentes_informe;
    public String n_aprobados;
    public String n_aprobados_informe;
    public String desarrollo;
    public String desarrollo_informe;
    public String conclusiones;
    public String conclusiones_informe;
    public String recomendaciones;
      public String recomendaciones_informe;
    /////////contrato
    public String num_formulariocont;
    public String titulocont;
    public String titulocuerpo;
    public String antecendentes;
    public String antecendentes_informe_instructor;
    public String cuerpoantecedentes_informe_instructor;
    public String antecendentes_contrato;
    public String cuerpoantecedentes;
    public String cuerpoantecedentes_contrato;
    public String dochabilitantes;
    public String cuerpohabilitantes;
    public String objeto;
    public String cuerpoobjeto;
    public String cuerpoobjeto_informe;
    public String cuerpoobjeto_contrato;
    public String honorarios;
    public String cuerpohonorarios;
    public String duracion;
    public String cuerpoduracion;
    public String constancia;
    public String cuerpoconstancia;
    public String cuerpoconstancia_informe;
    public String cuerpoconstancia_contrato;
    public String terminacion;
    public String cuerpoterminacion;
    public String obligaciones;
    public String cuerpoobligaciones;
    public String definicionterminos;
    public String cuerpoodefinicionterminos;
    public String controversias;
    public String cuerpocontroversias;
    public String notificaciones;
    public String cuerponotificaciones;
    public String ratificacion;
    public String cuerporatificacion;
    public String cuerporatificacio_informe;
    public String cuerporatificacion_contrato;
    public String vicerrector;
    public String vicerrector_informe;
    public String vicerrector_contrato;
    public String docenteruc_contrato;
    public String docenteruc;
    public String docenteru_informe;
    ///////////////
    public String tituloa;
    public String titulocp;
    public String aceptacion;
    public String aceptacioncp;
    public String aceptaciona;
    public String titulo1;
    public String num_formulario;
    public String num_formularioa;
    public String num_formulariocp;
    public String codigo_oficio;
    public String referencia;
    public Date fecha;
     public Date fecha_es;
    public Date fecha_informe_instructor;
    public Date fecha__informe_cumplimiento_director;
    public Date fechaa;
    public Date fechacp;
    public String nombre_ingeniero;
    public String nombre_ingeniero_informe_instructor;
    public String nombre_ingeniero_informe_cumplimiento;
    public String nombre_ingenierocp;
    public String nombre_ingenieroa;
    public String unidad_tematica;
    public String unidad_tematicaa;
    public String asignatura;
    public String asignaturaa;
    public String programa_postgrado;
    public String programa_postgrado_informe_instructor;
    public String programa_postgrado_informe_cumplimiento;
    public String programa_postgradocp;
    public String promocion;
    public String programa_postgradoa;
    public String promociona;
    public String fecha_clases;
    public String fecha_clasesa;
    public String nota;
    public String nota_personal;
    public String valor;
    public String valora;
    public String remitente;
    public String remitentecp;
    public String remitentea;
    public String especialidad_remitente;
    public String senores;
    public String lugar;
    public String numero_total_horas;
    public String fechas_grupos;

    public String getCuerpoantecedentes_informe_instructor() {
        return cuerpoantecedentes_informe_instructor;
    }

    public void setCuerpoantecedentes_informe_instructor(String cuerpoantecedentes_informe_instructor) {
        this.cuerpoantecedentes_informe_instructor = cuerpoantecedentes_informe_instructor;
    }

    public String getAntecendentes_informe_instructor() {
        return antecendentes_informe_instructor;
    }

    public void setAntecendentes_informe_instructor(String antecendentes_informe_instructor) {
        this.antecendentes_informe_instructor = antecendentes_informe_instructor;
    }

    public String getNum_formularioinf_instructor() {
        return num_formularioinf_instructor;
    }

    public void setNum_formularioinf_instructor(String num_formularioinf_instructor) {
        this.num_formularioinf_instructor = num_formularioinf_instructor;
    }

    public String getNombre_programa_invitacion() {
        return nombre_programa_invitacion;
    }

    public void setNombre_programa_invitacion(String nombre_programa_invitacion) {
        this.nombre_programa_invitacion = nombre_programa_invitacion;
    }

    public String getVicerrector_informe() {
        return vicerrector_informe;
    }

    public void setVicerrector_informe(String vicerrector_informe) {
        this.vicerrector_informe = vicerrector_informe;
    }

    public String getDocenteru_informe() {
        return docenteru_informe;
    }

    public void setDocenteru_informe(String docenteru_informe) {
        this.docenteru_informe = docenteru_informe;
    }

    public String getCuerporatificacio_informe() {
        return cuerporatificacio_informe;
    }

    public void setCuerporatificacio_informe(String cuerporatificacio_informe) {
        this.cuerporatificacio_informe = cuerporatificacio_informe;
    }

    public String getRecomendaciones_informe() {
        return recomendaciones_informe;
    }

    public void setRecomendaciones_informe(String recomendaciones_informe) {
        this.recomendaciones_informe = recomendaciones_informe;
    }

    public String getCuerpoconstancia_informe() {
        return cuerpoconstancia_informe;
    }

    public void setCuerpoconstancia_informe(String cuerpoconstancia_informe) {
        this.cuerpoconstancia_informe = cuerpoconstancia_informe;
    }

    public String getConclusiones_informe() {
        return conclusiones_informe;
    }

    public void setConclusiones_informe(String conclusiones_informe) {
        this.conclusiones_informe = conclusiones_informe;
    }

    public String getCuerpoobjeto_informe() {
        return cuerpoobjeto_informe;
    }

    public void setCuerpoobjeto_informe(String cuerpoobjeto_informe) {
        this.cuerpoobjeto_informe = cuerpoobjeto_informe;
    }

    public String getDesarrollo_informe() {
        return desarrollo_informe;
    }

    public void setDesarrollo_informe(String desarrollo_informe) {
        this.desarrollo_informe = desarrollo_informe;
    }

    public String getN_aprobados_informe() {
        return n_aprobados_informe;
    }

    public void setN_aprobados_informe(String n_aprobados_informe) {
        this.n_aprobados_informe = n_aprobados_informe;
    }

    public String getN_asistentes_informe() {
        return n_asistentes_informe;
    }

    public void setN_asistentes_informe(String n_asistentes_informe) {
        this.n_asistentes_informe = n_asistentes_informe;
    }

    public String getFecha_cursos_informe() {
        return fecha_cursos_informe;
    }

    public void setFecha_cursos_informe(String fecha_cursos_informe) {
        this.fecha_cursos_informe = fecha_cursos_informe;
    }

    public String getVicerrector_contrato() {
        return vicerrector_contrato;
    }

    public void setVicerrector_contrato(String vicerrector_contrato) {
        this.vicerrector_contrato = vicerrector_contrato;
    }

    public String getDocenteruc_contrato() {
        return docenteruc_contrato;
    }

    public void setDocenteruc_contrato(String docenteruc_contrato) {
        this.docenteruc_contrato = docenteruc_contrato;
    }

    public String getCuerporatificacion_contrato() {
        return cuerporatificacion_contrato;
    }

    public void setCuerporatificacion_contrato(String cuerporatificacion_contrato) {
        this.cuerporatificacion_contrato = cuerporatificacion_contrato;
    }

    public String getCuerpoconstancia_contrato() {
        return cuerpoconstancia_contrato;
    }

    public void setCuerpoconstancia_contrato(String cuerpoconstancia_contrato) {
        this.cuerpoconstancia_contrato = cuerpoconstancia_contrato;
    }

    public String getCuerpoobjeto_contrato() {
        return cuerpoobjeto_contrato;
    }

    public void setCuerpoobjeto_contrato(String cuerpoobjeto_contrato) {
        this.cuerpoobjeto_contrato = cuerpoobjeto_contrato;
    }

    public String getCuerpoantecedentes_contrato() {
        return cuerpoantecedentes_contrato;
    }

    public void setCuerpoantecedentes_contrato(String cuerpoantecedentes_contrato) {
        this.cuerpoantecedentes_contrato = cuerpoantecedentes_contrato;
    }

    public String getAntecendentes_contrato() {
        return antecendentes_contrato;
    }

    public void setAntecendentes_contrato(String antecendentes_contrato) {
        this.antecendentes_contrato = antecendentes_contrato;
    }

    public String getTitulo_inf() {
        return titulo_inf;
    }

    public void setTitulo_inf(String titulo_inf) {
        this.titulo_inf = titulo_inf;
    }

    public String getNota_personal() {
        return nota_personal;
    }

    public void setNota_personal(String nota_personal) {
        this.nota_personal = nota_personal;
    }

    public String getPrograma_postgrado_informe_instructor() {
        return programa_postgrado_informe_instructor;
    }

    public void setPrograma_postgrado_informe_instructor(String programa_postgrado_informe_instructor) {
        this.programa_postgrado_informe_instructor = programa_postgrado_informe_instructor;
    }

    public String getPrograma_postgrado_informe_cumplimiento() {
        return programa_postgrado_informe_cumplimiento;
    }

    public void setPrograma_postgrado_informe_cumplimiento(String programa_postgrado_informe_cumplimiento) {
        this.programa_postgrado_informe_cumplimiento = programa_postgrado_informe_cumplimiento;
    }

    public String getNombre_ingeniero_informe_instructor() {
        return nombre_ingeniero_informe_instructor;
    }

    public void setNombre_ingeniero_informe_instructor(String nombre_ingeniero_informe_instructor) {
        this.nombre_ingeniero_informe_instructor = nombre_ingeniero_informe_instructor;
    }

    public String getNombre_ingeniero_informe_cumplimiento() {
        return nombre_ingeniero_informe_cumplimiento;
    }

    public void setNombre_ingeniero_informe_cumplimiento(String nombre_ingeniero_informe_cumplimiento) {
        this.nombre_ingeniero_informe_cumplimiento = nombre_ingeniero_informe_cumplimiento;
    }

    public Date getFecha_es() {
        return fecha_es;
    }

    public void setFecha_es(Date fecha_es) {
        this.fecha_es = fecha_es;
    }

    public Date getFecha_informe_instructor() {
        return fecha_informe_instructor;
    }

    public void setFecha_informe_instructor(Date fecha_informe_instructor) {
        this.fecha_informe_instructor = fecha_informe_instructor;
    }

    public Date getFecha__informe_cumplimiento_director() {
        return fecha__informe_cumplimiento_director;
    }

    public void setFecha__informe_cumplimiento_director(Date fecha__informe_cumplimiento_director) {
        this.fecha__informe_cumplimiento_director = fecha__informe_cumplimiento_director;
    }

    public String getReporte_informe_instructor() {
        return reporte_informe_instructor;
    }

    public void setReporte_informe_instructor(String reporte_informe_instructor) {
        this.reporte_informe_instructor = reporte_informe_instructor;
    }

    public String getReporte_informe() {
        return reporte_informe;
    }

    public void setReporte_informe(String reporte_informe) {
        this.reporte_informe = reporte_informe;
    }

    public String getDesarrollo() {
        return desarrollo;
    }

    public void setDesarrollo(String desarrollo) {
        this.desarrollo = desarrollo;
    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getNum_horas() {
        return num_horas;
    }

    public void setNum_horas(String num_horas) {
        this.num_horas = num_horas;
    }

    public String getFecha_cursos() {
        return fecha_cursos;
    }

    public void setFecha_cursos(String fecha_cursos) {
        this.fecha_cursos = fecha_cursos;
    }

    public String getN_asistentes() {
        return n_asistentes;
    }

    public void setN_asistentes(String n_asistentes) {
        this.n_asistentes = n_asistentes;
    }

    public String getN_aprobados() {
        return n_aprobados;
    }

    public void setN_aprobados(String n_aprobados) {
        this.n_aprobados = n_aprobados;
    }

    public String getReporte_txtcontrato_segunda_hoja() {
        return reporte_txtcontrato_segunda_hoja;
    }

    public void setReporte_txtcontrato_segunda_hoja(String reporte_txtcontrato_segunda_hoja) {
        this.reporte_txtcontrato_segunda_hoja = reporte_txtcontrato_segunda_hoja;
    }

    public String getReporte_txtcontrato() {
        return reporte_txtcontrato;
    }

    public void setReporte_txtcontrato(String reporte_txtcontrato) {
        this.reporte_txtcontrato = reporte_txtcontrato;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTxtcertificado_personal() {
        return txtcertificado_personal;
    }

    public void setTxtcertificado_personal(String txtcertificado_personal) {
        this.txtcertificado_personal = txtcertificado_personal;
    }

    public String getTxtaceptacion() {
        return txtaceptacion;
    }

    public void setTxtaceptacion(String txtaceptacion) {
        this.txtaceptacion = txtaceptacion;
    }

    public String getTxt_nombre_promocion() {
        return txt_nombre_promocion;
    }

    public void setTxt_nombre_promocion(String txt_nombre_promocion) {
        this.txt_nombre_promocion = txt_nombre_promocion;
    }

    public String getTxttotal_estudiantes() {
        return txttotal_estudiantes;
    }

    public void setTxttotal_estudiantes(String txttotal_estudiantes) {
        this.txttotal_estudiantes = txttotal_estudiantes;
    }

    public String getTxtnombre_completo() {
        return txtnombre_completo;
    }

    public void setTxtnombre_completo(String txtnombre_completo) {
        this.txtnombre_completo = txtnombre_completo;
    }

    public Date getTxtsoli_fecha_respuesta() {
        return txtsoli_fecha_respuesta;
    }

    public void setTxtsoli_fecha_respuesta(Date txtsoli_fecha_respuesta) {
        this.txtsoli_fecha_respuesta = txtsoli_fecha_respuesta;
    }

    public String getTxtgenero() {
        return txtgenero;
    }

    public void setTxtgenero(String txtgenero) {
        this.txtgenero = txtgenero;
    }

    public String getTxtid() {
        return txtid;
    }

    public void setTxtid(String txtid) {
        this.txtid = txtid;
    }

    public String getTxtitulo() {
        return txtitulo;
    }

    public void setTxtitulo(String txtitulo) {
        this.txtitulo = txtitulo;
    }

    public String getTxtsoli_redaccion() {
        return txtsoli_redaccion;
    }

    public void setTxtsoli_redaccion(String txtsoli_redaccion) {
        this.txtsoli_redaccion = txtsoli_redaccion;
    }

    public String getTxtsoli_asunto() {
        return txtsoli_asunto;
    }

    public void setTxtsoli_asunto(String txtsoli_asunto) {
        this.txtsoli_asunto = txtsoli_asunto;
    }

    public Date getTxtsoli_fecha() {
        return txtsoli_fecha;
    }

    public void setTxtsoli_fecha(Date txtsoli_fecha) {
        this.txtsoli_fecha = txtsoli_fecha;
    }

    public String getTxtcodigo_dinamico() {
        return txtcodigo_dinamico;
    }

    public void setTxtcodigo_dinamico(String txtcodigo_dinamico) {
        this.txtcodigo_dinamico = txtcodigo_dinamico;
    }

    public String getTxtcodigo_fecha_dia() {
        return txtcodigo_fecha_dia;
    }

    public void setTxtcodigo_fecha_dia(String txtcodigo_fecha_dia) {
        this.txtcodigo_fecha_dia = txtcodigo_fecha_dia;
    }

    public String getTxtcontrato() {
        return txtcontrato;
    }

    public void setTxtcontrato(String txtcontrato) {
        this.txtcontrato = txtcontrato;
    }

    public String getTxtipo() {
        return txtipo;
    }

    public void setTxtipo(String txtipo) {
        this.txtipo = txtipo;
    }

    public String getTxtgrupo_modulo() {
        return txtgrupo_modulo;
    }

    public void setTxtgrupo_modulo(String txtgrupo_modulo) {
        this.txtgrupo_modulo = txtgrupo_modulo;
    }

    public String getTxtapellidos() {
        return txtapellidos;
    }

    public void setTxtapellidos(String txtapellidos) {
        this.txtapellidos = txtapellidos;
    }

    public String getTxtcuerpo() {
        return txtcuerpo;
    }

    public void setTxtcuerpo(String txtcuerpo) {
        this.txtcuerpo = txtcuerpo;
    }

    public String getTxttema() {
        return txttema;
    }

    public void setTxttema(String txttema) {
        this.txttema = txttema;
    }

    public String getTxthorainicio2() {
        return txthorainicio2;
    }

    public void setTxthorainicio2(String txthorainicio2) {
        this.txthorainicio2 = txthorainicio2;
    }

    public String getTxthorafin2() {
        return txthorafin2;
    }

    public void setTxthorafin2(String txthorafin2) {
        this.txthorafin2 = txthorafin2;
    }

    public Date getTxthorainicio() {
        return txthorainicio;
    }

    public void setTxthorainicio(Date txthorainicio) {
        this.txthorainicio = txthorainicio;
    }

    public Date getTxthorafin() {
        return txthorafin;
    }

    public void setTxthorafin(Date txthorafin) {
        this.txthorafin = txthorafin;
    }

    public String getTxtunidad() {
        return txtunidad;
    }

    public void setTxtunidad(String txtunidad) {
        this.txtunidad = txtunidad;
    }

    public String getTxtdia() {
        return txtdia;
    }

    public void setTxtdia(String txtdia) {
        this.txtdia = txtdia;
    }

    public String getTxtaula() {
        return txtaula;
    }

    public void setTxtaula(String txtaula) {
        this.txtaula = txtaula;
    }

    public String getTxtasignatuta() {
        return txtasignatuta;
    }

    public void setTxtasignatuta(String txtasignatuta) {
        this.txtasignatuta = txtasignatuta;
    }

    public String getTxtmodulo() {
        return txtmodulo;
    }

    public void setTxtmodulo(String txtmodulo) {
        this.txtmodulo = txtmodulo;
    }

    public String getTxtpromocion() {
        return txtpromocion;
    }

    public void setTxtpromocion(String txtpromocion) {
        this.txtpromocion = txtpromocion;
    }

    public String getTxtprofesor() {
        return txtprofesor;
    }

    public void setTxtprofesor(String txtprofesor) {
        this.txtprofesor = txtprofesor;
    }

    public String getTxtbloque() {
        return txtbloque;
    }

    public void setTxtbloque(String txtbloque) {
        this.txtbloque = txtbloque;
    }

    public String getTxtpiso() {
        return txtpiso;
    }

    public void setTxtpiso(String txtpiso) {
        this.txtpiso = txtpiso;
    }

    public String getTxtnumero() {
        return txtnumero;
    }

    public void setTxtnumero(String txtnumero) {
        this.txtnumero = txtnumero;
    }

    public Integer getTxtestudiantes() {
        return txtestudiantes;
    }

    public void setTxtestudiantes(Integer txtestudiantes) {
        this.txtestudiantes = txtestudiantes;
    }

    public String getTxtcodigoasignatura() {
        return txtcodigoasignatura;
    }

    public void setTxtcodigoasignatura(String txtcodigoasignatura) {
        this.txtcodigoasignatura = txtcodigoasignatura;
    }

    public String getTxtcodpromocion() {
        return txtcodpromocion;
    }

    public void setTxtcodpromocion(String txtcodpromocion) {
        this.txtcodpromocion = txtcodpromocion;
    }

    public String getTxtcodigo_programa() {
        return txtcodigo_programa;
    }

    public void setTxtcodigo_programa(String txtcodigo_programa) {
        this.txtcodigo_programa = txtcodigo_programa;
    }

    public String getTxtcodigomod() {
        return txtcodigomod;
    }

    public void setTxtcodigomod(String txtcodigomod) {
        this.txtcodigomod = txtcodigomod;
    }

    public Integer getTxtcreditos() {
        return txtcreditos;
    }

    public void setTxtcreditos(Integer txtcreditos) {
        this.txtcreditos = txtcreditos;
    }

    public String getTxtnombresmod() {
        return txtnombresmod;
    }

    public void setTxtnombresmod(String txtnombresmod) {
        this.txtnombresmod = txtnombresmod;
    }

    public String getTxttipomod() {
        return txttipomod;
    }

    public void setTxttipomod(String txttipomod) {
        this.txttipomod = txttipomod;
    }

    public HtmlDataTable getTabla() {
        return tabla;
    }

    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
    }

    public String getTxtdescripcion() {
        return txtdescripcion;
    }

    public void setTxtdescripcion(String txtdescripcion) {
        this.txtdescripcion = txtdescripcion;
    }

    public Date getTxtfechafin() {
        return txtfechafin;
    }

    public void setTxtfechafin(Date txtfechafin) {
        this.txtfechafin = txtfechafin;
    }

    public Date getTxtfechainicio() {
        return txtfechainicio;
    }

    public void setTxtfechainicio(Date txtfechainicio) {
        this.txtfechainicio = txtfechainicio;
    }

    public Date getTxtfecharegistro() {
        return txtfecharegistro;
    }

    public void setTxtfecharegistro(Date txtfecharegistro) {
        this.txtfecharegistro = txtfecharegistro;
    }

    public String getTxtcedula() {
        return txtcedula;
    }

    public void setTxtcedula(String txtcedula) {
        this.txtcedula = txtcedula;
    }

    public String getTxtcodigo() {
        return txtcodigo;
    }

    public void setTxtcodigo(String txtcodigo) {
        this.txtcodigo = txtcodigo;
    }

    public String getTxtcursos() {
        return txtcursos;
    }

    public void setTxtcursos(String txtcursos) {
        this.txtcursos = txtcursos;
    }

    public Date getTxtfecha_nacimiento() {
        return txtfecha_nacimiento;
    }

    public void setTxtfecha_nacimiento(Date txtfecha_nacimiento) {
        this.txtfecha_nacimiento = txtfecha_nacimiento;
    }

    public String getTxtfotografia() {
        return txtfotografia;
    }

    public void setTxtfotografia(String txtfotografia) {
        this.txtfotografia = txtfotografia;
    }

    public String getTxtnacionalidad() {
        return txtnacionalidad;
    }

    public void setTxtnacionalidad(String txtnacionalidad) {
        this.txtnacionalidad = txtnacionalidad;
    }

    public String getTxtnombres() {
        return txtnombres;
    }

    public void setTxtnombres(String txtnombres) {
        this.txtnombres = txtnombres;
    }

    public String getNum_formularioinf() {
        return num_formularioinf;
    }

    public void setNum_formularioinf(String num_formularioinf) {
        this.num_formularioinf = num_formularioinf;
    }

    public String getTituloinf() {
        return tituloinf;
    }

    public void setTituloinf(String tituloinf) {
        this.tituloinf = tituloinf;
    }

    public String getNum_formulariocont() {
        return num_formulariocont;
    }

    public void setNum_formulariocont(String num_formulariocont) {
        this.num_formulariocont = num_formulariocont;
    }

    public String getDefinicionterminos() {
        return definicionterminos;
    }

    public void setDefinicionterminos(String definicionterminos) {
        this.definicionterminos = definicionterminos;
    }

    public String getCuerpoodefinicionterminos() {
        return cuerpoodefinicionterminos;
    }

    public void setCuerpoodefinicionterminos(String cuerpoodefinicionterminos) {
        this.cuerpoodefinicionterminos = cuerpoodefinicionterminos;
    }

    public String getControversias() {
        return controversias;
    }

    public void setControversias(String controversias) {
        this.controversias = controversias;
    }

    public String getCuerpocontroversias() {
        return cuerpocontroversias;
    }

    public void setCuerpocontroversias(String cuerpocontroversias) {
        this.cuerpocontroversias = cuerpocontroversias;
    }

    public String getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(String notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getCuerponotificaciones() {
        return cuerponotificaciones;
    }

    public void setCuerponotificaciones(String cuerponotificaciones) {
        this.cuerponotificaciones = cuerponotificaciones;
    }

    public String getRatificacion() {
        return ratificacion;
    }

    public void setRatificacion(String ratificacion) {
        this.ratificacion = ratificacion;
    }

    public String getCuerporatificacion() {
        return cuerporatificacion;
    }

    public void setCuerporatificacion(String cuerporatificacion) {
        this.cuerporatificacion = cuerporatificacion;
    }

    public String getVicerrector() {
        return vicerrector;
    }

    public void setVicerrector(String vicerrector) {
        this.vicerrector = vicerrector;
    }

    public String getDocenteruc() {
        return docenteruc;
    }

    public void setDocenteruc(String docenteruc) {
        this.docenteruc = docenteruc;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getTitulocont() {
        return titulocont;
    }

    public void setTitulocont(String titulocont) {
        this.titulocont = titulocont;
    }

    public String getTitulocuerpo() {
        return titulocuerpo;
    }

    public void setTitulocuerpo(String titulocuerpo) {
        this.titulocuerpo = titulocuerpo;
    }

    public String getAntecendentes() {
        return antecendentes;
    }

    public void setAntecendentes(String antecendentes) {
        this.antecendentes = antecendentes;
    }

    public String getCuerpoantecedentes() {
        return cuerpoantecedentes;
    }

    public void setCuerpoantecedentes(String cuerpoantecedentes) {
        this.cuerpoantecedentes = cuerpoantecedentes;
    }

    public String getDochabilitantes() {
        return dochabilitantes;
    }

    public void setDochabilitantes(String dochabilitantes) {
        this.dochabilitantes = dochabilitantes;
    }

    public String getCuerpohabilitantes() {
        return cuerpohabilitantes;
    }

    public void setCuerpohabilitantes(String cuerpohabilitantes) {
        this.cuerpohabilitantes = cuerpohabilitantes;
    }

    public String getCuerpoobjeto() {
        return cuerpoobjeto;
    }

    public void setCuerpoobjeto(String cuerpoobjeto) {
        this.cuerpoobjeto = cuerpoobjeto;
    }

    public String getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(String honorarios) {
        this.honorarios = honorarios;
    }

    public String getCuerpohonorarios() {
        return cuerpohonorarios;
    }

    public void setCuerpohonorarios(String cuerpohonorarios) {
        this.cuerpohonorarios = cuerpohonorarios;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getCuerpoduracion() {
        return cuerpoduracion;
    }

    public void setCuerpoduracion(String cuerpoduracion) {
        this.cuerpoduracion = cuerpoduracion;
    }

    public String getConstancia() {
        return constancia;
    }

    public void setConstancia(String constancia) {
        this.constancia = constancia;
    }

    public String getCuerpoconstancia() {
        return cuerpoconstancia;
    }

    public void setCuerpoconstancia(String cuerpoconstancia) {
        this.cuerpoconstancia = cuerpoconstancia;
    }

    public String getTerminacion() {
        return terminacion;
    }

    public void setTerminacion(String terminacion) {
        this.terminacion = terminacion;
    }

    public String getCuerpoterminacion() {
        return cuerpoterminacion;
    }

    public void setCuerpoterminacion(String cuerpoterminacion) {
        this.cuerpoterminacion = cuerpoterminacion;
    }

    public String getObligaciones() {
        return obligaciones;
    }

    public void setObligaciones(String obligaciones) {
        this.obligaciones = obligaciones;
    }

    public String getCuerpoobligaciones() {
        return cuerpoobligaciones;
    }

    public void setCuerpoobligaciones(String cuerpoobligaciones) {
        this.cuerpoobligaciones = cuerpoobligaciones;
    }

    public Date getFechacp() {
        return fechacp;
    }

    public void setFechacp(Date fechacp) {
        this.fechacp = fechacp;
    }

    public String getRemitentecp() {
        return remitentecp;
    }

    public void setRemitentecp(String remitentecp) {
        this.remitentecp = remitentecp;
    }

    public String getPrograma_postgradocp() {
        return programa_postgradocp;
    }

    public void setPrograma_postgradocp(String programa_postgradocp) {
        this.programa_postgradocp = programa_postgradocp;
    }

    public String getAceptacioncp() {
        return aceptacioncp;
    }

    public void setAceptacioncp(String aceptacioncp) {
        this.aceptacioncp = aceptacioncp;
    }

    public String getNombre_ingenierocp() {
        return nombre_ingenierocp;
    }

    public void setNombre_ingenierocp(String nombre_ingenierocp) {
        this.nombre_ingenierocp = nombre_ingenierocp;
    }

    public String getTitulocp() {
        return titulocp;
    }

    public void setTitulocp(String titulocp) {
        this.titulocp = titulocp;
    }

    public String getNum_formulariocp() {
        return num_formulariocp;
    }

    public void setNum_formulariocp(String num_formulariocp) {
        this.num_formulariocp = num_formulariocp;
    }

    public String getRemitentea() {
        return remitentea;
    }

    public void setRemitentea(String remitentea) {
        this.remitentea = remitentea;
    }

    public String getAceptaciona() {
        return aceptaciona;
    }

    public void setAceptaciona(String aceptaciona) {
        this.aceptaciona = aceptaciona;
    }

    public String getValora() {
        return valora;
    }

    public void setValora(String valora) {
        this.valora = valora;
    }

    public String getFecha_clasesa() {
        return fecha_clasesa;
    }

    public void setFecha_clasesa(String fecha_clasesa) {
        this.fecha_clasesa = fecha_clasesa;
    }

    public String getAsignaturaa() {
        return asignaturaa;
    }

    public void setAsignaturaa(String asignaturaa) {
        this.asignaturaa = asignaturaa;
    }

    public String getUnidad_tematicaa() {
        return unidad_tematicaa;
    }

    public void setUnidad_tematicaa(String unidad_tematicaa) {
        this.unidad_tematicaa = unidad_tematicaa;
    }

    public String getPrograma_postgradoa() {
        return programa_postgradoa;
    }

    public void setPrograma_postgradoa(String programa_postgradoa) {
        this.programa_postgradoa = programa_postgradoa;
    }

    public String getPromociona() {
        return promociona;
    }

    public void setPromociona(String promociona) {
        this.promociona = promociona;
    }

    public Date getFechaa() {
        return fechaa;
    }

    public void setFechaa(Date fechaa) {
        this.fechaa = fechaa;
    }

    public String getNombre_ingenieroa() {
        return nombre_ingenieroa;
    }

    public void setNombre_ingenieroa(String nombre_ingenieroa) {
        this.nombre_ingenieroa = nombre_ingenieroa;
    }

    public String getTituloa() {
        return tituloa;
    }

    public void setTituloa(String tituloa) {
        this.tituloa = tituloa;
    }

    public String getNum_formularioa() {
        return num_formularioa;
    }

    public void setNum_formularioa(String num_formularioa) {
        this.num_formularioa = num_formularioa;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getSenores() {
        return senores;
    }

    public void setSenores(String senores) {
        this.senores = senores;
    }

    public String getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(String aceptacion) {
        this.aceptacion = aceptacion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTitulo1() {
        return titulo1;
    }

    public void setTitulo1(String titulo1) {
        this.titulo1 = titulo1;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getEspecialidad_remitente() {
        return especialidad_remitente;
    }

    public void setEspecialidad_remitente(String especialidad_remitente) {
        this.especialidad_remitente = especialidad_remitente;
    }

    public String getNum_formulario() {
        return num_formulario;
    }

    public void setNum_formulario(String num_formulario) {
        this.num_formulario = num_formulario;
    }

    public String getCodigo_oficio() {
        return codigo_oficio;
    }

    public void setCodigo_oficio(String codigo_oficio) {
        this.codigo_oficio = codigo_oficio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre_ingeniero() {
        return nombre_ingeniero;
    }

    public void setNombre_ingeniero(String nombre_ingeniero) {
        this.nombre_ingeniero = nombre_ingeniero;
    }

    public String getUnidad_tematica() {
        return unidad_tematica;
    }

    public void setUnidad_tematica(String unidad_tematica) {
        this.unidad_tematica = unidad_tematica;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getPrograma_postgrado() {
        return programa_postgrado;
    }

    public void setPrograma_postgrado(String programa_postgrado) {
        this.programa_postgrado = programa_postgrado;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String getFecha_clases() {
        return fecha_clases;
    }

    public void setFecha_clases(String fecha_clases) {
        this.fecha_clases = fecha_clases;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
