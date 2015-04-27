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
public class Noeud implements Comparable {
int nom;
int X;
int Y;
int heuristique;
int cout;

    public Noeud(int heuristique, int cout, int X,int Y, int nom) {
        this.X = X;
        this.Y = Y;
        this.nom = nom;
        this.heuristique= heuristique;
        this.cout = cout;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
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
    public int compareTo(Noeud comparestu) {
        int compareHeur=((Noeud)comparestu).getHeuristique();
        /* For Ascending order*/
        //return this.heuristique-compareHeur;

        /* For Descending order do like this */
        return compareHeur-this.heuristique;
    }

    

    

    
   
}