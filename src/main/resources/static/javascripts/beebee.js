//vamos modularizar nosso javascript

//Estamos desclarando nosso objeto global com o nome do nosso projeto

var BeeBee = BeeBee || {}; //verifica se já tem o objeto, se não tiver ele cria um novo

//criando a função contrutora
BeeBee.MaskMoney = (function () { // 1- Criamos esse nameEspace de BeeBee declarado acima
	
	function MaskMoney() { // 2- Criando a função
		
		// 3- Declarando/Inicializando as variaveis
		this.decimal = $('.js-decimal');//fica assim pois estamos inicializando no objeto
		this.plain = $('.js-plain');
	}
	
	// 4- Declarando a execução do comportamento descrito acima no this.decimal ou this.plain ... esta deixamos aqui dentro do "prototype"
	
	MaskMoney.prototype.enable = function() { //colocamos dentro de .enable, pois podemos criar ali em baixo uma função disable, caso seja necessário
		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
		this.plain.maskMoney({precision: 0 , thousands: '.'});
		
	}
		return MaskMoney;// retornamos o resultado	
		
		
//})(); ***asssim ou  //para executar essa função contrutora tem que informar () em um dos dois lugares no começo dessa linha

}()); // tanto faz colocar o "()" aqui ou na forma descrita acima

//*** finalizando a declaração do objeto

/***
 * Mascara de telefone para números de celular de são paulo com o digito 9 a mais
 */

BeeBee.MaskPhoneNumber = (function() {
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $(".js-phone-number");
	}	
	
	MaskPhoneNumber.prototype.enable = function() {
		var maskBehavior = function(val) {
			return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		    }
		};
		this.inputPhoneNumber.mask(maskBehavior, options);
		
		
	}
	return MaskPhoneNumber;

}());
$(function() { // 5- Executando nosso objeto criado acima
	
	//essa variavel poderia ser qq nome, esta com esse nome para facilitar e ficar mais legivel o código
	var maskMoney = new BeeBee.MaskMoney();
	maskMoney.enable();
	
	var maskPhoneNumber = new BeeBee.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
});
	
/**** TUDO ISSO VAI ALI PARA CIMA AS VAR SE TORNAM this.decimal... e this plain...
$(function() {
	var decimal = $('.js-decimal');
	decimal.maskMoney({ decimal: ',', thousands: '.'});
	
	var plain = $('.js-plain');
	plain.maskMoney({precision: 0 , thousands: '.'});
});

*/ 