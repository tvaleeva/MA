package ru.amfitel.task.client.tree;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.amfitel.task.client.callback.DeleteCallback;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.editor.CabinetEditor;
import ru.amfitel.task.client.editor.DTOEditor;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public class CabinetDraggableLabel extends DraggableLabel<CabinetDTO> {

    public CabinetDraggableLabel(CabinetDTO object) {
        super(object);
    }

    @Override
    protected String getLabelText() {
        return getObject().toString();
    }

    @Override
    public DTOEditor getEditor(AsyncCallback<CabinetDTO> redrawCallback, AsyncCallback<CabinetDTO> deleteCallback) {
        return new CabinetEditor(redrawCallback, deleteCallback);
    }

    @Override
    protected boolean isDroppable() {
        return false;
    }

}
