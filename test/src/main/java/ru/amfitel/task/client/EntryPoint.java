package ru.amfitel.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.callback.*;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.editor.BuildEditor;
import ru.amfitel.task.client.editor.CabinetEditor;
import ru.amfitel.task.client.editor.DTOEditor;
import ru.amfitel.task.client.editor.FloorEditor;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;
import ru.amfitel.task.client.tree.BuildDraggableLabel;
import ru.amfitel.task.client.tree.CabinetDraggableLabel;
import ru.amfitel.task.client.tree.DraggableLabel;
import ru.amfitel.task.client.tree.FloorDraggableLabel;
import ru.amfitel.task.client.tree.item.TreeItemWithButton;
import ru.amfitel.task.client.tree.treeChangeEvent.TreeChangeEvent;
import ru.amfitel.task.client.tree.treeChangeEvent.TreeChangeEventHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint {

    //обращение  к серверу
    private BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    private VerticalPanel right;

    private final Tree tree = new Tree();

    private final AsyncCallback clearCallback = new AsyncCallback() {
        @Override
        public void onFailure(Throwable caught) {
            //right.clear();
        }

        @Override
        public void onSuccess(Object result) {
            right.clear();
        }
    };

    private final AsyncCallback deleteCallback = new DeleteCallback(tree);
    private final AsyncCallback replaceCallback = new ReplaceCallback(tree);
    private final AsyncCallback insertCallback = new InsertCallback(tree);


    public void onModuleLoad() {
        HorizontalPanel panel = new HorizontalPanel();
        RootPanel.get().add(panel);
        VerticalPanel left = new VerticalPanel();
        right = new VerticalPanel();
        Anchor a = new Anchor("logout");
        a.setHref(GWT.getHostPageBaseURL()+"logout");
        left.add(a);
        panel.add(left);
        panel.add(right);

        redrawTree(tree);
        left.add(tree);


        final WrappedCallback<AbstractDTO> replaceCallback = new WrappedCallback<AbstractDTO>(clearCallback, this.replaceCallback);
        final WrappedCallback<AbstractDTO> deleteCallback = new WrappedCallback<AbstractDTO>(clearCallback, this.deleteCallback);

        tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
            @Override
            public void onSelection(SelectionEvent<TreeItem> selectionEvent) {

                TreeItem item = selectionEvent.getSelectedItem();
                DraggableLabel label = (DraggableLabel)item.getWidget();
                DTOEditor editor = label.getEditor(replaceCallback, deleteCallback);
                editor.edit(label.getObject());
                right.clear();
                right.add(editor);

            }
        });
    }

    private TreeChangeEventHandler treeChangeEventHandler = new TreeChangeEventHandler() {
        @Override
        public void onMessageReceived(TreeChangeEvent event) {
            final WrappedCallback insert = new WrappedCallback(clearCallback, insertCallback);
            final WrappedCallback delete = new WrappedCallback(clearCallback, deleteCallback);
            TreeItem treeItem = event.getNewItem();
            if (treeItem instanceof TreeItemWithButton){
                TreeItemWithButton treeItemWithButton = (TreeItemWithButton) treeItem;
                treeItemWithButton.addTreeChangeHandler(treeChangeEventHandler);

                Widget widget = treeItemWithButton.getWidget();
                if (widget instanceof DraggableLabel){
                    DraggableLabel draggableLabel = (DraggableLabel) widget;
                    final AbstractDTO abstractDTO = draggableLabel.getObject();

                    switch (abstractDTO.getObjectType()){
                        case BUILDING:

                            draggableLabel.addDropHandler(new DropHandler() {
                                @Override
                                public void onDrop(DropEvent event) {
                                    event.preventDefault();
                                    FloorDTO floorDTO = (FloorDTO) DraggableLabel.getDragging().getObject();
                                    floorDTO.setIdBuild(abstractDTO.getId());
                                    final WrappedCallback deleteAndInsertCallback = new WrappedCallback(deleteCallback, insertCallback);

                                    buildingService.saveFloorDTO(floorDTO, deleteAndInsertCallback);
                                }
                            });

                            treeItemWithButton.addClickHandler(new ClickHandler() {
                                @Override
                                public void onClick(ClickEvent clickEvent) {
                                    right.clear();
                                    FloorEditor floorEditor = new FloorEditor(insert, delete);
                                    right.add(floorEditor);
                                    FloorDTO floorDTO= new FloorDTO();
                                    floorDTO.setIdBuild(abstractDTO.getId());
                                    floorEditor.edit(floorDTO);
                                }
                            });


                            break;
                        case FLOOR:
                            draggableLabel.addDropHandler(new DropHandler() {
                                @Override
                                public void onDrop(DropEvent event) {
                                    event.preventDefault();
                                    CabinetDTO  cabinetDTO = (CabinetDTO) DraggableLabel.getDragging().getObject();
                                    cabinetDTO.setIdFloor(abstractDTO.getId());
                                    final WrappedCallback deleteAndInsertCallback = new WrappedCallback(deleteCallback,insertCallback);
                                    buildingService.saveCabinetDTO(cabinetDTO,deleteAndInsertCallback);
                                }
                            });
                            treeItemWithButton.addClickHandler(new ClickHandler() {
                                @Override
                                public void onClick(ClickEvent event) {
                                    right.clear();
                                    CabinetEditor cabinetEditor = new CabinetEditor(insert, delete);
                                    right.add(cabinetEditor);
                                    CabinetDTO cabinetDTO= new CabinetDTO();
                                    cabinetDTO.setIdFloor(abstractDTO.getId());
                                    cabinetEditor.edit(cabinetDTO);

                                }
                            });
                            break;
                        case CABINET:
                            break;
                    }
                }
            }

        }
    };

    private void redrawTree(final Tree tree) {

        tree.clear();
        final WrappedCallback insert = new WrappedCallback(clearCallback, insertCallback);
        final WrappedCallback delete = new WrappedCallback(clearCallback, deleteCallback);


        buildingService.loadBuildings(new FailureIgnoreCallback<List<BuildDTO>>() {

            @Override
            public void onSuccess(List<BuildDTO> buildDTOs) {
                TreeItemWithButton root = new TreeItemWithButton();
                tree.addItem(root);
                root.addTreeChangeHandler(treeChangeEventHandler);
                root.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        right.clear();
                        BuildEditor buildEditor = new BuildEditor(insert, delete);
                        right.add(buildEditor);
                        buildEditor.edit(new BuildDTO());
                    }
                });
                root.setState(true);
                //реакция дерева на сохранение объекта
                for (final BuildDTO b : buildDTOs) {
                    DraggableLabel<BuildDTO> bdl = new BuildDraggableLabel(b);
                    TreeItemWithButton buildItem = new TreeItemWithButton(bdl);
                   // buildItem.addTreeChangeHandler(treeChangeEventHandler);
                    root.addItem(buildItem);
                    for (final FloorDTO f : b.getFloors()) {
                        DraggableLabel<FloorDTO> floorDTODraggableLabel = new FloorDraggableLabel(f);
                        TreeItemWithButton floorItem = new TreeItemWithButton(floorDTODraggableLabel);
                        buildItem.addItem(floorItem);
                        for (CabinetDTO c : f.getCabinets()) {
                            TreeItem cabinetItem = new TreeItem(new CabinetDraggableLabel(c));
                            floorItem.addItem(cabinetItem);
                        }
                    }
                }

            }
        });

    }
}
