package viewcontroller.request.api.event;

import viewcontroller.request.Event;

public interface EventRequest<D> {

    Event getEvent();

    void isFinished();

    void isFinished(D data);
}
