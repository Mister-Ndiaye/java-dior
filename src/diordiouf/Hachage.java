package diordiouf;
import java.io.FileInputStream;
import java.security.*;
import java.util.StringJoiner;


public class Hachage{
    
    public static String hashData(String data,String algo) throws Exception{
        MessageDigest md;
        
            md = MessageDigest.getInstance(algo);
            byte[] empreinte = md.digest(data.getBytes());
            
        
        return Utils.toHex(empreinte);
    }
    
    public static String hashFile(String path,String algo) throws Exception{
        MessageDigest md = MessageDigest.getInstance(algo);
        FileInputStream fis = new FileInputStream(path);
        DigestInputStream dis = new DigestInputStream(fis, md);
        byte[] b = new byte[fis.available()];
		byte[] empreinte = null;
		while(dis.read(b)!=-1) {
			empreinte = md.digest();
		}
		fis.close();
        
        return Utils.toHex(empreinte) ;
    }

	public static void main(String[] args) throws Exception{
		String texte = "je m'appelle Dior DIOUF";
                hashData(texte,"SHA-1");
                System.out.println(hashFile("C:\\Users\\Dior\\Documents\\message.txt", "SHA-256"));
                System.out.println(chemin("C:\\Users\\Dior\\Documents\\message.txt"));
                System.out.println(hashData(texte, "SHA-1"));
		
	}
        
        public static String chemin(String path){
            var tab = path.split("\\\\");
            /*tab[tab.length-1]=tab[tab.length-1];
            StringJoiner joiner = new StringJoiner("\\\\");
            for (String part : tab) {
                joiner.add(part);
                
            }*/
            return tab[tab.length-1];
        }
        
        

}
