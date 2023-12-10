package tn.esprit.tic.springproj1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj1.Entities.*;
import tn.esprit.tic.springproj1.Repository.ChambreRepository;
import tn.esprit.tic.springproj1.Repository.FoyerRepository;
import tn.esprit.tic.springproj1.Repository.ReservationRepository;

import java.lang.reflect.Type;
import java.time.Year;
import java.util.*;

@Service
public class ChambreService implements IChambreService {
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    FoyerRepository foyerRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Override
    public List<Chambre> retrieveAllChambres(){return chambreRepository.findAll();};
    @Override
    public Chambre addChambre(Chambre e){return chambreRepository.save(e);}
    @Override
    public Chambre updateChambre(Chambre e){return chambreRepository.save(e);}
    @Override
    public Chambre retrieveChambre(Long idChambre){ return chambreRepository.findById(idChambre).get();}
    @Override
    public void removeChambre(Long idChambre){
        chambreRepository.deleteById(idChambre);
    }

    @Scheduled(fixedRate = 60000)
    public void listeChambresParBloc(){
        List<Foyer> foyers = foyerRepository.findAll();
        HashMap<String,List<Chambre>> ChambreParBloc = new HashMap<>();
        for(Foyer foyer : foyers){
            for(Bloc bloc : foyer.getBlocs()){
                String NomBloc = bloc.getNomBloc();
                for (Chambre chambre : bloc.getChambres()){
                    if(!ChambreParBloc.containsKey(NomBloc)){
                        ChambreParBloc.put(NomBloc, new ArrayList<>());
                    }
                    ChambreParBloc.get(NomBloc).add(chambre);
                }
            }

        for(Map.Entry<String,List<Chambre>> entry : ChambreParBloc.entrySet()){
            System.out.println("bloc : "+entry.getKey() +"ayant une capacité de " + entry.getValue().size());
            System.out.println("Liste des chambres de bloc "+ entry.getKey());
            for(Chambre chambre : entry.getValue()){
                System.out.println("Chambre numero " + chambre.getNumeroChambre() +" de type " +chambre.getTypeC());
            }
        }
        }
    }

    @Scheduled(fixedRate = 300000)
    public void pourcentageChambreParTypeChambre(){
      List<Chambre> chambres = chambreRepository.findAll();
      Map<TypeChambre,Integer> CountByType = new HashMap<>();
      int totalChambres = chambres.size();
      for(Chambre chambre : chambres){
          TypeChambre type = chambre.getTypeC();
          CountByType.put(type, CountByType.getOrDefault(type , 0) +1 );
      }
        System.out.println("nombre total chambres " + totalChambres);
      for(Map.Entry<TypeChambre,Integer> entry : CountByType.entrySet()){
          double pourcentage = entry.getValue()*100 / totalChambres;
          System.out.println("le pourcentage des chambres pour le type "+ entry.getKey() +" est " + entry.getValue());
      }

    }

   /* @Scheduled(fixedRate = 300000) // Every 5 minutes
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        List<Chambre> chambres = chambreRepository.findAll();

        for (Chambre chambre : chambres) {
            Long totalCapacity = calculateTotalCapacity(chambre); // Implement this method

            int totalReservationsInCurrentYear = countReservationsForChambreInYear(chambre, currentYear); // Implement this method

            Long availablePlaces = totalCapacity - totalReservationsInCurrentYear;

            System.out.println("nombre place restante en " + currentYear + " pour la chambre " +
                    chambre.getIdChambre() + " est égal à " + availablePlaces);
        }
    } */
    private Long calculateTotalCapacity(Chambre chambre) {
        Bloc bloc = chambre.getBloc(); //
        Foyer foyer = bloc.getFoyer();
        Long totalCapacity = foyer.getCapaciteFoyer();
        return totalCapacity;
    }
   /* private int countReservationsForChambreInYear(Chambre chambre, int year) {
        int reservationsInYear = 0;

        List<Reservation> reservations = reservationRepository.findByChambreIdAndYear(chambre.getIdChambre(), year); // Use a custom query to fetch reservations
        reservationsInYear = reservations.size();

        return reservationsInYear;
    } */
}
