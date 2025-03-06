import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtil {
    public static void citesteIntrebariDinFisier(String fisier, JocTrivia joc) {
        try (Scanner scanner = new Scanner(new File(fisier))) {
            while (scanner.hasNextLine()) {
                // Citim întrebarea
                String intrebare = scanner.nextLine();
                // Citim răspunsul corect
                String raspunsCorect = scanner.nextLine();
                // Adăugăm întrebarea în joc
                joc.adaugaIntrebare(new Intrebare(intrebare, raspunsCorect));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fișierul " + fisier + " nu a fost găsit.");
        }
    }
}
