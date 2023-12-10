package tn.esprit.tic.springproj1.Service;

import tn.esprit.tic.springproj1.Entities.Bloc;

import java.util.List;

public interface IBlocService {
    List<Bloc> retrieveAllBlocs();

    Bloc addBloc(Bloc e);

    Bloc updateBloc( Bloc e);
    Bloc retrieveBloc (Long idBloc);
    void removeBloc(Long idBloc);

     Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc) ;
}
