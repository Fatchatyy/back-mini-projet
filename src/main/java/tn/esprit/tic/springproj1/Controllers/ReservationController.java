package tn.esprit.tic.springproj1.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.esprit.tic.springproj1.Entities.RecaptchaResponse;
import tn.esprit.tic.springproj1.Entities.Reservation;
import tn.esprit.tic.springproj1.Service.IReservationService;

import java.util.Collections;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {
    @Autowired
    private IReservationService reservationService;

    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "6Lf2jSMpAAAAACW0OFYQGJTxlYdFPf6JYgbqrjms";




    @GetMapping("/retrieve-all-reservation")
    public List<Reservation> getReservations(){
        List<Reservation> reservations = reservationService.retrieveAllReservations();
        return reservations;
    }
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") Long ReservationId){
        Reservation reservation = reservationService.retrieveReservation(ReservationId);
        return reservation;
    }
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation R){
        Reservation reservation= reservationService.addReservation(R);
        return reservation;
    }
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long reservationId){
        reservationService.removeReservation(reservationId);
    }
    @PutMapping("/update-reservation")
    public Reservation updateReservation(@RequestBody Reservation R){
        Reservation reservation = reservationService.updateReservation(R);
        return reservation;
    }
    @PostMapping("/yourEndpoint")
    public String verifyRecaptcha(@RequestParam("recaptchaResponse") String recaptchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("are we here");
        String url = RECAPTCHA_VERIFY_URL
                + "?secret=" + SECRET_KEY
                + "&response=" + recaptchaResponse;

        RecaptchaResponse response = restTemplate.postForObject(url, null, RecaptchaResponse.class);
        System.out.println("are we here too "+ response.isSuccess());
        if (response != null && response.isSuccess()) {
            System.out.println("successfuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuul");
            return "reCAPTCHA verified successfully";
        } else {
            System.out.println("faileeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed");
            return "reCAPTCHA verification failed";
        }
    }
    @GetMapping("/generate/{reservation-id}")
    public ByteArrayResource generatePDF(@PathVariable("reservation-id") Long idReservation) {
        return reservationService.generatePDFFromReservationData(idReservation);
    }



}
