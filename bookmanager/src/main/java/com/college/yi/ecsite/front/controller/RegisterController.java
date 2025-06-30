package com.college.yi.ecsite.front.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.college.yi.ecsite.exception.DuplicateEmailException;
import com.college.yi.ecsite.front.form.RegisterForm;
import com.college.yi.ecsite.front.service.RegisterService;


@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@GetMapping
	public String registerFormView(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "register";
	}
	
    @PostMapping
    public String register(@Valid  RegisterForm form , BindingResult result , Model model) {
    	if(result.hasErrors()) {
    		model.addAttribute("registerForm", form);
    		return "register";
    	}
    	
    	if (!form.getPassword().equals(form.getConfirmPassword())) {
    		result.rejectValue("confirmPassword", "mismatch", "パスワードが一致しません。");
    		model.addAttribute("registerForm", form);
    		return "register";
    	}
    	
    	try {
    		registerService.registerUser(form);
    		return "redirect:/login";
    	} catch (DuplicateEmailException e) {
    		result.rejectValue("email", "duplicate", "このメールアドレスは既に使われています");
    		return "register";
    	}
    }
}