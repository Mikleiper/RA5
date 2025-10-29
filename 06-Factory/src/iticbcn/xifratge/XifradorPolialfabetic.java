package iticbcn.xifratge;

import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    
    private final char[] ALFABETMAJUS = {'A','À','Á','B','C','Ç','D','E','È','É','F','G','H','I','Ì','Í','Ï','J','K','L','M','N','Ñ','O','Ò','Ó','P','Q','R','S','T','U','Ù','Ú','Ü','V','W','X','Y','Z'};
    private char[] permutatMajus;
    private Random random;

    private void initRandom(long clau){// Crea el generador aleatori amb la llavor donada
        random = new Random(clau);
    }

    /*
     * permutem l'alfabet
     */
    private void permutaAlfabet(){
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

    private int index(char c, char[] abc) {
        for (int i = 0; i < abc.length; i++) {
            if (abc[i] == Character.toUpperCase(c)) {
                return i;
            }
        }
        return -1;
    }

    /*
    * Converteix la clau (String) a un número long, o llença excepció si no pot
    */
    private long convertirClau(String clau) throws ClauNoSuportada {
        try {
            return Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    // -------------------------------
    // Mètodes de la interfície Xifrador
    // -------------------------------

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long seed = convertirClau(clau);
        initRandom(seed); // Inicialitza el Random abans del xifrat

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
        return new TextXifrat(solucio.toString().getBytes());
    }
    
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long seed = convertirClau(clau);
        initRandom(seed); // Inicialitza el Random abans del desxifrat

        String text = xifrat.toString();
        StringBuilder solucio = new StringBuilder();

        for (char c : text.toCharArray()) {
            permutaAlfabet(); //permutem cada caracter   
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
}