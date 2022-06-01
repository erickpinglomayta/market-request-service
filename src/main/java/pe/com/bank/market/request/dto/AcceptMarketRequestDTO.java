package pe.com.bank.market.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptMarketRequestDTO {
		
	private String marketRequestId;
	private String sellerBootcoinWalletId;
	
}