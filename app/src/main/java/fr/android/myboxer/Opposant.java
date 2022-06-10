package fr.android.myboxer;

public class Opposant {
    private String nom;
    private int age;
    private int poids;

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
