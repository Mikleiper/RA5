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

        //convertim list permutada a array
        char [] permutat = new char[llistaChar.size()];
        for (int i = 0; i < llistaChar.size(); i ++){
            permutat[i] = llistaChar.get(i);
        }

        return permutat;
    }

    /*
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
    }*/

    private static int index(char c, boolean majuscula) {
        if (majuscula) {
            return new String(MAJUS).indexOf(c);
        } else {
            return new String(MAJUS).toLowerCase().indexOf(c);
        }
    }

    private static String xifraMonoAlfa(String cadena){
        StringBuilder solucio = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            boolean majuscula = Character.isUpperCase(c); // mirar si és minúscula o majuscula
            int idx = index(c,majuscula); 
            if (idx != -1) {
                char trobat = permutaAlfabet(MAJUS)[idx]; 
                solucio.append(majuscula ? trobat : Character.toLowerCase(trobat)); // si és majuscula True append directament, si és majuscula False append tolowercase
            } else {
                solucio.append(c); // no és un char de l'alfabet
            }
        }
        return solucio.toString();
    }

    private static String desxifraMonoAlfa(String cadena){
        StringBuilder solucio = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            boolean majuscula = Character.isUpperCase(c); // mirar si és minúscula o majuscula
            int idx = indexDesxifrat(c,majuscula); 
            if (idx != -1) {
                char trobat = MAJUS[idx]; 
                solucio.append(majuscula ? trobat : Character.toLowerCase(trobat)); // si és majuscula True append directament, si és majuscula False append tolowercase
            } else {
                solucio.append(c); // no és un char de l'alfabet
            }
        }
        return solucio.toString();
    }

    public static void main(String[] args) {
        String [] proves = {"El Pingu pingunea", "Qui dia passa, any empeny!", "8 On hi ha fum, 76? hi ha foc." };
        for (String i : proves){
            System.out.printf("Original:   %s%n", i);
            System.out.printf("Xifrat:   %s%n", xifraMonoAlfa(i));
            System.out.printf("Desxifrat:   %s%n", desxifraMonoAlfa(i));
        }
    }
}
//TODO revisar desxifrat
//TODO quitar [] permutado del inicio
