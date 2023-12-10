package tn.esprit.tic.springproj1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.tic.springproj1.Entities.*;
import tn.esprit.tic.springproj1.Repository.EtudiantRepository;
import tn.esprit.tic.springproj1.Repository.ReservationRepository;
import org.springframework.web.client.HttpClientErrorException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EtudiantRepository etudiantRepository;

    @Override
    public List<Reservation> retrieveAllReservations(){return reservationRepository.findAll();}
    @Override
    public Reservation addReservation(Reservation e){return reservationRepository.save(e);}
    @Override
    public Reservation updateReservation(Reservation e){return reservationRepository.save(e);}
    @Override
    public Reservation retrieveReservation(Long idReservation){
        return reservationRepository.findById(idReservation).get();
    }
    @Override
    public void removeReservation(Long idReservation){
        reservationRepository.deleteById(idReservation);
    }
    @Override
    public Etudiant affecterEtudiantAReservation (String nomEt, String prenomEt, Long idReservation){
        Reservation reservation = reservationRepository.findById(idReservation).get();
        Etudiant etudiant = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);

        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
      //  reservation.setEtudiants(etudiants);


        reservationRepository.save(reservation);


        return etudiant;

    }
    public ByteArrayResource generatePDFFromReservationData(Long idReservation) {

        Reservation reservation = retrieveReservation(idReservation); // Fetching reservations data

        // Format reservations data into the required format for PDF.co
        String htmlContent = formatReservationDataForHTML(reservation);
        System.out.println("htmlContent" + htmlContent);
        // Make API call to PDF.co using RestTemplate with the formatted data
        String apiKey = "fatmamansour1106@gmail.com_g9AGw4P71N3bfwIihguAyLoxn6q96H705dn95W871YMqklckk6CS78cc8xwGP8g6Y4fWXvFfE21h4tuP2jN5LG6JpBN34QrRM398d39J7SklzYjT723H64PT14xAKwX81XgNf5Q7WvZZU3p805tRIz8VHM";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", apiKey);
        String requestBody = "{\"html\":\"" + htmlContent + "\"}";


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        System.out.println("request" + request);
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(
                    "https://api.pdf.co/v1/pdf/convert/from/html", // Example API endpoint
                    HttpMethod.POST,
                    request,
                    byte[].class
            );


            byte[] pdfBytes = response.getBody();
            if (pdfBytes != null) {
                savePDFToFile(pdfBytes, "generated.pdf");
                System.out.println("the byte array"+new ByteArrayResource(pdfBytes));
                return new ByteArrayResource(pdfBytes);
            } else {
                return null;
            }
        } catch (Exception e) {
            // Handle the exception, e.g., log the error message
            e.printStackTrace();
            return null;
    }
    }
    private void savePDFToFile(byte[] pdfBytes, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(pdfBytes);
        fos.close();
    }
    private String formatReservationDataForHTML(Reservation reservation) {
        StringBuilder htmlContentBuilder = new StringBuilder();

        // Start building the HTML content
        htmlContentBuilder.append("<html><body>");
        htmlContentBuilder.append("<h1>Reservation Details</h1>");
        // Loop through reservations and create HTML content

            htmlContentBuilder.append("<div>");
            htmlContentBuilder.append("<p>ID: ").append(reservation.getIdReservation()).append("</p>");
            htmlContentBuilder.append("<p>Year: ").append(reservation.getAnneeUniversitaire()).append("</p>");
            htmlContentBuilder.append("<p>Validity: ").append(reservation.getEstValide()).append("</p>");
            // Add more fields as needed

            htmlContentBuilder.append("</div>");


        // End HTML content
        htmlContentBuilder.append("</body></html>");

        return htmlContentBuilder.toString();
    }

}
