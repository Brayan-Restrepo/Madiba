/**
 * 
 */
$(document).ready(function() {
	/*$(document.getElementById('formBotones:btn-liquidar')).click(function(event) {	
    	$('#modalAtualizar').modal();
    });*/
	//Datemask dd/mm/yyyy
    $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
    //Money Euro
    $("[data-mask]").inputmask();
    
    $("#ocultarBusqueda").click(function(){
    	
    	var oculto = $(document.getElementById('formBusqueda:divBusquedas')).css('display');
    	console.log(oculto);
    	if(oculto == "none"){
    		$(document.getElementById('formBusqueda:divBusquedas')).show("swing");
    		$(document.getElementById('formBusqueda:mensaje')).show("swing");
    	}else if(oculto == "block" || oculto=="inline-block"){
    		$(document.getElementById('formBusqueda:divBusquedas')).hide("linear");
    		$(document.getElementById('formBusqueda:mensaje')).hide("linear");
    	}
	 });
});