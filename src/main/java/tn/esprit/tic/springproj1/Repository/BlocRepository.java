package tn.esprit.tic.springproj1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tic.springproj1.Entities.Bloc;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {
    Bloc findBynomBloc(String nomBloc);
}


