package socialnow.Utils;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import socialnow.SemanticTagging.SemanticResponse;
import socialnow.SemanticTagging.Semantic_Search_Data;

/**
 * Created by Erdem on 11/26/2015.
 */
public class RequestSender {
   public static String base= "https://www.wikidata.org/w/api.php";
    final static Gson gson = new Gson();
//    ?action=wbsearchentities&language=en&format=json

    public static SemanticResponse searchSemantics(String search) throws UnirestException {

        HttpResponse<String> response =  Unirest.get(base).queryString("action","wbsearchentities").
                queryString("language","en").
                queryString("format","json").
                queryString("search",search).
                asString();

    return  gson.fromJson(response.getBody(), SemanticResponse.class) ;
    }


}
