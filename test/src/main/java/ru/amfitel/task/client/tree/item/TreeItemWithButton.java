package ru.amfitel.task.client.tree.item;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsTreeItem;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import ru.amfitel.task.client.tree.treeChangeEvent.TreeChangeEvent;
import ru.amfitel.task.client.tree.treeChangeEvent.TreeChangeEventHandler;

/**
 * Created by Bublik on 09.04.2016.
 */
public class TreeItemWithButton extends TreeItem implements HasHandlers, HasClickHandlers{

    Button button;

    TreeItem buttonItem;

    private HandlerManager handlerManager;

    public TreeItemWithButton() {
        handlerManager = new HandlerManager(this);
        button = new Button("+");
        buttonItem = addItem(button);
    }

    public TreeItemWithButton(SafeHtml html) {
        super(html);
        handlerManager = new HandlerManager(this);
        button = new Button("+");
        buttonItem = addItem(button);
    }

    public TreeItemWithButton(Widget widget) {
        super(widget);
        handlerManager = new HandlerManager(this);
        button = new Button("+");
        buttonItem = addItem(button);
    }

    @Override
    public void addItem(IsTreeItem isItem) {
        super.addItem(isItem);
        moveButtonDown(isItem);
        fireTreeChangeEvent(isItem.asTreeItem());
    }

    @Override
    public TreeItem addItem(SafeHtml itemHtml) {
        TreeItem newItem = super.addItem(itemHtml);
        moveButtonDown(newItem);
        fireTreeChangeEvent(newItem);
        return newItem;
    }

    @Override
    public void addItem(TreeItem item) {
        super.addItem(item);
        moveButtonDown(item);
        fireTreeChangeEvent(item);
    }

    @Override
    public TreeItem addItem(Widget widget) {
        TreeItem newItem = super.addItem(widget);
        moveButtonDown(newItem);
        fireTreeChangeEvent(newItem);
        return newItem;
    }

    void fireTreeChangeEvent (TreeItem newItem) {
        TreeChangeEvent treeChangeEvent = new TreeChangeEvent(newItem);
        handlerManager.fireEvent(treeChangeEvent);
    }

    private void moveButtonDown(IsTreeItem treeItem){
        if (buttonItem != null && treeItem != buttonItem){
            removeItem(buttonItem);
            addItem(buttonItem);
        }
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return button.addClickHandler(handler);
    }

    public HandlerRegistration addTreeChangeHandler(TreeChangeEventHandler handler) {
        return handlerManager.addHandler(TreeChangeEvent.TYPE, handler);
    }


    @Override
    public void fireEvent(GwtEvent<?> event) {
        button.fireEvent(event);
    }
}
