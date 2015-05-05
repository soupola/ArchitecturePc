/*Il y a 5 maisons de couleurs différentes, toutes sur une rangée.
Dans chaque maison vit une personne de nationalité différente.
Chacune de ces 5 personnes boit une boisson, fume une marque de cigarette & possède un animal. Personne ne boit la même boisson, ne fume les mêmes cigarettes ni n'a le même animal de compagnie.
 
L'Anglais vit dans la maison rouge.
Le Suédois a un chien.
Le Danois boit du thé.
La maison verte est à gauche de la maison blanche.
Le propriétaire de la maison verte boit du café.
Celui qui fume des Pall Mall a un oiseau.
Le propriétaire de la maison jaune fume des Dunhill.
Le propriétaire de la maison du centre boit du lait.
Le Norvégien vit dans la première maison.
Celui qui fume des Blends vit à côté du propriétaire du chat.
Celui qui a un cheval vit à côté de celui qui fume des Dunhill.
Celui qui fume des Blue Masters boit de la bière.
L'Allemand fume des Princes.
Le Norvégien vit à côté de la maison bleue.
Celui qui fume des Blends a un voisin qui boit de l'eau.
 
Qui possède le poisson ?*/

package partie3;
import solver.Solver;
import solver.constraints.Constraint;
import solver.constraints.ICF;
import solver.constraints.IntConstraintFactory;
import solver.constraints.LogicalConstraintFactory;
import solver.variables.IntVar;
import solver.variables.VariableFactory;
/**
 *
 * @author Gilles
 */
