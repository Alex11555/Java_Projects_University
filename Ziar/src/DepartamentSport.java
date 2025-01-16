import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DepartamentSport implements Observator {
    private final List<Stire> stiriSport = new ArrayList<>();

    @Override
    public void primesteStire(Stire stire) {
        if (stire.getSubiectStiri().equals(SubiectStiri.SPORT)) {
            try {
                // Adăugăm știrea în lista locală
                stiriSport.add(stire);

                // Sortăm știrile descrescător după data lor
                Collections.sort(stiriSport, Comparator.comparing(Stire::getData).reversed());

                // Scriem știrile sortate în fișier
                File stiriFile = new File("stiriSport.txt");
                if (!stiriFile.exists()) {
                    stiriFile.createNewFile();
                }

                try (FileWriter scrieStiriSport = new FileWriter(stiriFile, false)) { // Suprascriem fișierul
                    for (Stire s : stiriSport) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String dataFormatata = dateFormat.format(s.getData());
                        scrieStiriSport.write("Data: " + dataFormatata + "; Titlu: " + s.getTitlu() + "; Continut: " + s.getContinut() + "\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

