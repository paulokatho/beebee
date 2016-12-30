package com.katho.beebee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.katho.beebee.service.CadastroCervejaService;
import com.katho.beebee.storage.FotoStorage;
import com.katho.beebee.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {

	@Bean
	public FotoStorage fotoStorage() {
		
		// aqui declaramos qual implementação(StorageLocal, S3 Amazon) do profile (producao, teste) do sistema de arquivos que vamos utilizar
		return new FotoStorageLocal();
	}
	
}
