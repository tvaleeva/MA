package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import ru.amfitel.task.client.dictionary.ObjectType;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.client.tree.BuildDraggableLabel;
import ru.amfitel.task.client.tree.CabinetDraggableLabel;
import ru.amfitel.task.client.tree.DraggableLabel;
import ru.amfitel.task.client.tree.FloorDraggableLabel;
import ru.amfitel.task.client.tree.item.TreeItemWithButton;

/**
 * Created by Bublik on 05.04.2016.
 */
public class InsertCallback<O extends AbstractDTO> extends TreeChangeCallback<O> {


    public InsertCallback(Tree tree) {
        super(tree);
    }

    @Override
    public void onSuccess(AbstractDTO o) {
        insertNewElement(o);
    }

    private void insertNewElement(AbstractDTO abstractDTO) {
        ObjectType requiredType = null;
        Long requiredId = null;
        switch (abstractDTO.getObjectType()) {
            case BUILDING:
                break;
            case FLOOR:
                requiredType = ObjectType.BUILDING;
                requiredId = ((FloorDTO) abstractDTO).getIdBuild();
                break;
            case CABINET:
                requiredType = ObjectType.FLOOR;
                requiredId = ((CabinetDTO) abstractDTO).getIdFloor();
                break;
        }

        TreeItem newTreeItem = createTreeItem(abstractDTO);
        if (requiredType == null) {
            tree.getItem(0).addItem(newTreeItem);
        } else {
            if (abstractDTO.getObjectType()==ObjectType.FLOOR) {
                FloorDTO floorDTO = (FloorDTO) abstractDTO;
                for(CabinetDTO cabinet : floorDTO.getCabinets() ){
                    newTreeItem.addItem(createTreeItem(cabinet));
                }
            }
            TreeItem treeItem = findElement(requiredType, requiredId);
            treeItem.addItem(newTreeItem);
        }
    }

    private TreeItem createTreeItem(AbstractDTO a) {
        DraggableLabel label = null;
        switch (a.getObjectType()) {
            case BUILDING:
                label = new BuildDraggableLabel((BuildDTO) a);
                break;
            case FLOOR:
                label = new FloorDraggableLabel((FloorDTO) a);
                break;
            case CABINET:
                label = new CabinetDraggableLabel((CabinetDTO) a);
                return new TreeItem(label);
        }
        return new TreeItemWithButton(label);
    }

    private AbstractDTO extractObject(TreeItem treeItem) {
        return ((DraggableLabel) treeItem.getWidget()).getObject();
    }


}
