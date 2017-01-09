var BeeBee = BeeBee || {};

BeeBee.ComboEstado = (function() {
	
	function ComboEstado() {
		this.combo = $('#estado');
		this.emitter = $({}); //criando um objeto do jquery
		this.on = this.emitter.on.bind(this.emitter); //com a função "on" vou conseguir lançar eventos através do objeto "emmiter"
	}
	
	ComboEstado.prototype.iniciar = function() {
		this.combo.on('change', onEstadoAlterado.bind(this)); 
		
	}
	
	function onEstadoAlterado() {
		//console.log('estado selecionado', this.combo.val());*** Só para conferir no console se está pegando o valor correto do estado
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboEstado;
	
}());

BeeBee.ComboCidade = (function() {
	
	function ComboCidade(comboEstado) {
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
		this.imgLoading = $('.js-img-loading');
		
	}
	
	ComboCidade.prototype.iniciar = function() {
		reset.call(this);
		this.comboEstado.on('alterado', onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado(evento, codigoEstado) { //Essa function é para quando o estado for alterado no combo estado mesmo.
		//console.log('codigo do estado no combo cidade', codigoEstado); *** Esse console exibe o código do Estado dentro do objeto Cidade, ou seja quando selecionar um estado lá no console o que será exibido é o cod. estado que foi passado para Cidade.
		
		if (codigoEstado) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'estado': codigoEstado},
				beforeSend : iniciarRequisicao.bind(this),
				complete : finalizarRequisicao.bind(this)
			});
			
			resposta.done(onBuscarCidadesFinalizado.bind(this));
		}else {
			reset.call(this); //reseta o combo de cidades para ficar desabilitado de novo caso não tenha um código de cidade
		}
	}
	
	//Essa function entra em ação depois que retorna do servidor a resposta do método json pesquisaPorCodigoEstado
	function onBuscarCidadesFinalizado(cidades) {
		var options = [];
		cidades.forEach(function(cidade) {
			options.push('<option value"' + cidade.codigo + '">' + cidade.nome + '</option>')//está acrescentando o option do combo de cidades.
		});
		
		this.combo.html(options.join(''));//o join junta todo o código do <option value... descrito acima no push()>
		this.combo.removeAttr('disabled');//habilita o combo de cidade qdo escolhe um estado
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione a cidade</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function iniciarRequisicao() {//para aparecer o icone de carregando
		reset.call(this);//deixa o combo desabilitado milissegundos quando uma cidade é desabilitada
		this.imgLoading.show();
	}
	
	function finalizarRequisicao() {//para esconder o icone de carregamento
		this.imgLoading.hide();
	}
	
	return ComboCidade;
	
}());

$(function() {
	
	var comboEstado =  new BeeBee.ComboEstado();
	comboEstado.iniciar();
	
	var comboCidade = new BeeBee.ComboCidade(comboEstado);
	comboCidade.iniciar();
});