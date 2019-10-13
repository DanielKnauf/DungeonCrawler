package viewcontroller.request.api.display;

import viewcontroller.request.Event;
import viewcontroller.request.api.event.EventRequestBase;

import java.util.List;

public class DisplayRequestBase<D> extends EventRequestBase<D> implements DisplayRequest<D> {

    private final List<D> displayItems;

    public DisplayRequestBase(Event event, List<D> displayItems) {
        super(event);
        this.displayItems = displayItems;
    }

    @Override
    public List<D> getDisplayItems() {
        return displayItems;
    }
}
