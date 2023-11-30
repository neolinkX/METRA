<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="gob.sct.medprev.dao.TDEXPResultado"%>
<%@ page import="gob.sct.medprev.dao.TDPERDatos"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
<%@ page import="gob.sct.medprev.vo.TVEXPResultado"%>
<%@ page import="gob.sct.medprev.dao.TDEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPResultado"%>
<%@ page import="java.util.*"%>
<%   
System.out.println("ContentText --");
	TDEXPResultado dEXPResultados = new TDEXPResultado();
	TVEXPResultado vEXPResultados;
	TDPERDatos dPERDatos = new TDPERDatos();
	Vector vcEXPResultados = new Vector();
	String cExpediente = "0";
	//cExpediente = request.getParameter("id");
	cExpediente = session.getAttribute("id")+"";
	//String cInumExamen = "6";
	String cInumExamen = ""+session.getAttribute("inumExamen");
	//cInumExamen = request.getParameter("episodio");
	//String iCveServicio = "11";
	String iCveServicio = ""+session.getAttribute("resservicio");
	String iCveRama2 = ""+session.getAttribute("resrama");
	String filtro = "";
	String genero = "";
	try {
		genero = dPERDatos.SenFindBy("Select cSexo from perdatos where icveexpediente = " + cExpediente);
	} catch (Exception e) {
		genero = "";
	}
	
	
	
	

	////////////////carga de Servicio///////////////////////////////////////////
	TDEXPServicio dEXPServicio = new TDEXPServicio();
    TVEXPServicio vEXPServicio = new TVEXPServicio();
	Vector vcEXPServicio = new Vector();
	int iConcluido = 0;
	vcEXPServicio = dEXPServicio.getLConcluidoCarga(cExpediente, cInumExamen, iCveServicio);
	 if (vcEXPServicio.size() > 0) {
         vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
         iConcluido = vEXPServicio.getLConcluido();
     } else {
    	 iConcluido=-1;
     }
	///////////////////////////////////////////////////////////////////////////

	
	
	
	
	
	TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
	int transporte = 0;
	String cWhere = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente+ " "
			+ "AND INUMEXAMEN = " + cInumExamen
			+ " ORDER BY ICVEMDOTRANS DESC";

	try {
		transporte = dEXPExamAplica.RegresaInt(cWhere);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Contetransporte="+transporte);
	
	int categoria = 0;
	cWhere = "SELECT ICVECATEGORIA FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente + " "
			+ "AND INUMEXAMEN = " + cInumExamen + " "
			+ "AND ICVEMDOTRANS = " + transporte
			+ " ORDER BY ICVECATEGORIA DESC";
	try {
		categoria = dEXPExamAplica.RegresaInt(cWhere);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("categoria="+categoria);
	
	int motivo = 0;
    cWhere = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente + " "
			+ "AND INUMEXAMEN = " + cInumExamen + " "
			+ "AND ICVEMDOTRANS = " + transporte + " "
			+ "ORDER BY ICVECATEGORIA DESC";
	try {
		motivo = dEXPExamAplica.RegresaInt(cWhere);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("motivo="+motivo);
	
	int Concluido2 = 0;
    cWhere = "SELECT LCONCLUIDO FROM EXPSERVICIO "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente + " "
			+ "AND INUMEXAMEN = " + cInumExamen + " "
			+ "AND ICVESERVICIO = " + iCveServicio + " ";
	try {
		Concluido2 = dEXPExamAplica.RegresaInt(cWhere);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Concluido2="+Concluido2);
	
	if(Concluido2 > 0 ){
		filtro = " where  r.iCveServicio =  " + iCveServicio + "" + "		    and r.iCveRama     =  "
				+ iCveRama2 + " and r.ICVEEXPEDIENTE = " + cExpediente + " "
						+ "AND r.INUMEXAMEN = " + cInumExamen + " ";
	}else{
		filtro = " where                      " + "    	    r.icveproceso = 1" + "		    and r.icvemdotrans = "
				+ transporte + "" + "		    and r.icvemotivo = " + motivo + ""
				+ "		    and r.iCveServicio =  " + iCveServicio + "" + "		    and r.iCveRama     =  "
				+ iCveRama2 + "" + "		    and (s.cGenero     =   '" + genero + "' OR s.cGenero='A')  ";
	}
	
	try {
		if(Concluido2 > 0 ){
			vcEXPResultados = dEXPResultados.findResultadoSintoma2(filtro);
		}else{
			vcEXPResultados = dEXPResultados.findResultadoSintoma3(filtro);	
		}
	} catch (Exception e) {
		vcEXPResultados = new Vector();
		System.out.println("Error");
	}
	
    System.out.println(vcEXPResultados.size());
  %>  
<!-- <?php
/*
 * Arreglo con los Textos para los cuestionarios de Cardiologia
 *
*/
$q_cardio = array(
                  'cardio_5'  =>  'Lesiones Vasculares Arteriales o Venosas, AnatÃ³micas o funcionales que representen un riesgo de isquemia o embolismo pulmonar/sistÃ©mico:',
                  'cardio_6'  =>  'HipertensiÃ³n arterial sistÃ©mica no controlada (NOM 030-SSA-1999):',
                  'cardio_7'  =>  'PrÃ³tesis Valvulares',
                  'cardio_8'  =>  'Marcapasos',
                  'cardio_9'  =>  'Transplante CardÃ­aco',
                  'cardio_10' =>  'CardiopatÃ­a IsquÃ©mica',
                  'cardio_11' =>  'Injerto o Puente de Arteria Coronaria (BY PASS), o angioplastÃ­a (con/sin STENT) o antecedente de infarto al miocardio'
                );

$q_cardio_name = array(
                  'cardio_5'  =>  'car_lesiones_vasculares_arteriales_venosas',
                  'cardio_6'  =>  'car_hipertension_arterial_sistemica',
                  'cardio_7'  =>  'car_protesis_valvulares',
                  'cardio_8'  =>  'car_marcapasos',
                  'cardio_9'  =>  'car_transplante_cardiaco',
                  'cardio_10' =>  'car_cardiopatia_isquemica',
                  'cardio_11' =>  'car_injerto_arteria_coronaria'
                  );


$q_cardio_prev = array( 'cardio_prev_1'  => array('label'=>'Peso','form_help'=>''),
                        'cardio_prev_2'  => array('label'=>'Talla','form_help'=>''),
                        'cardio_prev_3'  => array('label'=>'Kg. sobrepeso','form_help'=>''),
                        'cardio_prev_4'  => array('label'=>'% sobrepeso	','form_help'=>''),
                        'cardio_prev_5'  => array('label'=>'Edad','form_help'=>'No. en aÃ±os cumplidos'),
                        'cardio_prev_6'  => array('label'=>'Ant. Fam. IAM. Edad	','form_help'=>'Edad en que le ocurriÃ³'),
                        'cardio_prev_7'  => array('label'=>'Ant. Personal IAM.','form_help'=>'NO=0;SI=hace: aÃ±os,meses.'),
                        'cardio_prev_8'  => array('label'=>'Tabaquismo','form_help'=>'NO=0 SI= No. X dÃ­a'),
                        'cardio_prev_9'  => array('label'=>'TensiÃ³n o Ansiedad	','form_help'=>'NO(0) MOD(1) ALTA(2) GRAVE(3)'),
                        'cardio_prev_10' => array('label'=>'Obesidad','form_help'=>'GRADO 1, 2, 3 O 4.'),
                        'cardio_prev_11'  => array('label'=>'Frec. Cardiaca','form_help'=>'No. latidos por minuto'),
                        'cardio_prev_12'  => array('label'=>'HipertensiÃ³n','form_help'=>'SI=1 NO=0'),
                        'cardio_prev_13'  => array('label'=>'PresiÃ³n SistÃ³lica','form_help'=>'Cifras sin decimales'),
                        'cardio_prev_14'  => array('label'=>'PresiÃ³n DiastÃ³lica	','form_help'=>'Cifras sin decimales'),
                        'cardio_prev_15'  => array('label'=>'E.C.G. Reposo	','form_help'=>'NORMAL(0) ANORMAL(1) ISQ(3)'),
                        'cardio_prev_16'  => array('label'=>'Diabetes','form_help'=>'SI (1); NO(0)'),
                        'cardio_prev_17'  => array('label'=>'Glucemia','form_help'=>'mg % Cifras sin decimales'),
                        'cardio_prev_18'  => array('label'=>'Colesterol','form_help'=>'mg % Cifras sin decimales'),
                        'cardio_prev_19'  => array('label'=>'Trigliceridos','form_help'=>'mg % Cifras sin decimales'),
                        'cardio_prev_20' => array('label'=>'Ã�cido Ãšrico','form_help'=>'mg % Cifras sin decimales'));


/*
 * Arreglo con los Textos para el Formulario de Signos Vitales
 *
 */
$q_signos = array('signos_campo1'  => array('label'=>'PresiÃ³n Arterial SistÃ³lica','form_help'=>'mmHg'),
                  'signos_campo2' => array('label'=> 'PresiÃ³n Arterial DiastÃ³lica','form_help'=>'mmHg'),
                  'signos_campo3' => array('label'=>'Frecuencia Cardiaca','form_help'=>'latidos por minuto'),
                  'signos_campo4' => array('label'=>'Frecuencia Respiratoria','form_help'=>'respiraciones por minuto'),
                  'signos_campo5' => array('label'=>'Peso','form_help'=>'Kg'),
                  'signos_campo6' => array('label'=>'Estatura','form_help'=>'cm'),
                  'signos_campo7' => array('label'=>'Temperatura','form_help'=>'grados centigrados'),
                  'signos_campo8' => array('label'=>'Indice de Masa Corporal','form_help'=>'kg/m2'),
                  'signos_campo9' => array('label'=>'% de Grasa','form_help'=>'%'),
                  'signos_campo10' => array('label'=>'Circunferencia Cintura','form_help'=>'cm'),
                  'signos_campo11' => array('label'=>'Circunferencia de Cuello','form_help'=>'cm'));

/*
 * Arreglo con los Textos para el Formulario de ExploraciÃ³n FÃ­sica
 *
 */
$q_expFis = array('exp_fis_insg1'  => array('label'=>'Cabeza:','form_help'=>''),
                  'exp_fis_insg2' => array('label'=> 'Cuero Cabelludo:','form_help'=>''),
                  'exp_fis_insg3' => array('label'=>'Edad CronolÃ³gica:','form_help'=>''),
                  'exp_fis_insg4' => array('label'=>'Piel:','form_help'=>''),
                  'exp_fis_insg5' => array('label'=>'ConstituciÃ³n Corporal:','form_help'=>''),
                  'exp_fis_insg6' => array('label'=>'Marcha:','form_help'=>''),
                  'exp_fis_insg7' => array('label'=>'ROMBERG:','form_help'=>''),
                  'exp_fis_insg8' => array('label'=>'Punta Nariz:','form_help'=>''),
                  'exp_fis_ojo1' => array('label'=>'Estrabismo:','form_help'=>''),
                  'exp_fis_ojo2' => array('label'=>'Motilidad Ocular:','form_help'=>''),
                  'exp_fis_ojo3' => array('label'=>'Ptosis Palpebral:','form_help'=>''),
                  'exp_fis_ojo4' => array('label'=>'Especifique Ptosis Palpebral:','form_help'=>''),
                  'exp_fis_ojo5' => array('label'=>'Conjuntivas:','form_help'=>''),
                  'exp_fis_ojo6' => array('label'=>'Esclera Derecho:','form_help'=>''),
                  'exp_fis_ojo7' => array('label'=>'Esclera Izquierdo:','form_help'=>''),
                  'exp_fis_ojo8' => array('label'=>'Reflejo Fotomotor Derecho:','form_help'=>''),
                  'exp_fis_ojo9' => array('label'=>'Reflejo Fotomotor Izquierdo:','form_help'=>''),
                  'exp_fis_ojo10' => array('label'=>'Reflejo Consensual Derecho:','form_help'=>''),
                  'exp_fis_ojo11' => array('label'=>'Reflejo Consensual Izquierdo:','form_help'=>''),
                  'exp_fis_ojo12' => array('label'=>'Nistagmus:','form_help'=>''),
                  'exp_fis_ojo13' => array('label'=>'Dolor Ocular a la PalpaciÃ³n (Derecho) H57.1:','form_help'=>''),
                  'exp_fis_ojo14' => array('label'=>'Dolor Ocular a la PalpaciÃ³n (Izquierdo):','form_help'=>''),
                  'exp_fis_ojo15' => array('label'=>'SecreciÃ³n Ojo Derecho:','form_help'=>''),
                  'exp_fis_ojo16' => array('label'=>'SecreciÃ³n Ojo Izquierdo:','form_help'=>''),
                  'exp_fis_oido1' => array('label'=>'InspecciÃ³n Oido Externo:','form_help'=>''),
                  'exp_fis_oido2' => array('label'=>'Oido Medio:','form_help'=>''),
                  'exp_fis_oido3' => array('label'=>'ImplantaciÃ³n de Pabellones Auriculares:','form_help'=>'Auriculares'),
                  'exp_fis_nariz1' => array('label'=>'DesviaciÃ³n Septal:','form_help'=>''),
                  'exp_fis_nariz2' => array('label'=>'Permeabilidad Fosas Nasales:','form_help'=>''),
                  'exp_fis_nariz3' => array('label'=>'Mucosa Nasal:','form_help'=>''),
                  'exp_fis_nariz4' => array('label'=>'Cornete Medio e Inferior(MEATOS):','form_help'=>''),
                  'exp_fis_nariz5' => array('label'=>'Secreciones Nasales:','form_help'=>''),
                  'exp_fis_cuello1' => array('label'=>'Movilidad AnteflexiÃ³n y DorsoflexiÃ³n:','form_help'=>''),
                  'exp_fis_cuello2' => array('label'=>'SimetrÃ­a de Cuello:','form_help'=>''),
                  'exp_fis_cuello3' => array('label'=>'Pulso Venosos Yugular Derecho e Izquierdo:','form_help'=>''),
                  'exp_fis_cuello4' => array('label'=>'Pulso CarotÃ­deo Derecho e Izquierdo:','form_help'=>''),
                  'exp_fis_cuello5' => array('label'=>'TrÃ¡quea PosiciÃ³n:','form_help'=>''),
                  'exp_fis_cuello6' => array('label'=>'GlÃ¡ndula Tiroides:','form_help'=>''),
                  'exp_fis_cuello7' => array('label'=>'AdenopatÃ­as:','form_help'=>''),
                  'exp_fis_acardiaca1' => array('label'=>'InspecciÃ³n Levantamientos Paraesternales:','form_help'=>'Levantamientos Paraesternales'),
                  'exp_fis_acardiaca2' => array('label'=>'PalpaciÃ³n: Fremito:','form_help'=>'FrÃ©mito'),
                  'exp_fis_acardiaca3' => array('label'=>'PalpaciÃ³n: Levantamiento Paraesternales','form_help'=>'Levantamientos Paraesternales'),
                  'exp_fis_acardiaca4' => array('label'=>'PercusiÃ³n: DelimitaciÃ³n del Ã�rea Cardiaca','form_help'=>'DelimitaciÃ³n del Ã�rea Cardiaca'),
                  'exp_fis_acardiaca5' => array('label'=>'AuscultaciÃ³n: Primer Ruido Cardiaco','form_help'=>'Primer Ruido Cardiaco'),
                  'exp_fis_acardiaca6' => array('label'=>'AuscultaciÃ³n: Segundo Ruido Cardiaco','form_help'=>'Segundo Ruido Cardiaco'),
                  'exp_fis_acardiaca7' => array('label'=>'AuscultaciÃ³n: Presencia de Tercer y/o Cuarto Ruidos Cardiacos','form_help'=>'Presencia de Tercer y/o Cuarto Ruidos Cardiacos'),
                  'exp_fis_acardiaca8' => array('label'=>'Existe Presencia de Soplos Cardiacos:','form_help'=>''),
                  'exp_fis_abdomen1' => array('label'=>'Forma:','form_help'=>''),
                  'exp_fis_abdomen2' => array('label'=>'Cicatrices:','form_help'=>''),
                  'exp_fis_abdomen3' => array('label'=>'Movimientos PeristÃ¡lticos:','form_help'=>''),
                  'exp_fis_abdomen4' => array('label'=>'Hepatomegalia:','form_help'=>''),
                  'exp_fis_abdomen5' => array('label'=>'Hernias:','form_help'=>''),
                  'exp_fis_abdomen6' => array('label'=>'Especifique Hernia (s):','form_help'=>''),
                  'exp_fis_abdomen7' => array('label'=>'Datos de IrritaciÃ³n Peritoneal:','form_help'=>''),
                  'exp_fis_abdomen8' => array('label'=>'Imagen de Abdomen:','form_help'=>''),
                  'exp_fis_abdomen9' => array('label'=>'PalpaciÃ³n:','form_help'=>''),
                  'exp_fis_abdomen10' => array('label'=>'Puntos Ureterales Dolorosos:','form_help'=>''),
                  'exp_fis_abdomen11' => array('label'=>'Signo Giordano:','form_help'=>''),
                  'exp_fis_extremidades1' => array('label'=>'SimetrÃ­a Extremidades TorÃ¡cicas:','form_help'=>''),
                  'exp_fis_extremidades2' => array('label'=>'Especifique AsimetrÃ­a Extremidades Superiores:','form_help'=>''),
                  'exp_fis_extremidades3' => array('label'=>'SimetrÃ­a Extremidades PÃ©lvicas:','form_help'=>''),
                  'exp_fis_extremidades4' => array('label'=>'Especifique AsimetrÃ­a Extremidades PÃ©lvicas:','form_help'=>''),
                  'exp_fis_extremidades5' => array('label'=>'Movilidades de Extremidades:','form_help'=>''),
                  'exp_fis_extremidades6' => array('label'=>'Tono Muscular:','form_help'=>''),
                  'exp_fis_extremidades7' => array('label'=>'Fuerza Muscular:','form_help'=>''),
                  'exp_fis_extremidades8' => array('label'=>'Reflejo Bicipital Derecho e Izquierdo:','form_help'=>''),
                  'exp_fis_extremidades9' => array('label'=>'ExploraciÃ³n de Columna Dorsolumbar:','form_help'=>''),
                  'exp_fis_extremidades10' => array('label'=>'Babinski:','form_help'=>''),
                  'exp_fis_extremidades11' => array('label'=>'Amputaciones:','form_help'=>''),
                  'exp_fis_extremidades12' => array('label'=>'AmputaciÃ³n de Extremidades:','form_help'=>''),
                  'exp_fis_extremidades13' => array('label'=>'Amputaciones en Mano Derecha:','form_help'=>''),
                  'exp_fis_extremidades14' => array('label'=>'Amputaciones en Mano Izquierda:','form_help'=>''),
                  'exp_fis_extremidades15' => array('label'=>'Uso de PrÃ³tesis:','form_help'=>''),
                  'exp_fis_extremidades16' => array('label'=>'Datos de NeurotensiÃ³n:','form_help'=>''),
                  'exp_fis_extremidades17' => array('label'=>'Especifique Datos de NeurotensiÃ³n:','form_help'=>''),
                  'exp_fis_extremidades18' => array('label'=>'Presenta Datos de Dermatomocosis Ungueal o Plantar:','form_help'=>''),
                  'exp_fis_extremidades19' => array('label'=>'Especifique Dermatomocosis Ungueal o Plantar:','form_help'=>''),
                  'exp_fis_torax1' => array('label'=>'InspecciÃ³n:','form_help'=>''),
                  'exp_fis_torax2' => array('label'=>'Forma y Volumen:','form_help'=>''),
                  'exp_fis_torax3' => array('label'=>'Movilidad:','form_help'=>''),
                  'exp_fis_torax4' => array('label'=>'PalpaciÃ³n:','form_help'=>''),
                  'exp_fis_torax5' => array('label'=>'PercusiÃ³n:','form_help'=>''),
                  'exp_fis_torax6' => array('label'=>'AuscultaciÃ³n:','form_help'=>''),
                  'exp_fis_torax7' => array('label'=>'Referencia Torax Anterior:','form_help'=>''),
                  'exp_fis_torax8' => array('label'=>'Referencia Torax Posterior:','form_help'=>'')
                );


/*
 * Arreglo con los Textos para el Formulario de ExploraciÃ³n FÃ­sica
 *
 */
$q_gineco = array('exp_gineco1'  => array('label'=>'ProtrusiÃ³n CutÃ¡nea de Mamas:','form_help'=>''),
                  'exp_gineco2' => array('label'=> 'UmbilicaciÃ³n y cambios de direcciÃ³n del pezÃ³n:','form_help'=>''),
                  'exp_gineco3' => array('label'=>'RetracciÃ³n de la Piel:','form_help'=>''),
                  'exp_gineco4' => array('label'=>'Piel de Naranja:','form_help'=>''),
                  'exp_gineco5' => array('label'=>'Hiperemia o Hipertemia Local:','form_help'=>''),
                  'exp_gineco6' => array('label'=>'UlceraciÃ³n CutÃ¡nea:','form_help'=>''),
                  'exp_gineco7' => array('label'=>'SecreciÃ³n por el PezÃ³n:','form_help'=>''),
                  'exp_gineco8' => array('label'=>'Aumento de la red venosa superficial:','form_help'=>''),
                  'exp_gineco9' => array('label'=>'Presenta otros hallazgos clÃ­nicos:','form_help'=>''),
                  'exp_gineco10' => array('label'=>'Bordes de GlÃ¡ndula Mamaria:','form_help'=>''),
                  'exp_gineco11' => array('label'=>'Mastitis:','form_help'=>''),
                  'exp_gineco12' => array('label'=>'Mastalgia:','form_help'=>''),
                  'exp_gineco13' => array('label'=>'Fecha de Ãºltima Regla:','form_help'=>''),
                  'exp_gineco14' => array('label'=>'Grupo SanguÃ­neo y RH:','form_help'=>''),
                  'exp_gineco15' => array('label'=>'NÃºmero de Embarazos:','form_help'=>''),
                  'exp_gineco16' => array('label'=>'NÃºmero de Partos:','form_help'=>''),
                  'exp_gineco17' => array('label'=>'NÃºmero de Cesareas:','form_help'=>''),
                  'exp_gineco18' => array('label'=>'Embarazo Actual:','form_help'=>''),
                  'exp_gineco19' => array('label'=>'Edad Gestional:','form_help'=>''),
                  'exp_gineco20' => array('label'=>'Factores de Riesgo:','form_help'=>''),
                  'exp_gineco21' => array('label'=>'IdentificaciÃ³n de Signos y SÃ­ntomas de Alarma:','form_help'=>''),
                  'exp_gineco22' => array('label'=>'Â¿Presenta alguna emergencia obstÃ©trica?','form_help'=>'Especifique'),
                  'exp_gineco23' => array('label'=>'Â¿Cuenta con algÃºn mÃ©todo de planificaciÃ³n familiar?','form_help'=>''),
                  'exp_gineco24' => array('label'=>'Inicio de vida sexual activa:','form_help'=>''),
                  'exp_gineco25' => array('label'=>'NÃºmero de Abortos:','form_help'=>''),
                  'exp_gineco26' => array('label'=>'Especifique Hallazgos ClÃ­nicos:','form_help'=>'')
                );

/*
 * Arreglo con los Textos para el Formulario de oftalmologia
 *
 */
$q_oftalmo = array( 'oftalmo_agudeza1'  => array('label'=>'Agudeza Visual Cercana Ojo Derecho sin CorrecciÃ³n:','form_help'=>'(Resultado solo Decimales: 0.0)'),
                  'oftalmo_agudeza2' => array('label'=> 'Agudeza Visual Cercana Ojo Izquierdo sin CorrecciÃ³n:','form_help'=>'(Resultado solo Decimales: 0.0)'),
                  'oftalmo_agudeza3' => array('label'=>'Agudeza Visual Intermedia Ojo Derecho sin CorrecciÃ³n:','form_help'=>'(Resultado solo Decimales: 0.0)'),
                  'oftalmo_agudeza4' => array('label'=>'Agudeza Visual Intermedia Ojo Izquierdo sin CorrecciÃ³n:','form_help'=>'(Resultado solo Decimales: 0.0)'),
                  'oftalmo_agudeza5' => array('label'=>'Agudeza Visual Lejana Ojo Derecho sin CorrecciÃ³n:','form_help'=>'(Resultado solo Decimales: 0.0)'),
                  'oftalmo_agudeza6' => array('label'=>'Agudeza Visual Lejana Ojo Izquierdo sin CorrecciÃ³n:','form_help'=>'(Resultado solo Decimales: 0.0)'),
                  'oftalmo_agudeza7' => array('label'=>'Â¿Utiliza Lentes para corregir su agudeza visual?:','form_help'=>''),
                  'oftalmo_agudeza8' => array('label'=>'Sentido de profundidad o estereopsis:','form_help'=>'Segundos de arco'),
                  'oftalmo_agudeza9' => array('label'=>'Campo Visual:','form_help'=>'%'),
                  'oftalmo_agudeza10' => array('label'=>'Movimientos Oculares:','form_help'=>'%'),
                  'oftalmo_agudeza11' => array('label'=>'Ojo Derecho:','form_help'=>''),
                  'oftalmo_agudeza12' => array('label'=>'Ojo Izquierdo:','form_help'=>''),
                  'oftalmo_agudeza13' => array('label'=>'Especifique PatologÃ­a:','form_help'=>''),
                  'oftalmo_valoracion14' => array('label'=>'Cristalino Transparente:','form_help'=>''),
                  'oftalmo_valoracion15' => array('label'=>'Presencia de Fondo Rojo:','form_help'=>''),
                  'oftalmo_valoracion16' => array('label'=>'Presencia de Hemorragia en  VÃ­treo:','form_help'=>''),
                  'oftalmo_valoracion17' => array('label'=>'Presencia de Microaneurismas:','form_help'=>''),
                  'oftalmo_valoracion18' => array('label'=>'Presencia de Exudados Duros:','form_help'=>''),
                  'oftalmo_valoracion19' => array('label'=>'Los exudados Duros:','form_help'=>''),
                  'oftalmo_valoracion20' => array('label'=>'Presencia de Exudados Blandos:','form_help'=>''),
                  'oftalmo_valoracion21' => array('label'=>'ProliferaciÃ³n Neovascular:','form_help'=>''),
                  'oftalmo_valoracion22' => array('label'=>'Hemorragia Intrarretinal:','form_help'=>''),
                  'oftalmo_valoracion23' => array('label'=>'Arrosariamiento Venoso:','form_help'=>''),
                  'oftalmo_valoracion24' => array('label'=>'RetinopatÃ­a DiabÃ©tica:','form_help'=>''),
                  'oftalmo_valoracion25' => array('label'=>'Grado de RetinopatÃ­a:','form_help'=>''),
                  'oftalmo_valoracion26' => array('label'=>'Test de EvaluaciÃ³n:','form_help'=>'VisiÃ³n CromÃ¡tica'),
                  'oftalmo_valoracion27' => array('label'=>'Especifique tipo de:','form_help'=>'Discromatopsia')
                );

/*
 * Arreglo con los Textos para el Formulario de EvaluaciÃ³n oftalmolÃ³gica
 *
 */


$q_eval_name = array('oftal_polarizantes',
                     'oftal_alteraciones_pupila',
                     'oftal_antecedentes_cirugia_ojos',
                     'oftal_agudeza_anormal',
                     'oftal_vision_borrosa',
                     'oftal_nistagmus',
                     'oftal_dificultad_distinguir_colores',
                     'oftal_tension_anormal',
                     'oftal_manchas_vista',
                     'oftal_dolor_ocular',
                     'oftal_fotofobia',
                     'oftal_trauma_ocular',
                     'oftal_secreciones',
                     'oftal_mareo',
                     'oftal_cataratas',
                     'oftal_estrabismo',
                     'oftal_alteraciones_fondo_ojo',
                     'oftal_motilidad_anormal',
                     'oftal_binocular_normal',
                     'oftal_alteraciones_parpados',
                     'oftal_alteraciones_conjuntiva',
                     'oftal_palpebral',
                     'oftal_cornea_anormal',
                     'oftal_alteraciones_hendidura_palpebral',
                     'oftal_iris_anormal',
                     'oftal_alteraciones_hendidura_palpebral_inferior',
                     'oftal_reflejo_corneal_anormal',
                     'oftal_alteraciones_conjuntiva_palpebral_superior',
                     'oftal_reflejo_conjuntival_anormal',
                     'oftal_esclera_anormal',
                     'oftal_reflejo_fotomotor_anormal',
                     'oftal_miosis',
                     'oftal_consensual_anormal',
                     'oftal_midriasis'
                   );

$q_eval_oftalmo = array( 'oftalmo_eval1'  => array('label'=>'USO DE LENTES DE SOL POLARIZANTES:','form_help'=>''),
                         'oftalmo_eval2'  => array('label'=>'ALTERACIONES EN LA PUPILA:','form_help'=>''),
                         'oftalmo_eval3'  => array('label'=>'ANTECEDENTES DE CIRUGIA EN LOS OJOS:','form_help'=>''),
                         'oftalmo_eval4'  => array('label'=>'AGUDEZA VISUAL ANORMAL:','form_help'=>''),
                         'oftalmo_eval5'  => array('label'=>'PRESENCIA DE VISION BORROSA:','form_help'=>''),
                         'oftalmo_eval6'  => array('label'=>'PRESENCIA DE NISTAGMUS:','form_help'=>''),
                         'oftalmo_eval7'  => array('label'=>'DIFICULTAD PARA DISTINGUIR COLORES:','form_help'=>''),
                         'oftalmo_eval8'  => array('label'=>'TENSION OCULAR-PALPACIÃ“N ANORMAL:','form_help'=>''),
                         'oftalmo_eval9'  => array('label'=>'PERCEPCIÃ“N DE MANCHAS EN LA VISTA:','form_help'=>''),
                         'oftalmo_eval10'  => array('label'=>'PRESENCIA DE DOLOR OCULAR:','form_help'=>''),
                         'oftalmo_eval11'  => array('label'=>'PRESENCIA DE FOTOFOBIA:','form_help'=>''),
                         'oftalmo_eval12'  => array('label'=>'TRAUMA OCULAR EN LOS ÃšLTIMOS TRES MESES:','form_help'=>''),
                         'oftalmo_eval13'  => array('label'=>'PRESENCIA DE SECRECIONES:','form_help'=>''),
                         'oftalmo_eval14'  => array('label'=>'PRESENCIA DE MAREO EN LOS ÃšLTIMOS TRES MESES:','form_help'=>''),
                         'oftalmo_eval15'  => array('label'=>'PRESENCIA DE CATARATAS:','form_help'=>''),
                         'oftalmo_eval16'  => array('label'=>'ESTRABISMO:','form_help'=>''),
                         'oftalmo_eval17'  => array('label'=>'ALTERACIONES EN EL FONDO DE OJO:','form_help'=>''),
                         'oftalmo_eval18'  => array('label'=>'MOTILIDAD ANORMAL:','form_help'=>''),
                         'oftalmo_eval19'  => array('label'=>'ALTERACIONES EN LA VISIÃ“N BINOCULAR NORMAL:','form_help'=>''),
                         'oftalmo_eval20'  => array('label'=>'ALTERACIONES EN PARPADOS	:','form_help'=>''),
                         'oftalmo_eval21'  => array('label'=>'ALTERACIONES EN LA CONJUNTIVA:','form_help'=>''),
                         'oftalmo_eval22'  => array('label'=>'PTOSIS PALPEBRAL:','form_help'=>''),
                         'oftalmo_eval23'  => array('label'=>'CÃ“RNEA ANORMAL:','form_help'=>''),
                         'oftalmo_eval24'  => array('label'=>'ALTERACIONES EN LA HENDIDURA PALPEBRAL:','form_help'=>''),
                         'oftalmo_eval25'  => array('label'=>'IRIS ANORMAL:','form_help'=>''),
                         'oftalmo_eval26'  => array('label'=>'ALTERACIONES EN LA CONJUNTIVA PALPEBRAL INFERIOR:','form_help'=>''),
                         'oftalmo_eval27'  => array('label'=>'REFLEJO CORNEAL ANORMAL:','form_help'=>''),
                         'oftalmo_eval28'  => array('label'=>'ALTERACIONES EN LA CONJUNTIVA PALPEBRAL SUPERIOR	:','form_help'=>''),
                         'oftalmo_eval29'  => array('label'=>'REFLEJO CONJUNTIVAL ANORMAL:','form_help'=>''),
                         'oftalmo_eval30'  => array('label'=>'ESCLERA ANORMAL:','form_help'=>''),
                         'oftalmo_eval31'  => array('label'=>'REFLEJO FOTOMOTOR ANORMAL	:','form_help'=>''),
                         'oftalmo_eval32'  => array('label'=>'PRESENCIA DE MIOSIS	:','form_help'=>''),
                         'oftalmo_eval33'  => array('label'=>'REFLEJO CONSENSUAL ANORMAL:','form_help'=>''),
                         'oftalmo_eval34'  => array('label'=>'PRESENCIA DE MIDRIASIS:','form_help'=>''));


$q_eval_oftalmo_name = array( 'oftalmo_eval1'  => array('label'=>'oftal_polarizantes','form_help'=>''),
                         'oftalmo_eval2'  => array('label'=>'oftal_alteraciones_pupila','form_help'=>''),
                         'oftalmo_eval3'  => array('label'=>'oftal_antecedentes_cirugia_ojos','form_help'=>''),
                         'oftalmo_eval4'  => array('label'=>'oftal_agudeza_anormal','form_help'=>''),
                         'oftalmo_eval5'  => array('label'=>'oftal_vision_borrosa','form_help'=>''),
                         'oftalmo_eval6'  => array('label'=>'oftal_nistagmus','form_help'=>''),
                         'oftalmo_eval7'  => array('label'=>'oftal_dificultad_distinguir_colores','form_help'=>''),
                         'oftalmo_eval8'  => array('label'=>'oftal_tension_anormal','form_help'=>''),
                         'oftalmo_eval9'  => array('label'=>'oftal_manchas_vista','form_help'=>''),
                         'oftalmo_eval10'  => array('label'=>'oftal_dolor_ocular','form_help'=>''),
                         'oftalmo_eval11'  => array('label'=>'oftal_fotofobia','form_help'=>''),
                         'oftalmo_eval12'  => array('label'=>'oftal_trauma_ocular','form_help'=>''),
                         'oftalmo_eval13'  => array('label'=>'oftal_secreciones','form_help'=>''),
                         'oftalmo_eval14'  => array('label'=>'oftal_mareo','form_help'=>''),
                         'oftalmo_eval15'  => array('label'=>'oftal_cataratas','form_help'=>''),
                         'oftalmo_eval16'  => array('label'=>'oftal_estrabismo','form_help'=>''),
                         'oftalmo_eval17'  => array('label'=>'oftal_alteraciones_fondo_ojo','form_help'=>''),
                         'oftalmo_eval18'  => array('label'=>'oftal_motilidad_anormal','form_help'=>''),
                         'oftalmo_eval19'  => array('label'=>'oftal_binocular_normal','form_help'=>''),
                         'oftalmo_eval20'  => array('label'=>'oftal_alteraciones_parpados','form_help'=>''),
                         'oftalmo_eval21'  => array('label'=>'oftal_alteraciones_conjuntiva','form_help'=>''),
                         'oftalmo_eval22'  => array('label'=>'oftal_palpebral','form_help'=>''),
                         'oftalmo_eval23'  => array('label'=>'oftal_cornea_alockSessionnormal','form_help'=>''),
                         'oftalmo_eval24'  => array('label'=>'oftal_alteraciones_hendidura_palpebral','form_help'=>''),
                         'oftalmo_eval25'  => array('label'=>'oftal_iris_anormal','form_help'=>''),
                         'oftalmo_eval26'  => array('label'=>'oftal_alteraciones_hendidura_palpebral_inferior','form_help'=>''),
                         'oftalmo_eval27'  => array('label'=>'oftal_reflejo_corneal_anormal','form_help'=>''),
                         'oftalmo_eval28'  => array('label'=>'oftal_alteraciones_conjuntiva_palpebral_superior','form_help'=>''),
                         'oftalmo_eval29'  => array('label'=>'oftal_reflejo_conjuntival_anormal','form_help'=>''),
                         'oftalmo_eval30'  => array('label'=>'oftal_esclera_anormal','form_help'=>''),
                         'oftalmo_eval31'  => array('label'=>'oftal_reflejo_fotomotor_anormal','form_help'=>''),
                         'oftalmo_eval32'  => array('label'=>'oftal_miosis','form_help'=>''),
                         'oftalmo_eval33'  => array('label'=>'oftal_consensual_anormal','form_help'=>''),
                         'oftalmo_eval34'  => array('label'=>'oftal_midriasis','form_help'=>''));

$q_eval_oftalmo2 = array('oftalmo_eval35'  => array('label'=>'AGUDEZA VISUAL CERCANA OJO DERECHO CORREGIDA	:','form_help'=>''),
                         'oftalmo_eval36'  => array('label'=>'AGUDEZA VISUAL CERCANA OJO IZQUIERDO CORREGIDA	:','form_help'=>''),
                         'oftalmo_eval37'  => array('label'=>'AGUDEZA VISUAL INTERMEDIA OJO DERECHO CORREGIDA:','form_help'=>''),
                         'oftalmo_eval38'  => array('label'=>'AGUDEZA VISUAL INTERMEDIA OJO IZQUIERDO CORREGIDA	:','form_help'=>''),
                         'oftalmo_eval39'  => array('label'=>'AGUDEZA VISUAL LEJANA OJO DERECHO &nbsp;CORREGIDA:','form_help'=>''),
                         'oftalmo_eval40'  => array('label'=>'AGUDEZA VISUAL LEJANA OJO IZQUIERDO CORREGIDA:','form_help'=>''),
                         'oftalmo_eval41'  => array('label'=>'CAMPO VISUAL:','form_help'=>''),
                         'oftalmo_eval43'  => array('label'=>'OTRA (ESPECIFIQUE):','form_help'=>''),
                         'oftalmo_eval44'  => array('label'=>'SE DETERMINÃ“:','form_help'=>''),
                         'oftalmo_eval45'  => array('label'=>'DIAGNOSTICO Y RECOMENDACIONES:','form_help'=>''),
                         'oftalmo_eval46'  => array('label'=>'VISIÃ“N BINOCULAR O ESTEREOPSIS:','form_help'=>''),
                         'oftalmo_eval47'  => array('label'=>'APTO:','form_help'=>''));
 -->