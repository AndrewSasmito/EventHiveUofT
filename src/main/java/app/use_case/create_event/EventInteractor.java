package app.use_case.create_event;


import app.entity.Event.Event;
import app.entity.Event.EventFactory;

/**
 * The Event Interactor
 * The Event Interactor handles the input
 * If a user with the inputted username exists, then get the userPresenter to prepare a fail view on frontend
 * Do a similar thing for other errors
 * Otherwise, interact with the database through hte userDAO to save the user and prepare a success view to the
 * front end.
 *
 * NOTE: userPresenter is just the outputBoundary class
 * Note: EventInteractor is an implementation of the inputBoundary interface
 */
public class EventInteractor implements EventInputBoundary {
    EventUserDataAccessInterface eventDataAccessObject;
    EventOutputBoundary eventPresenter;
    EventFactory eventFactory;

    public EventInteractor(EventUserDataAccessInterface eventDataAccessObject,
                           EventOutputBoundary eventPresenter,
                           EventFactory eventFactory) {

        this.eventDataAccessObject = eventDataAccessObject;
        this.eventPresenter = eventPresenter;
        this.eventFactory = eventFactory;
    }

    @Override
    public void execute(EventInputData eventInputData) {
        if (eventDataAccessObject.valid_coordinates(eventInputData.getLatitude(), eventInputData.getLongitude())) {
            eventPresenter.prepareFailView("Invalid coordinates");
        } else if (eventDataAccessObject.valid_capacity(eventInputData.getCapacity())) {
            eventPresenter.prepareFailView("Invalid capacity");
        } else{
            final Event event = eventFactory.create(eventInputData.getEventId(), eventInputData.getOrganizer(),
                    eventInputData.getTitle(), eventInputData.getDescription(), eventInputData.getDateTime(),
                    eventInputData.getCapacity(), eventInputData.getLatitude(), eventInputData.getLongitude(),
                    eventInputData.getTags());
            eventDataAccessObject.save(event);

            final EventOutputData eventOutputData = new EventOutputData(event.getTitle(), false);
            eventPresenter.prepareSuccessView(eventOutputData);
        }
    }
}