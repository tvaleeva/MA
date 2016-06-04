package ru.amfitel.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import ru.amfitel.task.client.editor.BuildEditor;

/**
 * @author alexanderdemin.
 */
public class MainView extends Composite {

    @UiField
    VerticalPanel left;

    @UiField
    VerticalPanel right;

    @UiField
    Tree tree;

    //Надо вручную создать в конструкторе перед вызовом uiBinder.createAndBindUi(this);
    @UiField(provided = true)
    Widget editor;

    //HorizontalPanel - корневой элемент
    //MainView - класс на который маппить (текущий)
    private interface MainViewUiBinder extends UiBinder<HorizontalPanel, MainView> {}
    private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

    public MainView() {
        editor = new SimplePanel();
        initWidget(uiBinder.createAndBindUi(this));
    }

}
