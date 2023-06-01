package com.online_shopping.entity;

import lombok.Data;

@Data
public class UserEntity {
	private int id;
	private String username;
	private String email;
	private String password;
	private String address;
	private int roleId;
}
