package viewcontroller.request.api.event;

import viewcontroller.request.Event;
import viewcontroller.request.api.HasPropertyChangeListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventRequestBase<D> implements HasPropertyChangeListener, EventRequest<D> {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private final AtomicBoolean isAlreadyFinished = new AtomicBoolean(false);

    private final Event event;

    public EventRequestBase(Event event) {
        this.event = event;
    }

    @Override
    public Event getEvent() {
        return event;
    }

    @Override
    public void isFinished() {
        isFinished(null);
    }

    @Override
    public void isFinished(D data) {
        if (isAlreadyFinished.getAndSet(true)) {
            return;
        }

        support.firePropertyChange("isFinished", true, data);
    }

    @Override
    public void addPropertyChangedListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangedListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
