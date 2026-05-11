public class FinallyDemo {
    public static void main(String args[]){
        int a[] = {1,2,3,4,5};
        try {
            for (int i = 0; i < 9; i++) {
                System.out.println(a[i]);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println("Program Concluded");
        }
    }
}
