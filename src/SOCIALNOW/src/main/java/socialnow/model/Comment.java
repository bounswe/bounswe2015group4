package socialnow.model;

import socialnow.forms.Comment.Create_Comment_Form;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *This model defines comment
 *
 * @author Erdem
 *@param id it is the id of comment
 *@param owner_token shows the owner of comment
 *@param comment_text what is written in the comment
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String owner_token;

    @NotNull
    private String comment_text;

/**
*@return current id
*/
    public long getId() {
        return id;
    }
/**
 * 
 * @param id id to set
 */

    public void setId(long id) {
        this.id = id;
    }
/**
 * 
 * Empty constructor
 */
    public Comment() {
    }

/**
*@return current owner of the comment
*/

    public String getOwner_token() {
        return owner_token;
    }
/**
 * 
 * @param owner_token owner of the comment to set
 */
    public void setOwner_token(String owner_token) {
        this.owner_token = owner_token;
    }
/**
*@return current comment
*/
    public String getComment_text() {
        return comment_text;
    }

/**
 * 
 * @param comment_text defines what is written in the comment
 */

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }
/**
 * Getting the info from HTML form and creates new objects
 * @param setComment_text creates new comment
 * @param setOwner_token assigns new owner
 */

    public Comment(Create_Comment_Form form){
        this.setComment_text(form.getComment_text());
        this.setOwner_token(form.getOwner_token());
    }
}
