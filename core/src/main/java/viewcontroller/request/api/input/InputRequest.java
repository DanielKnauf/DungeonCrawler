package viewcontroller.request.api.input;

public interface InputRequest<D> {

    InputRequestCallback<D> getCallback();

    boolean isCorrectType(Object candidate);

}
