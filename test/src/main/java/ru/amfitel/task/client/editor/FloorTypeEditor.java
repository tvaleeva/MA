package ru.amfitel.task.client.editor;


import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import ru.amfitel.task.client.dictionary.FloorType;
import ru.amfitel.task.client.dictionary.Material;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class FloorTypeEditor extends FlowPanel implements LeafValueEditor<FloorType> {
 private ListBox listBox;

    public FloorTypeEditor() {
         listBox = new ListBox();


        for(FloorType m : FloorType.values()){
            listBox.addItem(m.getName());

        }
        add(listBox);
    }

    @Override
    public void setValue(FloorType material) {

        for(int i=0;i<listBox.getItemCount();i++){
            if (listBox.getItemText(i).equals(material.getName())){
                listBox.setSelectedIndex(i);
                return;
            }
        }
    }

    @Override
    public FloorType getValue() {
        return FloorType.getByName(listBox.getSelectedItemText());
    }
}
