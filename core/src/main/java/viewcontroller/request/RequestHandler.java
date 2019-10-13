package viewcontroller.request;

import viewcontroller.ViewStateInterpreter;
import viewcontroller.request.api.display.DisplayRequestBase;
import viewcontroller.request.api.event.EventRequestBase;
import viewcontroller.request.api.input.InputRequest;
import viewcontroller.request.api.input.InputRequestBase;
import viewcontroller.request.api.input.InputRequestCallback;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class RequestHandler {

    private final ViewStateInterpreter view;

    private final LinkedList<EventRequestBase> requests = new LinkedList<>();

    private final PropertyChangeListener listener = this::evaluatePropertyChange;

    private void evaluatePropertyChange(PropertyChangeEvent event) {
        if ("isFinished".equals(event.getPropertyName())
                && event.getOldValue() instanceof Boolean
                && (Boolean) event.getOldValue()) {
            showNextRequest(event.getNewValue());
        }
    }

    private void showNextRequest(Object data) {
        EventRequestBase currentRequest = requests.pollFirst();

        if (currentRequest != null) {
            currentRequest.removePropertyChangedListener(listener);

            if (currentRequest instanceof InputRequest
                    && ((InputRequest) currentRequest).isCorrectType(data)) {
                ((InputRequest) currentRequest).getCallback().receiveAnswer(data);
            }
        }

        if (requests.isEmpty()) {
            return;
        }

        EventRequestBase nextRequest = requests.getFirst();

        if (nextRequest != null) {
            forwardRequest(nextRequest);
        }
    }

    public RequestHandler(ViewStateInterpreter view) {
        this.view = view;
    }

    public void startViewRequest(Event event) {
        startViewRequest(event, null, null, null);
    }

    public <D> void startViewRequest(Event event, D data) {
        startViewRequest(event, null, null, Collections.singletonList(data));
    }

    public <D> void startViewRequest(Event event, D... data) {
        startViewRequest(event, null, null, Arrays.asList(data));
    }

    public <D> void startViewRequest(Event event, InputRequestCallback<D> callback, Class<D> clazz, List<D> data) {
        EventRequestBase newRequest;

        if (data == null) {
            newRequest = new EventRequestBase<>(event);
        } else if (callback == null) {
            newRequest = new DisplayRequestBase<D>(event, data);
        } else {
            newRequest = new InputRequestBase<D>(event, callback, data) {
                @Override
                public boolean isCorrectType(Object candidate) {
                    return Objects.equals(clazz.getName(), candidate.getClass().getGenericSuperclass().getTypeName());
                }
            };
        }

        receiveViewRequest(newRequest);
    }

    private void receiveViewRequest(EventRequestBase hasPropertyChangeListener) {
        requests.add(hasPropertyChangeListener);

        if (requests.size() == 1) {
            forwardRequest(hasPropertyChangeListener);
        }
    }

    private void forwardRequest(EventRequestBase hasPropertyChangeListener) {
        hasPropertyChangeListener.addPropertyChangedListener(listener);
        view.handleRequest(hasPropertyChangeListener);
    }
}
