package socialnow.Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by mertcan on 22.11.2015.
 */
public class Util {
    public static Random random = new Random();

    static Logger log = Logger.getLogger("Recommendation Controller");
    public static String hash(String password){
        String salt_ = "AGnkbkhBHgvspuh";
        byte[] salt = salt_.getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = null;
        byte[] hash = new byte[0];
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(hash);
    }


    public static boolean arrayContains(String[] arr, String elem){
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i].equals(elem))
                return true;
        }

        return false;
    }
    public static String[] find_common(String[][] tags,int K) {
        // TODO Auto-generated method stub
        Set<String> allTags = new TreeSet<String>();
        int N=0;
        for(int i=0;i>=0;i++)
        {    if(tags[i][0]==null) break;
            N++;
            for(int j=0;j>=0;j++)
            {    if(tags[i][j]==null) break;
                allTags.add(tags[i][j]);
            }
        }
        String[] tagsAr=new String[allTags.size()];
        tagsAr=allTags.toArray(tagsAr);
        int[][] cnt=new int[tagsAr.length][N];
        int pst;
        for(int i=0;i<N;i++)
            for( int j=0;j>=0;j++)
            {    if(tags[i][j]==null) break;
                pst=Arrays.binarySearch(tagsAr,tags[i][j]);
                if(pst>=0&&pst<tagsAr.length)
                    cnt[pst][i]++;
            }
        double tf[] = new double[tagsAr.length];
        for(int i=0;i<tagsAr.length;i++)
            for(int j=0;j<N;j++)
            {    if(cnt[i][j]>0)
            {    tf[i]+=1.000000000;
                tf[i]+=Math.log(cnt[i][j]);
            }
            }
        mypair[] tagtf = new mypair[tagsAr.length];
        for(int i=0;i<tagsAr.length;i++)
            tagtf[i] = new mypair(tf[i],tagsAr[i]);
        Arrays.sort(tagtf);
        String[] res=new String[K];
        for(int i=0;i<K;i++)
            res[i]=tagtf[i].tag;
        return res;
    }



    public static String deleteFromArray(String[] arr, String[] elems){
        String result = "";
        for (int i = 0; i <elems.length; i++) {
            int count=0;
            for (int j = 0; j <arr.length ; j++) {
                if(!arr[j].equals(elems[i]) || count > 0 ){
                    result = result + "," + arr[i];
                }
                else{
                    count++;
                }
            }
        }
        return result;
    }




  public static String[] findMostOccurence(String[] tags){
       String[] result = new String[3];
      result[0]="";
      result[1]="";
      result[2]="";

      HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i <tags.length ; i++) {
          String  tag = tags[i];
            if(map.containsKey(tag)){
                map.put(tag,map.get(tag)+1);
            }
            else {
                map.put(tag,1);
            }

        }
        int max=0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if(value >= max){
                max = value;
                result[0]=key;
            }
        }
      map.remove(result[0]);
       max=0;
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
          String key = entry.getKey();
          int value = entry.getValue();
          if(value >= max){
              max = value;
              result[1]=key;
          }
      }
      max=0;
      map.remove(result[1]);
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
          String key = entry.getKey();
          int value = entry.getValue();
          if(value >= max){
              max = value;
              result[2]=key;
          }
      }



        return result;
    }





    public static String deleteFromArray(String[] arr, String elem){
        String result = "";

        for (int i = 0; i <arr.length ; i++) {
            if(!arr[i].equals(elem) && !arr[i].equals("")){
                result = result + "," + arr[i];
            }

        }

    return result;
    }

    public static String generate_token(){
        return UUID.randomUUID().toString();
    }
}