public class Partie3 {
    public static void main(String[] args) {
    
        //solver
        Solver solver = new Solver();
        
        //variables 
        
        //nationnalité(5)
        IntVar anglais = VariableFactory.bounded("Anglais", 1, 5, solver);
        IntVar suedois = VariableFactory.bounded("suédois", 1, 5, solver);
        IntVar danois = VariableFactory.bounded("Danois", 1, 5, solver);
        IntVar norvegien = VariableFactory.bounded("Norvegien", 1, 5, solver);
        IntVar allemand = VariableFactory.bounded("Allemand", 1, 5, solver);
        //Boisson
        IntVar the = VariableFactory.bounded("thé", 1, 5, solver);
        IntVar cafe = VariableFactory.bounded("café", 1, 5, solver);
        IntVar lait = VariableFactory.bounded("lait", 1, 5, solver);
        IntVar biere = VariableFactory.bounded("biere", 1, 5, solver);
        IntVar eau = VariableFactory.bounded("eau", 1, 5, solver);
        //animal
        IntVar chien = VariableFactory.bounded("chien", 1, 5, solver);
        IntVar oiseau = VariableFactory.bounded("oiseau", 1, 5, solver);
        IntVar chat = VariableFactory.bounded("chat", 1, 5, solver);
        IntVar cheval = VariableFactory.bounded("cheval", 1, 5, solver);
        IntVar poisson = VariableFactory.bounded("poisson", 1, 5, solver);
        //cigarettes
        IntVar pallMall = VariableFactory.bounded("Pall Mall", 1, 5, solver);
        IntVar dunHill = VariableFactory.bounded("DunHill", 1, 5, solver);
        IntVar blends = VariableFactory.bounded("Blends", 1, 5, solver);
        IntVar blueMasters = VariableFactory.bounded("Blue Masters", 1, 5, solver);
        IntVar princes = VariableFactory.bounded("Princes", 1, 5, solver);
        //maisons
        IntVar rouge = VariableFactory.bounded("rouge", 1, 5, solver);
        IntVar verte = VariableFactory.bounded("verte", 1, 5, solver);
        IntVar jaune = VariableFactory.bounded("jaune", 1, 5, solver);
        IntVar bleue = VariableFactory.bounded("bleue", 1, 5, solver);
        IntVar blanche = VariableFactory.bounded("blanche", 1, 5, solver);
        
        //mise des var dans des tableaux 
        IntVar nationalite[]= {anglais,suedois,danois,norvegien,allemand};
        IntVar boisson[]= {the,cafe,lait,biere,eau};
        IntVar animal[]={chien,oiseau,chat,cheval,poisson};
        IntVar cigarette[]={pallMall,dunHill,blends,blueMasters,princes};
        IntVar maison[]={rouge,verte,jaune,bleue,blanche};
        
    //contraintes 
            
            //initiales
            Constraint conNationalite = IntConstraintFactory.alldifferent(nationalite);
            Constraint conBoisson = IntConstraintFactory.alldifferent(boisson);
            Constraint conAnimal = IntConstraintFactory.alldifferent(animal);
            Constraint conCigarette = IntConstraintFactory.alldifferent(cigarette);
            Constraint conMaison = IntConstraintFactory.alldifferent(maison);
            
            //ajout des contraintes 
            solver.post(conNationalite);
            solver.post(conBoisson);
            solver.post(conAnimal);
            solver.post(conCigarette);
            solver.post(conMaison);
        
            
            //supplémentaires
            
            //L'Anglais vit dans la maison rouge.
            Constraint con1 = IntConstraintFactory.arithm(anglais, "=", rouge);
            //Le Suédois a un chien.
            Constraint con2 = IntConstraintFactory.arithm(suedois, "=", chien);
            //Le Danois boit du thé.
            Constraint con3 = IntConstraintFactory.arithm(danois, "=", the);
            //La maison verte est à gauche de la maison blanche.
            Constraint con4 = ICF.arithm(verte, "=", blanche,"-",1);
            //Le propriétaire de la maison verte boit du café.
            Constraint con5 = ICF.arithm(verte, "=", cafe);
            //Celui qui fume des Pall Mall a un oiseau.
            Constraint con6 = ICF.arithm(pallMall, "=", oiseau);
            //Le propriétaire de la maison jaune fume des Dunhill.
            Constraint con7 = ICF.arithm(jaune, "=", dunHill);
            //Le propriétaire de la maison du centre boit du lait.
            Constraint con8 = ICF.arithm(lait, "=", 3);
            //Le Norvégien vit dans la première maison.
            Constraint con9 = ICF.arithm(norvegien, "=", 1);
            //Celui qui fume des Blends vit à côté du propriétaire du chat.
            Constraint con10 = ICF.arithm(blends, "=", chat,"+",1);
            Constraint con11 = ICF.arithm(blends, "=", chat,"-",1);
                //Création d'une contraine d'exclusion
                Constraint conExclu = LogicalConstraintFactory.or(con10,con11);
            //Celui qui a un cheval vit à côté de celui qui fume des Dunhill.
            Constraint con12 = ICF.arithm(cheval, "=", dunHill,"+",1);
            Constraint con13 = ICF.arithm(cheval, "=", dunHill,"-",1);
                //Création d'une contraine d'exclusion
                Constraint conExclu2 = LogicalConstraintFactory.or(con12,con13);
            //Celui qui fume des Blue Masters boit de la bière.
            Constraint con14 = ICF.arithm(blueMasters, "=", biere);
            //L'Allemand fume des Princes.
            Constraint con15 = ICF.arithm(allemand, "=", princes);
            //Le Norvégien vit à côté de la maison bleue.
            Constraint con16 = ICF.arithm(norvegien, "=", bleue,"+",1);
            Constraint con17 = ICF.arithm(norvegien, "=", bleue,"-",1);
                //Création d'une contraine d'exclusion
                Constraint conExclu3 = LogicalConstraintFactory.or(con16,con17);
            //Celui qui fume des Blends a un voisin qui boit de l'eau.
            Constraint con18 = ICF.arithm(eau, "=", blends,"+",1);
            Constraint con19 = ICF.arithm(eau, "=", blends,"-",1);
                //Création d'une contraine d'exclusion
                Constraint conExclu4 = LogicalConstraintFactory.or(con18,con19);
                
            //Contrainte totale
            Constraint total = LogicalConstraintFactory.and(con1,con2,con3,con4,con5,con6,con7,con8,con9,con14,con15,conExclu,conExclu2,conExclu3,conExclu4);
           
        //résolution du probleme
            solver.post(total);
            solver.findSolution();
            
            System.out.println("résolution du probleme avec " + solver.getNbCstrs()+" contraintes:");
            do
            {
              System.out.println("le "+nationalite[0]+" vis dans la maison "+maison[0]+" avec un "+animal[0]+" en buvant "+boisson[0]+" en fumant des "+cigarette[0]);
              System.out.println("le "+nationalite[1]+" vis dans la maison "+maison[1]+" avec un "+animal[1]+" en buvant "+boisson[1]+" en fumant des "+cigarette[1]);
              System.out.println("le "+nationalite[2]+" vis dans la maison "+maison[2]+" avec un "+animal[2]+" en buvant "+boisson[2]+" en fumant des "+cigarette[2]);
              System.out.println("le "+nationalite[3]+" vis dans la maison "+maison[3]+" avec un "+animal[3]+" en buvant "+boisson[3]+" en fumant des "+cigarette[3]);
              System.out.println("le "+nationalite[4]+" vis dans la maison "+maison[4]+" avec un "+animal[4]+" en buvant "+boisson[4]+" en fumant des "+cigarette[4]);
            }
            while(solver.nextSolution());
    }   
}
