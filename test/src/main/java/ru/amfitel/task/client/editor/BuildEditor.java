package ru.amfitel.task.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import ru.amfitel.task.client.callback.DeleteCallback;
import ru.amfitel.task.client.callback.WrappedCallback;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Bublik on 2ito8.03.2016.
 */

public class BuildEditor extends DTOEditor<BuildDTO> implements ClickHandler {

    public TextBox name;
    public DateBox date;
    public TextBox address;
    public IntegerBox countFloor;
    public MaterialEditor material;
    public Button saveButton;
    public Button deleteButton;
    private Label labelName;
    private Label labelDate;
    private Label labelAddress;
    private Label labelCountFloor;
    private Label labelMaterial;
    BuildingServiceAsync buildingService = GWT.create(BuildingService.class);
    // Create the Driver
    Driver driver = GWT.create(Driver.class);

    public BuildEditor(AsyncCallback<BuildDTO> callback, AsyncCallback<BuildDTO> deleteCallback) {
        super(callback,deleteCallback);
        labelName = new Label("Название: ");
        labelAddress = new Label("Адрес: ");
        labelMaterial = new Label("Материал: ");
        labelCountFloor = new Label("Кол-во этажей");
        labelDate = new Label("Дата постройки");
        name = new TextBox();
        name.setName("Название");
        date = new DateBox();


        date.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd-MM-yyyy")));
        address = new TextBox();
        countFloor = new IntegerBox();
        material = new MaterialEditor();

        deleteButton = new Button("Удалить");
        saveButton = new Button("Сохранить");
        saveButton.addClickHandler(this);


        add(labelName);
        add(name);
        add(labelAddress);
        add(address);
        add(labelDate);
        add(date);
        add(labelMaterial);
        add(material);
        add(labelCountFloor);
        add(countFloor);
        add(saveButton);
        addErrorsPanel();
    }

    class ErrorCallback<T> extends ru.amfitel.task.client.callback.AsyncCallback<T> {

        @Override
        public void onSuccess(T result) {

        }

        @Override
        protected void onConstractViolation(Set<ConstraintViolation<?>> violations) {
            super.onConstractViolation(violations);
            setErrors(violations);
        }
    }

    @Override
    public void onClick(ClickEvent clickEvent) {
        WrappedCallback wrappedCallback = new WrappedCallback(callback, new ErrorCallback<>());
        BuildDTO buildDTO = driver.flush();
        buildingService.saveBuildDTO(buildDTO, wrappedCallback);
    }

    @Override
    public void edit(final BuildDTO p) {
        driver.initialize(this);
        // Copy the data in the object into the UI
        driver.edit(p);
        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                buildingService.deleteBuild(p.getId(), deleteCallback);
            }
        });
        if (p.getId() != null)
            add(deleteButton);
    }

    public TextBox name() {
        return name;
    }

    public DateBox date() {
        return date;
    }

    public TextBox address() {
        return address;
    }

    public IntegerBox countFloor() {
        return countFloor;
    }

    interface Driver extends SimpleBeanEditorDriver<BuildDTO, BuildEditor> {
    }
}


