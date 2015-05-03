/**
 * Created by mertcan on 3.5.2015.
 */
public class MathematicalOperations {
    public int binaryPlus(int x, int y){
        return x + y;
    }
    
    public int binaryMinus(int x, int y) {
        return x - y;
    }
    
    public int binaryMultiplication(int x, int y){
        return x*y;
    }
    
    public boolean isGreater(int x, int y) {
        if(x>y) return true;
        else return false;
    }
    
    public boolean isSmaller(int x, int y){
        if(x<y) return true;
        else return false;
    }
    
    public boolean isEqual(int x, int y) {
        if(x==y) return true;
        else return false;
    }

    public int remainder(int x, int y) {
        return x % y;
    }
}
