 package gob.sct.elic.dto;
 
 import java.util.Date;
 
 public class SolicitudDTO
 {
   private int idSolicitud;
   private Date fechaSolicitud;
   private Date fechaEntrega;
   private String descOficinaEntrega;
   private String descOficinaRecepcion;
 
   public int getIdSolicitud()
   {
     return this.idSolicitud;
   }
 
   public void setIdSolicitud(int idSolicitud) {
     this.idSolicitud = idSolicitud;
   }
 
   public Date getFechaSolicitud() {
     return this.fechaSolicitud;
   }
 
   public void setFechaSolicitud(Date fechaSolicitud) {
     this.fechaSolicitud = fechaSolicitud;
   }
 
   public String getDescOficinaEntrega() {
     return this.descOficinaEntrega;
   }
 
   public void setDescOficinaEntrega(String descOficinaEntrega) {
     this.descOficinaEntrega = descOficinaEntrega;
   }
 
   public String getDescOficinaRecepcion() {
     return this.descOficinaRecepcion;
   }
 
   public void setDescOficinaRecepcion(String descOficinaRecepcion) {
     this.descOficinaRecepcion = descOficinaRecepcion;
   }
 
   public Date getFechaEntrega() {
     return this.fechaEntrega;
   }
 
   public void setFechaEntrega(Date fechaEntrega) {
     this.fechaEntrega = fechaEntrega;
   }
 }

/* Location:           C:\Users\UTIC\Desktop\produccion\elic\WEB-INF\classes\
 * Qualified Name:     gob.sct.elic.dto.SolicitudDTO
 * JD-Core Version:    0.6.0
 */