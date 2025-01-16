import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
Scriitor scriitor=new Scriitor("Vasile","Alexandru",2021,10,31);
scriitor.afiseazaInformatii();
String caleFisier="./scriitor.txt";
scriitor.cautaScriitori(caleFisier);
scriitor.creeazaFisier();
        Scanner scanner = new Scanner(System.in);
        List<Long> numbers = new ArrayList<>();

        System.out.println("Introduceți 10 numere întregi lungi:");
        for (int i = 0; i < 10; i++) {
            long num = scanner.nextLong();
            numbers.add(num);
        }
        List<Long> oddNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 != 0) {
                oddNumbers.add(numbers.get(i));
            }
        }
    }
}