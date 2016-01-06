package socialnow.Synoym;


/**
 * Created by Erdem on 1/6/2016.
 */
public class ObjectJson {

   List  list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void fillList(){
       list.fillSynoymList();
    }
}
