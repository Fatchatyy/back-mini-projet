package tn.esprit.tic.springproj1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj1.Entities.Foyer;
import tn.esprit.tic.springproj1.Entities.Universite;
import tn.esprit.tic.springproj1.Repository.FoyerRepository;
import tn.esprit.tic.springproj1.Repository.UniversiteRepository;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService{
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    FoyerRepository foyerRepository;
    @Override
    public List<Universite> retrieveAllUniversites(){return universiteRepository.findAll();}
    @Override
    public Universite addUniversite(Universite e){return universiteRepository.save(e);}
    @Override
    public Universite updateUniversite(Universite e){return universiteRepository.save(e);}
    @Override
    public Universite retrieveUniversite(Long idUniversite){
        return universiteRepository.findById(idUniversite).get();
    }
    @Override
    public void removeUniversites(Long idUniversite){
        universiteRepository.deleteById(idUniversite);
    }
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        // Recherche du Foyer par ID
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        // Recherche de l'Universite par nom
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);


        // Affectation du Foyer à l'Universite
        foyer.setUniversite(universite);

        // Sauvegarde du Foyer mis à jour
        foyerRepository.save(foyer);

        return universite;
    }
    public Universite desaffecterFoyerAUniversite(long idFoyer, long idUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).get();
        Universite universite = universiteRepository.findById(idUniversite).get();

        // Remove the association by setting the Foyer's universite reference to null
        foyer.setUniversite(null);

        // Save the Foyer to update the association
        foyerRepository.save(foyer);

        return universite;
    }

}
