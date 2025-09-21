public class Rot13 {
    
    static String abcMinus = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz";
    static String abcMajus = abcMinus.toUpperCase();

    public static String xifraRot13(String cadena){
        char [] minus = abcMinus.toCharArray();
        char [] majus = abcMajus.toCharArray();
        String solucio = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int pos = abcMinus.indexOf(c);
            if (pos != -1) { //la lletra és minuscula
                solucio += minus[(pos + 13) % minus.length];
            } else {
                pos = abcMajus.indexOf(c);
                if (pos != -1){ //la lletra és majuscula
                    solucio += majus[(pos + 13) % majus.length];
                } else {
                    solucio += c;
                }
            }
        }
        return solucio;
    }
    
    public static String desxifraRot13(String cadena){
        char [] minus = abcMinus.toCharArray();
        char [] majus = abcMajus.toCharArray();
        String solucio = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int pos = abcMinus.indexOf(c);
            if (pos != -1) { //la lletra és minuscula
                solucio += minus[((pos - 13) % minus.length + minus.length) % minus.length];//convetim l'índex en +
            } else {
                pos = abcMajus.indexOf(c);
                if (pos != -1){ //la lletra és majuscula
                    solucio += majus[((pos - 13) % minus.length + minus.length) % minus.length];//convetim l'índex en +
                } else {
                    solucio += c;
                }
            }
        }
        return solucio;
    }
    
/*  PER FER PROVES  
    public static void main(String[] args) {
        String cadena = "Zmálx, zmá bc acñ nà?";
        String xifrada = desxifraRot13(cadena);

        System.out.println("Original: " + cadena);
        System.out.println("Xifrada : " + xifrada);
    }
}
*/
