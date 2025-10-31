package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA()throws Exception{
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");//creem un generador de clau  amb l'algoritme RSA (basat en números prims), pot ser d'altres tipus DSA, EC, etc
        generador.initialize(2048);//Inicialitzem amb mida de 2048 bits (segura)
        KeyPair parellClaus = generador.generateKeyPair();//Generem el parell de claus
        return parellClaus; 
    }

    public byte[]xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); //Crear el motor de xifrat amb algoritme "RSA", amb mode d'operació "ECB" amb padding PKCS#1- padding=relleno fix per ajustar el tamany de les dades que es xifraràn. Cipher es la classe q utilitza java per realitzar el xifrat i desxifrat
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica); //Inicialitzar en mode ENCRYPT (xifrar) amb la clau pública
        byte[] dades = msg.getBytes("UTF-8"); //Convertir el text a xifrar a bytes
        byte[] dadesXifrades = cipher.doFinal(dades);  //Xifrar el text
        return dadesXifrades;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada); //Inicialitzar cipher en mode DECRYPT (dexifrar) amb la clau privada
        byte[] dadesDesxifrades = cipher.doFinal(msgXifrat); //desxifra [] de bytes del text xifrat
        String textDesxifrat = new String(dadesDesxifrades, "UTF-8");  // convertim els bytes de [] desxifrat a text (String)
        return textDesxifrat;
    }
}

