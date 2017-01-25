package com.katho.beebee.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.katho.beebee.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;
	private FotoStorage fotoStorage;
	
	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
		
		this.files = files;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
		
	}
	
	@Override
	public void run() {
		
		// TODO: Aqui é a parte demorada - Salvar a foto no sistema de arquivos...
		String nomeFoto = this.fotoStorage.salvarTemporariamente(files);		
		String contentType = files[0].getContentType();
		resultado.setResult(new FotoDTO(nomeFoto, contentType)); // isso chega no browser para o cliente como response		

	}
}
