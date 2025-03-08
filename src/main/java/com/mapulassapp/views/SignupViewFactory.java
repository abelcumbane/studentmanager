package com.mapulassapp.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.mapulassapp.services.SecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

@org.springframework.stereotype.Component
public class SignupViewFactory {
	
	@Autowired
	private SecurityService securityService;
	
	private class SignupForm{

		private VerticalLayout root;
		private TextField username;
		private PasswordField password;
		private PasswordField passwordAgain;
		private Button signup; 
		private Button cancel;
		
	
		public SignupForm init() {
			username = new TextField("Username");
			password = new PasswordField("Password");
			passwordAgain = new PasswordField("Password");
			signup = new Button("Signup");
			cancel = new Button("Cancel");
			
			signup.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
			
			
			
			root = new VerticalLayout();
			root.setAlignItems(Alignment.CENTER);
			root.setJustifyContentMode(JustifyContentMode.CENTER);
			root.setMargin(true);
				
			return this;		
		}


		public Component layout() {
			root.add(username);
			root.add(password);
			root.add(passwordAgain);
			root.add(new HorizontalLayout(signup,cancel));
			
			
			cancel.addClickListener(e -> {
				cancel.getUI().ifPresent(ui -> ui.navigate("login"));
			});
			
			signup.addClickListener(e-> {
				if(!password.getValue().isEmpty()&&password.getValue().equals(passwordAgain.getValue())) {
					securityService.save(username.getValue(), password.getValue());
					signup.getUI().ifPresent(ui -> ui.navigate("login"));
				}else {
					Notification notification = Notification.show("Password do not match...");
					notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
					notification.setPosition(Position.TOP_CENTER);
				}
				
			});
			
			return root;
		}		
		
	}
	
	public Component create() {
		return new SignupForm().init().layout();
	}

}
