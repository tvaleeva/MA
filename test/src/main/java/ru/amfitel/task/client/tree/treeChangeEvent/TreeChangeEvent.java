package ru.amfitel.task.client.tree.treeChangeEvent;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * Created by Bublik on 10.04.2016.
 */
public class TreeChangeEvent extends GwtEvent<TreeChangeEventHandler> {

    private TreeItem newItem;

    public TreeChangeEvent(TreeItem newItem) {
        this.newItem = newItem;
    }

    public static Type<TreeChangeEventHandler> TYPE = new Type<TreeChangeEventHandler>();


    @Override
    public Type<TreeChangeEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(TreeChangeEventHandler handler) {
        handler.onMessageReceived(this);
    }

    public TreeItem getNewItem() {
        return newItem;
    }
}
