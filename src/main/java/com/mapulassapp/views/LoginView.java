package com.mapulassapp.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Login")
@Route(value = "login")
@AnonymousAllowed
@PreserveOnRefresh

public class LoginView extends VerticalLayout{
	
	//public static final String PATH = "login";
	
	public LoginView(LoginViewFactory loginViewFactory) {
		add(loginViewFactory.create());
		
	}

}
