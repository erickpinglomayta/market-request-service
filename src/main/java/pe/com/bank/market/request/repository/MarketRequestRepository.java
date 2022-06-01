package pe.com.bank.market.request.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bank.market.request.document.MarketRequestDocument;


@Repository
public interface MarketRequestRepository extends ReactiveMongoRepository<MarketRequestDocument, String>{

}
