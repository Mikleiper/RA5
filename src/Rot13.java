public class Rot13 {
    
    String  abcMinus = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz";
    String abcMajus = abcMinus.toUpperCase();

    char [] minus = abcMinus.toCharArray();
    char [] majus = abcMajus.toCharArray();
    
    xifraRot13(String cadena){
        for (char l : cadena.toCharArray()) {
            if (l >= 'a' && l <= 'z'){
                ((char) ((l - 'a' + 13) % 40 + 'a'));
            }
        }
        return 
    }


    desxifraRot13(cadena){

    } 
}














