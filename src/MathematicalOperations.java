/**
 * Created by mertcan on 3.5.2015.
 */
public class MathematicalOperations {

    //! Implements integer addition
    /*!
      \param x an integer argument.
      \param y an integer argument.
      \return the sum of x and y
    */
    public int binaryPlus(int x, int y){
        return x + y;
    }

    //! Implements integer substraction
    /*!
      \param x an integer argument, the minuend.
      \param y an integer argument, the subtrahend.
      \return the difference
    */
    public int binaryMinus(int x, int y) {
        return x - y;
    }

    //! Implements integer multiplication operation
    /*!
      \param x an integer argument, a multiplicand.
      \param y an integer argument, a multiplicand.
      \return the product of x and y
    */
    public int binaryMultiplication(int x, int y){
        return x*y;
    }

    //! Implements a comparison operation between two integers
    /*!
      \param x first integer argument.
      \param y second integer argument.
      \return true if the first argument is bigger than the second, false otherwise.
    */
    public boolean isGreater(int x, int y) {
        if(x>y) return true;
        else return false;
    }

    //! Implements a comparison operation between two integers
    /*!
      \param x first integer argument.
      \param y second integer argument.
      \return true if the first argument is smaller than the second, false otherwise.
    */
    public boolean isSmaller(int x, int y){
        if(x<y) return true;
        else return false;
    }

    //! Implements integer equality operation
    /*!
      \param x an integer argument.
      \param y an integer argument.
      \return true if the arguments are equal to each other, false otherwise.
    */
    public boolean isEqual(int x, int y) {
        if(x==y) return true;
        else return false;
    }

    //! Implements the remainder operation
    /*!
      \param x an integer argument, the dividend.
      \param y an integer argument, the divisor.
      \return the remainder of the division operation x/y.
    */
    public int remainder(int x, int y) {
        return x % y;
    }
}
