package tn.esprit.tic.springproj1.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.tic.springproj1.Entities.RecaptchaResponse;

@RestController
@RequestMapping("/mock-recaptcha")
public class MockRecaptchaController {

    @PostMapping("/verify")
    public RecaptchaResponse mockRecaptchaVerification(@RequestParam("response") String recaptchaResponse) {
        // Simulate reCAPTCHA verification logic
        // Here, you can validate the provided recaptchaResponse and return a mock response
        boolean success = recaptchaResponse.equals("mock-response"); // Change this condition based on your mock

        return new RecaptchaResponse(success);
    }
}