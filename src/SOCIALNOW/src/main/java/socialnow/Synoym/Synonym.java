package socialnow.Synoym;

import java.util.ArrayList;

/**
 * Created by Erdem on 1/6/2016.
 */
public class Synonym {
    ArrayList<ObjectJson> response = new ArrayList<>();


    public void setResponse(ArrayList<ObjectJson> response) {
        this.response = response;
    }
    public ArrayList<ObjectJson> getResponse() {

        return response;
    }

    public void fillLists(){
        response.forEach(ObjectJson::fillList);
    }




    public Synonym() {
    }




}
