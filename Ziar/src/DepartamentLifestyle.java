import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DepartamentLifestyle implements Observator {
    private final List<String> stiriLifestyle = new ArrayList<>();

    @Override
    public void primesteStire(Stire stire) {
        if (stire.getSubiectStiri().equals(SubiectStiri.LIFESTYLE)) {
            try {
                // Formatăm data știrii
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String data = dateFormat.format(new Date());

                // Salvăm știrea în lista locală
                stiriLifestyle.add(data + " - " + stire.getContinut());

                // Sortăm lista descrescător după dată
                Collections.sort(stiriLifestyle, Collections.reverseOrder());

                // Scriem lista în fișier
                File stiriFile = new File("stiriLifestyle.txt");
                if (!stiriFile.exists()) {
                    stiriFile.createNewFile();
                }

                try (FileWriter scrieStiriLifestyle = new FileWriter(stiriFile, false)) { // Suprascriem fișierul
                    for (String s : stiriLifestyle) {
                        scrieStiriLifestyle.write(s + "\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
