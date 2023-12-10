package tn.esprit.tic.springproj1.Service;

import tn.esprit.tic.springproj1.Entities.Foyer;

import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyer();

    Foyer addFoyer(Foyer e);

    Foyer updateFoyer(Foyer e);

    Foyer retrieveFoyer(Long idFoyer);

    void removeFoyer(Long idFoyer);

    void archiverFoyer(long idFoyer);

    Foyer addFoyerWithBloc(Foyer f);
}
