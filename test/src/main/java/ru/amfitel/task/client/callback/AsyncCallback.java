package ru.amfitel.task.client.callback;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by Bublik on 07.04.2016.
 */

//TODO delete me
public abstract class AsyncCallback<T> implements com.google.gwt.user.client.rpc.AsyncCallback<T> {

    @Override
    public void onFailure(Throwable caught) {
        if (caught instanceof ConstraintViolationException){
            Set<ConstraintViolation<?>> set = ((ConstraintViolationException) caught).getConstraintViolations();
            this.onConstractViolation(set);
        }
    }

    public abstract void onSuccess(T result);

    protected void onConstractViolation(Set<ConstraintViolation<?>> violations){

    }
}
