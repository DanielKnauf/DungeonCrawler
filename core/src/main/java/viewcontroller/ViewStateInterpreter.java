package viewcontroller;

import viewcontroller.request.api.event.EventRequest;

public interface ViewStateInterpreter {

    void handleRequest(EventRequest eventRequest);
}
