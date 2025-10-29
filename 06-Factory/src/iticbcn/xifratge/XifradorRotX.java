package iticbcn.xifratge;



public class XifradorRotX implements Xifrador {
    // ------------------------------------------
    // CONSTANTS
    // ------------------------------------------
    private static final char [] MINUS = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
    private static final char [] MAJUS = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();

    // -------------------------------
    // FUNCIONS PRIVADES D'AJUDA
    // -------------------------------

    /*
    * Busca la posició del caràcter dins l’alfabet
    */
    private int index(char c, boolean majuscula) {
        if (majuscula) {
            return new String(MAJUS).indexOf(c);
        } else {
            return new String(MINUS).indexOf(c);
        }
    }

    /*
    * Aplica el desplaçament a tota la cadena.
    * Els espais, números, etc es mantenen iguals, continue.
    */
    private String rotX(String cadena, int desplaçament) {
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

            solucio.append(c); // no és ni majúscula ni minúscula, la deixem igual
        }

        return solucio.toString();
    }

    // -------------------------------
    // VALIDACIÓ DE CLAU->DESPLAÇAMENT DEL 0-40       
    // -------------------------------

    /*
    * Comprova que la clau sigui un enter entre 0 i 40.
    * Si no -> excepció ClauNoSuportada.
    * L’alfabet té 41 lletres (0 a 40 són valors vàlids)
    */
    private int validaClau(String clau) throws ClauNoSuportada {
        try {
            int desplaçament = Integer.parseInt(clau);
            if (desplaçament < 0 || desplaçament > 40)
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            return desplaçament;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    // -------------------------------
    // MÈTODES DE LA INTERFÍCIE XIFRADOR
    // -------------------------------

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament = validaClau(clau);  // Validem la clau abans de fer res
        String resultat = rotX(msg, desplaçament);  // Apliquem la rotació
        return new TextXifrat(resultat.getBytes()); // Guardem el resultat en bytes dins un TextXifrat
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament = validaClau(clau);     // Validem la clau
        String text = xifrat.toString();         // Convertim el text xifrat a String
        String resultat = rotX(text, -desplaçament); // Desplacem en sentit contrari
        return resultat;                          // Retornem el text original
    }
}

