package ru.amfitel.task.client;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by Bublik on 27.03.2016.
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint {

    public void onModuleLoad() {
        MainView mainView = new MainView();
        RootPanel.get().add(mainView);
    }
}
