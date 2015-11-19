package com.mkyong.common.controller;

import com.google.gson.Gson;
import com.mkyong.common.model.SignUpForm;
import com.mkyong.common.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mkyong.common.model.Shop;
@Controller
public class JSONController {
	final String TAG= "erdemTEST";
	final Gson gson = new Gson();


	@RequestMapping( value = "/signUp", method = RequestMethod.POST)
	public @ResponseBody
	User signUp(@RequestBody String formData) {
		User user = new User();

		SignUpForm form = gson.fromJson(formData, SignUpForm.class);

		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword().hashCode());
		user.setRole(form.getRole());
		return user;
	}
}