package tn.esprit.tic.springproj1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj1.Entities.Bloc;
import tn.esprit.tic.springproj1.Entities.Chambre;
import tn.esprit.tic.springproj1.Repository.BlocRepository;
import tn.esprit.tic.springproj1.Repository.ChambreRepository;

import java.util.List;

@Service
public class BlocService implements IBlocService{

    @Autowired
    BlocRepository blocRepository;
    @Autowired
    ChambreRepository chambreRepository;
    @Override
    public List<Bloc> retrieveAllBlocs(){ return blocRepository.findAll();}
    public Bloc addBloc(Bloc e){return blocRepository.save(e);}
    @Override
    public Bloc updateBloc(Bloc e){return blocRepository.save(e);}
    @Override
    public Bloc retrieveBloc(Long idBloc){ return blocRepository.findById(idBloc).get();}
    @Override
    public void removeBloc(Long idBloc){
        blocRepository.deleteById(idBloc);
    }

    public Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc){
        Bloc bloc = blocRepository.findBynomBloc(nomBloc);
        numChambre.stream().forEach(
                c -> {
                    Chambre chambre = chambreRepository.findByNumeroChambre(c);
                    chambre.setBloc(bloc);
       chambre.setBloc(bloc);
                    chambreRepository.save(chambre);
                }
        );
        return bloc;
    }
}


