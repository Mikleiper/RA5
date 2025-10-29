package iticbcn.xifratge;

import java.util.*;


public class XifradorMonoalfabetic implements Xifrador {
    final char[] MAJUS = {'A','À','Á','B','C','Ç','D','E','È','É','F','G','H','I','Ì','Í','Ï','J','K','L','M','N','Ñ','O','Ò','Ó','P','Q','R','S','T','U','Ù','Ú','Ü','V','W','X','Y','Z'};
    
    private char[] permutat;
    public XifradorMonoalfabetic() {
        permutat = permutaAlfabet(MAJUS);
    }

    private static char [] permutaAlfabet(char[] array){
        // Convertir char[] a List per poder usar Collections.shuffle
        List<Character> llistaChar = new ArrayList<>();
        for(char c : array){
            llistaChar.add(c);
        }
        //Permutem
        Collections.shuffle(llistaChar);
        //convertim list permutada a array
        char [] permutat = new char[llistaChar.size()];
        for (int i = 0; i < permutat.length; i ++){
            permutat[i] = llistaChar.get(i);
        }
        return permutat;
    }

    private int index(char c, char[] abc) {
        for (int i = 0; i < abc.length; i++) {
            if (Character.toUpperCase(c) == abc[i]) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Comprova que la clau sigui null, si no llença una excepció.
     */
    private void comprovaClau(String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
    }

    // -------------------------------
    // Mètodes de la interfície Xifrador
    // -------------------------------

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        
        StringBuilder solucio = new StringBuilder();

        for (char c : msg.toCharArray()) {
            int idx = index(c, MAJUS); 
            if (idx != -1) {
                char trobat = permutat[idx]; 
                solucio.append(Character.isUpperCase(c) ? trobat : Character.toLowerCase(trobat)); // si és majuscula True append directament, si és majuscula False append tolowercase
            } else {
                solucio.append(c); // no és un char de l'alfabet
            }
        }
        return new TextXifrat(solucio.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        comprovaClau(clau);

        String text = xifrat.toString();
        StringBuilder solucio = new StringBuilder();

        for (char c : text.toCharArray()) {
            int idx = index(c, permutat); 
            if (idx != -1) {
                char trobat = MAJUS[idx]; 
                solucio.append(Character.isUpperCase(c) ? trobat : Character.toLowerCase(trobat)); // si és majuscula True append directament, si és majuscula False append tolowercase
            } else {
                solucio.append(c); // no és un char de l'alfabet
            }
        }
        return solucio.toString();
    }
}
