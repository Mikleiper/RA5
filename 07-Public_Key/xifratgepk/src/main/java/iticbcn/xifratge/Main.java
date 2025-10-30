package iticbcn.xifratge;

import java.security.KeyPair;
import javx.xml.bind.DatatypeConverter;

public class Main {
    public static void main(String[] args) {
        ClauPublica cp = new ClauPublica();
        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";
        KeyPair parellClaus = cp.generaParellClausRSA();
        byte[] msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic());
        System.out.println("=============================");
        System.out.println("Text xifrat:");
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat));

        String msgDesxifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate());
        System.out.println("=============================");
        System.out.println("Text desxifrat:"+ msgDesxifrat);

        String strClauPub = DatatypeConverter.printHexBinary(parellClaus.getPublic().getEncoded());

        String strClauPriv = DatatypeConverter.printHexBinary(parellClaus.getPrivate().getEncoded());

        System.out.println("=============================");
        System.out.println("Clau pública:"+ strClauPub);
        System.out.println("=============================");
        System.out.println("Clau privada:"+ strClauPriv);
    }
}