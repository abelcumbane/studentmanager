	package com.mapulassapp.views;
	
	import java.util.ArrayList;
	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mapulassapp.models.Status;
	import com.mapulassapp.models.Student;
import com.mapulassapp.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
	import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
	import com.vaadin.flow.router.Route;
	
	
	@PageTitle(value = "Home")
	@Route(value = "")
	public class MainView extends VerticalLayout{
		
		//Construtor de injection!
		private final StudentService studentService;
		private LogoLayout logoLayout;
		private Grid<Student> grid;
		private TextField filterField; 
		private Student student;
		
		public MainView(StudentService studentService) {
			this.studentService = studentService;
			setSizeFull();
			setAlignItems(Alignment.CENTER);
			
			createFieldVariables();
			configuredGrid();
			add(logoLayout,createToolbar(),grid);
			loadStudents();	
			
		}
		
		private Component createToolbar() {
			filterField.setPlaceholder("Filter by name...");
			filterField.setClearButtonVisible(true);
			filterField.setValueChangeMode(ValueChangeMode.LAZY); 
			filterField.addValueChangeListener(e -> updateStudent());
			
			Button addStudentButton = new Button(new Icon(VaadinIcon.PLUS));
			Button editStudentButton = new Button(new Icon(VaadinIcon.EDIT));
			Button removeStudentButton = new Button(new Icon(VaadinIcon.TRASH));
			

			addStudentButton.addClickListener(e->
				getUI().ifPresent(ui -> ui.navigate("/add-student")));
			
			removeStudentButton.addClickListener(e->
			getUI().ifPresent(ui -> ui.navigate("/remove-student")));
			
			//student = new Student();
			editStudentButton.addClickListener(e->
			getUI().ifPresent(ui -> ui.navigate("/edit-student")));
			
			return new HorizontalLayout(filterField,addStudentButton,editStudentButton,removeStudentButton);
		}

		
		private void updateStudent() {
			grid.setItems(studentService.find(filterField.getValue()));
		}

		
		private void configuredGrid() {
			grid.setSizeFull();
			grid.setColumns("country","zipCode");
			grid.addColumn(s -> s.getName()).setHeader("Name");
			grid.addColumn(s -> s.getAge()).setHeader("Age");
			
			grid.addComponentColumn(s -> {
				Icon icon;
				
			if(s.getStatus().getName().equals("ACTIVE")) {
				icon = VaadinIcon.CIRCLE.create();
				icon.setColor("green");
				
			}else if(s.getStatus().getName().equals("PASSIVE")) {
				icon = VaadinIcon.CLOSE_CIRCLE.create();
				icon.setColor("red");	
				
			}else{
				icon = VaadinIcon.CHECK_CIRCLE.create();
				icon.setColor("orange");	
			}
			return icon;
			
			}).setHeader("Status");
			
			grid.getColumns().forEach(col -> col.setAutoWidth(true));	
		}
		

		private void loadStudents() {
			grid.setItems(studentService.findAll());
			
		}

		private void createFieldVariables() {
			this.logoLayout = new LogoLayout();
			this.grid = new Grid<>(Student.class);
			this.filterField = new TextField();
		}
	
	}
