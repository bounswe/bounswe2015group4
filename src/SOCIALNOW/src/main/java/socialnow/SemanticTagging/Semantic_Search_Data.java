package socialnow.SemanticTagging;

import java.util.Arrays;

/**
 * Created by Erdem on 11/26/2015.
 */
public class Semantic_Search_Data {
    String id,concepturi,url,title,label,description,pageid;

    Match match;

    @Override
    public String toString() {
        return "Semantic_Search_Data{" +
                "id='" + id + '\'' +
                ", concepturi='" + concepturi + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", pageid='" + pageid + '\'' +
                ", match=" + match.toString() +
                ", aliasas=" + Arrays.toString(aliasas) +
                '}';
    }

    public Semantic_Search_Data(String id, String concepturi, String url, String title, String label, String description, String pageid, Match match, String[] aliasas) {
        this.id = id;
        this.concepturi = concepturi;
        this.url = url;
        this.title = title;
        this.label = label;
        this.description = description;
        this.pageid = pageid;
        this.match = match;
        this.aliasas = aliasas;
    }

    public Semantic_Search_Data() {

    }

    public String[] getAliasas() {

        return aliasas;
    }

    public void setAliasas(String[] aliasas) {
        this.aliasas = aliasas;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConcepturi() {
        return concepturi;
    }

    public void setConcepturi(String concepturi) {
        this.concepturi = concepturi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String[] aliasas;


    }

