public class RotX {
    final static char [] MINUS = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
    final static char [] MAJUS = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();

    private static int index(char c, boolean majuscula) {
        if (majuscula) {
            return new String(MAJUS).indexOf(c);
        } else {
            return new String(MINUS).indexOf(c);
        }
    }

    private static String rotX(String cadena, int desplaçament) {
        StringBuilder solucio = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            int idx = index(c, false); // mirar si és minúscula
            if (idx != -1) {
                int novaPos = (idx + desplaçament) % MINUS.length;
                if (novaPos < 0) novaPos += MINUS.length;
                solucio.append(MINUS[novaPos]);
                continue;
            }

            idx = index(c, true); // mirar si és majúscula
            if (idx != -1) {
                int novaPos = (idx + desplaçament) % MAJUS.length;
                if (novaPos < 0) novaPos += MAJUS.length;
                solucio.append(MAJUS[novaPos]);
                continue;
            }

            solucio.append(c); // no és ni majúscula ni minúscula
        }

        return solucio.toString();
    }

    public static String xifraRotX(String cadena, int desplaçament) {
        return rotX(cadena, desplaçament);
    }

    public static String desxifraRotX(String cadena, int desplaçament) {
        return rotX(cadena, -desplaçament);
    }
    
    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int desplaçament = 1; desplaçament < MINUS.length; desplaçament++) {
            System.out.println("(" + desplaçament + ") -> " + desxifraRotX(cadenaXifrada, desplaçament));
        }
    }

    public static void main(String[] args){
        System.out.println("Xifrat:");
        System.out.println("(4) Hola, calçot -> " + xifraRotX("Hola, calçot", 4));

        System.out.println("\nDesxifrat:");
        System.out.println("(4) Ïqoc, écoèqü -> " + desxifraRotX("Ïqoc, écoèqü", 4));

        System.out.println("\nForça bruta:");
        forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }

}

