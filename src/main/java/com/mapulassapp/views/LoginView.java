package com.mapulassapp.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route(value = "/login")
public class LoginView extends VerticalLayout{
	
	public static final String PATH = "/login";
	
	public LoginView(LoginViewFactory loginViewFactory) {
		add(loginViewFactory.create());
		
	}

}
