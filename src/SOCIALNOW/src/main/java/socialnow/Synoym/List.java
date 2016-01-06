package socialnow.Synoym;

import java.util.ArrayList;

/**
 * Created by Erdem on 1/6/2016.
 */
public class List {
    @Override
    public String toString() {
        return "List{" +
                "category='" + category + '\'' +
                ", synonyms='" + synonyms + '\'' +
                ", synonymList=" + synonymList +
                '}';
    }

    String category;
    String synonyms;

    public ArrayList<String> getSynonymList() {
        return synonymList;
    }

    public void setSynonymList(ArrayList<String> synonymList) {
        this.synonymList = synonymList;
    }

    ArrayList<String> synonymList = new ArrayList<>();

    public void fillSynoymList(){
        String[] arr  = synonyms.split("\\|");


        for (int i = 0; i <arr.length ; i++) {
            synonymList.add(arr[i]);
        }
    }

    public List() {
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }
}
