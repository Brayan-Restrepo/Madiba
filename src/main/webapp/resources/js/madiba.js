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
    
	$(".mascara").each(function(key, element){
		$(element).text(mascaraPrecio(parseFloat($(element).text())));
	});
});


function mascaraPrecio(numFloat){
    var cadenaMascarada = numeral(numFloat).format('$ 0,0.00')
    while (cadenaMascarada.toString().indexOf(",") != -1){
        cadenaMascarada = cadenaMascarada.toString().replace(",","|");
    }
    while (cadenaMascarada.toString().indexOf(".") != -1){
        cadenaMascarada = cadenaMascarada.toString().replace(".",",");
    }
    while (cadenaMascarada.toString().indexOf("|") != -1){
        cadenaMascarada = cadenaMascarada.toString().replace("|",".");
    }
    return cadenaMascarada;
}