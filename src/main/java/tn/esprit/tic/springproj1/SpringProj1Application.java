package tn.esprit.tic.springproj1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.esprit.tic.springproj1.Controllers.FoyerController;
import tn.esprit.tic.springproj1.Entities.Foyer;
import tn.esprit.tic.springproj1.Service.FoyerService;

@SpringBootApplication
public class SpringProj1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringProj1Application.class, args);


    }
}
