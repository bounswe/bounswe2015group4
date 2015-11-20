package com.controller;

import com.database.UserConnection;
import com.google.gson.Gson;
import com.model.ErrorJSON;
import com.model.LoginForm;
import com.model.SignUpForm;
import com.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

	final String TAG= "erdemTEST";
	final Gson gson = new Gson();

	@RequestMapping( value = "/signUp", method = RequestMethod.POST)
	public @ResponseBody
    User signUp(@RequestBody String formData) {
		User user = new User();

		SignUpForm form = gson.fromJson(formData, SignUpForm.class);
        if(UserConnection.addUser(form.getEmail(),form.getPassword(),form.getName(),form.getRole())) {
            user.setName(form.getName());
            user.setEmail(form.getEmail());
            user.setPassword(form.getPassword());
            user.setRole(form.getRole());
            return user;
        }else{
            ErrorJSON errorJSON = new ErrorJSON();
            errorJSON.setCode(8081);
            errorJSON.setMessage("Database Error");
            user.setErrorJSON(errorJSON);
            return user;
        }

	}


	@RequestMapping( value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	User login(@RequestBody String loginFormData) {
		User user = new User();
		LoginForm form = gson.fromJson(loginFormData, LoginForm.class);

        String names[] = UserConnection.checkUser(form.getEmail(), form.getPassword());

        if (names.length == 0) {
            ErrorJSON errorJSON = new ErrorJSON();
            errorJSON.setCode(8080);
            errorJSON.setMessage("No user found");
            user.setErrorJSON(errorJSON);
        } else {

            user.setEmail(form.getEmail());
            user.setPassword(form.getPassword());
            return user;
        }

        return user;
	}




}