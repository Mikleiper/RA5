import java.util.*;

public class Polialfabetic {
    
    private static final char[] ALFABETMAJUS = {'A','À','Á','B','C','Ç','D','E','È','É','F','G','H','I','Ì','Í','Ï','J','K','L','M','N','Ñ','O','Ò','Ó','P','Q','R','S','T','U','Ù','Ú','Ü','V','W','X','Y','Z'};
    private static char[] permutatMajus;
    private static Random random;
    private static final long CLAU = 1234;

    //funció index més neta, nomes comprova int index, no majuscules/minuscules
    private static int index(char c, char [] abc) {
        for (int i = 0; i < abc.length; i++){
            if (abc[i] == Character.toUpperCase(c)){
                return i;
            }
        }
        return -1;
    }

    public static void initRandom(long clau){
        random = new Random(clau);
    }

    public static void permutaAlfabet(){
        //creem un arraylist per poder utilitzar després el metodes shuffle
        List<Character> llistaChar = new ArrayList<>();
        for(char c : ALFABETMAJUS){
            llistaChar.add(c);
        }        
        //permutem amb llavor random executada abans per void initRandom
        Collections.shuffle(llistaChar, random);
        //tornem a convertir a char array
        permutatMajus = new char [llistaChar.size()];
        for (int i = 0; i < permutatMajus.length; i ++){
            permutatMajus[i] = llistaChar.get(i);
        }
    }

    private static String xifraPoliAlfa(String msg){
        
        StringBuilder solucio = new StringBuilder();
        for (char c : msg.toCharArray()) {
            permutaAlfabet(); //permutem cada caracter
            int idx = index(c, ALFABETMAJUS); 
            if (idx != -1) {
                char trobat = permutatMajus[idx]; 
                solucio.append(Character.isUpperCase(c) ? trobat : Character.toLowerCase(trobat)); // si és majuscula True append directament, si és minuscula False append tolowercase
            } else {
                solucio.append(c); // no és un char de l'alfabet
            }
        }
        return solucio.toString();
    }
    
    public static String desxifraPoliAlfa(String msgXifrat){
        StringBuilder solucio = new StringBuilder();
        for (char c : msgXifrat.toCharArray()) {
            permutaAlfabet(); //permutem cada caracter   EL PROBLEMA ES Q PERMUTA TB LOS ESPACIOS!!! HAY Q PONER UN IF Q FILTRE PRIMERO LOS ESPACIOS!!!!!!
            int idx = index(c, permutatMajus);
            if (idx != -1) {
                char trobat = ALFABETMAJUS[idx]; 
                solucio.append(Character.isUpperCase(c) ? trobat : Character.toLowerCase(trobat)); // si és majuscula True append directament, si és minuscula False append tolowercase
            } else {
                solucio.append(c); // no és un char de l'alfabet
            }
        }
        return solucio.toString();
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 Àrbitre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bóvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(CLAU);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(CLAU);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}