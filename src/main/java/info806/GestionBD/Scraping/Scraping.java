package info806.GestionBD.Scraping;

import com.fasterxml.jackson.databind.SerializationFeature;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) throws JSONException, IOException {

        /*
        String bodytext = "{\"queryid\":\"fb33204c-acff-4451-98c0-2b5847179e5d\",\"advancedQuery\":{\"limitClause\":\"ZMAT:\\\"bande_dessinees\\\" languageLimit_s:\\\"fre\\\"\",\"pageSize\":10,\"searchContext\":\"advancedsearch\",\"searchType\":\"all\",\"section\":\"*\",\"sort\":\"score\",\"terms\":[]},\"mappedFQ\":{},\"includeFacets\":true,\"sf\":\"*\",\"order\":\"score\",\"pageSize\":8,\"locale\":\"fr\"}";
        JSONObject json = new JSONObject(bodytext);
        System.out.println(json);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        // Data attached to the request.
        HttpEntity<JSONObject> requestBody = new HttpEntity<>(json, headers);

        // Send request with POST method.
        JSONObject e = restTemplate.postForObject(URL_SEARCH, requestBody, JSONObject.class);
        */





        //SEARCH
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"includeFacets\":false,\"advancedQuery\":{\"limitClause\":\"ZMAT:\\\"bande_dessinees\\\" languageLimit_s:\\\"fre\\\"\",\"pageSize\":10,\"searchContext\":\"advancedsearch\",\"searchType\":\"all\",\"section\":\"*\",\"sort\":\"score\",\"terms\":[]},\"order\":\"score\",\"queryid\":\"fb33204c-acff-4451-98c0-2b5847179e5d\",\"sf\":\"*\",\"mappedFQ\":{},\"pageNo\":1\r\n,\"pageSize\":8,\"locale\":\"fr\"}");
        Request request = new Request.Builder()
                .url("https://catalogue.bm-lyon.fr/in/rest/api/search")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "JSESSIONID=EE1AAD31F97DB757C9709E1E30B2D2CC")
                .build();
        Response response = client.newCall(request).execute();
        // RSP
        String txt = response.body().string();
        response.close();
        JSONObject json = new JSONObject(txt);
        int maxPage = json.getInt("maxPageNo");
        System.out.println(json.getInt("maxPageNo"));

        //NOTICE
        OkHttpClient noticeClient = new OkHttpClient().newBuilder()
                .build();
        Request noticeRequest = new Request.Builder()
                .url("https://catalogue.bm-lyon.fr/in/rest/api/notice?id=p::usmarcdef_0000389350")
                .method("GET", null)
                .addHeader("Cookie", "JSESSIONID=EE1AAD31F97DB757C9709E1E30B2D2CC")
                .build();
        Response noticeResponse = client.newCall(request).execute();



        JSONArray jarr = json.getJSONArray("resultSet");

        String id;

        String isbn = "";
        String titre = "";
        String serie = "";
        String numero = "";
        String image = "";
        String creator = "";
        String creatorbis = "";


        for (int i = 1 ; i <= /*maxPage*/8 ; i++){
            body = RequestBody.create(mediaType, "{\"includeFacets\":false,\"advancedQuery\":{\"limitClause\":\"ZMAT:\\\"bande_dessinees\\\" languageLimit_s:\\\"fre\\\"\",\"pageSize\":10,\"searchContext\":\"advancedsearch\",\"searchType\":\"all\",\"section\":\"*\",\"sort\":\"score\",\"terms\":[]},\"order\":\"score\",\"queryid\":\"fb33204c-acff-4451-98c0-2b5847179e5d\",\"sf\":\"*\",\"mappedFQ\":{},\"pageNo\":"+i+"\r\n,\"pageSize\":8,\"locale\":\"fr\"}");
            request = new Request.Builder()
                    .url("https://catalogue.bm-lyon.fr/in/rest/api/search")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "JSESSIONID=EE1AAD31F97DB757C9709E1E30B2D2CC")
                    .build();
            response = client.newCall(request).execute();
            txt = response.body().string();
            json = new JSONObject(txt);
            //System.out.println(json.getJSONObject("resultSet"));
            jarr = json.getJSONArray("resultSet");

            for(int j = 0; j < jarr.length(); j++){

                JSONObject p = (JSONObject)jarr.get(j);

                p = (JSONObject)p.getJSONArray("id").get(0);

                id = p.getString("value");

                System.out.println("https://catalogue.bm-lyon.fr/in/rest/api/notice?id="+id);

                noticeRequest = new Request.Builder()
                        .url("https://catalogue.bm-lyon.fr/in/rest/api/notice?id="+id)
                        .method("GET", null)
                        .addHeader("Cookie", "JSESSIONID=EE1AAD31F97DB757C9709E1E30B2D2CC")
                        .build();

                noticeResponse = client.newCall(noticeRequest).execute();

                txt = noticeResponse.body().string();
                json = new JSONObject(txt);
                System.out.println(json);

                JSONArray fields = json.getJSONArray("fields");
                for(int k = 0; k < fields.length(); k++){
                    JSONObject o = (JSONObject)fields.get(k);
                    String name = o.getString("name");
                    //System.out.println(name);
                    switch (name){
                        default:
                            break;
                        case "creator":
                            creator = (((JSONObject)(o.getJSONArray("values")).get(0)).getJSONObject("qa")).getString("Answer");
                            break;
                        case "title":
                            titre = ((((JSONObject)(o.getJSONArray("values")).get(0)).getJSONObject("qa")).getString("Answer").split("\\[|\\/"))[0];
                            break;
                        case "creatorOther":
                            creatorbis = (((JSONObject)(o.getJSONArray("values")).get(0)).getJSONObject("qa")).getString("Answer");
                            break;
                        case "relationSet":
                            String[]foo = (((JSONObject)(o.getJSONArray("values")).get(0)).getJSONObject("qa")).getString("Answer").split(";");
                            if(foo.length>=2){
                                serie = foo[0];
                                numero = foo[1];
                            }else{
                                serie = foo[0];
                            }
                            break;
                        case "isbn":
                            isbn = (((((JSONObject)(o.getJSONArray("values")).get(0)).getJSONObject("qa")).getString("Answer")).split(" "))[0];
                            break;
                    }
                }

                System.out.println("Titre : "+titre+"\nISBN : "+isbn+"\nCreator : "+creator+"\nCreatorBis : "+creatorbis+"\nSerie : "+serie+"\nNuméro : "+numero);

                isbn = "";
                titre = "";
                serie = "";
                numero = "";
                image = "";
                creator = "";
                creatorbis = "";

            }
        }

    }

}
