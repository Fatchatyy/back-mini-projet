package tn.esprit.tic.springproj1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"universite", "Blocs"})
@Entity
@Table(name="Foyer")
public class Foyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;

    private String nomFoyer;
    private Long capaciteFoyer;
    private boolean archived = false;

    @JsonIgnore
    @OneToOne(mappedBy = "foyer")
    private Universite universite;

    @JsonIgnore
    @OneToMany(mappedBy = "foyer")
    private Set<Bloc> Blocs;

}
