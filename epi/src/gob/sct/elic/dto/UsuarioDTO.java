 package gob.sct.elic.dto;
 
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.io.Serializable;
 
 public class UsuarioDTO
   implements Serializable
 {
   int iCveusuario;
   java.sql.Date dtRegistro;
   String cUsuario;
   String cPassword;
   String cNombre;
   String cApPaterno;
   String cApMaterno;
   String cCalle;
   String cColonia;
   String numInterior;
   String numExterior;
   int iCvePais;
   int centroCapacitacion;
   String cDscPais;
   String cDescNacionalidad;
   String rfc;
   java.util.Date fechaNacimiento;
   int iCveEntidadFed;
   String cDscEntidadFed;
   String descEstado;
   int iCveMunicipio;
   String cDscMunicipio;
   int iCodigoPostal;
   String codigoPostal;
   String cTelefono;
   int iCveUnidadOrg;
   int lBloqueado;
   String cID;
   int iCvePersonal;
   String cCurp;
   private String correoElectronico;
   private String telefono;
   LicenciaDTO licencia;
 
   public int getICvePersonal()
   {
     return this.iCvePersonal;
   }
 
   public void setICvePersonal(int cvePersonal) {
     this.iCvePersonal = cvePersonal;
   }
 
   private void readObject(ObjectInputStream ois)
     throws ClassNotFoundException, IOException
   {
     ois.defaultReadObject();
   }
 
   private void writeObject(ObjectOutputStream oos) throws IOException {
     oos.defaultWriteObject();
   }
 
   public String getID() {
     return this.cID;
   }
 
   public void setID(String cID) {
     this.cID = cID;
   }
 
   public String getCApMaterno() {
     return this.cApMaterno;
   }
 
   public String getCApPaterno() {
     return this.cApPaterno;
   }
 
   public String getCCalle() {
     return this.cCalle;
   }
 
   public String getCNombre() {
     return this.cNombre;
   }
 
   public String getCColonia() {
     return this.cColonia;
   }
 
   public String getNumExterior() {
     return this.numExterior;
   }
 
   public void setNumExterior(String numExterior) {
     this.numExterior = numExterior;
   }
 
   public String getNumInterior() {
     return this.numInterior;
   }
 
   public void setNumInterior(String numInterior) {
     this.numInterior = numInterior;
   }
 
   public String getCPassword() {
     return this.cPassword;
   }
 
   public String getCTelefono() {
     return this.cTelefono;
   }
 
   public String getCUsuario() {
     return this.cUsuario;
   }
 
   public java.sql.Date getDtRegistro() {
     return this.dtRegistro;
   }
 
   public int getICveEntidadFed() {
     return this.iCveEntidadFed;
   }
 
   public int getICveMunicipio() {
     return this.iCveMunicipio;
   }
 
   public int getICvePais() {
     return this.iCvePais;
   }
 
   public int getICveUnidadOrg() {
     return this.iCveUnidadOrg;
   }
 
   public int getICveusuario() {
     return this.iCveusuario;
   }
 
   public int getLBloqueado() {
     return this.lBloqueado;
   }
 
   public void setLBloqueado(int lBloqueado) {
     this.lBloqueado = lBloqueado;
   }
 
   public void setICveusuario(int iCveusuario) {
     this.iCveusuario = iCveusuario;
   }
 
   public void setICveUnidadOrg(int iCveUnidadOrg) {
     this.iCveUnidadOrg = iCveUnidadOrg;
   }
 
   public void setICvePais(int iCvePais) {
     this.iCvePais = iCvePais;
   }
 
   public void setICveMunicipio(int iCveMunicipio) {
     this.iCveMunicipio = iCveMunicipio;
   }
 
   public void setICveEntidadFed(int iCveEntidadFed) {
     this.iCveEntidadFed = iCveEntidadFed;
   }
 
   public void setICodigoPostal(int iCodigoPostal) {
     this.iCodigoPostal = iCodigoPostal;
   }
 
   public void setDtRegistro(java.sql.Date dtRegistro) {
     this.dtRegistro = dtRegistro;
   }
 
   public void setCUsuario(String cUsuario) {
     this.cUsuario = cUsuario;
   }
 
   public void setCTelefono(String cTelefono) {
     this.cTelefono = cTelefono;
   }
 
   public void setCPassword(String cPassword) {
     this.cPassword = cPassword;
   }
 
   public void setCNombre(String cNombre) {
     this.cNombre = cNombre;
   }
 
   public void setCColonia(String cColonia) {
     this.cColonia = cColonia;
   }
 
   public void setCCalle(String cCalle) {
     this.cCalle = cCalle;
   }
 
   public void setCApPaterno(String cApPaterno) {
     this.cApPaterno = cApPaterno;
   }
 
   public void setCApMaterno(String cApMaterno) {
     this.cApMaterno = cApMaterno;
   }
 
   public String getCDscEntidadFed() {
     return this.cDscEntidadFed;
   }
 
   public void setCDscEntidadFed(String cDscEntidadFed) {
     this.cDscEntidadFed = cDscEntidadFed;
   }
 
   public String getCDscMunicipio() {
     return this.cDscMunicipio;
   }
 
   public void setCDscMunicipio(String cDscMunicipio) {
     this.cDscMunicipio = cDscMunicipio;
   }
 
   public String getCDscPais() {
     return this.cDscPais;
   }
 
   public void setCDscPais(String cDscPais) {
     this.cDscPais = cDscPais;
   }
 
   public String getcDescNacionalidad() {
     return this.cDescNacionalidad;
   }
 
   public void setcDescNacionalidad(String cDescNacionalidad) {
     this.cDescNacionalidad = cDescNacionalidad;
   }
 
   public String getRfc() {
     return this.rfc;
   }
 
   public void setRfc(String rfc) {
     this.rfc = rfc;
   }
 
   public java.util.Date getFechaNacimiento() {
     return this.fechaNacimiento;
   }
 
   public void setFechaNacimiento(java.util.Date fechaNacimiento) {
     this.fechaNacimiento = fechaNacimiento;
   }
 
   public LicenciaDTO getLicencia() {
     return this.licencia;
   }
 
   public void setLicencia(LicenciaDTO licencia) {
     this.licencia = licencia;
   }
 
   public String getCodigoPostal() {
     return this.codigoPostal;
   }
 
   public void setCodigoPostal(String codigoPostal) {
     this.codigoPostal = codigoPostal;
   }
 
   public String getDescEstado() {
     return this.descEstado;
   }
 
   public void setDescEstado(String descEstado) {
     this.descEstado = descEstado;
   }
 
   public int getCentroCapacitacion() {
     return this.centroCapacitacion;
   }
 
   public void setCentroCapacitacion(int centroCapacitacion) {
     this.centroCapacitacion = centroCapacitacion;
   }
 
   public String getCorreoElectronico() {
     return this.correoElectronico;
   }
 
   public void setCorreoElectronico(String correoElectronico) {
     this.correoElectronico = correoElectronico;
   }
 
   public String getCCurp() {
     return this.cCurp;
   }
 
   public void setCCurp(String curp) {
     this.cCurp = curp;
   }
 
   public String getTelefono() {
     return this.telefono;
   }
 
   public void setTelefono(String telefono) {
     this.telefono = telefono;
   }
 
   public void destroy()
   {
   }
 }

/* Location:           C:\Users\UTIC\Desktop\produccion\elic\WEB-INF\classes\
 * Qualified Name:     gob.sct.elic.dto.UsuarioDTO
 * JD-Core Version:    0.6.0
 */