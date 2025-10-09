import java.util.*;

public class Polialfabetic {
    
    private static final char[] ALFABETMAJUS = {'A','À','Á','B','C','Ç','D','E','È','É','F','G','H','I','Ì','Í','Ï','J','K','L','M','N','Ñ','O','Ò','Ó','P','Q','R','S','T','U','Ù','Ú','Ü','V','W','X','Y','Z'};
    private static char[] permutat;
    private static Random random;
    private static final long CLAU = 1234;

    private static int index(char c, boolean majuscula, char [] abc) { //en comptes de convertir a String busquem directament al array
        for (int i = 0; i < abc.length; i++){
            if (majuscula){
                if(abc[i] == c){
                    return i;
                }
            } else {
                if (Character.toLowerCase(abc[i]) == c){
                    return i;
                }
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
        
        //permutem
        initRandom(CLAU);
        Collections.shuffle(llistaChar, random);

        //tornem a convertir a char array
        permutat = new char [llistaChar.size()];
        for (int i = 0; i < permutat.length; i ++){
            permutat[i] = llistaChar.get(i);
        }
    }

    public static String xifraPoliAlfa(String msg){
        StringBuilder solucio = new StringBuilder();
        permutaAlfabet();

        for (char c :msg.toCharArray()){
            boolean majuscula = Character.isUpperCase(c); // mirar si és minúscula o majuscula
            int idx = index(c,majuscula, ALFABETMAJUS); 
            if (idx != -1) { // si es compleix l'if significa que és un char en majuscula
                
                char trobat = 
            }
        }
        
        
        for (int i = 0; i < msg.length(); i ++){
            char c = msg.charAt(i);
            if c isCharacter

            permutaAlfabet();
            c = permutat[i];



        }
    }

    public static String desxifraPoliAlfa(String msgXifrat){

    }



}


// Collections.shuffle(permutat, random);