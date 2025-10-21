import java.security.*;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class aes {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    //donat un string en clar el xifre amb AES-CBC-256 i desxifre els bits xifrats a un string de nou

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Stojakovic8!"; //convertir a un hash de mida fixa

    // --- Genera una clau AES de 256 bits a partir d'una contrasenya ---
    private static SecretKeySpec generaHash(String clau)throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(clau.getBytes("UTF-8"));
        byte[] key = Arrays.copyOf(hash, 32); // 256 bits
        return new SecretKeySpec(key, ALGORISME_XIFRAT);
    }

    private static byte[] combinaArrays(byte[] a, byte[] b) {
    byte[] resultat = new byte[a.length + b.length];
    for (int i = 0; i < a.length; i++){
        resultat[i] = a[i];
    }
    for (int i = 0; i < b.length; i++){
        resultat[a.length + i] = b[i];
    }

    return resultat;
}

    public static byte [] xifraAES(String msg, String clau)
    throws Exception{
        //Genera IVParameterSpec(sense iv no es pot desxifrar! cal guardar-ho)
        byte [] msgArray = msg.getBytes("UTF-8"); //obtenir bytes d l'string
        SecureRandom random = new SecureRandom(); //Crea un generador de números aleatorios segurs criptogràficament. Secure random és més lent q random, però impossible de predir. 
        random.nextBytes(iv); // omple l’array iv amb bytes aleatoris
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        //Genera hash
        SecretKeySpec secretKey = generaHash(clau);

        //Encrypt
        Cipher cipher = Cipher.getInstance(FORMAT_AES);  //Cipher és la classe que s’utilitza per xifrar, és com el “motor” que fa les operacions criptogràfiques
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec); //mode encriptar, scretkey clau AES derivada de la contrasenya,  l’IV (Initialization Vector) per evitar patrons repetitius
        byte[] xifrat = cipher.doFinal(msgArray); //aplica el xifratge i retorna el resultat en bytes.

        //Combinar IV i part xifrada
        byte[] resultat = combinaArrays(iv, xifrat);

        //return iv+msgxifrat
        return resultat;
    }

    public static String desxifraAES(byte [] bIvMsgXifrat, String clau)
    throws Exception{
         //Extreure l'IV
        byte[] iv = new byte[MIDA_IV];
        for (int i = 0; i < MIDA_IV; i++) {
            iv[i] = bIvMsgXifrat[i];
        }

         //Extreure la parta xifrada
        byte[] msgXifrat = new byte[bIvMsgXifrat.length - MIDA_IV];
        for (int i = 0; i < msgXifrat.length; i++) {
            msgXifrat[i] = bIvMsgXifrat[MIDA_IV + i];
        }

         //Crea IV i Fer hash de la clau
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKey = generaHash(clau);

         //Desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] desxifrat = cipher.doFinal(msgXifrat); 

         //return String desxifrat
        return new String(desxifrat, "UTF-8");

    }

    /*
     *  En xifrar
     *  [IV][TEXT_XIFRAT]  --> guardem tot junt
     * // En desxifrar
     * IV = primers 16 bytes
     * TEXT_XIFRAT = resta de bytes
     * 
     * IvParameterSpec ivSpec = new IvParameterSpec(IV);
     * cipher.init(DECRYPT_MODE, clau, ivSpec);
     * textEnClar = cipher.doFinal(TEXT_XIFRAT);   
     */

    public static void main(String[] args) {
        String msgs [] = {"Lorem ipsum dicet",
                    "Hola Andrés cómo es tu cuñado",
                    "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++)  {
            String msg = msgs[i];

            byte [] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);    
            } catch (Exception e) {
            System.err.println("Error de xifrat: "
                + e.getLocalizedMessage());    
            }
        System.out.println("------------------");
        System.out.println("Msg: " + msg);
        System.out.println("Msg: " + new String(bXifrats));
        System.out.println("Msg: " + desxifrat);
        }
    }
}