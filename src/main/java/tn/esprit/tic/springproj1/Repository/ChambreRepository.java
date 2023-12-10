package tn.esprit.tic.springproj1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.springproj1.Entities.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {
    Chambre findByNumeroChambre (Long numChambre);
}
