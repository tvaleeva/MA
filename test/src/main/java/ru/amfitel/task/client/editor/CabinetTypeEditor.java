package ru.amfitel.task.client.editor;


import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import ru.amfitel.task.client.dictionary.CabinetType;
import ru.amfitel.task.client.dictionary.FloorType;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class CabinetTypeEditor extends FlowPanel implements LeafValueEditor<CabinetType> {
 private ListBox listBox;

    public CabinetTypeEditor() {
         listBox = new ListBox();


        for(CabinetType m : CabinetType.values()){
            listBox.addItem(m.getName());

        }
        add(listBox);
    }

    @Override
    public void setValue(CabinetType material) {

        for(int i=0;i<listBox.getItemCount();i++){
            if (listBox.getItemText(i).equals(material.getName())){
                listBox.setSelectedIndex(i);
                return;
            }
        }
    }

    @Override
    public CabinetType getValue() {
        return CabinetType.getByName(listBox.getSelectedItemText());
    }
}
