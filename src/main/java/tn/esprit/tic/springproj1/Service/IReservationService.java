package tn.esprit.tic.springproj1.Service;

import org.springframework.core.io.ByteArrayResource;
import tn.esprit.tic.springproj1.Entities.Etudiant;
import tn.esprit.tic.springproj1.Entities.RecaptchaResponse;
import tn.esprit.tic.springproj1.Entities.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation addReservation(Reservation e);
    Reservation updateReservation(Reservation e);
    Reservation retrieveReservation(Long idReservation);
    void removeReservation(Long idReservation);
    Etudiant affecterEtudiantAReservation (String nomEt, String prenomEt, Long idReservation) ;

    ByteArrayResource generatePDFFromReservationData(Long idReservation);
}
