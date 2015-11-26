package socialnow.SemanticTagging;

/**
 * Created by Erdem on 11/27/2015.
 */
public class Search_Info {

    String search;

    @Override
    public String toString() {
        return "Search_Info{" +
                "search='" + search + '\'' +
                '}';
    }

    public Search_Info() {
    }

    public Search_Info(String search) {

        this.search = search;
    }



    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
