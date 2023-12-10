package tn.esprit.tic.springproj1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj1.Entities.Bloc;
import tn.esprit.tic.springproj1.Entities.Foyer;
import tn.esprit.tic.springproj1.Repository.BlocRepository;
import tn.esprit.tic.springproj1.Repository.FoyerRepository;

import java.util.List;
import java.util.Set;

@Service
public class FoyerService implements IFoyerService{
    @Autowired
    FoyerRepository foyerRepository;
    BlocRepository blocRepository;
    @Override
    public List<Foyer> retrieveAllFoyer(){return foyerRepository.findAll();}
    @Override
    public Foyer addFoyer(Foyer e){return foyerRepository.save(e);}
    @Override
    public Foyer updateFoyer(Foyer e){return foyerRepository.save(e);}
    @Override
    public Foyer retrieveFoyer(Long idFoyer){ return foyerRepository.findById(idFoyer).get();}
    @Override
    public void removeFoyer(Long idFoyer){
         foyerRepository.deleteById(idFoyer);
    }

    public void archiverFoyer (long idFoyer){
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        if(foyer!= null){
            foyer.setArchived(true);
            foyerRepository.save(foyer);
        }
    }
    public Foyer addFoyerWithBloc(Foyer foyer) {
        //sauvegarder le fils
        Foyer foyer1 = foyerRepository.save(foyer);
        //parcourir les lists des parents
        foyer.getBlocs().stream().forEach(
                bloc -> {
                    bloc.setFoyer(foyer1);
                    blocRepository.save(bloc);
                }
        );
        return foyer;
        // !!!!  khtrna m child ll parent  !!!!
    }

}
