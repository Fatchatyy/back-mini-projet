package tn.esprit.tic.springproj1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.springproj1.Entities.Foyer;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer,Long> {
}
