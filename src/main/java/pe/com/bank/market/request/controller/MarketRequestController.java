package pe.com.bank.market.request.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.com.bank.market.request.document.MarketRequestDocument;
import pe.com.bank.market.request.dto.AcceptMarketRequestDTO;
import pe.com.bank.market.request.dto.MarketRequestResponseDTO;
import pe.com.bank.market.request.dto.RequestBootcoinDTO;
import pe.com.bank.market.request.service.MarketRequestService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("/v1")
@RestController
public class MarketRequestController {
	
	MarketRequestService marketRequestService;
	
	@GetMapping("/getAllMarketRequest")
	public Flux<MarketRequestDocument> getAllMarketRequest(){
		return marketRequestService.getAllMarketRequest();
	}
	
	@GetMapping("/getMarketRequestById/{marketRequestId}")
	public Mono<MarketRequestDocument> getMarketRequestById(@PathVariable String marketRequestId){
		return marketRequestService.getMarketRequestById(marketRequestId);
	}
	
	@PostMapping("/saveMarkeRequest")
	public Mono<MarketRequestDocument> saveMarkeRequest(@RequestBody MarketRequestDocument marketRequestDocument){
		return marketRequestService.saveMarkeRequest(marketRequestDocument);
	}
	
	@PutMapping("/updateMarkeRequestById/{marketRequestId}")
	public Mono<MarketRequestDocument> updateMarkeRequestById(@RequestBody MarketRequestDocument marketRequestDocument,@PathVariable String marketRequestId){
		return marketRequestService.updateMarkeRequestById(marketRequestDocument,marketRequestId);
	}
	
	@DeleteMapping("/deleteMarketRequestById/{marketRequestId}")
	public Mono<Void> deleteMarketRequestById(@PathVariable String marketRequestId){
		return marketRequestService.deleteMarketRequestById(marketRequestId);
	}
	
	@PostMapping("/purchaseMarquetRequest")
	public Mono<MarketRequestResponseDTO>  purchaseMarquetRequest(@RequestBody RequestBootcoinDTO requestBootcoinDTO){
		return marketRequestService.purchaseMarquetRequest(requestBootcoinDTO);
	}
	
	@PostMapping("/acceptMarketRequest")
	public Mono<MarketRequestResponseDTO>  acceptMarketRequest(@RequestBody AcceptMarketRequestDTO acceptMarketRequestDTO){
		return marketRequestService.acceptMarketRequest(acceptMarketRequestDTO);
	}

}
