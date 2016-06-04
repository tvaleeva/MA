package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.tree.DraggableLabel;

/**
 * Created by Bublik on 05.04.2016.
 */
public class ReplaceCallback<O extends AbstractDTO> extends TreeChangeCallback<O> {

    public ReplaceCallback(Tree tree) {
        super(tree);
    }

    @Override
    public void onSuccess(AbstractDTO abstractDTO) {
        replaceTreeElement(abstractDTO);
    }

    private void replaceTreeElement(AbstractDTO abstractDTO) {
        TreeItem treeItem = findElement(abstractDTO);
        DraggableLabel draggableLabel = (DraggableLabel) treeItem.getWidget();
        draggableLabel.setObject(abstractDTO);

    }

}
