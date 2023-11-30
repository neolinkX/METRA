 package gob.sct.elic.dto;
 
 import java.util.Date;
 
 public class LicenciaDTO
 {
   private String idLicencia;
   private Date vigenciaInicial;
   private Date vigenciaFinal;
   private int idDepartamento;
   private String descLugarExpedicion;
   private String descTipoLicencia;
   private String descAbreviacionTipoLicencia;
   private String descObservacion;
   private int idCategoria;
   private String nombreAutoriza;
   private String apellidoPaternoAutoriza;
   private String apellidoMaternoAutoriza;
 
   public String getDescLugarExpedicion()
   {
     return this.descLugarExpedicion;
   }
 
   public void setDescLugarExpedicion(String descLugarExpedicion) {
     this.descLugarExpedicion = descLugarExpedicion;
   }
 
   public Date getVigenciaFinal() {
     return this.vigenciaFinal;
   }
 
   public void setVigenciaFinal(Date vigenciaFinal) {
     this.vigenciaFinal = vigenciaFinal;
   }
 
   public Date getVigenciaInicial() {
     return this.vigenciaInicial;
   }
 
   public void setVigenciaInicial(Date vigenciaInicial) {
     this.vigenciaInicial = vigenciaInicial;
   }
 
   public String getIdLicencia() {
     return this.idLicencia;
   }
 
   public void setIdLicencia(String idLicencia) {
     this.idLicencia = idLicencia;
   }
 
   public String getDescTipoLicencia() {
     return this.descTipoLicencia;
   }
 
   public void setDescTipoLicencia(String descTipoLicencia) {
     this.descTipoLicencia = descTipoLicencia;
   }
 
   public String getDescObservacion() {
     return this.descObservacion;
   }
 
   public void setDescObservacion(String descObservacion) {
     this.descObservacion = descObservacion;
   }
 
   public String getDescAbreviacionTipoLicencia() {
     return this.descAbreviacionTipoLicencia;
   }
 
   public void setDescAbreviacionTipoLicencia(String descAbreviacionTipoLicencia) {
     this.descAbreviacionTipoLicencia = descAbreviacionTipoLicencia;
   }
 
   public int getIdCategoria() {
     return this.idCategoria;
   }
 
   public void setIdCategoria(int idCategoria) {
     this.idCategoria = idCategoria;
   }
 
   public String getNombreAutoriza() {
     return this.nombreAutoriza;
   }
 
   public void setNombreAutoriza(String nombreAutoriza) {
     this.nombreAutoriza = nombreAutoriza;
   }
 
   public String getApellidoPaternoAutoriza() {
     return this.apellidoPaternoAutoriza;
   }
 
   public void setApellidoPaternoAutoriza(String apellidoPaternoAutoriza) {
     this.apellidoPaternoAutoriza = apellidoPaternoAutoriza;
   }
 
   public String getApellidoMaternoAutoriza() {
     return this.apellidoMaternoAutoriza;
   }
 
   public void setApellidoMaternoAutoriza(String apellidoMaternoAutoriza) {
     this.apellidoMaternoAutoriza = apellidoMaternoAutoriza;
   }
 
   public int getIdDepartamento() {
     return this.idDepartamento;
   }
 
   public void setIdDepartamento(int idDepartamento) {
     this.idDepartamento = idDepartamento;
   }
 }

/* Location:           C:\Users\UTIC\Desktop\produccion\elic\WEB-INF\classes\
 * Qualified Name:     gob.sct.elic.dto.LicenciaDTO
 * JD-Core Version:    0.6.0
 */