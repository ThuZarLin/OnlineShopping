package com.online_shopping.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.online_shopping.entity.DivisionEntity;
import com.online_shopping.entity.ItemEntity;
import com.online_shopping.entity.UserEntity;
import com.online_shopping.form.ConfirmOrderForm;
import com.online_shopping.form.LoginForm;


@Mapper
public interface ShopMapper {
	public List<ItemEntity> getAllItems();
	
	public UserEntity login(LoginForm loginUser);
	
	public boolean isEmailSatisfy(@Param("email") String email);
	
	public List<DivisionEntity> getDivisions();
	
	public int createOrder(ConfirmOrderForm confirmOrderForm);
	
	public void createOrderDetails(@Param("qty") int qty, @Param("orderId") int orderId, @Param("itemId") int itemId);
}
