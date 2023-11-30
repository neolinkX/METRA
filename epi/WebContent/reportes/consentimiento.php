<?php
require_once('../vendor/setasign/fpdf/fpdf.php');
require_once('../vendor/setasign/fpdi/fpdi.php');
require_once('../vendor/visualmx/PDF_MC_Table/PDF_MC_Table.php');
class CONSENTIMIENTO extends PDF_MC_Table
{
        var $datos_p;
        var $edad_persona;
        var $sexo_persona;
        var $num_expediente;
        var $datos_episodio;

       function setConfig($var,$val)
       {
              $this->{$var} = $val;
       }

       function Header()
       {
              $this->setSourceFile("../resources/plantillas_pdf/consentimiento.pdf");
              $tplIdx = $this->importPage(1);
              // pagina importada posicion inicial 10,10 ancho de 210 mm y alto de 300 mm
              $this->useTemplate($tplIdx, 1, 1, 210, 280);
              $this->SetFont('Helvetica','',10);
       }
       function printdata(){
              $this->AddPage();
              $this->SetFont('Helvetica','',10);
              $this->SetDrawColor(255);
              $this->SetLineWidth(.3);
              $dom = $this->datos_p->dom_calle.' '.$this->datos_p->dom_numext.' '.$this->datos_p->dom_numint.' '.$this->datos_p->dom_colonia;
              $this->Text(25, 47, utf8_decode($dom));

              $this->Text(143, 47, $this->datos_episodio['fecha_alta']);

              $this->Text(187, 47, $this->datos_episodio['hora_alta']);

              $um = "Unidad médica o tercero";
              $this->Text(45, 56, utf8_decode($um));

              $np = $this->datos_p->nombre_paciente.' '.$this->datos_p->apaterno_paciente.' '.$this->datos_p->amaterno_paciente;
              $this->Text(25, 62, utf8_decode($np));


              $this->Text(143, 62, $this->edad_persona);

              $this->Text(25, 66, $this->datos_p->rfc);

              $this->Text(110, 66, $this->datos_p->curp);

              $this->Text(32, 71, $this->num_expediente);

              $this->Text(32, 76, $this->sexo_persona);

              $this->SetXY(5,83);
              $this->MultiCell(35, 5, utf8_decode($this->datos_episodio['id_modotransporte_tramite']), 0, 'C', false);
              $this->SetXY(40,83);
              $this->MultiCell(30, 5, utf8_decode($this->datos_episodio['id_categoria_tramite']), 0, 'C', false);
              $this->SetXY(70,83);
              $this->MultiCell(40, 5, utf8_decode($this->datos_episodio['id_motivo_tramite']), 0, 'C', false);

       }
}

$pdf = new CONSENTIMIENTO($orientation='P', $unit='mm', $size='LETTER');
$pdf->setConfig('datos_p',$datos_p);
$pdf->setConfig('edad_persona',$edad_persona);
$pdf->setConfig('sexo_persona',$sexo_persona);
$pdf->setConfig('num_expediente',$num_expediente);
$pdf->setConfig('datos_episodio',$datos_episodio);

$pdf->printdata();
$pdf->Output('../public/tmp/'.$token.'.pdf','F');
