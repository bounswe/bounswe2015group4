package socialnow.SemanticTagging;

import java.util.List;

/**
 * Created by Erdem on 11/26/2015.
 */
public class SemanticResponse {

    Search_Info searchinfo;
    List<Semantic_Search_Data> search;

    @Override
    public String toString() {
        return "SemanticResponse{" +
                "searchinfo=" + searchinfo.toString() +
                ", search=" + search.toString() +
                '}';
    }

    public SemanticResponse(Search_Info searchinfo, List<Semantic_Search_Data> search) {
        this.searchinfo = searchinfo;
        this.search = search;
    }

    public Search_Info getSearchinfo() {

        return searchinfo;
    }

    public void setSearchinfo(Search_Info searchinfo) {
        this.searchinfo = searchinfo;
    }

    public List<Semantic_Search_Data> getSearch() {
        return search;
    }

    public void setSearch(List<Semantic_Search_Data> search) {
        this.search = search;
    }
}
