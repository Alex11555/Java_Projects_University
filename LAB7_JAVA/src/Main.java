import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        // Creăm buffer-ul partajat între producător și consumatori
        List<String> buffer = new ArrayList<>();

        // Instanțiem producătorul și cei doi consumatori cu ID-uri diferite
        ProducatorNesincronizat producator = new ProducatorNesincronizat(buffer);
        ConsumatorNesincronizat consumator1 = new ConsumatorNesincronizat(buffer, "1");
        ConsumatorNesincronizat consumator2 = new ConsumatorNesincronizat(buffer, "2");

        // Creăm și pornim thread-urile pentru producător și cei doi consumatori
        Thread threadProducator = new Thread(producator);
        Thread threadConsumator1 = new Thread(consumator1);
        Thread threadConsumator2 = new Thread(consumator2);

        threadProducator.start();
        threadConsumator1.start();
        threadConsumator2.start();

        // Așteptăm ca toate thread-urile să se încheie
        try {
            threadProducator.join();
            threadConsumator1.join();
            threadConsumator2.join();

            // După ce toate thread-urile au terminat, așteptăm 5 secunde înainte de a încheia complet programul
            System.out.println("Productia s-a incheiat. Asteptam 5 secunde...");
            Thread.sleep(5000); // Pauza de 5 secunde

            // Oprirea completă a programului
            System.out.println("Programul se opreste acum.");
            System.exit(0);  // Oprirea tuturor thread-urilor și a programului
        } catch (InterruptedException e) {
            System.out.println("Unul dintre thread-uri a fost întrerupt.");
        }
    }
}
