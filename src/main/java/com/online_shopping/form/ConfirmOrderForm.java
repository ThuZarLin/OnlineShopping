package com.online_shopping.form;

import java.util.List;

import com.online_shopping.entity.DivisionEntity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ConfirmOrderForm {
	@NotEmpty(message="Receiver name is required!")
	private String receiverName;
	@NotEmpty(message="Receiver phone is required!")
	private String receiverPhone;
	@NotEmpty(message="Receiver address is required!")
	private String receiverAddress;
	private int divisionId;
	
	private int  userId;
	private String itemList;
	private List<DivisionEntity> divisionList;
//	private int locationId;
}
