import java.io.*;

public class CodeRunner {
    public static String run(String code) {
        try {
            // Scrierea codului într-un fișier temporar
            File file = new File("Main.java");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(code);
            }

            // Compilarea codului
            Process compile = Runtime.getRuntime().exec("javac Main.java");
            compile.waitFor();

            // Executarea codului
            Process execute = Runtime.getRuntime().exec("java Main");
            BufferedReader reader = new BufferedReader(new InputStreamReader(execute.getInputStream()));
            execute.waitFor();

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Ștergerea fișierelor temporare
            file.delete();
            new File("Main.class").delete();

            return output.toString();
        } catch (Exception e) {
            return "Eroare: " + e.getMessage();
        }
    }
}
