package pe.com.bank.market.request.service;

import pe.com.bank.market.request.document.MarketRequestDocument;
import pe.com.bank.market.request.dto.AcceptMarketRequestDTO;
import pe.com.bank.market.request.dto.MarketRequestResponseDTO;
import pe.com.bank.market.request.dto.RequestBootcoinDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MarketRequestService {
	
	public Flux<MarketRequestDocument> getAllMarketRequest();
	public Mono<MarketRequestDocument> getMarketRequestById(String marketRequestId);
	public Mono<MarketRequestDocument> saveMarkeRequest(MarketRequestDocument marketRequestDocument);
	public Mono<MarketRequestDocument> updateMarkeRequestById(MarketRequestDocument marketRequestDocument,String marketRequestId);
	public Mono<Void> deleteMarketRequestById(String marketRequestId);
	public Mono<MarketRequestResponseDTO>  purchaseMarquetRequest(RequestBootcoinDTO requestBootcoinDTO);
	public Mono<MarketRequestResponseDTO>  acceptMarketRequest(AcceptMarketRequestDTO acceptMarketRequestDTO);

}
