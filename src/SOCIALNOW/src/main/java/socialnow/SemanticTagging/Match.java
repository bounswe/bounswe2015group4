package socialnow.SemanticTagging;

/**
 * Created by Erdem on 11/27/2015.
 */
public class Match {

String type,language,text;

    @Override
    public String toString() {
        return "Match{" +
                "type='" + type + '\'' +
                ", language='" + language + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public Match() {
    }

    public Match(String type, String language, String text) {

        this.type = type;
        this.language = language;
        this.text = text;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
