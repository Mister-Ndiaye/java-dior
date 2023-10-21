import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyGenerationUtils {
    public static SecretKey generateDESKey(int keySize) throws NoSuchAlgorithmException {
        if (keySize != 56) {
            throw new IllegalArgumentException("La taille de la clé DES doit être de 56 bits.");
        }

        // Créez un générateur de clé DES
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(keySize);

        // Générez la clé secrète
        SecretKey secretKey = keyGenerator.generateKey();

        return secretKey;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Exemple : générez une clé DES de 56 bits
        SecretKey desKey = generateDESKey(56);
        System.out.println("Clé secrète DES générée : " + bytesToHex(desKey.getEncoded()));
    }

    // Une fonction utilitaire pour afficher les bytes de la clé sous forme hexadécimale
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
