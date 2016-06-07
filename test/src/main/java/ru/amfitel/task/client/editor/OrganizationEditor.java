package ru.amfitel.task.client.editor;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import ru.amfitel.task.client.dto.OrganizationDto;
import ru.amfitel.task.client.view.suggestBox.oracle.OrganizationsSuggestOracle;

/**
 * @author alexanderdemin.
 */
public class OrganizationEditor extends Composite implements LeafValueEditor<OrganizationDto>, TakesValue<OrganizationDto> {

    private OrganizationDto value;

    private SuggestBox wrapped;

    public OrganizationEditor() {
        wrapped = new SuggestBox(new OrganizationsSuggestOracle());
        wrapped.getValue();
        wrapped.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            @Override
            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {
                OrganizationsSuggestOracle.OrganizationSuggestion suggestion = (OrganizationsSuggestOracle.OrganizationSuggestion) event.getSelectedItem();
                value = suggestion.getOrganization();
            }
        });
        initWidget(wrapped);
    }

    @Override
    public void setValue(OrganizationDto value) {
        this.value = value;
        if (value != null) {
            wrapped.setValue(value.getName());
        }
    }

    @Override
    public OrganizationDto getValue() {
        return this.value;
    }
}
