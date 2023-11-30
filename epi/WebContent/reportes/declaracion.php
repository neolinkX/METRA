<?php
require_once('../vendor/setasign/fpdf/fpdf.php');
require_once('../vendor/setasign/fpdi/fpdi.php');
require_once('../vendor/visualmx/PDF_MC_Table/PDF_MC_Table.php');
class DECLARACION extends PDF_MC_Table
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
              $this->setSourceFile("../resources/plantillas_pdf/declaracion.pdf");
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

              $this->Text(55, 37, $this->datos_episodio['fecha_alta']);

              $np = $this->datos_p->nombre_paciente.' '.$this->datos_p->apaterno_paciente.' '.$this->datos_p->amaterno_paciente;
              $this->Text(25, 44, utf8_decode($np));
       }
}

$pdf = new DECLARACION($orientation='P', $unit='mm', $size='LETTER');
$pdf->setConfig('datos_p',$datos_p);
$pdf->setConfig('edad_persona',$edad_persona);
$pdf->setConfig('sexo_persona',$sexo_persona);
$pdf->setConfig('num_expediente',$num_expediente);
$pdf->setConfig('datos_episodio',$datos_episodio);

$pdf->printdata();
$pdf->Output('../public/tmp/'.$token.'.pdf','F');
