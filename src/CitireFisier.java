import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CitireFisier {
    public static void citesteIntrebariDinFisier(String fisier, JocTrivia joc) { //metoda
        try (Scanner scanner = new Scanner(new File(fisier))) {//citim linie cu linie intrebarile
            while (scanner.hasNextLine()) {
                String intrebare = scanner.nextLine();
                String raspunsCorect = scanner.nextLine();
                joc.adaugaIntrebare(new Intrebare(intrebare, raspunsCorect));//facem intrebarea
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fișierul " + fisier + " nu a fost găsit.");//in cazul in care este o pb cu fisierul
        }
    }
}
