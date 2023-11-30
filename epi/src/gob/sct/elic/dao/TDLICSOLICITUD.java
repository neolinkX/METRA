 package gob.sct.elic.dao;
 
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.vo.TVDinRep;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

import cisws.generated.WSInsertaCita;
import cisws.generated.WSInsertaCitaPort;
import cisws.generated.WSInsertaCita_Impl;
import gob.sct.elic.dto.LicenciaDTO;
import gob.sct.elic.dto.SolicitudDTO;
import gob.sct.elic.dto.UsuarioDTO;
 
 public class TDLICSOLICITUD extends DAOBase
 {
   private TParametro VParametros = new TParametro("13");
   private String dataSourceName = this.VParametros.getPropEspecifica("ConDBModulo");
 
   public Vector findByCustom(String cKey, String cWhere)
     throws DAOException
   {
     Vector vcRecords = new Vector();
     this.cError = "";
     try {
       String cSQL = cWhere;
       vcRecords = FindByGeneric2(cKey, cSQL, this.dataSourceName);
     } catch (Exception e) {
       this.cError = e.toString();
 
       if (!this.cError.equals("")) {
         throw new DAOException(this.cError);
       }
 
       if (!this.cError.equals(""))
         throw new DAOException(this.cError);
     }
     finally
     {
       if (!this.cError.equals("")) {
         throw new DAOException(this.cError);
       }
     }
     return vcRecords;
   }
 
   public UsuarioDTO findPersona(Integer idSolicitud, Integer idTipoLicencia)
     throws Exception
   {
     return findPersona(idSolicitud, idTipoLicencia, null);
   }
 
   public UsuarioDTO findPersona(Integer idSolicitud, Integer idTipoLicencia, String iCveMdoTrans) throws Exception {
     Vector vcRecords = null;
     UsuarioDTO persona = null;
     try
     {
       String cSQL = 
         "SELECT P.CNOMBRE,P.CAPPATERNO,P.CAPMATERNO, D.CCALLE,D.CNUMEXT,D.CNUMINT,D.CCOLONIA, MUN.CNOMBRE AS ENT_FED, EST.CDSCESTADO, D.ICP, S.ICVEDEPARTAMENTO,  NAC.CDSCNACIONAL || ' / ' || NAC.CDSCNACING AS CDSCNACIONAL, P.DTNACIMIENTO, P.CRFC, DEP.CDSCDEPARTAMENTO, L.DTINICIO, L.DTFIN,  L.CLICENCIA, TL.CDSCTIPOLICENCIA, 'Grupo Sanguineo:' || cGpoSang  ||  case when LRH = 1 then ', RH:Positivo' else ' RH:Negativo' end ||  case when LUSALENTES = 1 AND LCONTACTO <> 1 then ', Usa Lentes / Use of Correcting Lense ' else '' end ||  case when LUSALENTES = 1 AND LCONTACTO = 1 then ', Usa Lentes de Contacto / Use of Correcting Contact Lenses' else '' end  as COBSERVACION, TL.CABREVIACION,  MEDP.ICVECATEGORIA, GCEN.CDSCCSCT, L.TSIMPRESION, USR.CNOMBRE AS NOMBRE_X, USR.CAPPATERNO AS PATERNO_X, USR.CAPMATERNO AS MATERNO_X,P.ICVEPERSONAL,(SELECT cValor FROM LICSOLREQEXTRAS LSRE where LSRE.ICVESOLICITUD = S.ICVESOLICITUD and LSRE.CNOMBRECAMPO = 'cLicExtNum') as cConvalida, P.CCURP FROM LICSOLICITUD S INNER JOIN PERDATOS P    ON P.ICVEPERSONAL = S.ICVEPERSONAL INNER JOIN PERDIRECCION D   ON D.ICVEPERSONAL = P.ICVEPERSONAL AND D.ICVEDIRECCION = (CASE P.ICVEDIRECCION WHEN 0 THEN 1 ELSE P.ICVEDIRECCION END) LEFT OUTER JOIN GRLMUNICIPIO MUN   ON D.ICVEPAIS = MUN.ICVEPAIS   AND D.ICVEESTADO = MUN.ICVEENTIDADFED   AND D.ICVEMUNICIPIO = MUN.ICVEMUNICIPIO LEFT OUTER JOIN GRLESTADO EST    ON EST.ICVEPAIS = D.ICVEPAIS   AND EST.ICVEESTADO = D.ICVEESTADO INNER JOIN GRLNACIONALIDAD NAC ON NAC.ICVEPAIS = P.ICVEPAISNAC INNER JOIN GRLDEPARTAMENTO DEP ON DEP.ICVEDEPARTAMENTO=S.ICVEDEPTOENT INNER JOIN LICPERLICENCIA L ON L.ICVESOLICITUD = S.ICVESOLICITUD INNER JOIN LICPERTIPOLIC PTL ON PTL.ICVESOLICITUD = S.ICVESOLICITUD INNER JOIN LICTIPOLICENCIA TL ON TL.ICVETIPOLICENCIA = PTL.ICVETIPOLICENCIA   LEFT OUTER JOIN SEGUSUARIO USR ON USR.ICVEUSUARIO = L.ICVEUSUARIO LEFT OUTER JOIN LICTIPOMEDPREV MEDP   ON MEDP.ICVETIPOLICENCIA = PTL.ICVETIPOLICENCIA   AND MEDP.ICVEMDOTRANS =  " + (
         iCveMdoTrans != null ? iCveMdoTrans : "1") + 
         " LEFT OUTER JOIN GRLCENTROSCT GCEN ON GCEN.ICVECENTROSCT = DEP.ICVECENTROSCT" + 
         " WHERE S.ICVESOLICITUD = " + idSolicitud.toString() + 
         " and PTL.ICVETIPOLICENCIA = " + idTipoLicencia.toString();
 
       vcRecords = FindByGeneric2("S.ICVESOLICITUD", cSQL, this.dataSourceName);
 
       if ((vcRecords != null) && (!vcRecords.isEmpty()))
       {
         TVDinRep vInfo = (TVDinRep)vcRecords.get(0);
 
         if (vInfo != null) {
           persona = new UsuarioDTO();
 
           persona.setCNombre(vInfo.getString("CNOMBRE"));
           persona.setCApPaterno(vInfo.getString("CAPPATERNO"));
           persona.setCApMaterno(vInfo.getString("CAPMATERNO"));
           persona.setCCalle(vInfo.getString("CCALLE"));
           persona.setCColonia(vInfo.getString("CCOLONIA"));
           persona.setNumExterior(vInfo.getString("CNUMEXT"));
           persona.setNumInterior(vInfo.getString("CNUMINT"));
           persona.setCDscEntidadFed(vInfo.getString("ENT_FED"));
           persona.setDescEstado(vInfo.getString("CDSCESTADO"));
           persona.setCodigoPostal(vInfo.getString("ICP"));
 
           persona.setcDescNacionalidad(vInfo.getString("CDSCNACIONAL"));
           //persona.setFechaNacimiento(vInfo.getDate("DTNACIMIENTO"));
           persona.setRfc(vInfo.getString("CRFC"));
           persona.setCCurp(vInfo.getString("CCURP"));
           persona.setICvePersonal(vInfo.getInt("ICVEPERSONAL"));
 
           LicenciaDTO licencia = new LicenciaDTO();
 
           licencia.setDescLugarExpedicion(vInfo.getString("CDSCCSCT"));
 
           licencia.setIdDepartamento(vInfo.getInt("ICVEDEPARTAMENTO"));
 
           licencia.setIdLicencia(vInfo.getString("CLICENCIA"));
 
           //licencia.setVigenciaInicial(vInfo.getDate("DTINICIO"));
 
           //licencia.setVigenciaFinal(vInfo.getDate("DTFIN"));
 
           licencia.setNombreAutoriza(vInfo.getString("NOMBRE_X"));
           licencia.setApellidoPaternoAutoriza(vInfo.getString("PATERNO_X"));
           licencia.setApellidoMaternoAutoriza(vInfo.getString("MATERNO_X"));
 
           licencia.setDescTipoLicencia(vInfo.getString("CDSCTIPOLICENCIA"));
 
           licencia.setDescAbreviacionTipoLicencia(vInfo.getString("CABREVIACION"));
 
           String cConvalida = "";
 
           if ((!vInfo.getString("cConvalida").equals("null")) && (!vInfo.getString("cConvalida").equals(""))) {
             cConvalida = ", Convalidación sujeta a Licencia extranjera número " + vInfo.getString("cConvalida") + 
               "/ This document is valid with foreing license number " + vInfo.getString("cConvalida");
           }
           licencia.setDescObservacion(vInfo.getString("COBSERVACION") + cConvalida);
 
           licencia.setIdCategoria(vInfo.getInt("ICVECATEGORIA"));
 
           persona.setLicencia(licencia);
         }
       }
       else {
         System.out.println("Logger::No existen datos de la persona");
       }
     }
     catch (Exception e) {
       throw e;
     }
 
     return persona;
   }
 
   public TVDinRep insert(TVDinRep vData, Connection cnNested)
     throws DAOException
   {
     DbConnection dbConn = null;
     Connection conn = cnNested;
     PreparedStatement lPStmt = null;
     boolean lSuccess = true;
     try
     {
       if (cnNested == null) {
         dbConn = new DbConnection(this.dataSourceName);
         conn = dbConn.getConnection();
         conn.setAutoCommit(false);
         conn.setTransactionIsolation(2);
       }
 
       String lSQL = 
         "insert into LICSOLICITUD(ICVESOLICITUD,ICVEPERSONAL,TSSOLICITUD,ICVEUSUARIO,ICVEDEPARTAMENTO,COBSERVACION,ICVEDEPTOENT) values (?,?,{FN NOW()},?,?,?,?)"; // this  
       
       int ICVESOLICITUD = 0;
 
       String wsdlUrl = this.VParametros.getPropEspecifica("WSCISURI") + "InsertaCita?WSDL";
       WSInsertaCita service = new WSInsertaCita_Impl(wsdlUrl);
       WSInsertaCitaPort port = service.getWSInsertaCitaPort();
 
       ICVESOLICITUD = port.insertaCita(this.VParametros.getPropEspecifica("WSCISUSR"), 
         this.VParametros.getPropEspecifica("WSCISPWD"), 
         vData.getInt("ICVETRAMITECIS"), 
         0, 
         1, 
         vData.getString("CNOMBRECIS"), 
         vData.getInt("ICVEAREACIS"));
 
       if (ICVESOLICITUD == 0) {
         lSuccess = false;
       } else {
         vData.put("ICVESOLICITUD", ICVESOLICITUD);
         vData.addPK(vData.getString("ICVESOLICITUD"));
 
         if (vData.getString("ICVEDEPTO").equals(""))
         {
           Vector vcData = findByCustom("", 
             "select ICVEDEPARTAMENTO from GRLUSRXDEPTO WHERE ICVEUSUARIO = " + 
             vData.getString("ICVEUSUARIO"));
           if (vcData.size() > 0) {
             TVDinRep vUltimo = (TVDinRep)vcData.get(0);
             vData.put("ICVEDEPARTAMENTO", vUltimo.getInt("ICVEDEPARTAMENTO"));
           } else {
             vData.put("ICVEDEPARTAMENTO", 0);
           }
         } else {
           vData.put("ICVEDEPARTAMENTO", vData.getString("ICVEDEPTO"));
         }
         lPStmt = conn.prepareStatement(lSQL);
         lPStmt.setInt(1, vData.getInt("ICVESOLICITUD"));
         lPStmt.setInt(2, vData.getInt("ICVEPERSONAL"));
         lPStmt.setInt(3, vData.getInt("ICVEUSUARIO"));
         lPStmt.setInt(4, vData.getInt("ICVEDEPARTAMENTO"));
         lPStmt.setString(5, vData.getString("COBSERVACION"));
         lPStmt.setInt(6, vData.getInt("ICVEDEPTOENT"));
         lPStmt.executeUpdate();
         if (cnNested == null)
           conn.commit();
       }
     }
     catch (Exception ex) {
       ex.printStackTrace();
       warn("insert", ex);
       if (cnNested == null) {
         try {
           conn.rollback();
         } catch (Exception e) {
           fatal("insert.rollback", e);
         }
       }
       lSuccess = false;
     }
     try {
       if (lPStmt != null) {
         lPStmt.close();
       }
       if (cnNested == null) {
         if (conn != null) {
           conn.close();
         }
         dbConn.closeConnection();
       }
     } catch (Exception ex2) {
       warn("insert.close", ex2);
     }
     if (!lSuccess)
       throw new DAOException("");
     return vData;
   }
 
   public TVDinRep updateRec(TVDinRep vData, Connection cnNested)
     throws DAOException
   {
     DbConnection dbConn = null;
     Connection conn = cnNested;
     PreparedStatement lPStmt = null;
     boolean lSuccess = true;
     try {
       if (cnNested == null) {
         dbConn = new DbConnection(this.dataSourceName);
         conn = dbConn.getConnection();
         conn.setAutoCommit(false);
         conn.setTransactionIsolation(2);
       }
 
       String lSQL = "update LICSOLICITUD set TSRECIBE={FN NOW()} where ICVESOLICITUD = ?  ";
 
       lPStmt = conn.prepareStatement(lSQL);
       lPStmt.setInt(1, vData.getInt("ICVESOLICITUD"));
 
       lPStmt.executeUpdate();
       if (cnNested == null)
         conn.commit();
     }
     catch (Exception ex) {
       warn("updateRec", ex);
       if (cnNested == null) {
         try {
           conn.rollback();
         } catch (Exception e) {
           fatal("updateRec.rollback", e);
         }
       }
       lSuccess = false;
     }
     try {
       if (lPStmt != null) {
         lPStmt.close();
       }
       if (cnNested == null) {
         if (conn != null) {
           conn.close();
         }
         dbConn.closeConnection();
       }
     } catch (Exception ex2) {
       warn("updateRec.close", ex2);
     }
     if (!lSuccess)
       throw new DAOException("");
     return vData;
   }
 
   public TVDinRep updateFin(TVDinRep vData, Connection cnNested)
     throws DAOException
   {
     DbConnection dbConn = null;
     Connection conn = cnNested;
     PreparedStatement lPStmt = null;
     boolean lSuccess = true;
     try {
       if (cnNested == null) {
         dbConn = new DbConnection(this.dataSourceName);
         conn = dbConn.getConnection();
         conn.setAutoCommit(false);
         conn.setTransactionIsolation(2);
       }
 
       String lSQL = "update LICSOLICITUD set TSFIN={FN NOW()} where ICVESOLICITUD = ?  ";
 
       lPStmt = conn.prepareStatement(lSQL);
       lPStmt.setInt(1, vData.getInt("ICVESOLICITUD"));
 
       lPStmt.executeUpdate();
       if (cnNested == null)
         conn.commit();
     }
     catch (Exception ex) {
       warn("updateRec", ex);
       if (cnNested == null) {
         try {
           conn.rollback();
         } catch (Exception e) {
           fatal("updateRec.rollback", e);
         }
       }
       lSuccess = false;
     }
     try {
       if (lPStmt != null) {
         lPStmt.close();
       }
       if (cnNested == null) {
         if (conn != null) {
           conn.close();
         }
         dbConn.closeConnection();
       }
     } catch (Exception ex2) {
       warn("updateRec.close", ex2);
     }
     if (!lSuccess)
       throw new DAOException("");
     return vData;
   }
 
   public TVDinRep updateDest(TVDinRep vData, Connection cnNested)
     throws DAOException
   {
     DbConnection dbConn = null;
     Connection conn = cnNested;
     PreparedStatement lPStmt = null;
     boolean lSuccess = true;
     try {
       if (cnNested == null) {
         dbConn = new DbConnection(this.dataSourceName);
         conn = dbConn.getConnection();
         conn.setAutoCommit(false);
         conn.setTransactionIsolation(2);
       }
 
       if (vData.getInt("ICVEDEPTODEST") <= 0) {
         vData.remove("ICVEDEPTODEST");
         vData.put("ICVEDEPTODEST", vData.getInt("ICVEDEPARTAMENTO"));
       }
 
       String lSQL = "update LICSOLICITUD set ICVEDEPTODEST=? where ICVESOLICITUD = ?  ";
 
       lPStmt = conn.prepareStatement(lSQL);
       lPStmt.setInt(1, vData.getInt("ICVEDEPTODEST"));
       lPStmt.setInt(2, vData.getInt("ICVESOLICITUD"));
 
       lPStmt.executeUpdate();
       if (cnNested == null)
         conn.commit();
     }
     catch (Exception ex) {
       warn("updateRec", ex);
       if (cnNested == null) {
         try {
           conn.rollback();
         } catch (Exception e) {
           fatal("updateRec.rollback", e);
         }
       }
       lSuccess = false;
     }
     try {
       if (lPStmt != null) {
         lPStmt.close();
       }
       if (cnNested == null) {
         if (conn != null) {
           conn.close();
         }
         dbConn.closeConnection();
       }
     } catch (Exception ex2) {
       warn("updateRec.close", ex2);
     }
     if (!lSuccess)
       throw new DAOException("");
     return vData;
   }
 
   public TVDinRep updateDesautorizaLic(TVDinRep vData, Connection cnNested)
     throws DAOException
   {
     DbConnection dbConn = null;
     Connection conn = cnNested;
     PreparedStatement lPStmt = null;
     PreparedStatement lPStmt2 = null;
     PreparedStatement lPStmt3 = null;
     PreparedStatement lPStmt4 = null;
     PreparedStatement lPStmt5 = null;
     PreparedStatement lPStmt6 = null;
     PreparedStatement lPStmt7 = null;
     PreparedStatement lPStmt8 = null;
     PreparedStatement lPStmt9 = null;
     boolean lSuccess = true;
     try {
       if (cnNested == null) {
         dbConn = new DbConnection(this.dataSourceName);
         conn = dbConn.getConnection();
         conn.setAutoCommit(false);
         conn.setTransactionIsolation(2);
       }
 
       String lSQL = "update LICSOLICITUD set TSRECIBE= null, TSFIN = null, COBSERVACION=COBSERVACION || 'DA:" + vData.getString("ICVEUSUARIO") + " ', iCveDAUsu = " + vData.getString("ICVEUSUARIO") + ", dtDA = {FN NOW()} where ICVESOLICITUD = ?  ";
       lPStmt = conn.prepareStatement(lSQL);
       lPStmt.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt.executeUpdate();
 
       String lSQL2 = "update LICSOLCATEGORIA set TSAPRUEBA= null, LAPROBADO = null where ICVESOLICITUD = ?  ";
       lPStmt2 = conn.prepareStatement(lSQL2);
       lPStmt2.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt2.executeUpdate();
 
       String lSQL3 = "update LICSOLREQ set TSAPRUEBA= null, LAPROBADO = 0 where ICVESOLICITUD = ?  ";
       lPStmt3 = conn.prepareStatement(lSQL3);
       lPStmt3.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt3.executeUpdate();
 
       String lSQL4 = "DELETE from LICSOLARTICULO where ICVESOLICITUD = ?  ";
       lPStmt4 = conn.prepareStatement(lSQL4);
       lPStmt4.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt4.executeUpdate();
 
       String lSql7 = "DELETE from LICPERTIPOLICCAP where ICVESOLICITUD = ?";
 
       lPStmt7 = conn.prepareStatement(lSql7);
       lPStmt7.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt7.executeUpdate();
 
       String lSQL5 = " DELETE from LICPERTIPOLIC PTL  where PTL.cLicencia = (Select PL.cLicencia                         from LICPERLICENCIA PL                         where PL.iCveSolicitud = ? )     and PTL.dtAlta = (Select (PL.dtInicio)                      from LICPERLICENCIA PL                       where PL.iCveSolicitud = ? )";
 
       lPStmt5 = conn.prepareStatement(lSQL5);
       lPStmt5.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt5.setInt(2, vData.getInt("ICVESOLICITUD"));
       lPStmt5.executeUpdate();
 
       String lSQL6 = "DELETE from LICPERLICENCIA where ICVESOLICITUD = ?  ";
       lPStmt6 = conn.prepareStatement(lSQL6);
       lPStmt6.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt6.executeUpdate();
 
       String lSQL8 = "UPDATE LICSOLCAPACIDADES SET LAPROBADO=null, TSAPRUEBA=null WHERE ICVESOLICITUD = ?";
       lPStmt8 = conn.prepareStatement(lSQL8);
       lPStmt8.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt8.executeUpdate();
 
       String lSQL9 = "DELETE FROM LICSOLTMP WHERE ICVESOLICITUD = ?";
       lPStmt9 = conn.prepareStatement(lSQL9);
       lPStmt9.setInt(1, vData.getInt("ICVESOLICITUD"));
       lPStmt9.executeUpdate();
 
       if (cnNested == null)
         conn.commit();
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
       warn("updateRec", ex);
       if (cnNested == null) {
         try {
           conn.rollback();
         } catch (Exception e) {
           fatal("updateRec.rollback", e);
         }
       }
       lSuccess = false;
       try
       {
         if (lPStmt != null) lPStmt.close();
         if (lPStmt2 != null) lPStmt2.close();
         if (lPStmt3 != null) lPStmt3.close();
         if (lPStmt4 != null) lPStmt4.close();
         if (lPStmt5 != null) lPStmt5.close();
         if (lPStmt6 != null) lPStmt6.close();
         if (cnNested == null) {
           if (conn != null) {
             conn.close();
           }
           dbConn.closeConnection();
         }
       } catch (Exception ex2) {
         warn("updateRec.close", ex2);
       }
       if (!lSuccess) {
         throw new DAOException("");
       }
 
       try
       {
         if (lPStmt != null) lPStmt.close();
         if (lPStmt2 != null) lPStmt2.close();
         if (lPStmt3 != null) lPStmt3.close();
         if (lPStmt4 != null) lPStmt4.close();
         if (lPStmt5 != null) lPStmt5.close();
         if (lPStmt6 != null) lPStmt6.close();
         if (cnNested == null) {
           if (conn != null) {
             conn.close();
           }
           dbConn.closeConnection();
         }
       } catch (Exception ex2) {
         warn("updateRec.close", ex2);
       }
       if (!lSuccess) {
         throw new DAOException("");
       }
 
       try
       {
         if (lPStmt != null) lPStmt.close();
         if (lPStmt2 != null) lPStmt2.close();
         if (lPStmt3 != null) lPStmt3.close();
         if (lPStmt4 != null) lPStmt4.close();
         if (lPStmt5 != null) lPStmt5.close();
         if (lPStmt6 != null) lPStmt6.close();
         if (cnNested == null) {
           if (conn != null) {
             conn.close();
           }
           dbConn.closeConnection();
         }
       } catch (Exception ex2) {
         warn("updateRec.close", ex2);
       }
       if (!lSuccess) {
         throw new DAOException("");
       }
 
       try
       {
         if (lPStmt != null) lPStmt.close();
         if (lPStmt2 != null) lPStmt2.close();
         if (lPStmt3 != null) lPStmt3.close();
         if (lPStmt4 != null) lPStmt4.close();
         if (lPStmt5 != null) lPStmt5.close();
         if (lPStmt6 != null) lPStmt6.close();
         if (cnNested == null) {
           if (conn != null) {
             conn.close();
           }
           dbConn.closeConnection();
         }
       } catch (Exception ex2) {
         warn("updateRec.close", ex2);
       }
       if (!lSuccess)
         throw new DAOException("");
     }
     finally
     {
       try
       {
         if (lPStmt != null) lPStmt.close();
         if (lPStmt2 != null) lPStmt2.close();
         if (lPStmt3 != null) lPStmt3.close();
         if (lPStmt4 != null) lPStmt4.close();
         if (lPStmt5 != null) lPStmt5.close();
         if (lPStmt6 != null) lPStmt6.close();
         if (cnNested == null) {
           if (conn != null) {
             conn.close();
           }
           dbConn.closeConnection();
         }
       } catch (Exception ex2) {
         warn("updateRec.close", ex2);
       }
       if (!lSuccess)
         throw new DAOException("");
     }
     return vData;
   }
 
   public TVUsuario obtenerUsuarioPorSolicitud(int idSolicitud) throws Exception
   {
     Vector vcRecords = null;
     TVUsuario usuario = null;
     try
     {
       String cSQL = 
         "SELECT SOL.ICVEUSUARIO, USR.CNOMBRE, USR.CAPPATERNO, USR.CAPMATERNO FROM LICSOLICITUD SOL INNER JOIN SEGUSUARIO USR ON USR.ICVEUSUARIO = SOL.ICVEUSUARIO where SOL.ICVESOLICITUD = " + 
         idSolicitud;
 
       vcRecords = FindByGeneric2("", cSQL, this.dataSourceName);
 
       if ((vcRecords != null) && (!vcRecords.isEmpty()))
       {
         TVDinRep vInfo = (TVDinRep)vcRecords.get(0);
 
         if (vInfo != null)
         {
           usuario = new TVUsuario();
 
           usuario.setCNombre(vInfo.getString("CNOMBRE"));
           usuario.setCApPaterno(vInfo.getString("CAPPATERNO"));
           usuario.setCApMaterno(vInfo.getString("CAPMATERNO"));
         }
       }
     }
     catch (Exception e) {
       throw e;
     }
     return usuario;
   }
 
   public SolicitudDTO buscarSolicitud(int idSolicitud) throws Exception {
     Vector vcRecords = null;
     SolicitudDTO solicitud = null;
     try {
       String cSQL = 
         "SELECT S.ICVESOLICITUD,S.ICVEPERSONAL,S.TSSOLICITUD,S.ICVEUSUARIO,S.ICVEDEPARTAMENTO, S.TSFIN,S.TSRECIBE,S.COBSERVACION,S.ICVEDEPTODEST,S.ICVEDAUSU,S.DTDA,S.ICVEDEPTOENT,  DEP.CDSCDEPARTAMENTO,GCEN.CDSCCSCT FROM LICSOLICITUD S INNER JOIN GRLDEPARTAMENTO DEP ON DEP.ICVEDEPARTAMENTO = S.ICVEDEPTOENT LEFT OUTER JOIN GRLCENTROSCT GCEN ON GCEN.ICVECENTROSCT = DEP.ICVECENTROSCT where ICVESOLICITUD = " + 
         idSolicitud;
 
       vcRecords = FindByGeneric2("", cSQL, this.dataSourceName);
 
       if ((vcRecords != null) && (!vcRecords.isEmpty())) {
         TVDinRep vInfo = (TVDinRep)vcRecords.get(0);
         if (vInfo != null)
         {
           solicitud = new SolicitudDTO();
 
           solicitud.setIdSolicitud(vInfo.getInt("ICVESOLICITUD"));
           //solicitud.setFechaSolicitud(new java.util.Date(vInfo.getTimeStamp("TSSOLICITUD").getTime()));
           //solicitud.setFechaEntrega(new java.util.Date(vInfo.getTimeStamp("TSRECIBE").getTime()));
           solicitud.setDescOficinaEntrega(vInfo.getString("CDSCDEPARTAMENTO"));
           solicitud.setDescOficinaRecepcion(vInfo.getString("CDSCCSCT"));
         }
       }
     }
     catch (Exception e)
     {
       throw e;
     }
     return solicitud;
   }
 
   public UsuarioDTO findPersonaEsp(Integer idSolicitud, Integer idTipoLicencia, String iCveMdoTrans) throws Exception {
     Vector vcRecords = null;
     UsuarioDTO persona = null;
     try
     {
       String cSQL = 
         "SELECT P.CNOMBRE,P.CAPPATERNO,P.CAPMATERNO, D.CCALLE,D.CNUMEXT,D.CNUMINT,D.CCOLONIA, MUN.CNOMBRE AS ENT_FED, EST.CDSCESTADO, D.ICP, S.ICVEDEPARTAMENTO,  NAC.CDSCNACIONAL AS CDSCNACIONAL, P.DTNACIMIENTO, P.CRFC, DEP.CDSCDEPARTAMENTO, L.DTINICIO, L.DTFIN,  L.CLICENCIA, TL.CDSCTIPOLICENCIA, 'Grupo Sanguineo:' || cGpoSang  ||  case when LRH = 1 then ', RH:Positivo' else ' RH:Negativo' end ||  case when LUSALENTES = 1 AND LCONTACTO <> 1 then ', Usa Lentes' else '' end ||  case when LUSALENTES = 1 AND LCONTACTO = 1 then ', Usa Lentes de Contacto' else '' end  as COBSERVACION, TL.CABREVIACION,  MEDP.ICVECATEGORIA, GCEN.CDSCCSCT, L.TSIMPRESION, USR.CNOMBRE AS NOMBRE_X, USR.CAPPATERNO AS PATERNO_X, USR.CAPMATERNO AS MATERNO_X,P.ICVEPERSONAL,(SELECT cValor FROM LICSOLREQEXTRAS LSRE where LSRE.ICVESOLICITUD = S.ICVESOLICITUD and LSRE.CNOMBRECAMPO = 'cLicExtNum') as cConvalida, P.CCURP FROM LICSOLICITUD S INNER JOIN PERDATOS P    ON P.ICVEPERSONAL = S.ICVEPERSONAL INNER JOIN PERDIRECCION D   ON D.ICVEPERSONAL = P.ICVEPERSONAL AND D.ICVEDIRECCION = (CASE P.ICVEDIRECCION WHEN 0 THEN 1 ELSE P.ICVEDIRECCION END) LEFT OUTER JOIN GRLMUNICIPIO MUN   ON D.ICVEPAIS = MUN.ICVEPAIS   AND D.ICVEESTADO = MUN.ICVEENTIDADFED   AND D.ICVEMUNICIPIO = MUN.ICVEMUNICIPIO LEFT OUTER JOIN GRLESTADO EST    ON EST.ICVEPAIS = D.ICVEPAIS   AND EST.ICVEESTADO = D.ICVEESTADO INNER JOIN GRLNACIONALIDAD NAC ON NAC.ICVEPAIS = P.ICVEPAISNAC INNER JOIN GRLDEPARTAMENTO DEP ON DEP.ICVEDEPARTAMENTO=S.ICVEDEPTOENT INNER JOIN LICPERLICENCIA L ON L.ICVESOLICITUD = S.ICVESOLICITUD INNER JOIN LICPERTIPOLIC PTL ON PTL.ICVESOLICITUD = S.ICVESOLICITUD INNER JOIN LICTIPOLICENCIA TL ON TL.ICVETIPOLICENCIA = PTL.ICVETIPOLICENCIA   LEFT OUTER JOIN SEGUSUARIO USR ON USR.ICVEUSUARIO = L.ICVEUSUARIO LEFT OUTER JOIN LICTIPOMEDPREV MEDP   ON MEDP.ICVETIPOLICENCIA = PTL.ICVETIPOLICENCIA   AND MEDP.ICVEMDOTRANS =  " + (
         iCveMdoTrans != null ? iCveMdoTrans : "1") + 
         " LEFT OUTER JOIN GRLCENTROSCT GCEN ON GCEN.ICVECENTROSCT = DEP.ICVECENTROSCT" + 
         " WHERE S.ICVESOLICITUD = " + idSolicitud.toString() + 
         " and PTL.ICVETIPOLICENCIA = " + idTipoLicencia.toString();
 
       vcRecords = FindByGeneric2("S.ICVESOLICITUD", cSQL, this.dataSourceName);
 
       if ((vcRecords != null) && (!vcRecords.isEmpty()))
       {
         TVDinRep vInfo = (TVDinRep)vcRecords.get(0);
 
         if (vInfo != null) {
           persona = new UsuarioDTO();
 
           persona.setCNombre(vInfo.getString("CNOMBRE"));
           persona.setCApPaterno(vInfo.getString("CAPPATERNO"));
           persona.setCApMaterno(vInfo.getString("CAPMATERNO"));
           persona.setCCalle(vInfo.getString("CCALLE"));
           persona.setCColonia(vInfo.getString("CCOLONIA"));
           persona.setNumExterior(vInfo.getString("CNUMEXT"));
           persona.setNumInterior(vInfo.getString("CNUMINT"));
           persona.setCDscEntidadFed(vInfo.getString("ENT_FED"));
           persona.setDescEstado(vInfo.getString("CDSCESTADO"));
           persona.setCodigoPostal(vInfo.getString("ICP"));
 
           persona.setcDescNacionalidad(vInfo.getString("CDSCNACIONAL"));
           //persona.setFechaNacimiento(vInfo.getDate("DTNACIMIENTO"));
           persona.setRfc(vInfo.getString("CRFC"));
           persona.setCCurp(vInfo.getString("CCURP"));
           persona.setICvePersonal(vInfo.getInt("ICVEPERSONAL"));
 
           LicenciaDTO licencia = new LicenciaDTO();
 
           licencia.setDescLugarExpedicion(vInfo.getString("CDSCCSCT"));
 
           licencia.setIdDepartamento(vInfo.getInt("ICVEDEPARTAMENTO"));
 
           licencia.setIdLicencia(vInfo.getString("CLICENCIA"));
 
           //licencia.setVigenciaInicial(vInfo.getDate("DTINICIO"));
 
           //licencia.setVigenciaFinal(vInfo.getDate("DTFIN"));
 
           licencia.setNombreAutoriza(vInfo.getString("NOMBRE_X"));
           licencia.setApellidoPaternoAutoriza(vInfo.getString("PATERNO_X"));
           licencia.setApellidoMaternoAutoriza(vInfo.getString("MATERNO_X"));
 
           licencia.setDescTipoLicencia(vInfo.getString("CDSCTIPOLICENCIA"));
 
           licencia.setDescAbreviacionTipoLicencia(vInfo.getString("CABREVIACION"));
 
           String cConvalida = "";
 
           if ((!vInfo.getString("cConvalida").equals("null")) && (!vInfo.getString("cConvalida").equals(""))) {
             cConvalida = ", Convalidación sujeta a Licencia extranjera número " + vInfo.getString("cConvalida") + 
               "/ This document is valid with foreing license number " + vInfo.getString("cConvalida");
           }
           licencia.setDescObservacion(vInfo.getString("COBSERVACION") + cConvalida);
 
           licencia.setIdCategoria(vInfo.getInt("ICVECATEGORIA"));
 
           persona.setLicencia(licencia);
         }
       }
       else {
         System.out.println("Logger::No existen datos de la persona");
       }
     }
     catch (Exception e) {
       throw e;
     }
 
     return persona;
   }
 
   public UsuarioDTO findPersonaPTLEsp(Integer idSolicitud, Integer idTipoLicencia, String iCveMdoTrans) throws Exception {
     Vector vcRecords = null;
     UsuarioDTO persona = null;
     try
     {
       String cSQL = 
         "SELECT P.CNOMBRE,P.CAPPATERNO,P.CAPMATERNO, D.CCALLE,D.CNUMEXT,D.CNUMINT,D.CCOLONIA, MUN.CNOMBRE AS ENT_FED, EST.CDSCESTADO, D.ICP, S.ICVEDEPARTAMENTO,  NAC.CDSCNACIONAL AS CDSCNACIONAL, P.DTNACIMIENTO, P.CRFC, DEP.CDSCDEPARTAMENTO, L.DTINICIO, L.DTFIN,  L.CLICENCIA, TL.CDSCTIPOLICENCIA, 'Grupo Sanguineo:' || cGpoSang  ||  case when LRH = 1 then ', RH:Positivo' else ' RH:Negativo' end ||  case when LUSALENTES = 1 AND LCONTACTO <> 1 then ', Usa Lentes' else '' end ||  case when LUSALENTES = 1 AND LCONTACTO = 1 then ', Usa Lentes de Contacto' else '' end  as COBSERVACION, TL.CABREVIACION,  MEDP.ICVECATEGORIA, GCEN.CDSCCSCT, L.TSIMPRESION, USR.CNOMBRE AS NOMBRE_X, USR.CAPPATERNO AS PATERNO_X, USR.CAPMATERNO AS MATERNO_X,P.ICVEPERSONAL,(SELECT cValor FROM LICSOLREQEXTRAS LSRE where LSRE.ICVESOLICITUD = S.ICVESOLICITUD and LSRE.CNOMBRECAMPO = 'cLicExtNum') as cConvalida, P.CCURP FROM LICSOLICITUD S INNER JOIN PERDATOS P    ON P.ICVEPERSONAL = S.ICVEPERSONAL INNER JOIN PERDIRECCION D   ON D.ICVEPERSONAL = P.ICVEPERSONAL AND D.ICVEDIRECCION = (CASE P.ICVEDIRECCION WHEN 0 THEN 1 ELSE P.ICVEDIRECCION END) LEFT OUTER JOIN GRLMUNICIPIO MUN   ON D.ICVEPAIS = MUN.ICVEPAIS   AND D.ICVEESTADO = MUN.ICVEENTIDADFED   AND D.ICVEMUNICIPIO = MUN.ICVEMUNICIPIO LEFT OUTER JOIN GRLESTADO EST    ON EST.ICVEPAIS = D.ICVEPAIS   AND EST.ICVEESTADO = D.ICVEESTADO INNER JOIN GRLNACIONALIDAD NAC ON NAC.ICVEPAIS = P.ICVEPAISNAC INNER JOIN GRLDEPARTAMENTO DEP ON DEP.ICVEDEPARTAMENTO=S.ICVEDEPTOENT INNER JOIN LICPERTIPOLIC PTL ON PTL.ICVESOLICITUD = S.ICVESOLICITUD INNER JOIN LICPERLICENCIA L ON L.CLICENCIA = PTL.CLICENCIA AND L.DTFIN > '" + 
         new java.sql.Date(Calendar.getInstance().getTime().getTime()) + "' " + 
         " INNER JOIN LICTIPOLICENCIA TL ON TL.ICVETIPOLICENCIA = PTL.ICVETIPOLICENCIA  " + 
         " LEFT OUTER JOIN SEGUSUARIO USR ON USR.ICVEUSUARIO = L.ICVEUSUARIO" + 
         " LEFT OUTER JOIN LICTIPOMEDPREV MEDP" + 
         "   ON MEDP.ICVETIPOLICENCIA = PTL.ICVETIPOLICENCIA" + 
         "   AND MEDP.ICVEMDOTRANS =  " + (iCveMdoTrans != null ? iCveMdoTrans : "1") + 
         " LEFT OUTER JOIN GRLCENTROSCT GCEN ON GCEN.ICVECENTROSCT = DEP.ICVECENTROSCT" + 
         " WHERE PTL.ICVESOLICITUD = " + idSolicitud.toString() + 
         " and PTL.ICVETIPOLICENCIA = " + idTipoLicencia.toString() + 
         " ORDER BY PTL.DTBAJA DESC";
 
       vcRecords = FindByGeneric2("S.ICVESOLICITUD", cSQL, this.dataSourceName);
 
       if ((vcRecords != null) && (!vcRecords.isEmpty()))
       {
         TVDinRep vInfo = (TVDinRep)vcRecords.get(0);
 
         if (vInfo != null) {
           persona = new UsuarioDTO();
 
           persona.setCNombre(vInfo.getString("CNOMBRE"));
           persona.setCApPaterno(vInfo.getString("CAPPATERNO"));
           persona.setCApMaterno(vInfo.getString("CAPMATERNO"));
           persona.setCCalle(vInfo.getString("CCALLE"));
           persona.setCColonia(vInfo.getString("CCOLONIA"));
           persona.setNumExterior(vInfo.getString("CNUMEXT"));
           persona.setNumInterior(vInfo.getString("CNUMINT"));
           persona.setCDscEntidadFed(vInfo.getString("ENT_FED"));
           persona.setDescEstado(vInfo.getString("CDSCESTADO"));
           persona.setCodigoPostal(vInfo.getString("ICP"));
 
           persona.setcDescNacionalidad(vInfo.getString("CDSCNACIONAL"));
           //persona.setFechaNacimiento(vInfo.getDate("DTNACIMIENTO"));
           persona.setRfc(vInfo.getString("CRFC"));
           persona.setCCurp(vInfo.getString("CCURP"));
           persona.setICvePersonal(vInfo.getInt("ICVEPERSONAL"));
 
           LicenciaDTO licencia = new LicenciaDTO();
 
           licencia.setDescLugarExpedicion(vInfo.getString("CDSCCSCT"));
 
           licencia.setIdDepartamento(vInfo.getInt("ICVEDEPARTAMENTO"));
 
           licencia.setIdLicencia(vInfo.getString("CLICENCIA"));
 
           //licencia.setVigenciaInicial(vInfo.getDate("DTINICIO"));
 
           //licencia.setVigenciaFinal(vInfo.getDate("DTFIN"));
 
           licencia.setNombreAutoriza(vInfo.getString("NOMBRE_X"));
           licencia.setApellidoPaternoAutoriza(vInfo.getString("PATERNO_X"));
           licencia.setApellidoMaternoAutoriza(vInfo.getString("MATERNO_X"));
 
           licencia.setDescTipoLicencia(vInfo.getString("CDSCTIPOLICENCIA"));
 
           licencia.setDescAbreviacionTipoLicencia(vInfo.getString("CABREVIACION"));
 
           String cConvalida = "";
 
           if ((!vInfo.getString("cConvalida").equals("null")) && (!vInfo.getString("cConvalida").equals(""))) {
             cConvalida = ", Convalidación sujeta a Licencia extranjera número " + vInfo.getString("cConvalida") + 
               "/ This document is valid with foreing license number " + vInfo.getString("cConvalida");
           }
           licencia.setDescObservacion(vInfo.getString("COBSERVACION") + cConvalida);
 
           licencia.setIdCategoria(vInfo.getInt("ICVECATEGORIA"));
 
           persona.setLicencia(licencia);
         }
       }
       else {
         System.out.println("Logger::No existen datos de la persona");
       }
     }
     catch (Exception e) {
       throw e;
     }
 
     return persona;
   }
 }

/* Location:           C:\Users\UTIC\Desktop\produccion\elic\WEB-INF\classes\
 * Qualified Name:     gob.sct.elic.dao.TDLICSOLICITUD
 * JD-Core Version:    0.6.0
 */