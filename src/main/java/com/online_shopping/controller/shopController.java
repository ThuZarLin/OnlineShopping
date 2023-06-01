package com.online_shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.online_shopping.entity.UserEntity;
import com.online_shopping.form.ConfirmOrderForm;
import com.online_shopping.form.LoginForm;
import com.online_shopping.form.OrderForm;
import com.online_shopping.service.CommonService;
import com.online_shopping.service.ShopService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class shopController {
	@Autowired
	ShopService shopService;
	
	@Autowired
	CommonService commonService;
	
	@PostConstruct
	public void init() {
		//System.out.println(this.shopService.getAllItems());
//		LoginForm loginUser = new LoginForm();
//		loginUser.setEmail("mama@gmail.com");
//		loginUser.setPassword("1234");
//		System.out.println(this.shopService.login(loginUser));
		//System.out.println(this.shopService.getDivisions());
	}
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		model.addAttribute("Items", this.shopService.getAllItems());
//		model.addAttribute("itemId", 10);
//		model.addAttribute("itemName", "Lipstick");
//		model.addAttribute("itemPrice", 5000);
//		LoginForm loginForm = new LoginForm();
//		loginForm.setEmail("thuzar@gmail.com");
		//loginForm.setPassword(null)
		//session.setAttribute("name", "Thu Zar Lin");
		model.addAttribute("Auth", commonService.checkAuth(session));
		model.addAttribute("loginForm", new LoginForm());
		return "screens/index";
	}
	
	@PostMapping("/create/order")
	public String createOrder(@ModelAttribute OrderForm orderForm, Model model, HttpSession session) {
		//System.out.println(session.getAttribute("name"));
		UserEntity authUser = (UserEntity)session.getAttribute("Auth");
		if(authUser == null) {
			return "redirect:/";
		}
		if(orderForm.getItemList().equals("[]") || orderForm.getItemList().equals(null) ) {
			return "redirect:/";
		}
		model.addAttribute("Auth", commonService.checkAuth(session));
		model.addAttribute("itemList", orderForm.getItemList());
		ConfirmOrderForm confirmOrderForm = new ConfirmOrderForm();
		confirmOrderForm.setDivisionList(this.shopService.getDivisions());
		model.addAttribute("confirmOrderForm", confirmOrderForm);
		return "screens/order";
	}
	
	@PostMapping("/")
	public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors() || !this.shopService.login(loginForm, model, session)) {
			model.addAttribute("err_msg", "error occurs");
			model.addAttribute("loginForm", loginForm);
			model.addAttribute("Items", this.shopService.getAllItems());
			return "screens/index";
		}
	
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("Auth");
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String Signup(Model model, HttpSession session) {
		model.addAttribute("Auth", commonService.checkAuth(session));
		return "screens/signup";
	}
	
	@PostMapping("/confirm/order")
	public String confirmOrder(@ModelAttribute ConfirmOrderForm confirmOrderForm, HttpSession session) {
		UserEntity authUser = (UserEntity)session.getAttribute("Auth");
		confirmOrderForm.setUserId( authUser.getId() );
//		confirmOrderForm.setLocationId( Integer.parseInt(confirmOrderForm.getDivisionId()) );
		this.shopService.createOrder(confirmOrderForm);
		return "redirect:/";
	}
}
