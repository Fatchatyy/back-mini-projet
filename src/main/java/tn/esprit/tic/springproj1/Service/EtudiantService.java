package tn.esprit.tic.springproj1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj1.Entities.Etudiant;
import tn.esprit.tic.springproj1.Entities.Reservation;
import tn.esprit.tic.springproj1.Repository.EtudiantRepository;
import tn.esprit.tic.springproj1.Repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class EtudiantService implements IEtudiantService{

    @Autowired
    EtudiantRepository etudiantRepository;
    ReservationRepository reservationRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants(){return etudiantRepository.findAll();}
    @Override
    public Etudiant addEtudiant(Etudiant e){return etudiantRepository.save(e);}
    @Override
    public Etudiant updateEtudiant(Etudiant e){return etudiantRepository.save(e);}
    @Override
    public Etudiant retrieveEtudiant(Long idEtudiant){return etudiantRepository.findById(idEtudiant).get();}
    @Override
    public void removeEtudiant(Long idEtudiant){
        etudiantRepository.deleteById(idEtudiant);
    }

    public Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, String idReservation){
       Etudiant etudiant = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);
       Reservation reservation = reservationRepository.findByIdReservation(Long.valueOf(idReservation)).get();
       List<Etudiant> etudiantmiseajour =new ArrayList<>();
       if(reservation.getEtudiants()!=null){
           etudiantmiseajour = reservation.getEtudiants();
       }
       etudiantmiseajour.add(etudiant);
       reservation.setEtudiants(etudiantmiseajour);
       reservationRepository.save(reservation);
       return etudiant;
    }
}
