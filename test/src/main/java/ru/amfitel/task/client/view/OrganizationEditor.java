package ru.amfitel.task.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.callback.AsyncCallback;
import ru.amfitel.task.client.dto.OrganizationDto;

/**
 * @author alexanderdemin.
 */
public class OrganizationEditor extends DialogBox implements Editor<OrganizationDto> {

    private OrganizationSaver organizationSaver;

    interface Driver extends SimpleBeanEditorDriver<OrganizationDto, OrganizationEditor> {
    }

    Driver driver = GWT.create(Driver.class);

    public TextBox name;
    public TextBox inn;

    public OrganizationEditor(OrganizationSaver organizationSaver, OrganizationDto organizationDto) {
        this.organizationSaver = organizationSaver;

        name = new TextBox();
        inn = new TextBox();

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(name);
        verticalPanel.add(inn);

        add(verticalPanel);


        HorizontalPanel buttons = new HorizontalPanel();
        Button saveButton = new Button("Сохранить");
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                save();
            }
        });
        Button cancelButton = new Button("Отмена");
        cancelButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                cancel();
            }
        });
        buttons.add(saveButton);
        buttons.add(cancelButton);
        verticalPanel.add(buttons);
        edit(organizationDto);
        center();
    }

    private void edit (OrganizationDto organizationDto) {
        driver.initialize(this);
        driver.edit(organizationDto);
    }

    void save() {
        OrganizationDto edited = driver.flush();
        organizationSaver.save(edited, new AsyncCallback() {
            @Override
            public void onSuccess(Object result) {
                hide(false);
            }
        });
    }

    void cancel() {
        hide(false);
    }

    public TextBox name() {
        return name;
    }

    public TextBox inn() {
        return inn;
    }
}
