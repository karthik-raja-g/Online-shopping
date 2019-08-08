package common.utils;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;
/**
 *<p>
 * It generates a hash code for a given string using SHA-256 algorithm
 *</p>
 *
 * @author       Karthik    created on 7 August 2019
 *
 */ 
public class StringHasher {

    static final Logger logger = Logger.getLogger(StringHasher.class); 
/**
 * It generates a unique hash for given string and returns the result
 *
 * @param        text          The string to generate hash
 * 
 * @return       hashString    The generated hash for the string
 *
 */
    public static String getSHA(String text) {
        StringBuffer hashString = new StringBuffer();
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) 
                    hashString.append('0');
                hashString.append(hex);
            }

        } catch(NoSuchAlgorithmException ex){
              logger.error(ex);
        } catch(UnsupportedEncodingException ex){
              logger.error(ex);
        }
        return hashString.toString();

    }

}
