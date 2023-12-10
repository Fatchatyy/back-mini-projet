package tn.esprit.tic.springproj1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.springproj1.Entities.Universite;
import tn.esprit.tic.springproj1.Service.IUniversiteService;

import java.util.List;

@RestController
public class UniversiteController {
    @Autowired
    private IUniversiteService universiteService;

    @GetMapping("/retrieve-all-Universite")
    public List<Universite> getUniversitesr() {
        List<Universite>  universites = universiteService.retrieveAllUniversites();
        return universites;
    }
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite getUniversite(@PathVariable("universite-id") Long universiteId){
        Universite universite = universiteService.retrieveUniversite(universiteId);
        return universite;
    }
    @PostMapping("/add-Universite")
    public Universite addUniversite(@RequestBody Universite U){
        Universite universite = universiteService.addUniversite(U);
        return universite;
    }
    @DeleteMapping("/detele-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long universiteId){
        universiteService.removeUniversites(universiteId);
    }
    @PutMapping("/update-universite")
    public Universite updateUniversite(@RequestBody Universite U){
        Universite universite = universiteService.updateUniversite(U);
        return universite;
    }

}
