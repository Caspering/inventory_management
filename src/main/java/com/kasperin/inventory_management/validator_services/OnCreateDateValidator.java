package com.kasperin.inventory_management.validator_services;

import com.kasperin.inventory_management.domain.Items.ProcessedFood;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

//@SupportedValidationTarget({ValidationTarget.ANNOTATED_ELEMENT})
public class OnCreateDateValidator implements ConstraintValidator<OnCreateDateConstraint, ProcessedFood> {

    @Override
    public void initialize(OnCreateDateConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(ProcessedFood processedFood, ConstraintValidatorContext constraintValidatorContext) {

        //create localDate instances for comparing dates.
        LocalDate expDate = processedFood.getExpDate();
        LocalDate mfgDate = processedFood.getMfgDate();

        //compare dates
        int diff = expDate.compareTo(mfgDate);

        if (processedFood == null){
            return true;
        }

        boolean isValid = (diff == 0);

        if (diff == 0) {
            return true;
        } else if (diff > 0) {
            return true;
        }  else if (diff < 0) {
            return false;
        }

    if ( !isValid ) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate( "{my.custom.template}" )
                    .addPropertyNode( "expDate" ).addConstraintViolation();
        }


        return false;
    }
}
