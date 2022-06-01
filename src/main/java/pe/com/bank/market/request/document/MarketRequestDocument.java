package pe.com.bank.market.request.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "market_request")
public class MarketRequestDocument {

	@Id
	private String marketRequestId;
	private Double amount;	
	private String statusRequest;
	private String paymentType;
	private Long paymentNumber;
	private Date requestDate;
	private Date requestAnsweredDate;
	private String marketId;
	private String buyerBootcoinWalletId;

}
