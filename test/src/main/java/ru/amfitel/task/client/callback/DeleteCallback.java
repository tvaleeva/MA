package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.ui.Tree;
import ru.amfitel.task.client.dto.AbstractDTO;

/**
 * Created by Bublik on 05.04.2016.
 */
public class DeleteCallback<O extends AbstractDTO> extends TreeChangeCallback<O>{

    public DeleteCallback(Tree tree) {
        super(tree);
    }

    @Override
    public void onSuccess(AbstractDTO o) {
        removeElementTree(o);
    }
}
