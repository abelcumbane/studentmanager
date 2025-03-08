package com.mapulassapp.views;

import java.util.Set;

import com.mapulassapp.constants.Constants;
import com.mapulassapp.models.Student;
import com.mapulassapp.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import jakarta.annotation.security.PermitAll;

@PageTitle("Remove Students")
@Route("remove-student")
@PermitAll
//@PreserveOnRefresh
//@AnonymousAllowed
public class RemoveStudentView extends VerticalLayout implements SelectionListener<Grid<Student>, Student> {
	
	private Grid<Student> grid;
	private final StudentService studentService;
	private Button remove;
	private Button cancel;
	private Set<Student> selected;
	
	public RemoveStudentView(StudentService studentService) {
		this.studentService = studentService;
		
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		
		createFieldVariables();
		confiGuredGrid();
		
		add(grid,creatButtonLayout());
		
		loadStudents();
	}

	private void loadStudents() {
		grid.setItems(studentService.findAll());	
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addSelectionListener(this);
	}

	private Component creatButtonLayout() {
		remove.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		
		cancel.addClickListener(e -> closeView());
		remove.addClickListener(e -> removeSelected());
		
		return new HorizontalLayout(remove,cancel);
	}

	private void removeSelected() {
		selected.forEach(studentService::remove);
		Notification notification = Notification.show("Student removed successfully...");
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		notification.setPosition(Position.TOP_CENTER);
		
		//Remove the student from the grid (update the grid)
		grid.setItems(studentService.findAll());
		
	}

	private void closeView() {
		getUI().ifPresent(ui -> ui.navigate(""));
	}

	private void confiGuredGrid() {
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

	private void createFieldVariables() {
		grid = new Grid<>(Student.class);
		remove = new Button(Constants.REMOVE);
		cancel = new Button(Constants.CANCEL);
		
	}

	@Override
	public void selectionChange(SelectionEvent<Grid<Student>, Student> event) {
		selected = event.getAllSelectedItems();
	}

}
