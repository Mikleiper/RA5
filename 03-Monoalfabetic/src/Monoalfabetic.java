import java.util.*;


public class Monoalfabetic {
    final static char[] MAJUS = {'A','À','Á','B','C','Ç','D','E','È','É','F','G','H','I','Ì','Í','Ï','J','K','L','M','N','Ñ','O','Ò','Ó','P','Q','R','S','T','U','Ù','Ú','Ü','V','W','X','Y','Z'};
    private final static char[] PERMUMAJUS = permutaAlfabet(MAJUS);

    private static char [] permutaAlfabet(char[] array){
        // Convertir char[] a List per poder usar Collections.shuffle
        List<Character> llistaChar = new ArrayList<>();
        for(char c : array){
            llistaChar.add(c);
        }
        //Permutem
        Collections.shuffle(llistaChar);

        //convertim list a array
        char [] permutat = new char[llistaChar.size()];
        for (int i = 0; i < llistaChar.size(); i ++){
            permutat[i] = llistaChar.get(i);
        }

        return permutat;
    }

    private static int indexXifrat(char c, boolean majuscula) {
        if (majuscula) {
            return new String(MAJUS).indexOf(c);
        } else {
            return new String(MAJUS).toLowerCase().indexOf(c);
        }
    }

    private static int indexDesxifrat(char c, boolean majuscula) {
        if (majuscula) {
            return new String(PERMUMAJUS).indexOf(c);
        } else {
            return new String(PERMUMAJUS).toLowerCase().indexOf(c);
        }
    }

    private static String xifraMonoAlfa(String cadena){
        StringBuilder solucio = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            int idx = indexXifrat(c, false); // mirar si és minúscula
            if (idx != -1) {
                solucio.append(Character.toLowerCase(PERMUMAJUS[idx]));
                continue;
            }

            idx = indexXifrat(c, true); // mirar si és majúscula
            if (idx != -1) {
                solucio.append(PERMUMAJUS[idx]);
                continue;
            }

            solucio.append(c); // no és ni majúscula ni minúscula
        }
        return solucio.toString();
    }

    private static String desxifraMonoAlfa(String cadena){
        StringBuilder solucio = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            int idx = indexDesxifrat(c, false); // mirar si és minúscula
            if (idx != -1) {
                solucio.append(Character.toLowerCase(PERMUMAJUS[idx]));
                continue;
            }

            idx = indexDesxifrat(c, true); // mirar si és majúscula
            if (idx != -1) {
                solucio.append(PERMUMAJUS[idx]);
                continue;
            }

            solucio.append(c); // no és ni majúscula ni minúscula
        }
        return solucio.toString();
    }
    public static void main(String[] args) {
        System.out.println(xifraMonoAlfa("Hello"));
    }
}

