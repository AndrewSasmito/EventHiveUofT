package app.interface_adapter.filter_event;

import java.util.List;

import app.use_case.filter_event.FilterEventInputBoundary;
import app.use_case.filter_event.FilterEventInputData;

/**
 * Controller for the Filter Event Use Case.
 */
public class FilterEventController {
    private final FilterEventInputBoundary userFilterEventUseCaseInteractor;

    public FilterEventController(FilterEventInputBoundary userFilterEventUseCaseInteractor) {
        this.userFilterEventUseCaseInteractor = userFilterEventUseCaseInteractor;
    }

    /**
     * Execute the Filter Event Use Case.
     * @param tags Event Categories.
     */
    public void execute(List<String> tags) {
        final FilterEventInputData filterEventInputData = new FilterEventInputData(tags);
        this.userFilterEventUseCaseInteractor.execute(filterEventInputData);
    }

    /**
     * Switches the view to the home screen.
     */
    public void switchToHomeView() {
        this.userFilterEventUseCaseInteractor.switchToHomeView();
    }
}
