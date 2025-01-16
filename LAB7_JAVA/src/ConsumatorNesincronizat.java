import java.util.List;

public class ConsumatorNesincronizat implements Runnable {
    private final List<String> buffer;
    private final String id;

    public ConsumatorNesincronizat(List<String> buffer, String id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            if (!buffer.isEmpty()) {
                String mesaj = buffer.remove(0);

                if (mesaj.equals(Main.EOF)) {
                    System.out.println("Consumator " + id + ": Productie incheiata (EOF detectat).");
                    break;
                }

                System.out.println("Consumator " + id + ": " + mesaj);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Consumatorul " + id + " a fost întrerupt.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
