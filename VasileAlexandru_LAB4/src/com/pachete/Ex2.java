package com.pachete;
import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduceți un șir de caractere: ");
        // Cream un obiect Scanner pentru a citi de la tastatura
        String input = scanner.nextLine();

        System.out.println("Ați introdus: " + input);

        if (args.length > 0) {
            // Afișam primul element din vectorul args
            System.out.println("Primul argument din linia de comandă: " + args[0]);
        } else {
            System.out.println("Nu au fost furnizate argumente în linia de comandă.");
        }
    }
}
