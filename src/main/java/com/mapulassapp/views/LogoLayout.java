package com.mapulassapp.views;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LogoLayout extends HorizontalLayout {
	
	private Image image;
	
	public LogoLayout() {
		
		image = new Image("images/Logo-uem.png","my image");
		image.setHeight("100px");
		setJustifyContentMode( JustifyContentMode.CENTER);
		
		add(image);
	}

}
