import javax.sound.midi.SysexMessage;

public class TryCatchDemo {
    public static void main(String args[]){
        try{
            double ans = 5/0;
            System.out.println(" " + ans);
        }catch(ArithmeticException e){
            System.out.println(e.getMessage());
            //System.out.println(e.getStackTrace());
            //System.out.println(e.getCause());
        }
    }
}
