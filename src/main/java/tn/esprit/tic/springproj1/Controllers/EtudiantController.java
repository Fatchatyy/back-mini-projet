package tn.esprit.tic.springproj1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.springproj1.Entities.Etudiant;
import tn.esprit.tic.springproj1.Service.IEtudiantService;

import java.util.List;

@RestController
public class EtudiantController {
    @Autowired
    private IEtudiantService  etudiantservice;

    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants(){
        List<Etudiant> listEtudiants =etudiantservice.retrieveAllEtudiants();
        return listEtudiants;
    }
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    @Operation(description = "récupérer un étudiant par son id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found Etudiant",
                content = { @Content (mediaType = "application/json",
                    schema = @Schema(implementation = Etudiant.class))}),
        @ApiResponse(responseCode = "400" , description = "Invalid id supplied",
                content = @Content),
        @ApiResponse(responseCode = "400" , description = "Etudiant not found",
                content = @Content) })
    @ResponseBody
    public Etudiant retrieveEtudiant (@Parameter(description = "id of student to be searched")
                                        @PathVariable("etudiant-id") Long etudiantId){
        return etudiantservice.retrieveEtudiant(etudiantId);
    }
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e){
        Etudiant etudiant = etudiantservice.addEtudiant(e);
        return etudiant;
    }
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) {etudiantservice.removeEtudiant(etudiantId);
    }
    @PutMapping("/update-etudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e){
        Etudiant etudiant = etudiantservice.updateEtudiant(e);
        return etudiant;
    }
}
