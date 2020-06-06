//package com.kasperin.inventory_management.validator_services;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.*;
//
//import static java.lang.annotation.ElementType.*;
//import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target({ CONSTRUCTOR, FIELD, METHOD, PARAMETER, TYPE, ANNOTATION_TYPE})
//@Retention(RUNTIME)
//@Constraint(validatedBy = {UniqueBarcodeValidator.class})
//@Documented
//public @interface UniqueBarcode {
//
//
//        String message() default "An item with the specified barcode exists in the repository";
//
//        Class<?>[] groups() default {};
//
//        Class<? extends Payload>[] payload() default {};
//
//
//
//
//
//
//
//
//}
