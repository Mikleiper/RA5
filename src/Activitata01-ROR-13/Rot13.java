public class Rot13 {
    
    String  abcMinus = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz";
    String abcMajus = abcMinus.toUpperCase();

    char [] minus = abcMinus.toCharArray();
    char [] majus = abcMajus.toCharArray();

    String cadena = "Holz";
    
    public static String xifraRot13(String cadena){
        char[] lletres = cadena.toCharArray();
        for (int i = 0; i < lletres.length; i++) {
            char c = lletres[i];
            if (c >= 'a' && c <= 'z') {
                lletres[i] = (char) ((c - 'a' + 13) % 40 + 'a'); //char - 'a' converteix el char en número x Unicode
            } else if (c >= 'A' && c <= 'Z') {
                lletres[i] = (char) ((c - 'A' + 13) % 40 + 'A');
            }
        }
        return new String(lletres);
    }


    /*desxifraRot13(cadena){

    }*/
    
    public static void main(String[] args) {
        String cadena = "HÓlh";
        String xifrada = xifraRot13(cadena);

        System.out.println("Original: " + cadena);
        System.out.println("Xifrada : " + xifrada);
    }

    
}
