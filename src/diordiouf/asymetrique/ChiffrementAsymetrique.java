/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diordiouf.asymetrique;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Dior
 */
public class ChiffrementAsymetrique {
    public static KeyPair generateKeyPair(int taille) throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(taille,new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }
    
    public static String encryptedRSA(String plaintext,PublicKey plublicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, plublicKey);
        
        byte[] encrypteMessageBytes = cipher.doFinal(plaintext.getBytes());
        String encrypteMessage = Base64.getEncoder().encodeToString(encrypteMessageBytes);
        return encrypteMessage;
    }
    
    public static String decryptedRSA(String encrypted,PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypteMessageBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decrypteMessageBytes);
    }
    
    public static void encryptFile(String inputFile, String outputFile, PublicKey publicKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);

        byte[] buffer = new byte[117];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            //cipherOutputStream.write(buffer, 0, bytesRead);
            byte[] encryptedBytes = cipher.doFinal(buffer, 0, bytesRead);
            outputStream.write(encryptedBytes);
        }

        cipherOutputStream.close();
        inputStream.close();
        outputStream.close();
    }

    public static void decryptFile
        
        (String inputFile, String outputFile, PrivateKey privateKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        FileInputStream inputStream = new FileInputStream(inputFile);
      //  CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        byte[] buffer = new byte[128];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] decryptedBytes = cipher.doFinal(buffer, 0, bytesRead);
                outputStream.write(decryptedBytes);
            }

       // cipherInputStream.close();
        inputStream.close();
        outputStream.close();
    }

    
    
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair(2048);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        System.out.println(privateKey);
        String dior = "sache que je t'aime amour";
        String cipher = encryptedRSA(dior, publicKey);
        String decipher = decryptedRSA(cipher, privateKey);
        encryptFile("C:\\Users\\Dior\\Documents\\message.txt", "C:\\Users\\Dior\\Documents\\message_enc.txt", publicKey);
       
        System.out.println(cipher);
        System.out.println(decipher);
    }
    
}
