var BeeBee = BeeBee || {};

BeeBee.MascaraCpfCnpj = (function() {
	
	function MascaraCpfCnpj() {

		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelCpfCnpj = $('[for=cpfOuCnpj]');//pegando o label pelo atributo for
		this.inputCpfCnpj = $('#cpfOuCnpj');//esse é o id do input
	}

	MascaraCpfCnpj.prototype.iniciar = function() {

		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));		
	}

	function onTipoPessoaAlterado(evento) {
		
		var tipoPessoaSelecionada = $(evento.currentTarget);

		this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
		console.log('documento', tipoPessoaSelecionada.data('documento'));//só para mostrar no console
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));

		this.inputCpfCnpj.val('');
		this.inputCpfCnpj.removeAttr('disabled');
	}

	return MascaraCpfCnpj;

}());

$(function() {
	var mascaraCpfCnpj = new BeeBee.MascaraCpfCnpj();
	mascaraCpfCnpj.iniciar();
});
