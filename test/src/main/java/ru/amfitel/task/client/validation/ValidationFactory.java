package ru.amfitel.task.client.validation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.entity.Build;

import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * Created by Bublik on 07.04.2016.
 */
public final class ValidationFactory extends AbstractGwtValidatorFactory {

    @GwtValidation(value={BuildDTO.class}, groups = {Default.class})
    public interface GwtValidator extends Validator {
    }

    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }

}
