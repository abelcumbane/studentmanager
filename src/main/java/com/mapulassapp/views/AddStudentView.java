package com.mapulassapp.views;

import java.util.List;

import com.mapulassapp.constants.Constants;
import com.mapulassapp.models.Status;
import com.mapulassapp.models.Student;
import com.mapulassapp.services.StatusService;
import com.mapulassapp.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;


//@PreserveOnRefresh
//@AnonymousAllowed
//@PermitAll

@PageTitle("Add Student")
@Route("add-student")
@RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
public class AddStudentView extends VerticalLayout {
	
	private final StatusService statusService;
	private final StudentService studentService;
	
	private TextField name;
	private TextField age;
	private TextField zipCode;
	private TextField country;
	private ComboBox<Status> status;
	private LogoLayout image;
	
	private Button save;
	private Button close;
	
	private Binder<Student> binder;
	private Student student;
	
	
	public AddStudentView(StatusService statusService,StudentService studentService) {
		
		this.statusService = statusService;
		this.studentService = studentService;
		
		setAlignItems(Alignment.CENTER);
		
		createVariables();
		createStatus();
		createBinder();
		
		add(image);
		add(createFormLayout());
		
	}

	private void createBinder() {
		student = new Student();
		binder = new BeanValidationBinder<>(Student.class);
		binder.bindInstanceFields(this);
	}

	private void createStatus() {
		List<Status> statusItems = statusService.findAll();
		status.setItems(statusItems);
		status.setValue(statusItems.get(0));
		status.setItemLabelGenerator(Status::getName);
		
	}

	private Component createFormLayout() {
		FormLayout formLayout = new FormLayout();
		formLayout.add(name,age,zipCode,country,status,createButtons());
		formLayout.setColspan(image, 2);
		formLayout.setColspan(name, 2);
		
		return formLayout;
	}

	private Component createButtons() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		
		close.addClickListener(e -> closeView());
		save.addClickListener(e -> saveStudent());
		
		return new HorizontalLayout(save,close);
	}
	
	private void saveStudent() {
		try {
			binder.writeBean(student);
			studentService.save(student);
			clearFields();
			Notification notification = Notification.show(Constants.STUDENT_SAVED);
			notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			notification.setPosition(Position.TOP_CENTER);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	private void clearFields() {
		student = new Student();
		status.setValue(statusService.findAll().get(0));
		binder.getFields().forEach(HasValue::clear);
		
	}

	private void closeView() {
		getUI().ifPresent(ui -> ui.navigate("mainview"));
	}

	private void createVariables() {
		name = new TextField(Constants.NAME);
		age = new TextField(Constants.AGE);
		zipCode = new TextField(Constants.ZIP_CODE);
		country = new TextField(Constants.COUNTRY);
		status = new ComboBox<Status>(Constants.STATUS);
		image = new LogoLayout();
		
		save = new Button(Constants.SAVE);
		close = new Button(Constants.CANCEL);
	}
	

}
