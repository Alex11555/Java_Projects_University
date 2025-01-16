import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DepartamentEconomie implements Observator {
    private final List<Stire> stiriEconomie = new ArrayList<>();

    @Override
    public void primesteStire(Stire stire) {
        if (stire.getSubiectStiri().equals(SubiectStiri.ECONOMIE)) {
            try {
                // Adăugăm știrea în lista locală
                stiriEconomie.add(stire);

                // Sortăm știrile după data descrescător
                Collections.sort(stiriEconomie, Comparator.comparing(Stire::getData).reversed());

                // Scriem știrile sortate în fișier
                File stiriFile = new File("stiriEconomie.txt");
                if (!stiriFile.exists()) {
                    stiriFile.createNewFile();
                }

                try (FileWriter scrieStiriEconomie = new FileWriter(stiriFile, false)) { // Suprascriem fișierul
                    for (Stire s : stiriEconomie) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String dataFormatata = dateFormat.format(s.getData());
                        scrieStiriEconomie.write("Data: " + dataFormatata + "; Titlu: " + s.getTitlu() + "; Continut: " + s.getContinut() + "\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

