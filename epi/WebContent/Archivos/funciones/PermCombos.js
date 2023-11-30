
  function fActualizaCombo(iOpc, forma, oComboO, oComboA, filtro1, filtro2, filtro3){
	  while (oComboA.length > 0) oComboA.options[0]=null;
      oComboA.options[0] = new Option("Cargando Datos...","-1");
	  // Submitir al Panel de Llenado
      window.parent.FRMOtroPanel.location="pg07PermCombos.jsp?"+
                                          "iOpc="      + iOpc         + // Opción de llenado
                                          "&cCombo="   + oComboA.name + // Nombre del combo a llenar
                	                      "&hdAccion=" + document.forms[0].action + // Action
                                          "&iFiltro1=" + filtro1 +  // iCveUniMed
										  "&iFiltro2=" + filtro2 +  // iCveModulo
                                          "&iFiltro3=" + filtro3 ;  // iCveProceso

  }

