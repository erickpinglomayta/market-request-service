package pe.com.bank.market.request.service;

import java.util.Date;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pe.com.bank.market.request.document.MarketRequestDocument;
import pe.com.bank.market.request.dto.AcceptMarketRequestDTO;
import pe.com.bank.market.request.dto.MarketRequestResponseDTO;
import pe.com.bank.market.request.dto.MarketTransactionDTO;
import pe.com.bank.market.request.dto.RequestBootcoinDTO;
import pe.com.bank.market.request.repository.MarketRequestRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class MarketRequestServiceImpl implements MarketRequestService {
	
	
	MarketRequestRepository marketRequestRepository;
	StreamBridge streamBridge;
	
	public Flux<MarketRequestDocument> getAllMarketRequest(){
		return marketRequestRepository.findAll();
	}
	
	public Mono<MarketRequestDocument> getMarketRequestById(String marketRequestId){
		return marketRequestRepository.findById(marketRequestId);
	}
	
	public Mono<MarketRequestDocument> saveMarkeRequest(MarketRequestDocument marketRequestDocument){
		return marketRequestRepository.save(marketRequestDocument);
	}
	public Mono<MarketRequestDocument> updateMarkeRequestById(MarketRequestDocument updateMarketRequest,String marketRequestId){
	
		return marketRequestRepository.findById(marketRequestId).flatMap( marketRequest ->{
			marketRequest.setAmount(updateMarketRequest.getAmount() != null ? updateMarketRequest.getAmount():marketRequest.getAmount());
			marketRequest.setStatusRequest(updateMarketRequest.getStatusRequest() != null ? updateMarketRequest.getStatusRequest():marketRequest.getStatusRequest());
			marketRequest.setPaymentType(updateMarketRequest.getPaymentType() != null ? updateMarketRequest.getPaymentType():marketRequest.getPaymentType());
			marketRequest.setPaymentNumber(updateMarketRequest.getPaymentNumber() != null ? updateMarketRequest.getPaymentNumber():marketRequest.getPaymentNumber());
			marketRequest.setRequestDate(updateMarketRequest.getRequestDate() != null ? updateMarketRequest.getRequestDate():marketRequest.getRequestDate());
			marketRequest.setRequestAnsweredDate(updateMarketRequest.getRequestAnsweredDate() != null ? updateMarketRequest.getRequestAnsweredDate():marketRequest.getRequestAnsweredDate());
			marketRequest.setMarketId(updateMarketRequest.getMarketId() != null ? updateMarketRequest.getMarketId():marketRequest.getMarketId());
			marketRequest.setBuyerBootcoinWalletId(updateMarketRequest.getBuyerBootcoinWalletId() != null ? updateMarketRequest.getBuyerBootcoinWalletId():marketRequest.getBuyerBootcoinWalletId());
			return marketRequestRepository.save(marketRequest);
		});
	}
	public Mono<Void> deleteMarketRequestById(String marketRequestId){
		return marketRequestRepository.deleteById(marketRequestId);
	}
	
	public Mono<MarketRequestResponseDTO>  purchaseMarquetRequest(RequestBootcoinDTO requestBootcoinDTO){
		return marketRequestRepository.save(new MarketRequestDocument(null,requestBootcoinDTO.getAmount(),"Pendiente",requestBootcoinDTO.getPaymentType(),
				requestBootcoinDTO.getPaymentNumber(),new Date(),null,requestBootcoinDTO.getMarketId(),requestBootcoinDTO.getBuyerBootcoinWalletId())).flatMap( r -> {
					return Mono.just(new MarketRequestResponseDTO("0000","successful bootcoin request"));
				});
	
	}
	public Mono<MarketRequestResponseDTO>  acceptMarketRequest(AcceptMarketRequestDTO acceptMarketRequestDTO){
		return marketRequestRepository.findById(acceptMarketRequestDTO.getMarketRequestId()).flatMap( request -> {
			
			if(request.getStatusRequest().equals("Pendiente")) {
				
			return updateMarkeRequestById(new MarketRequestDocument(null,null,"Aceptado",null,null,null,new Date(),null,null),
					acceptMarketRequestDTO.getMarketRequestId()).flatMap( updatedMarket -> {						
						sendMarketTransaction(new MarketTransactionDTO(updatedMarket.getMarketRequestId(),updatedMarket.getAmount(),
								acceptMarketRequestDTO.getBuyerBootcoinWalletId(),acceptMarketRequestDTO.getSellerBootcoinWalletId(),
								updatedMarket.getPaymentType(),updatedMarket.getPaymentNumber()));
						return Mono.just(new MarketRequestResponseDTO("0000","successful accept request"));
					});
			}else {
				return Mono.just(new MarketRequestResponseDTO("0001","market was answered"));
			}
		});								 
	}
	
	private void sendMarketTransaction(MarketTransactionDTO marketTransactionDTO) {
		 streamBridge.send("sendMarketTransaction-out-0",marketTransactionDTO);
	}	
		

}
