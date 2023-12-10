package tn.esprit.tic.springproj1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.springproj1.Entities.Etudiant;
import tn.esprit.tic.springproj1.Entities.Reservation;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Etudiant findByNomEtAndPrenomEt(String nomEt, String prenomEt);
}
