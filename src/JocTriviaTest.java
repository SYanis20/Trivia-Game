import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class JocTriviaTest {

    private static final String FILE_PATH = "scoruri.txt";

    @Test
    public void testAdaugaIntrebare() { //testam daca adauga corect intrebarile in lista de intrb

        JocTrivia joc = new JocTrivia();//instanta
        Intrebare intrebare = new Intrebare("Care este capitala Franței?", "Paris");


        joc.adaugaIntrebare(intrebare);


        assertEquals(1, joc.getIntrebari().size());//verif daca lista are exact o intrb
        assertEquals("Care este capitala Franței?", joc.getIntrebari().get(0).getIntrebare());//textul intrebarii este bun
    }

    @Test
    public void testSalveazaScor() throws IOException, InterruptedException {//testam daca salveaza corect numele jucatorului si scorul in fisier

        JocTrivia joc = new JocTrivia();
        Jucator jucator = new Jucator("Yanis");
        jucator.getScor().adaugaPuncte(50);

        joc.salveazaScor(jucator);

        Thread.sleep(500);
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {//aici verificam daca fis nu este gol ci sunt numele si scorul
            String linie = reader.readLine();
            assertNotNull(linie);
            assertTrue(linie.contains("Yanis"));
            assertTrue(linie.contains("50"));
        }
    }

    @AfterEach
    public void cleanUp() {
        File file = new File(FILE_PATH);

        // Verificăm dacă fișierul există și dacă conține doar datele testului.
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null && line.contains("Yanis")) { // Date specifice testului
                    file.delete(); // Ștergem doar dacă este fișierul de test
                }
            } catch (IOException e) {
                System.out.println("Eroare la curățarea fișierului: " + e.getMessage());
            }
        }
    }
}