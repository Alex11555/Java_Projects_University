import java.util.ArrayList;
import java.util.List;


public class ProducatorNesincronizat implements Runnable {
    private final List<String> buffer;
    private final int limitaProductie = 9; // Numarul de litere din numele "Alexandru"

    public ProducatorNesincronizat(List<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < limitaProductie; i++) {
                String mesaj = String.format("Produs%03d", i).substring(0, 9);
                buffer.add(mesaj);
                System.out.println("Produs: " + mesaj);
                Thread.sleep(50);
            }

            buffer.add(Main.EOF);
            System.out.println("Productie incheiata (EOF adaugat).");
        } catch (InterruptedException e) {
            System.out.println("Producatorul a fost întrerupt.");
            Thread.currentThread().interrupt();
        }
    }
}
