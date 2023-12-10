package tn.esprit.tic.springproj1.Service;

import tn.esprit.tic.springproj1.Entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();
    Universite addUniversite(Universite e);
    Universite updateUniversite(Universite e);
    Universite retrieveUniversite(Long idUniversite);
    void removeUniversites(Long idUniversite);
    Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) ;

}
