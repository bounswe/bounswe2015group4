package socialnow.Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;
import java.util.UUID;

/**
 * Created by mertcan on 22.11.2015.
 */
public class Util {
    public static Random random = new Random();

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
        return "";
    }


    public static boolean arrayContains(String[] arr, String elem){
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i].equals(elem))
                return true;
        }

        return false;
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
