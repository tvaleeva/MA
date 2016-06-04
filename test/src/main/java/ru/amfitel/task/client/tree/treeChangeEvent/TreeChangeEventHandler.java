package ru.amfitel.task.client.tree.treeChangeEvent;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by Bublik on 10.04.2016.
 */
public interface TreeChangeEventHandler extends EventHandler {

    void onMessageReceived(TreeChangeEvent event);

}
