package com.online_shopping.form;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class OrderForm {
	private String itemList;
	
	public List<ItemOrderModel> toList() {
		try {
			return jsonParse( this.itemList );
		}catch( Exception e) {
			List<ItemOrderModel> nullItem = new ArrayList<ItemOrderModel>();
			return nullItem;
		}
		
	}
	
	private List<ItemOrderModel> jsonParse(String json) throws JsonMappingException, JsonProcessingException  {
		ObjectMapper mapper = new ObjectMapper();
		List<ItemOrderModel> map = mapper.readValue(json, new TypeReference<List<ItemOrderModel>>(){});
		return map;
	}
	
}
