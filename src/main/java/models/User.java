package models;

import java.util.Objects;

public class User {
    private int id;
    private String nom;



    public User() {
    }

    public User(String nom, String prenom, int number, String mail, String password) {
        this.nom = nom;

    }
    public User(int id ,String nom) {
        this.id = id ;
        this.nom = nom;
    }

    public User(String nom) {
        this.nom = nom;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }





    @Override
    public String toString() {
        return "User{" +
                "nom='" + nom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
