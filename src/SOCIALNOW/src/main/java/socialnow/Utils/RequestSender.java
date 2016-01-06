package socialnow.Utils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import socialnow.SemanticTagging.SemanticResponse;
import socialnow.SemanticTagging.Semantic_Search_Data;
import socialnow.Synoym.Synonym;

import java.util.logging.Logger;

/**
 * Created by Erdem on 11/26/2015.
 */
public class RequestSender {
    public static String base= "https://www.wikidata.org/w/api.php";
    final static Gson gson = new Gson();
//    ?action=wbsearchentities&language=en&format=json
static Logger log = Logger.getLogger("RequestSender");
    public static String searchSemantics(String search) throws UnirestException {

        HttpResponse<String> response =  Unirest.get(base).queryString("action","wbsearchentities").
                queryString("language","en").
                queryString("format","json").
                queryString("search",search).
                asString();


        return  response.getBody();
    }

    //http://thesaurus.altervista.org/thesaurus/v1?word=party&key=HmXWoNxAT2RUo0uzcCob&language=en_US&output=json

    public static Synonym findSynonyms(String word) throws UnirestException {
        String baseSynonyms = "http://thesaurus.altervista.org/thesaurus/v1";
        HttpResponse<String> response =  Unirest.get(baseSynonyms).queryString("key","HmXWoNxAT2RUo0uzcCob").
                queryString("language","en_US").
                queryString("output","json").
                queryString("word",word).
                asString();


        Synonym s =  gson.fromJson(response.getBody(), Synonym.class);
       log.info(s.toString());
        return s;
    }

}
