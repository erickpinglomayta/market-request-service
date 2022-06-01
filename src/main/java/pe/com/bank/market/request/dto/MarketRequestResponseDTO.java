package pe.com.bank.market.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketRequestResponseDTO {
	
	private String messageCode;
	private String messageDescription;

}
