package viewcontroller.request.api.input;

import viewcontroller.request.Event;
import viewcontroller.request.api.display.DisplayRequestBase;

import java.util.List;

public abstract class InputRequestBase<D> extends DisplayRequestBase<D> implements InputRequest<D> {

    private final InputRequestCallback<D> callback;

    protected InputRequestBase(Event event,
                               InputRequestCallback<D> callback,
                               List<D> items) {
        super(event, items);
        this.callback = callback;
    }

    @Override
    public InputRequestCallback<D> getCallback() {
        return callback;
    }
}
