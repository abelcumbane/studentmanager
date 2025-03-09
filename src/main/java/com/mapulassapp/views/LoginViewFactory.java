package com.mapulassapp.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.mapulassapp.constants.Constants;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Component
public class LoginViewFactory {
	
	@Autowired
	private final DaoAuthenticationProvider authenticationProvider;
	@Autowired
	private final HttpServletRequest request;
    
    public LoginViewFactory(DaoAuthenticationProvider authenticationProvider,HttpServletRequest request) {
        this.authenticationProvider = authenticationProvider;
        this.request = request;
    }
   
	private class LoginForm{
		private VerticalLayout root;
		private TextField username;
		private PasswordField password;
		private Button login;
		private Button signup; 
		

		/*
		private final HttpServletRequest request; // Agora final e inicializado no construtor

	    public LoginForm(HttpServletRequest request) {
	        this.request = request;
	    }
	    
	    */
	    
	
		public LoginForm init() {
			username = new TextField("Username");
			password = new PasswordField("Password");
			login = new Button("Login");
			signup = new Button("Signup");
			
			login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			signup.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
			
			login.addClickListener(e -> login());
			signup.addClickListener(e -> signup());
			root = new VerticalLayout();
			root.setAlignItems(Alignment.CENTER);
			root.setJustifyContentMode(JustifyContentMode.CENTER);
			root.setMargin(true);
				
			return this;		
		}
		
		
		private void signup() {
			signup.getUI().ifPresent(ui -> ui.navigate("signup"));
		}


		/*
		------------------------------------------------------------1. Outra forma de fazer o login -------------------------

		private void login() {
			try {
				var auth = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());
				var authenticated = authenticationProvider.authenticate(auth);
				SecurityContextHolder.getContext().setAuthentication(authenticated);
				
				
				login.getUI().ifPresent(ui -> ui.navigate("mainview"));
				 //UI.getCurrent().navigate("");
				
			} catch (AuthenticationException e) {
				Notification notification = Notification.show("Incorrect username or password...");
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.setPosition(Position.TOP_CENTER);
			}
		}

		
		
		 ------------------------------------------------------------2. Outra forma de fazer o login -------------------------
		private void login() {
		    try {
		        // Criação do token de autenticação
		        var auth = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());

		        // Autentica o usuário
		        var authenticated = authenticationProvider.authenticate(auth);

		        // Verifica se a autenticação foi bem-sucedida
		        if (authenticated != null && authenticated.isAuthenticated()) {
		            // Armazena a autenticação no SecurityContext
		            SecurityContextHolder.getContext().setAuthentication(authenticated);

		            // Navega para a página inicial ou página principal após login bem-sucedido
		            login.getUI().ifPresent(ui -> ui.navigate(""));  // Altere o caminho conforme necessário
		            login.getUI().ifPresent(ui -> ui.navigate("/add-student"));
		            login.getUI().ifPresent(ui -> ui.navigate("/remove-student"));           
		        } else {
		            // Caso o usuário não tenha sido autenticado
		            Notification notification = Notification.show("Autenticação falhou. Tente novamente.");
		            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		            notification.setPosition(Position.TOP_CENTER);
		        }
		        
		    } catch (AuthenticationException e) {
		        // Exceção de erro durante a autenticação
		        Notification notification = Notification.show("Nome de usuário ou senha incorretos...");
		        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		        notification.setPosition(Position.TOP_CENTER);

		        // Log para depuração
		        e.printStackTrace();
		    }
		}
		
		*/

		private void login() {
			try {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());
				Authentication auth = authenticationProvider.authenticate(token);
				
				SecurityContext sc = SecurityContextHolder.getContext();
				sc.setAuthentication(auth);
				HttpSession session = request.getSession(true);
				session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
				
				if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
					Notification notification = Notification.show("Credenciais validadas. Acessando aplicação.");
					notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
					notification.setPosition(Position.TOP_CENTER);
				}
				
				login.getUI().ifPresent(ui -> ui.navigate("mainview"));
			} catch (org.springframework.security.core.AuthenticationException e) {
				Notification notification = Notification.show("Credenciais de acesso inválidas!");
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.setPosition(Position.TOP_CENTER);
			}
		}

		public Component Logout() {
			root.add(
				    new Image(Constants.LOGIN_IMAGE, "") {{
				        setWidth("100px");
				        setHeight("100px");
				    }}
				);			
			root.add(username);
			root.add(password);
			root.add(new HorizontalLayout(login,signup));
			
			return root;	
		}
		
	}
	
		public Component create() {
			return new LoginForm().init().Logout();
		}
		

}
