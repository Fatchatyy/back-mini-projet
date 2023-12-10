package tn.esprit.tic.springproj1.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Temporal(TemporalType.DATE)
    private Date anneeUniversitaire;
    private Boolean estValide;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etudiant> Etudiants;

}
