public class VowelsConsonants {
    public static void main(String args[]) {
        String s1 = "Samarth";
        String s2 = s1.toUpperCase();
        int c_count = 0;
        int v_count = 0;
        for (int i = 0; i <= s1.length()-1; i++) {
            if(s2.charAt(i) == 'A' || s2.charAt(i) == 'E' || s2.charAt(i) == 'I' ||s2.charAt(i) == 'O' || s2.charAt(i) == 'U' ){
                v_count++;
            }else{
                c_count++;
            }
        }
        System.out.println("Consonants - " + c_count);
        System.out.println("Vowels - " + v_count);
    }
}
