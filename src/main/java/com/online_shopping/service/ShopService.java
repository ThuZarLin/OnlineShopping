package com.online_shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.ui.Model;

import com.online_shopping.entity.DivisionEntity;
import com.online_shopping.entity.ItemEntity;
import com.online_shopping.entity.UserEntity;
import com.online_shopping.form.ConfirmOrderForm;
import com.online_shopping.form.ItemOrderModel;
import com.online_shopping.form.LoginForm;
import com.online_shopping.form.OrderForm;
import com.online_shopping.repository.ShopMapper;

import jakarta.servlet.http.HttpSession;
@Service
@Transactional
public class ShopService {
	@Autowired
	ShopMapper shopMapper;
	public List<ItemEntity> getAllItems(){
		return this.shopMapper.getAllItems();
	}
	
	public boolean login(LoginForm loginUser, Model model, HttpSession session) {
		UserEntity authUser = this.shopMapper.login(loginUser);
		if(authUser != null) {
			session.setAttribute("Auth", authUser);
		}else {
			model.addAttribute("login_err", "email or password is not match");
		}
		//System.out.println(authUser);
		return authUser != null;
	}
	
	public boolean isEmailSatisfy(String email) {
		return this.shopMapper.isEmailSatisfy(email);
	}
	
	public List<DivisionEntity> getDivisions(){
		return this.shopMapper.getDivisions();
		}
	
	public void createOrder(ConfirmOrderForm confirmOrderForm) {
		OrderForm orderForm = new OrderForm();
		orderForm.setItemList(confirmOrderForm.getItemList());
		List<ItemOrderModel> orderDetails = orderForm.toList();
		try{
			int orderId = this.shopMapper.createOrder(confirmOrderForm);
			for(ItemOrderModel order: orderDetails) {
				this.shopMapper.createOrderDetails(order.getQty(), orderId, order.getId());
			}
			
		}catch(Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			}
	}
}
