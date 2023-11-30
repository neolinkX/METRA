<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <div class="form-group m-form__group">
    <div class="alert m-alert m-alert--default" role="alert">
      <b>RAMA MMPI-2, PAI</b>
    </div>
</div>
<div class="m-portlet__body">
  <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
    <li class="nav-item m-tabs__item">
      <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="true">
        Básicas
      </a>
    </li>
    <li class="nav-item  m-tabs__item">
      <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_2" role="tab" aria-expanded="true">
        Contenido
      </a>
    </li>
    <li class="nav-item  m-tabs__item">
      <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab" aria-expanded="true">
        Suplementarias
      </a>
    </li>
    <li class="nav-item  m-tabs__item">
      <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_4" role="tab" aria-expanded="true">
        Validez
      </a>
    </li>
    <li class="nav-item  m-tabs__item">
      <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_5" role="tab" aria-expanded="true">
        Clínicas
      </a>
    </li>
    <li class="nav-item  m-tabs__item">
      <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_6" role="tab" aria-expanded="true">
        Tratamiento
      </a>
    </li>
    <li class="nav-item  m-tabs__item">
      <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_7" role="tab" aria-expanded="true">
        Interpersonal
      </a>
    </li>
    <li class="nav-item  m-tabs__item">
      <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_8" role="tab" aria-expanded="true">
        Sub Escalas
      </a>
    </li>
  </ul>
  <form class="m-form" name="frm_pai" id="frm_pai">

    <div class="m-form__content">
      <div class="m-alert m-alert--icon alert alert-danger m--hide" role="alert" id="m_form_1_msg">
        <div class="m-alert__icon">
          <i class="la la-warning"></i>
        </div>
        <div class="m-alert__text">
          Favor de Verificar los datos.
        </div>
        <div class="m-alert__close">
          <button type="button" class="close" data-close="alert" aria-label="Close"></button>
        </div>
      </div>
    </div>

    <div class="tab-content">
      <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
        <div class="row">
          <label for="pai_basicasl" class="col-1 col-form-label">
            L:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicasl">
          </div>
          <label for="pai_basicasf" class="col-1 col-form-label">
            F:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicasf">
          </div>
          <label for="pai_basicask" class="col-1 col-form-label">
            K:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicask">
          </div>
          <label for="pai_basicasint" class="col-1 col-form-label">
            ?:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicasint">
          </div>
        </div>
        <div class="row">
          <label for="pai_basicashs" class="col-1 col-form-label">
            HS:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicashs">
          </div>
          <label for="pai_basicasd" class="col-1 col-form-label">
            D:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicasd">
          </div>
          <label for="pai_basicashi" class="col-1 col-form-label">
            HI:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicashi">
          </div>
          <label for="pai_basicasdp" class="col-1 col-form-label">
            DP:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicasdp">
          </div>
        </div>
        <div class="row">
          <label for="pai_basicasmf" class="col-1 col-form-label">
            MF:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicasmf">
          </div>
          <label for="pai_basicaspa" class="col-1 col-form-label">
            PA:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicaspa">
          </div>
          <label for="pai_basicaspt" class="col-1 col-form-label">
            PT:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicaspt">
          </div>
          <label for="pai_basicases" class="col-1 col-form-label">
            ES:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicases">
          </div>
        </div>
        <div class="row">
          <label for="pai_basicasma" class="col-1 col-form-label">
            MA:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicasma">
          </div>
          <label for="pai_basicasis" class="col-1 col-form-label">
            IS:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_basicasis">
          </div>
        </div>
      </div>
      <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
        <div class="row">
          <label for="pai_contenido_ans" class="col-1 col-form-label">
            ANS:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_ans">
          </div>
          <label for="pai_contenido_mie" class="col-1 col-form-label">
            MIE:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_mie">
          </div>
          <label for="pai_contenido_obs" class="col-1 col-form-label">
            OBS:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_obs">
          </div>
          <label for="pai_contenido_dep" class="col-1 col-form-label">
            DEP:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_dep">
          </div>
        </div>
        <div class="row">
          <label for="pai_contenido_sau" class="col-1 col-form-label">
            SAU:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_sau">
          </div>
          <label for="pai_contenido_del" class="col-1 col-form-label">
            DEL:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_del">
          </div>
          <label for="pai_contenido_enj" class="col-1 col-form-label">
            ENJ:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_enj">
          </div>
          <label for="pai_contenido_cin" class="col-1 col-form-label">
            CIN:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_cin">
          </div>
        </div>
        <div class="row">
          <label for="pai_contenido_pas" class="col-1 col-form-label">
            PAS:
          </label>
          <div class="m-form__group form-group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_pas">
          </div>
          <label for="pai_contenido_pta" class="col-1 col-form-label">
            PTA:
          </label>
          <div class="m-form__group form-group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_pta">
          </div>
          <label for="pai_contenido_bae" class="col-1 col-form-label">
            BAE:
          </label>
          <div class="m-form__group form-group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_bae">
          </div>
          <label for="pai_contenido_iso" class="col-1 col-form-label">
            ISO:
          </label>
          <div class="m-form__group form-group col-2">
              <input class="form-control m-input" type="number" name="pai_contenido_iso">
          </div>
        </div>
        <div class="row">
          <label for="pai_contenido_fam" class="col-1 col-form-label">
            FAM:
          </label>
          <div class="m-form__group form-group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_fam">
          </div>
          <label for="pai_contenido_dtr" class="col-1 col-form-label">
            DTR:
          </label>
          <div class="m-form__group form-group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_dtr">
          </div>
          <label for="pai_contenido_rtr" class="col-1 col-form-label">
            RTR:
          </label>
          <div class="m-form__group form-group col-2">
            <input class="form-control m-input" type="number" name="pai_contenido_rtr">
          </div>
        </div>
      </div>

      <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
        <div class="row">
          <label for="pai_sup_a" class="col-1 col-form-label">
            A:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_a">
          </div>
          <label for="pai_sup_a2" class="col-1 col-form-label">
            A:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_a2">
          </div>
          <label for="pai_sup_ar" class="col-1 col-form-label">
            R:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_ar">
          </div>
          <label for="pai_sup_fyo" class="col-1 col-form-label">
            FYO:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_fyo">
          </div>
        </div>
        <div class="row">
          <label for="pai_sup_amac" class="col-1 col-form-label">
            A-MAC:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_amac">
          </div>
          <label for="pai_sup_hr" class="col-1 col-form-label">
            HR:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_hr">
          </div>
          <label for="pai_sup_do" class="col-1 col-form-label">
            DO:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_do">
          </div>
          <label for="pai_sup_rs" class="col-1 col-form-label">
            RS:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_rs">
          </div>
        </div>
        <div class="row">
          <label for="pai_sup_dpr" class="col-1 col-form-label">
            DPR:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_dpr">
          </div>
          <label for="pai_sup_gm" class="col-1 col-form-label">
            GM:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_gm">
          </div>
          <label for="pai_sup_gf" class="col-1 col-form-label">
            GF:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_gf">
          </div>
          <label for="pai_sup_epk" class="col-1 col-form-label">
            EPK:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_epk">
          </div>
        </div>
        <div class="row">
          <label for="pai_sup_eps" class="col-1 col-form-label">
            EPS:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_eps">
          </div>
          <label for="pai_sup_is1" class="col-1 col-form-label">
            IS1:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_is1">
          </div>
          <label for="pai_sup_is2" class="col-1 col-form-label">
            IS2:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_is2">
          </div>
          <label for="pai_sup_is3" class="col-1 col-form-label">
              IS3:
          </label>
          <div class="form-group m-form__group col-2">
            <input class="form-control m-input" type="number" name="pai_sup_is3">
          </div>
        </div>
        <div class="row">
            <label for="pai_sup_fp" class="col-1 col-form-label">
              FP:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_sup_fp">
            </div>
            <label for="pai_sup_invar" class="col-1 col-form-label">
                INVAR:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_sup_invar">
            </div>
            <label for="pai_sup_inver" class="col-1 col-form-label">
              INVER:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_sup_inver">
            </div>
          </div>
        </div>

        <div class="tab-pane" id="m_tabs_6_4" role="tabpanel">
          <div class="row">
            <label for="pai_validez_inc" class="col-1 col-form-label">
              INC:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_validez_inc">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_validez_inf" class="col-1 col-form-label">
              INF:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_validez_inf">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_validez_imn" class="col-1 col-form-label">
              IMN:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_validez_imn">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_validez_imp" class="col-1 col-form-label">
              IMP:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_validez_imp">
              <span class="m-form__help">Puntuación T</span>
            </div>
          </div>
        </div>
        <div class="tab-pane" id="m_tabs_6_5" role="tabpanel">
          <div class="row">
            <label for="pai_clinicas_som" class="col-1 col-form-label">
              SOM:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_som">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_clinicas_ans" class="col-1 col-form-label">
              ANS:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_ans">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_clinicas_tra" class="col-1 col-form-label">
              TRA:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_tra">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_clinicas_dep" class="col-1 col-form-label">
              DEP:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_dep">
              <span class="m-form__help">Puntuación T</span>
            </div>
          </div>
          <div class="row">
            <label for="pai_clinicas_man" class="col-1 col-form-label">
              MAN:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_man">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_clinicas_par" class="col-1 col-form-label">
              PAR:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_par">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_clinicas_esq" class="col-1 col-form-label">
              ESQ:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_esq">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_clinicas_lim" class="col-1 col-form-label">
              LIM:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_lim">
              <span class="m-form__help">Puntuación T</span>
            </div>
          </div>
          <div class="row">
            <label for="pai_clinicas_ant" class="col-1 col-form-label">
              ANT:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_ant">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_clinicas_alc" class="col-1 col-form-label">
              ALC:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_alc">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_clinicas_drg" class="col-1 col-form-label">
              DRG:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_clinicas_drg">
              <span class="m-form__help">Puntuación T</span>
            </div>
          </div>
        </div>

        <div class="tab-pane" id="m_tabs_6_6" role="tabpanel">
          <div class="row">
            <label for="pai_tratamiento_agr" class="col-1 col-form-label">
              AGR:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_tratamiento_agr">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_tratamiento_sui" class="col-1 col-form-label">
              SUI:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_tratamiento_sui">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_tratamiento_est" class="col-1 col-form-label">
              EST:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_tratamiento_est">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_tratamiento_fas" class="col-1 col-form-label">
              FAS:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_tratamiento_fas">
              <span class="m-form__help">Puntuación T</span>
            </div>
          </div>
          <div class="row">
            <label for="pai_tratamiento_rtr" class="col-1 col-form-label">
              RTR:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_tratamiento_rtr">
              <span class="m-form__help">Puntuación T</span>
            </div>
          </div>
        </div>
        <div class="tab-pane" id="m_tabs_6_7" role="tabpanel">
          <div class="row">
            <label for="pai_interpersonal_dom" class="col-1 col-form-label">
              DOM:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_interpersonal_dom">
              <span class="m-form__help">Puntuación T</span>
            </div>
            <label for="pai_interpersonal_afa" class="col-1 col-form-label">
              AFA:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_interpersonal_afa">
              <span class="m-form__help">Puntuación T</span>
            </div>
          </div>
        </div>
        <div class="tab-pane" id="m_tabs_6_8" role="tabpanel">
          <div class="row">
            <label for="pai_subescala_afa" class="col-1 col-form-label">
              SOM-C:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_afa">
            </div>
            <label for="pai_subescala_soms" class="col-1 col-form-label">
              SOM-S:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_soms">
            </div>
            <label for="pai_subescala_somh" class="col-1 col-form-label">
              SOM-H:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_somh">
            </div>
            <label for="pai_subescala_ansc" class="col-1 col-form-label">
              ANS-C:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_ansc">
            </div>
          </div>
          <div class="row">
            <label for="pai_subescala_anse" class="col-1 col-form-label">
              ANS-E:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_anse">
            </div>
            <label for="pai_subescala_ansf" class="col-1 col-form-label">
              ANS-F:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_ansf">
            </div>
            <label for="pai_subescala_trao" class="col-1 col-form-label">
              TRA-O:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_trao">
            </div>
            <label for="pai_subescala_traf" class="col-1 col-form-label">
              TRA-F:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_traf">
            </div>
          </div>
          <div class="row">
            <label for="pai_subescala_trae" class="col-1 col-form-label">
              TRA-E:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_trae">
            </div>
            <label for="pai_subescala_depc" class="col-1 col-form-label">
              DEP-C:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_depc">
            </div>
            <label for="pai_subescala_depe" class="col-1 col-form-label">
              DEP-E:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_depe">
            </div>
            <label for="pai_subescala_depf" class="col-1 col-form-label">
              DEP-F:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_depf">
            </div>
          </div>
          <div class="row">
            <label for="pai_subescala_mana" class="col-1 col-form-label">
              MAN-A:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_mana">
            </div>
            <label for="pai_subescala_mang" class="col-1 col-form-label">
              MAN-G:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_mang">
            </div>
            <label for="pai_subescala_mani" class="col-1 col-form-label">
              MAN-I:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_mani">
            </div>
            <label for="pai_subescala_parh" class="col-1 col-form-label">
              PAR-H:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_parh">
            </div>
          </div>
          <div class="row">
            <label for="pai_subescala_parp" class="col-1 col-form-label">
              PAR-P:
            </label>
            <div class="form-group m-form__group col-2">
              <input class="form-control m-input" type="number" name="pai_subescala_parp">
            </div>
          </div>
          <div class="form-group m-form__group row">
            <label for="subescalas_interpretacion" class="col-3 col-form-label">
              Interpretación de Resultados:
            </label>
            <div class="col-9">
              <textarea maxlength="2000" class="form-control m-input m-input--air" id="subescalas_interpretacion" name="subescalas_interpretacion" rows="3"></textarea>
            </div>
          </div>
        </div>
        <div class="m-form-group m-form__groupportlet__foot m-portlet__foot--fit">
          <div class="m-form__actions">
            <button type="submit" class="btn btn-primary">
              Guardar
            </button>
            <button type="reset" class="btn btn-secondary">
              Limpiar
            </button>
          </div>
        </div>
      </div>
    </div>
</form>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/psicologia/valida_pai.js"></script>
    