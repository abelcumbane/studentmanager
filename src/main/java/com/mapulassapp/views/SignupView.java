package com.mapulassapp.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Signup")
@Route(value ="signup")
@AnonymousAllowed
public class SignupView extends VerticalLayout {
	
	public SignupView(SignupViewFactory signupViewactory) {
		
		add(signupViewactory.create());
	}

}
