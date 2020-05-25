//package com.kasperin.inventory_management.validator_services;
//
//import org.springframework.stereotype.Component;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validator;
//import javax.validation.executable.ExecutableValidator;
//import javax.validation.metadata.BeanDescriptor;
//import java.util.Set;
//
//@Component
//public class OnUpdateDateValidator implements Validator {
//    @Override
//    public <T> Set<ConstraintViolation<T>> validate(T t, Class<?>... classes) {
//        return null;
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//
//        if (errors.getErrorCount() == 0) {
//
//            SimpleDto param = (SimpleDto) target;
//            Date now = new Date();
//            if (param.getCreatedDatetime() == null) {
//
//                errors.reject("100",
//
//                        "Create Date Time can't be null");
//
//            } else if (now.before(param.getCreatedDatetime())) {
//
//                errors.reject("101",
//
//                        "Create Date Time can't be after current date time");
//
//            }
//
//    @Override
//    public <T> Set<ConstraintViolation<T>> validateProperty(T t, String s, Class<?>... classes) {
//        return null;
//    }
//
//    @Override
//    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> aClass, String s, Object o, Class<?>... classes) {
//        return null;
//    }
//
//    @Override
//    public BeanDescriptor getConstraintsForClass(Class<?> aClass) {
//        return null;
//    }
//
//    @Override
//    public <T> T unwrap(Class<T> aClass) {
//        return null;
//    }
//
//    @Override
//    public ExecutableValidator forExecutables() {
//        return null;
//    }
//}
