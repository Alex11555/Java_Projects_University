import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

    public static void adaugaStire(Redactie redactie, String subiectString, String titlu, String continut) {
        SubiectStiri subiect = SubiectStiri.valueOf(subiectString.toUpperCase());
        Stire stireNoua = new Stire(subiect, titlu, continut, new Date());
        redactie.adaugaStire(stireNoua);
    }

    // Metoda care creează și afișează formularul pentru adăugarea de știri
    public static void creeazaFormularAdaugareStire(Redactie redactie) {
        // Creăm fereastra formularului
        JFrame fereastra = new JFrame("Adaugă o Nouă Știre");
        fereastra.setSize(400, 300);
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panou = new JPanel();
        panou.setLayout(new GridLayout(4, 2));

        JTextField subiectField = new JTextField();
        JTextField titluField = new JTextField();
        JTextArea continutArea = new JTextArea(5, 20);


        panou.add(new JLabel("Subiect:"));
        panou.add(subiectField);
        panou.add(new JLabel("Titlu:"));
        panou.add(titluField);
        panou.add(new JLabel("Conținut:"));
        panou.add(new JScrollPane(continutArea));

        JButton adaugaButon = new JButton("Adaugă Știrea");
        panou.add(adaugaButon);

        // Ascultător pentru butonul de adăugare
        adaugaButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subiect = subiectField.getText();
                String titlu = titluField.getText();
                String continut = continutArea.getText();

                // Adăugăm știrea la redacție
                adaugaStire(redactie, subiect, titlu, continut);

                // Afișăm un mesaj de succes
                JOptionPane.showMessageDialog(fereastra, "Știrea a fost adăugată cu succes!");

                // Curățăm câmpurile
                subiectField.setText("");
                titluField.setText("");
                continutArea.setText("");
            }
        });

        // Adăugăm panoul la fereastră și o afișăm
        fereastra.add(panou);
        fereastra.setVisible(true);
    }


    public static void afiseazaStiri(String titluFereastra, List<String> titluriStiri) {
        JFrame fereastra = new JFrame(titluFereastra);
        fereastra.setSize(400, 400);
        fereastra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panou = new JPanel();
        panou.setLayout(new BoxLayout(panou, BoxLayout.Y_AXIS));

        for (String titlu : titluriStiri) {
            JLabel label = new JLabel(titlu);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            panou.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panou);
        fereastra.add(scrollPane);
        fereastra.setVisible(true);
    }
    public static void exercitiuFinanciarSlab(Redactie redactie) {
        // Lista de departamente disponibile
        List<Observator> departamente = List.of(
                new DepartamentSport(),
                new DepartamentEconomie(),
                new DepartamentIT(),
                new DepartamentMuzica(),
                new DepartamentLifestyle()
        );

        // Generăm un număr aleator între 0 și 4 pentru a selecta un departament
        Random rand = new Random();
        int indexAleator = rand.nextInt(departamente.size());

        // Aflăm departamentul ales aleator
        Observator departamentSelectat = departamente.get(indexAleator);

        // Desființăm departamentul ales
        redactie.stergereObservator(departamentSelectat);
        System.out.println("Departamentul " + departamentSelectat.getClass().getSimpleName() + " a fost desființat.");
    }

    public static void main(String[] args) {

        Redactie redactie = new Redactie();

        DepartamentSport sport = new DepartamentSport();
        DepartamentEconomie economie = new DepartamentEconomie();
        DepartamentIT it = new DepartamentIT();
        DepartamentMuzica muzica = new DepartamentMuzica();
        DepartamentLifestyle lifestyle = new DepartamentLifestyle();

        redactie.adaugaObservator(sport);
        redactie.adaugaObservator(economie);
        redactie.adaugaObservator(it);
        redactie.adaugaObservator(muzica);
        redactie.adaugaObservator(lifestyle);

        Stire stire1 = new Stire(SubiectStiri.LIFESTYLE, "Tendințe de toamnă", "Cele mai noi tendințe în modă pentru sezonul rece", new Date());
        Stire stire2 = new Stire(SubiectStiri.MUZICA, "Concert surpriză", "O trupă legendară va susține un concert neanunțat", new Date());
        Stire stire3 = new Stire(SubiectStiri.SPORT, "Victorie istorică", "Echipa națională câștigă în fața campionilor mondiali", new Date());
        Stire stire4 = new Stire(SubiectStiri.ECONOMIE, "Inflația scade", "Rata inflației a scăzut la cel mai mic nivel din ultimii 5 ani", new Date());

        redactie.adaugaStire(stire2);
        redactie.adaugaStire(stire3);
        redactie.adaugaStire(stire1);
        redactie.adaugaStire(stire4);
        exercitiuFinanciarSlab(redactie);
        creeazaFormularAdaugareStire(redactie);

        // Exemplu de date pentru ferestrele grafice
        List<String> titluriLifestyle = List.of("Tendințe de toamnă");
        List<String> titluriMuzica = List.of("Concert surpriză");
        List<String> titluriSport = List.of("Victorie istorică");
        List<String> titluriEconomie = List.of("Inflația scade");

        // Afișăm știrile pentru fiecare departament
        SwingUtilities.invokeLater(() -> {
            afiseazaStiri("Lifestyle", titluriLifestyle);
            afiseazaStiri("Muzică", titluriMuzica);
            afiseazaStiri("Sport", titluriSport);
            afiseazaStiri("Economie", titluriEconomie);
        });
    }
}
