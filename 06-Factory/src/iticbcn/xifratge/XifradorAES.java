package iticbcn.xifratge;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    private static final String ALGORISME_XIFRAT = "AES";
    private static final String ALGORISME_HASH = "SHA-256";
    private static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;

    /*
    * Genera una clau AES de 256 bits a partir d'una contrasenya 
    */ 
    private SecretKeySpec generaHash(String clau)throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(clau.getBytes("UTF-8"));
        byte[] key = new byte[32];
        for (int i = 0; i < 32; i++) {
            key[i] = hash[i];
        }
        return new SecretKeySpec(key, ALGORISME_XIFRAT);
    }

    /*
    * Combina IV i text xifrat en un sol array
    */ 
    private byte[] combinaArrays(byte[] a, byte[] b) {
        byte[] resultat = new byte[a.length + b.length];
        for (int i = 0; i < a.length; i++){
            resultat[i] = a[i];
        }
        for (int i = 0; i < b.length; i++){
            resultat[a.length + i] = b[i];
        }
        return resultat;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] iv = new byte[MIDA_IV];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeySpec secretKey = generaHash(clau);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            byte[] xifrat = cipher.doFinal(msg.getBytes("UTF-8"));
            byte[] resultat = combinaArrays(iv, xifrat);

            return new TextXifrat(resultat);

        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1); // igual que diu l’enunciat
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] tot = xifrat.getBytes();

            // Extreu IV
            byte[] iv = new byte[MIDA_IV];
            for (int i = 0; i < MIDA_IV; i++) iv[i] = tot[i];

            // Extreu text xifrat
            byte[] textXifrat = new byte[tot.length - MIDA_IV];
            for (int i = 0; i < textXifrat.length; i++) textXifrat[i] = tot[MIDA_IV + i];

            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKey = generaHash(clau);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            byte[] desxifrat = cipher.doFinal(textXifrat);
            return new String(desxifrat, "UTF-8");

        } catch (Exception e) {
            System.err.println("Error de desxifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }
}