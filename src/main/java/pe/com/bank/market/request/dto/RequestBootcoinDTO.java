package pe.com.bank.market.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBootcoinDTO {
	
	private String bootcoinWalletId;
	private Double amount;
	private String paymentType;
	private Long paymentNumber;
	private String marketId;

}
