public class Jucator {
    private String nume;
    private Scor scor;

    public Jucator(String nume) {
        this.nume = nume;
        this.scor = new Scor();
    }
//metode
    public String getNume() {
        return nume;
    }

    public Scor getScor() {
        return scor;
    }
}
