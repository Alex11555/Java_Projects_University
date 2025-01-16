import java.io.File;
import java.io.FileWriter;

public class DepartamentIT implements Observator {

    @Override
    public void primesteStire(Stire stire) {
        if (stire.getSubiectStiri().equals(SubiectStiri.IT)) {
            try {
                File stiriIT = new File("stiriIT.txt");
                if (!stiriIT.exists()) {
                    stiriIT.createNewFile();
                }
                FileWriter scrieStiriIT = new FileWriter(stiriIT, true);
                scrieStiriIT.write(stire.getContinut());
                scrieStiriIT.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
