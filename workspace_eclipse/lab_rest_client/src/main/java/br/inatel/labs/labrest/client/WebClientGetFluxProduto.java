package br.inatel.labs.labrest.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest.client.model.dto.ProdutoDTO;
import reactor.core.publisher.Flux;

public class WebClientGetFluxProduto {
	
	public static void main(String[] args) {
		
		List<ProdutoDTO> listaproduto = new ArrayList<ProdutoDTO>();
		
		Flux<ProdutoDTO> fluxproduto = WebClient.create(Constantes.BASE_URL)
		.get()
		.uri("/produto")
		.retrieve()
		.bodyToFlux(ProdutoDTO.class);
		fluxproduto.subscribe( p -> listaproduto.add(p) );
		
		fluxproduto.blockLast();
		
		System.out.println("Lista de produtos:");
		System.out.println(listaproduto);
		
	}
	
}
