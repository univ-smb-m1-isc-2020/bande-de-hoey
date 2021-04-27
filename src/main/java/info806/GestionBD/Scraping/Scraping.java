package info806.GestionBD.Scraping;

import com.fasterxml.jackson.databind.SerializationFeature;
import info806.GestionBD.model.Album;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class Scraping {

    static final String URL_BASE = "https://catalogue.bm-lyon.fr/in/rest/api/";
    static final String URL_SEARCH = "https://catalogue.bm-lyon.fr/in/rest/api/search";
    static final String URL_IMAGE = "https://catalogue.bm-lyon.fr/";
    static final String URL_NOTICE = "https://catalogue.bm-lyon.fr/in/rest/api/notice?";


    public Scraping(){}

    public  ArrayList<ArrayList<String>> scraping() throws JSONException, IOException{
        ArrayList<String> tempList = new ArrayList<String>();
        ArrayList<ArrayList<String>> listData = new ArrayList<ArrayList<String>>();
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
        Response noticeResponse;

        // Image
        OkHttpClient imageClient = new OkHttpClient().newBuilder()
                .build();
        Request imageRequest = new Request.Builder()
                .url("https://catalogue.bm-lyon.fr/in/rest/Thumb/image?id=p%3A%3Ausmarcdef_0000389350&isbn=9782205006933&author=Morris%2C+%281923-2001%29&title=Le+grand+duc+%2F+%5BLivre%5D+%2F+dessins+de+Morris+%3B+sc%C3%A9nario+de+Goscinny&year=1999&publisher=Dargaud&TypeOfDocument=LyonPhysicalDocument&mat=bande_dessinees&ct=true&size=512&isPhysical=1")
                .method("GET", null)
                .build();
        Response imageResponse;



        JSONArray jarr = json.getJSONArray("resultSet");

        String id;

        String isbn = "";
        String titre = "";
        String serie = "";
        String numero = "";
        String creator = "";
        String creatorbis = "";

        String image = "";


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
            jarr = json.getJSONArray("resultSet");

            for(int j = 0; j < jarr.length(); j++){

                JSONObject p = (JSONObject)jarr.get(j);

                p = (JSONObject)p.getJSONArray("id").get(0);

                id = p.getString("value");

                //System.out.println("https://catalogue.bm-lyon.fr/in/rest/api/notice?id="+id);

                noticeRequest = new Request.Builder()
                        .url("https://catalogue.bm-lyon.fr/in/rest/api/notice?id="+id)
                        .method("GET", null)
                        .addHeader("Cookie", "JSESSIONID=EE1AAD31F97DB757C9709E1E30B2D2CC")
                        .build();
                noticeResponse = noticeClient.newCall(noticeRequest).execute();

                txt = noticeResponse.body().string();
                json = new JSONObject(txt);
                System.out.println(json);

                JSONArray fields = json.getJSONArray("fields");
                for(int k = 0; k < fields.length(); k++){
                    JSONObject o = (JSONObject)fields.get(k);
                    String name = o.getString("name");
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
                JSONArray summary = json.getJSONArray("summary");
                for(int l = 0; l < summary.length(); l++){
                    JSONObject o = (JSONObject)summary.get(l);
                    String name = o.getString("name");
                    switch (name){
                        default:
                            break;
                        case "imageSource_512":
                            imageRequest = new Request.Builder()
                                    .url("https://catalogue.bm-lyon.fr"+o.getString("value"))
                                    .method("GET", null)
                                    .build();
                            imageResponse = imageClient.newCall(imageRequest).execute();

                            //
                            // IMAGE
                            /*
                            Image imagetest = null;
                            URL url = new URL("https://catalogue.bm-lyon.fr"+o.getString("value"));
                            imagetest = ImageIO.read(url);
                            ImageIO.write((RenderedImage) imagetest, "jpg", new File("C:\\Users\\Admin\\Pictures\\image.jpg"));
                            System.out.println(imageResponse.body().string());*/

                            break;
                    }
                }


                System.out.println("Titre : "+titre+"\nISBN : "+isbn+"\nCreator : "+creator+"\nCreatorBis : "+creatorbis+"\nSerie : "+serie+"\nNuméro : "+numero);

                //tempList Add Album
                tempList.add("");
                tempList.add(isbn);
                tempList.add(titre);
                tempList.add(image);
                tempList.add("");
                tempList.add(numero);

                //tempList Add other
                tempList.add(creator);
                tempList.add(creatorbis);
                tempList.add(serie);

                //Add tempList to listAlbum
                listData.add(tempList);

                //Clear tempList
                tempList.clear();

                isbn = "";
                titre = "";
                serie = "";
                numero = "";
                image = "";
                creator = "";
                creatorbis = "";
            }
        }

        return listData;
    }

}
