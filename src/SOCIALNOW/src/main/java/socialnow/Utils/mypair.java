package socialnow.Utils;

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.lang.Math;

class mypair implements Comparable <mypair> {
    Double val;
    String tag;
    public mypair(double a,String b)
    {    val=a;
        tag=b;
    }
    public int compareTo(mypair a)
    {    return -this.val.compareTo(a.val);
    }
}