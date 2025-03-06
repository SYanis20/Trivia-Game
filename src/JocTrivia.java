import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JocTrivia {
    private List<Intrebare> intrebari;//obiect de tip intrebare
    private List<Jucator> jucatori;
    private ExecutorService executorService;

    public JocTrivia() {
        intrebari = new ArrayList<>();//liste goale
        jucatori = new ArrayList<>();
        executorService = Executors.newSingleThreadExecutor();//thread
    }

    public void adaugaIntrebare(Intrebare intrebare) {//metoda
        intrebari.add(intrebare);
    }//metoda

    public List<Intrebare> getIntrebari() {
        return intrebari;
    }

    public void adaugaJucator(Jucator jucator) {
        jucatori.add(jucator);
    }

     public void salveazaScor(Jucator jucator) {
        executorService.submit(() -> {
            //pt a scrie in fisier
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("scoruri.txt", true))) {
                writer.write("Jucator: " + jucator.getNume() + ", Scor: " + jucator.getScor().getPuncte() + "\n");
            } catch (IOException e) {
                System.out.println("Eroare la scrierea în fișier: " + e.getMessage());
            }
        });
    }

    public void inchideExecutor() {
        executorService.shutdown();
    }

    public static void main(String[] args) {

        JocTrivia joc = new JocTrivia();
        FileUtil.citesteIntrebariDinFisier("questions.txt", joc);
        Scanner scanner = new Scanner(System.in);//pt a citi date
        System.out.print("Introdu numele jucatorului: ");
        String numeJucator = scanner.nextLine();
        Jucator jucator = new Jucator(numeJucator);
        joc.adaugaJucator(jucator);//il adaugam in lista pe jucator

        //aici parcugem fiecare intrebare
        for (Intrebare intrebare : joc.getIntrebari()) {
            System.out.println(intrebare.getIntrebare());
            String raspunsUtilizator = scanner.nextLine();//citim raspunsul

            if (raspunsUtilizator.trim().equalsIgnoreCase(intrebare.getRaspunsCorect())) {
                jucator.getScor().adaugaPuncte(10);
                System.out.println("Corect! Puncte: " + jucator.getScor().getPuncte());
            } else {
                System.out.println("Greșit! Răspunsul corect era: " + intrebare.getRaspunsCorect());
                System.out.println("Puncte: " + jucator.getScor().getPuncte());
            }
        }


        System.out.println("Joc terminat! Scorul tau este: " + jucator.getScor().getPuncte());

        joc.salveazaScor(jucator);
        joc.inchideExecutor();
    }
}