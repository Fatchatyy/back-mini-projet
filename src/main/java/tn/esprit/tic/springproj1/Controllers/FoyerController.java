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
import tn.esprit.tic.springproj1.Entities.Foyer;
import tn.esprit.tic.springproj1.Service.IFoyerService;

import java.util.List;

@RestController
public class FoyerController {
    @Autowired
    private IFoyerService foyerService;

    @GetMapping("/retrieve-all-Foyer")
    public List<Foyer> getFoyers(){
        List<Foyer> foyers = foyerService.retrieveAllFoyer();
        return foyers;
    }
    @GetMapping("/retrieve-foyer/{foyer_id}")
    @Operation(description = "récupérer un foyer par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Foyer",
                content ={ @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Foyer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                content = @Content),
            @ApiResponse(responseCode = "400", description = "foyer not found",
                content = @Content)
    })
    @ResponseBody
    public Foyer getFoyer(@Parameter(description = "id of foyer to be searched")
            @PathVariable("foyer_id") Long foyerId){
        Foyer foyer = foyerService.retrieveFoyer(foyerId);
        return foyer;
    }
    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f){
        Foyer foyer = foyerService.addFoyer(f);
        return foyer;
    }
    @DeleteMapping("/remove-foyer/{foyer_id}")
    public void removeFoyer(@PathVariable("foyer_id") Long foyerId){
        foyerService.removeFoyer(foyerId);
    }
    @PutMapping("/update-foyer")
    public Foyer updateFoyer(@RequestBody Foyer f){
        Foyer foyer = foyerService.updateFoyer(f);
        return foyer;
    }
}
