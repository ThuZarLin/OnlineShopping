package com.online_shopping.entity;

import lombok.Data;

@Data
public class ItemEntity {
	private int id;
	private String name;
	private int price;
	private String photoUrl;
	private int categoryId;
}
//th:item-id = "*{id}" th:item-name = "*{name}" th:item-price= "*{price}"