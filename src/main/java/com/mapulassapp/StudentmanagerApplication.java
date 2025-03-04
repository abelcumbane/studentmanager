package com.mapulassapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@SpringBootApplication
@Theme(themeClass = Lumo.class)
public class StudentmanagerApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagerApplication.class, args);
	}

}
