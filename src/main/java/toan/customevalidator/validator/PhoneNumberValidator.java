package toan.customevalidator.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import toan.customevalidator.model.PhoneNumber;

@Component
public class PhoneNumberValidator implements Validator {

    static final String ERROR_NUMBER = "number";

    @Override
    public boolean supports(Class<?> clazz) {
        return PhoneNumber.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PhoneNumber phoneNumber = (PhoneNumber) target;
        String number = phoneNumber.getNumber();
        ValidationUtils.rejectIfEmpty(errors, ERROR_NUMBER, "number.empty");
        if (number.length() > 11 || number.length() < 10) {
            errors.rejectValue(ERROR_NUMBER, "number.length");
        }
        if (!number.startsWith("0")) {
            errors.rejectValue(ERROR_NUMBER, "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")) {
            errors.rejectValue(ERROR_NUMBER, "number.matches");
        }
    }

}