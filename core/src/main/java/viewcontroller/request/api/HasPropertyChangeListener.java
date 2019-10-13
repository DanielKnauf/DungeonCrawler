package viewcontroller.request.api;

import java.beans.PropertyChangeListener;

public interface HasPropertyChangeListener {

    void addPropertyChangedListener(PropertyChangeListener listener);

    void removePropertyChangedListener(PropertyChangeListener listener);
}
