var BeeBee = BeeBee || {}; // caso não exista o objeto ele cria um novo objeto

BeeBee.UploadFoto = (function() {
	
	function UploadFoto() { // função construtora do uploadFoto
		
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		
		this.htmlFotoCervejaTemplate = $('#foto-cerveja').html();
		this.template = Handlebars.compile(this.htmlFotoCervejaTemplate); // esse pode demorar um pouco mais para iniciarlizar então é bom deixar no começo
		
		//this.htmlFotoCerveja = template({nomeFoto: resposta.nome}); // template não iniciliza agora, ele será inicializado no prototype na função onUploadCompleto()
		
		this.containerFotoCerveja = $('.js-container-foto-cerveja');
		
		this.uploadDrop = $('#upload-drop');
		
	}
	
	UploadFoto.prototype.iniciar = function () {
	//qdo uma requisição volta do servidor apos clicar em salvar esse método é executado
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.containerFotoCerveja.data('url-fotos'),
			complete: onUploadCompleto.bind(this) // esse (this) acessa todos os this. que tem no método construtor e não esquecer de criar a função onUploadCompleto()
			
		}
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		//para manter a foto na tela após clicar em salvar e não passar pela validação dos campos. Aula 14-10
		if (this.inputNomeFoto.val()) {
		//call() é para dizer que é o mesmo contexto que foi enviado na requisição anterior, não é um novo objeto
			onUploadCompleto.call(this, {nome: this.inputNomeFoto.val(), contentType: this.inputContentType.val()});
		}
	}
	
	function onUploadCompleto(resposta) {
		this.inputNomeFoto.val(resposta.nome); // mudei aqui
		this.inputContentType.val(resposta.contentType);	
		
		this.uploadDrop.addClass('hidden');
		var htmlFotoCerveja = this.template({nomeFoto: resposta.nome});
		this.containerFotoCerveja.append(htmlFotoCerveja);
		
		//agora vem a parte para remover a foto
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}
	
	function onRemoverFoto() {
		$('.js-foto-cerveja').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
	}
	
	return UploadFoto; // o retorno da função BeeBee.UploadFoto = (function() {
	
})(); // o "();" executa a função - BeeBee.UploadFoto


$(function() { // aqui é onde fazemos acontecer!!!
	
	var uploadFoto = new BeeBee.UploadFoto();
	uploadFoto.iniciar();
});
