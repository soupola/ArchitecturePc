/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie1;

/**
 *
 * @author Gilles
 */
public class Noeud implements Comparable<Noeud> {
String nom;
int X;
int Y;
int heuristique;
int cout;

    public Noeud(int heuristique, int cout, int X,int Y, String nom) {
        this.X = X;
        this.Y = Y;
        this.nom = nom;
        this.heuristique= heuristique;
        this.cout = cout;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHeuristique() {
        return heuristique;
    }
    
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public int getCout() {
        return cout;
    }

    public void setHeuristique(int heuristique) {
        this.heuristique = heuristique;
    }
    
    public void setX(int X) {
        this.X = X;
    }
    
    public void setY(int Y) {
        this.Y = Y;
    }
    
    
     public void setCout(int cout) {
        this.cout = cout;
    }
    @Override
    public int compareTo(Noeud comparestu) {
        int compareHeur=((Noeud)comparestu).getHeuristique();

        //return this.heuristique-compareHeur;

        return compareHeur-this.heuristique;
    }
     
    public int compareTo(Noeud node , Noeud lol) {
        int compareCout=((Noeud)node).getCout();

        //return this.heuristique-compareHeur;

        return compareCout-this.heuristique;
    }

    

    

    
   
}