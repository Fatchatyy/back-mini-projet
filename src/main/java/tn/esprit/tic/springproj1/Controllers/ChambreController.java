package tn.esprit.tic.springproj1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.springproj1.Entities.Chambre;
import tn.esprit.tic.springproj1.Service.IChambreService;

import java.util.List;

@RestController
@RequestMapping("/chambres")
public class ChambreController {
    @Autowired
    private IChambreService chambreService;

    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres(){
        List<Chambre> chambres = chambreService.retrieveAllChambres();
        return chambres;
    }
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chambreId){
        return chambreService.retrieveChambre(chambreId);
    }
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre e){
        Chambre chambre = chambreService.addChambre(e);
        return chambre;
    }
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chambreId){
        chambreService.removeChambre(chambreId);
    }
    @PutMapping("/update-chambre")
    public Chambre updateChambre(@RequestBody Chambre e){
        Chambre chambre = chambreService.updateChambre(e);
        return chambre;
    }
}
