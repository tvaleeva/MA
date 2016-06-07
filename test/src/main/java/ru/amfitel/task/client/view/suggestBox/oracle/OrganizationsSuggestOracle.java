package ru.amfitel.task.client.view.suggestBox.oracle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.SuggestOracle;
import ru.amfitel.task.client.callback.AsyncCallback;
import ru.amfitel.task.client.dto.OrganizationDto;
import ru.amfitel.task.client.service.BuildingService;
import ru.amfitel.task.client.service.BuildingServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexanderdemin.
 */
public class OrganizationsSuggestOracle extends SuggestOracle {

    public static class OrganizationSuggestion implements Suggestion {

        public OrganizationSuggestion(OrganizationDto organization) {
            this.organization = organization;
        }

        private OrganizationDto organization;

        @Override
        public String getDisplayString() {
            return organization.getName();
        }

        @Override
        public String getReplacementString() {
            return organization.getName();
        }

        public OrganizationDto getOrganization() {
            return organization;
        }
    }

    private BuildingServiceAsync buildingService = GWT.create(BuildingService.class);

    @Override
    public void requestSuggestions(final Request request,final  Callback callback) {


        String query = request.getQuery();
        buildingService.loadOrganizationsByName(query, new AsyncCallback<List<OrganizationDto>>() {
            @Override
            public void onSuccess(List<OrganizationDto> result) {
                Response response = new Response();
                List<Suggestion> suggestions = new ArrayList<Suggestion>();
                for (OrganizationDto organizationDto : result) {
                    suggestions.add(new OrganizationSuggestion(organizationDto));
                }
                response.setSuggestions(suggestions);
                callback.onSuggestionsReady(request, response);
            }
        });
    }
}
