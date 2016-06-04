package ru.amfitel.task.client.editor;


import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import ru.amfitel.task.client.dictionary.Material;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class MaterialEditor  extends FlowPanel implements LeafValueEditor<Material> {
 private ListBox listBox;

    public MaterialEditor() {
         listBox = new ListBox();


        for(Material m : Material.values()){
            listBox.addItem(m.getName());

        }
        add(listBox);
    }

    @Override
    public void setValue(Material material) {
        if(material!=null){

        for(int i=0;i<listBox.getItemCount();i++){
            if (listBox.getItemText(i).equals(material.getName())){
                listBox.setSelectedIndex(i);
                return;
            }
        }
        }
    }

    @Override
    public Material getValue() {
        return Material.getByName(listBox.getSelectedItemText());
    }
}
