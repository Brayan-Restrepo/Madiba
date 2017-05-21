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
});