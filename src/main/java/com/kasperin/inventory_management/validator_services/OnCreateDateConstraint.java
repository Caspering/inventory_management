package com.kasperin.inventory_management.validator_services;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ CONSTRUCTOR, FIELD, METHOD, PARAMETER, TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {OnCreateDateValidator.class})
@Documented
public @interface OnCreateDateConstraint {

    String message() default " Mfg is ahead of Exp";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


//    String mfgDate();
//    String expDate();



    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        OnCreateDateConstraint[] value();
    }


}
