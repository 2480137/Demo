import java.util.*;

public class FrequencyCharacters {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int count = 0;
        System.out.println("Enter any String");
        String s1 = s.nextLine();
        System.out.println("Enter the char");
        char ch = s.next().charAt(0);
        for (int i = 0; i <= s1.length()-1; i++) {
            if(s1.charAt(i) == ch){
                count++;
            }else{
                //
            }
        }
        System.out.println("Frequency of " + ch + " - " + count);

    }
}
