import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DepartamentMuzica implements Observator {
    private final List<Stire> stiriMuzica = new ArrayList<>();

    @Override
    public void primesteStire(Stire stire) {
        if (stire.getSubiectStiri().equals(SubiectStiri.MUZICA)) {
            try {
                // Adăugăm știrea în lista locală
                stiriMuzica.add(stire);

                // Sortăm știrile descrescător după dată
                Collections.sort(stiriMuzica, Comparator.comparing(Stire::getData).reversed());

                // Scriem știrile sortate în fișier
                File stiriFile = new File("stiriMuzica.txt");
                if (!stiriFile.exists()) {
                    stiriFile.createNewFile();
                }

                try (FileWriter scrieStiriMuzica = new FileWriter(stiriFile, false)) { // Suprascriem fișierul
                    for (Stire s : stiriMuzica) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String dataFormatata = dateFormat.format(s.getData());
                        scrieStiriMuzica.write("Data: " + dataFormatata + "; Titlu: " + s.getTitlu() + "; Continut: " + s.getContinut() + "\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
