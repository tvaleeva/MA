package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import ru.amfitel.task.client.dictionary.ObjectType;
import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.client.tree.DraggableLabel;

/**
 * Created by Bublik on 05.04.2016.
 */
public abstract class TreeChangeCallback<T> extends FailureIgnoreCallback<T> {

    protected Tree tree;

    public TreeChangeCallback(Tree tree) {
        this.tree = tree;
    }

    protected TreeItem findElement(AbstractDTO abstractDTO) {
        return findElement(abstractDTO.getObjectType(), abstractDTO.getId());

    }

    protected TreeItem findElement(ObjectType type, Long id) {
        for (int i = 0; i < tree.getItemCount(); i++) {
            TreeItem treeItem = tree.getItem(i);
            Widget widget = treeItem.getWidget();
            if (widget instanceof DraggableLabel) {
                DraggableLabel draggableLabel = (DraggableLabel) treeItem.getWidget();
                AbstractDTO abstractDTO = draggableLabel.getObject();
                //FIXME
                if (abstractDTO.getObjectType() == type && abstractDTO.getId().equals(id)) {
                    return treeItem;
                } else {
                    TreeItem item = find(treeItem, type, id);
                    if (item != null) {
                        return item;
                    }
                }
            } else {
                TreeItem item = find(treeItem, type, id);
                if (item != null) {
                    return item;
                }
            }
        }
        return null;
    }

    private TreeItem find(TreeItem parent, ObjectType type, Long id) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            TreeItem child = parent.getChild(i);
            Widget widget = child.getWidget();
            if (widget instanceof DraggableLabel) {
                DraggableLabel draggableLabel = (DraggableLabel) child.getWidget();
                AbstractDTO abstractDTO = draggableLabel.getObject();
                if (abstractDTO.getObjectType() == type && abstractDTO.getId().equals(id)) {
                    return child;

                } else {
                    TreeItem item = find(child, type, id);
                    if (item != null) {
                        return item;
                    }
                }
            }
            else {
                TreeItem item = find(child, type, id);
                if (item != null) {
                    return item;
                }
            }
        }
        return null;
    }

    protected void removeElementTree(AbstractDTO abstractDTO) {
        removeElementTree(abstractDTO.getObjectType(), abstractDTO.getId());

    }

    protected void removeElementTree(ObjectType type, Long id) {
        for (int i = 0; i < tree.getItemCount(); i++) {
            TreeItem treeItem = tree.getItem(i);
            Widget widget = treeItem.getWidget();
            if (widget instanceof DraggableLabel) {
                DraggableLabel draggableLabel = (DraggableLabel) treeItem.getWidget();
                AbstractDTO abstractDTO = draggableLabel.getObject();
                if (abstractDTO.getObjectType() == type && abstractDTO.getId().equals(id)) {
                    tree.removeItem(treeItem);
                    return;
                } else {
                    removeTreeItem(treeItem, type, id);
                }
            } else {
                removeTreeItem(treeItem, type, id);
            }
        }

    }

    private void removeTreeItem(TreeItem parent, ObjectType type, Long id) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            TreeItem child = parent.getChild(i);
            Widget widget = child.getWidget();
            if (widget instanceof DraggableLabel) {
                DraggableLabel draggableLabel = (DraggableLabel) child.getWidget();
                AbstractDTO abstractDTO = draggableLabel.getObject();
                if (abstractDTO.getObjectType() == type && abstractDTO.getId().equals(id)) {
                    parent.removeItem(child);
                    return;

                } else {
                    removeTreeItem(child, type, id);
                }
            } else {
                removeTreeItem(child, type, id);
            }
        }

    }
}
