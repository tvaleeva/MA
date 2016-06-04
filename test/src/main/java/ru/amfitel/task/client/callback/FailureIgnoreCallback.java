package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author tvaleeva
 * @since 31.03.2016
 */
public abstract class FailureIgnoreCallback<T> extends ru.amfitel.task.client.callback.AsyncCallback<T> {
    @Override
    public void onFailure(Throwable throwable) {
        if (throwable instanceof ConstraintViolationException){
            Set<ConstraintViolation<?>> set = ((ConstraintViolationException) throwable).getConstraintViolations();
            this.onConstractViolation(set);
        } else {
            Window.alert(throwable.getMessage());
        }
    }

    @Override
    public void onConstractViolation(Set<ConstraintViolation<?>> violations) {
        //do nothing
    }

    @Override
    public abstract void onSuccess(T t);
}
