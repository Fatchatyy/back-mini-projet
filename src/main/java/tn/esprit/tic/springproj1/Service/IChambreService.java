package tn.esprit.tic.springproj1.Service;

import tn.esprit.tic.springproj1.Entities.Chambre;

import java.util.List;

public interface IChambreService {
   public List<Chambre> retrieveAllChambres();
   public  Chambre addChambre(Chambre e);
    public Chambre updateChambre( Chambre e);
   public Chambre retrieveChambre(Long idChambre);
    void removeChambre(Long idChambre);
}
