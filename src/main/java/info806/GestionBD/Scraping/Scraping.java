package info806.GestionBD.Scraping;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Scraping {

    static final String URL_BASE = "https://catalogue.bm-lyon.fr/in/rest/api/";
    static final String URL_SEARCH = "https://catalogue.bm-lyon.fr/in/rest/api/search";
    static final String URL_IMAGE = "https://catalogue.bm-lyon.fr/";
    static final String URL_NOTICE = "https://catalogue.bm-lyon.fr/in/rest/api/notice?";


    /*
     peux visualiser une album (ISBN, titre, image série, numéro d'ordre pour la série, genre, format), une série (liste des albums appartenant a la série par ordre dans la série)
    je peux rechercher des bandes dessinée par auteur / série / titre / isbn,
    je peux marquer un album comm faisant partie de ma collection
    je peux marquer une série, un auteur comme suivie.

    */

    public static void main(String[] args) throws JSONException {

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        Test test = new Test(1);
        HttpEntity<JSONObject> requestBody = new HttpEntity<>(test.finalbf);

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_SEARCH, HttpMethod.POST, requestBody, JSONObject.class);



        System.out.println(response);

    }

}
