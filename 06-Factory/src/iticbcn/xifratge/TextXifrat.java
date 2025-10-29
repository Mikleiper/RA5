package iticbcn.xifratge;

/*
* Classe per guardar el text xifrat.
* Internament treballa amb un array de bytes.
*/

public class TextXifrat {
    private final byte[] dades; // Array de bytes que conté el text xifrat

    public TextXifrat(byte[] dades) {  //Constructor
        this.dades = new byte[dades.length];
        for (int i = 0; i < dades.length; i++) {
            this.dades[i] = dades[i];// Guardem una còpia per seguretat (per no modificar l’original)
        }
    }

    public byte[] getBytes() {  // Retorna una còpia de les dades en bytes.
        byte[] copia = new byte[dades.length];
        for (int i = 0; i < dades.length; i++) {
            copia[i] = dades[i];
        }
        return copia;
    }

    @Override
    public String toString() { 
        return new String(dades); // Mostra el contingut del text xifrat com Sting
    }
}
