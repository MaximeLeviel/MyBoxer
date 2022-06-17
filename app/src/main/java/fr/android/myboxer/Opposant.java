package fr.android.myboxer;

public class Opposant {
    private final String nom;
    private final int age;
    private final int poids;

    public Opposant(String nom, int age, int poids) {
        this.nom = nom;
        this.age = age;
        this.poids = poids;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public int getPoids() {
        return poids;
    }
}
