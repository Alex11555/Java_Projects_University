import java.util.Scanner;

public class Prim {
    static boolean VerificarePrim(int nr){
        if(nr<=1) {
            System.out.printf("Numarul %d nu este prim", nr);
            return false;
        }
        else {
            for(int i=2;i<=Math.sqrt(nr);i++){
                if(nr%i==0) {
                    System.out.printf("Numarul %d nu este prim", nr);
                    return false;
                }
            }
        }
        System.out.printf("Numarul %d este prim", nr);
        return true;
    }

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Prim.VerificarePrim(n);
    }
}
