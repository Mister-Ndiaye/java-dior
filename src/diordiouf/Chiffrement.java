/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diordiouf;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Dior
 */
public class Chiffrement {
    public static SecretKey genKey(String algo, int taille)throws Exception{
        KeyGenerator kg = KeyGenerator.getInstance(algo);
        kg.init(taille);
        SecretKey sk = kg.generateKey();
        return sk;
    }
    
    public static String encryptAlgo(String text, SecretKey sk) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sk);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public static String decryptAlgo(String text, SecretKey sk) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sk);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(text));
        
        return new String(decryptedBytes,"UTF-8");
    }
    
    public static SecretKey genKeyDES(int sizeKey) throws Exception{
        if (sizeKey!=56) {
           throw new IllegalArgumentException("la taille de la clé doit être de 56bits");
           
            
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(sizeKey);
        SecretKey secretKey =keyGenerator.generateKey();
        return secretKey;
        /*String cle = "Tdsi2023";
        byte[] keyBytes = cle.getBytes();
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        return secretKey;*/
    }
    
    public static String cipherDES(String message,SecretKey secretKey) throws Exception{
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrytedMessageBytes = cipher.doFinal(message.getBytes());
        String encrytedMessage = Base64.getEncoder().encodeToString(encrytedMessageBytes);
        return encrytedMessage;
    }
    
    public static String decipherDES(String chiffre,SecretKey secretKey) throws Exception{
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessageBytes = cipher.doFinal(Base64.getDecoder().decode(chiffre));
        return new String(decryptedMessageBytes);
    }
    public static void main(String[] args) throws Exception {
        String texte = "alassane amour";
        //SecretKey sk = genKey("AES", 128);
        SecretKey ds = genKeyDES(56);
        String ciphe = cipherDES(texte, ds);
        String decip = decipherDES(ciphe, ds);
        
        System.out.println(ciphe);
        System.out.println(decip);
        System.out.println(Utils.toHex(ds.getEncoded()));
        //System.out.println(Utils.toHex(sk.getEncoded()));
    }
}
