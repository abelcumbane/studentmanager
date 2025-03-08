package com.mapulassapp.views;

import java.util.List;

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

import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;

@PageTitle("Edit Student")
@Route("edit-student")
@PermitAll
//@PreserveOnRefresh
//@AnonymousAllowed
public class EditStudentView extends VerticalLayout implements HasUrlParameter<Integer> {
    
    private final StatusService statusService;
    private final StudentService studentService;
    
    private TextField name;
    private TextField age;
    private TextField zipCode;
    private TextField country;
    private ComboBox<Status> status;
    private LogoLayout image;
    
    private Button save;
    private Button cancel;
    
    private Binder<Student> binder;
    private Student student;
    
    public EditStudentView(StatusService statusService, StudentService studentService) {
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
        binder = new BeanValidationBinder<>(Student.class);
        binder.bindInstanceFields(this);
    }

    private void createStatus() {
        List<Status> statusItems = statusService.findAll();
        status.setItems(statusItems);
        status.setItemLabelGenerator(Status::getName);
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(name, age, zipCode, country, status, createButtons());
        formLayout.setColspan(image, 2);
        formLayout.setColspan(name, 2);
        
        return formLayout;
    }

    private Component createButtons() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        
        cancel.addClickListener(e -> closeView());
        save.addClickListener(e -> saveStudent());
        
        return new HorizontalLayout(save, cancel);
    }

    private void saveStudent() {
        try {
            binder.writeBean(student);
            studentService.update(student);
            Notification notification = Notification.show("Student updated successfully...");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setPosition(Position.TOP_CENTER);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    private void closeView() {
        getUI().ifPresent(ui -> ui.navigate(""));
    }

    private void createVariables() {
        name = new TextField("Name");
        age = new TextField("Age");
        zipCode = new TextField("Zip Code");
        country = new TextField("Country");
        status = new ComboBox<>("Status");
        image = new LogoLayout();
        
        save = new Button("Save");
        cancel = new Button("Cancel");
    }
    /*
    public void setParameter(BeforeEvent event, Integer studentId) {
        student = studentService.findById(studentId);
        if (student != null) {
            binder.readBean(student);
        } else {
            Notification.show("Student not found", 3000, Position.TOP_CENTER)
                .addThemeVariants(NotificationVariant.LUMO_ERROR);
            closeView();
        }
    }
    */
    
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer studentId) {
        if (studentId == null) {
            Notification.show("No student ID provided", 3000, Position.TOP_CENTER)
                .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }

        System.out.println("Fetching student with ID: " + studentId);
        student = studentService.findById(studentId);

        if (student != null) {
            binder.readBean(student);
        } else {
            Notification.show("Student not found", 3000, Position.TOP_CENTER)
                .addThemeVariants(NotificationVariant.LUMO_ERROR);
            getUI().ifPresent(ui -> ui.navigate("")); // Redirect if not found
        }
    }

}
