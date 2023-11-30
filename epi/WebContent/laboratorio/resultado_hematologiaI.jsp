<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.*"%>    
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%  
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
String CodigoValida = "";
%>    
<div class="form-group m-form__group">
  <div class="alert m-alert m-alert--default" role="alert">
    <b>RAMA HEMATOLOG&Iacute;A I</b>
  </div>
</div>
<div class="row">
  <form class="m-form m-form--fit m-form--group-seperator-dashed" id="frm_hematologiaI" name="frm_hematologiaI">
    <div class="col-md-12">
      <div class="m-portlet__body">
        <div class="m-section">

            <div class="form-group m-form__group row">
              <label for="hem_sedimentacion_globular" class="col-3 col-form-label">
                 Sedimentación Globular
              </label>
              <div class="col-4">
                <input type="text" class="form-control m-input" disabled value="<?=$datos_hematologiai[0]['hem_sedimentacion_globular']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  0-15
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_granulocitos" class="col-3 col-form-label">
                 Granulocitos
               </label>
              <div class="col-4">
                <input type="text" class="form-control m-input" disabled value="<?=$datos_hematologiai[0]['hem_granulocitos']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  % &nbsp;&nbsp;&nbsp; Rango: &nbsp; 1.14 - 6.5 x 10 / uL
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="hem_examenes_practicados" class="col-3 col-form-label">
                  Otros Examenes Practicados
                </label>
              <div class="col-9">
                <textarea class="form-control" name="hem_examenes_practicados" id="hem_examenes_practicados" rows="2" maxlength="500" disabled><?php echo ucwords($datos_hematologiai[0]['hem_examenes_practicados']); ?></textarea>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="leucocitos" class="col-3 col-form-label">
                  Leucocitos
                </label>
              <div class="col-4">
                <input type="text" class="form-control m-input" disabled value="<?=$datos_hematologiai[0]['hem_leucocitos']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  10*/ul
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="example-number-input" class="col-3 col-form-label">
                  Eritrocitos
                </label>
              <div class="col-4">
                <input type="text" class="form-control m-input" disabled value="<?=$datos_hematologiai[0]['hem_eritrocitos']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  10*/ul
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_hemoglobina" class="col-3 col-form-label">
                  Hemoglobina
                </label>
              <div class="col-4">
                <input type="text" class="form-control m-input" disabled value="<?=$datos_hematologiai[0]['hem_hemoglobina']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  g/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; H 14 - 18 M 12.5 - 16.0
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_hematocrito" class="col-3 col-form-label">
                  Hematocrito
                </label>
              <div class="col-4">
                <input type="text" class="form-control m-input" disabled value="<?=$datos_hematologiai[0]['hem_hematocrito']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                 % &nbsp;&nbsp;&nbsp; Rango: &nbsp;  H 40 - 54 M 38 - 47
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_mvc" class="col-3 col-form-label">
                  Volumen Corpuscular Medio (MCV)
                </label>
              <div class="col-4">
                <input class="form-control m-input" type="number" name="hem_mvc" disabled value="<?=$datos_hematologiai[0]['hem_mvc']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  fL
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_cmhb" class="col-3 col-form-label">
                  Concentración Media de HB (CMHB)
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_cmhb" disabled value="<?=$datos_hematologiai[0]['hem_cmhb']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  % &nbsp;&nbsp;&nbsp; Rango: &nbsp; H 31 - 35 M 31 - 34
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_mch" class="col-3 col-form-label">
                  Concentración Media de Hemoglobina (MCH)
              </label>
              <div class="col-4">
                <input class="form-control" type="number" name="hem_mch" disabled value="<?=$datos_hematologiai[0]['hem_cmhb']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  pg
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_ade" class="col-3 col-form-label">
                  Ancho de Distribución Eritrocitaria (ADE)
              </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_ade" disabled value="<?=$datos_hematologiai[0]['hem_ade']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  %
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_neutrofilos" class="col-3 col-form-label">
                  Neutrofilos
              </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_neutrofilos" id="hem_neutrofilos" disabled value="<?=$datos_hematologiai[0]['hem_neutrofilos']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  %
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_linfocitos" class="col-3 col-form-label">
                  Linfocitos
              </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_linfocitos" id="hem_linfocitos" disabled value="<?=$datos_hematologiai[0]['hem_linfocitos']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  %
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_monocitos" class="col-3 col-form-label">
                  Monocitos
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_monocitos" disabled value="<?=$datos_hematologiai[0]['hem_monocitos']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  %
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_eosinofilos" class="col-3 col-form-label">
                  Eosinofilos
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_eosinofilos" disabled value="<?=$datos_hematologiai[0]['hem_eosinofilos']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  %
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_basofilos" class="col-3 col-form-label">
                  Basofilos
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_basofilos" disabled value="<?=$datos_hematologiai[0]['hem_basofilos']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  %
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_neutrofilos10" class="col-3 col-form-label">
                  Neutrofilos
              </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_neutrofilos10" disabled value="<?=$datos_hematologiai[0]['hem_neutrofilos10']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  % &nbsp;&nbsp;&nbsp; Rango: &nbsp; 10*/uL
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_linfocitos10" class="col-3 col-form-label">
                  Linfocitos
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_linfocitos10" disabled value="<?=$datos_hematologiai[0]['hem_linfocitos10']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  % &nbsp;&nbsp;&nbsp; Rango: &nbsp; 10*/uL
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_monocitos10" class="col-3 col-form-label">
                  Monocitos
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_monocitos10" disabled value="<?=$datos_hematologiai[0]['hem_monocitos10']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  % &nbsp;&nbsp;&nbsp; Rango: &nbsp; 10*/uL
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_eosinofilos10" class="col-3 col-form-label">
                  Eosinofilos
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_eosinofilos10" disabled value="<?=$datos_hematologiai[0]['hem_eosinofilos10']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  % &nbsp;&nbsp;&nbsp; Rango: &nbsp; 10*/uL
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_basofilos10" class="col-3 col-form-label">
                  Basofilos
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_basofilos10" disabled value="<?=$datos_hematologiai[0]['hem_basofilos10']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  % &nbsp;&nbsp;&nbsp; Rango: &nbsp; 10*/uL
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_plaquetas" class="col-3 col-form-label">
                  Plaquetas
                </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_plaquetas" disabled value="<?=$datos_hematologiai[0]['hem_plaquetas']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  % &nbsp;&nbsp;&nbsp; Rango: &nbsp; 10*/uL
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_vpm" class="col-3 col-form-label">
                  Volumen Plaquetario Medio (VPM)
              </label>
              <div class="col-4">
                <input class="form-control m-input" type="number" name="hem_vpm" disabled value="<?=$datos_hematologiai[0]['hem_vpm']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  fL
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_plaquetocrito" class="col-3 col-form-label">
                Plaquetocrito
              </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_plaquetocrito" disabled value="<?=$datos_hematologiai[0]['hem_plaquetocrito']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  %
                </div>
              </div>
            </div>

            <div class="form-group m-form__group row">
              <label for="hem_adp" class="col-3 col-form-label">
                Ancho de Distribución Plaquetaria (ADP)
              </label>
              <div class="col-4">
                <input type="number" class="form-control" name="hem_adp" id="hem_adp" disabled value="<?=$datos_hematologiai[0]['hem_adp']?>">
              </div>
              <div class="col-5">
                <div class="alert m-alert m-alert--default" role="alert">
                  %
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="hem_observaciones" class="col-3 col-form-label">
                Observaciones
              </label>
              <div class="col-9">
                <textarea class="form-control m-input m-input--air" id="hem_observaciones" name="hem_observaciones" rows="2" maxlength="500" disabled><?php echo ucwords($datos_hematologiai[0]['hem_observaciones']); ?></textarea>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="hem_metodo" class="col-3 col-form-label">
                Método
              </label>
              <div class="col-9">
                <textarea class="form-control m-input m-input--air" id="hem_metodo" name="hem_metodo" rows="2" maxlength="500" disabled><?php echo ucwords($datos_hematologiai[0]['hem_metodo']); ?></textarea>
              </div>
            </div>
          </div>
        </div>
      </div>    
    </form>
  </div>
</div>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/laboratorio/valida_hemologiaI.js"></script>
