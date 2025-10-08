import java.util.*;


public class Monoalfabetic {
    final static char[] MAJUS = {'A','À','Á','B','C','Ç','D','E','È','É','F','G','H','I','Ì','Í','Ï','J','K','L','M','N','Ñ','O','Ò','Ó','P','Q','R','S','T','U','Ù','Ú','Ü','V','W','X','Y','Z'};
    final static char[] PERMUMAJUS = permutaAlfabet(MAJUS);

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

    private static String xifraMonoAlfa(String cadena){
        StringBuilder solucio = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            boolean majuscula = Character.isUpperCase(c); // mirar si és minúscula o majuscula
            int idx = index(c,majuscula, MAJUS); 
            if (idx != -1) {
                char trobat = PERMUMAJUS[idx]; 
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
            int idx = index(c,majuscula, PERMUMAJUS); 
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
            String xifrada = xifraMonoAlfa(i);  //aconseguim permutació estable per tal de provar si realment funcionen bé els metodes de xifrat i desxifrat
            String desxifrada = desxifraMonoAlfa(xifrada);
            System.out.printf("Original:   %s%n", i);
            System.out.printf("Xifrat:   %s%n", xifrada);
            System.out.printf("Desxifrat:   %s%n", desxifrada);
        }
    }
}
