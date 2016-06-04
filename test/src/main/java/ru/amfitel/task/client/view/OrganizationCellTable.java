package ru.amfitel.task.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ru.amfitel.task.client.callback.AsyncCallback;
import ru.amfitel.task.client.callback.FailureIgnoreCallback;
import ru.amfitel.task.client.callback.WrappedCallback;
import ru.amfitel.task.client.dto.OrganizationDto;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

import java.util.List;

/**
 * @author alexanderdemin.
 */
public class OrganizationCellTable extends Composite {

    private BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    interface OrganizationCellTableUiBinder extends UiBinder<SimplePanel, OrganizationCellTable>{};

    final static OrganizationCellTableUiBinder uiBinder = GWT.create(OrganizationCellTableUiBinder.class);

    private OrganizationDto selected;

    @UiField
    CellTable<OrganizationDto> organizationTable;

    @UiField
    Button createButton;

    @UiField
    Button editButton;

    public OrganizationCellTable() {
        initWidget(uiBinder.createAndBindUi(this));
        initTable();
    }

    private void initTable() {
        TextColumn<OrganizationDto> organizationNameColumn = new TextColumn<OrganizationDto>() {
            @Override
            public String getValue(OrganizationDto object) {
                return object.getName();
            }
        };
        organizationTable.addColumn(organizationNameColumn, "Name");
        TextColumn<OrganizationDto> organizationInnColumn = new TextColumn<OrganizationDto>() {
            @Override
            public String getValue(OrganizationDto object) {
                return object.getInn();
            }
        };
        organizationTable.addColumn(organizationInnColumn, "ИНН");
        reloadData();
        final SingleSelectionModel<OrganizationDto> selectionModel = new SingleSelectionModel<>(organizationTable.getKeyProvider());
        organizationTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                selected = selectionModel.getSelectedObject();
                onSelectionChanged(selected);
            }
        });
    }


    public void reloadData() {
        this.buildingService.loadOrganizations(new FailureIgnoreCallback<List<OrganizationDto>>() {
            @Override
            public void onSuccess(List<OrganizationDto> organizationDtos) {
                organizationTable.setRowData(organizationDtos);
            }
        });
    }

    private void onSelectionChanged (OrganizationDto organizationDto) {
        editButton.setEnabled(organizationDto != null);
    }


    @UiHandler("editButton")
    void openOrganizationEdition(ClickEvent clickEvent) {
        final AsyncCallback<Void> reloadCallback = new AsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                reloadData();
            }
        };

        OrganizationSaver saver = new OrganizationSaver() {
            @Override
            public void save(OrganizationDto organizationDto, AsyncCallback<Void> callback) {
                AsyncCallback wrappedCallback = new WrappedCallback<Void>(callback, reloadCallback);
                buildingService.updateOrganization(organizationDto, wrappedCallback);
            }
        };
        OrganizationEditor editor = new OrganizationEditor(saver, selected);
        editor.show();
    }

    @UiHandler("createButton")
    void openOrganizationCreation(ClickEvent clickEvent) {
        final AsyncCallback<Void> reloadCallback = new AsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                reloadData();
            }
        };
        OrganizationSaver saver = new OrganizationSaver() {
            @Override
            public void save(OrganizationDto organizationDto, AsyncCallback<Void> callback) {
                AsyncCallback wrappedCallback = new WrappedCallback<Void>(callback, reloadCallback);
                buildingService.createOrganization(organizationDto, wrappedCallback);
            }
        };
        OrganizationEditor editor = new OrganizationEditor(saver, new OrganizationDto());
        editor.show();
    }

}
