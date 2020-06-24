package toan.customevalidator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import toan.customevalidator.model.PhoneNumber;
import toan.customevalidator.validator.PhoneNumberValidator;

@Controller
public class PhoneNumberController {

    @Autowired
    private PhoneNumberValidator phoneNumberValidator;

    @GetMapping(path = "")
    public String getFormNewNumber(Model model) {
        model.addAttribute("phoneNumber", new PhoneNumber());
        return "form";
    }

    @PostMapping(path = "")
    public String checkValidation(@Validated @ModelAttribute("phoneNumber") PhoneNumber phoneNumber,
            BindingResult bindingResult, Model model) {
        phoneNumberValidator.validate(phoneNumber, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "form";
        } else {
            model.addAttribute("phone", phoneNumber.getNumber());
            return "result";
        }
    }
}