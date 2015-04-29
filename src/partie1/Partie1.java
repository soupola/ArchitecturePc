/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.sqrt;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
 
/**
 *
 * @author Gilles
 */

public class Partie1 {

    /**
     * @param args the command line arguments
     */
    //Noeud tableNode [13][13];
    Boolean debugEnfant=false;
    Boolean debugDfs=false;
    
    
    Noeud tableNoeud[][]= new Noeud[13][13];
    String tableString[][] = new String[13][13];
    public static void main(String[] args) {
        Partie1 obj = new Partie1();
        obj.readCSV();
        //obj.nds();
       // obj.dfs();
        obj.greedySearch();
       // obj.showCsv();
        
        
    }
    
   public void readCSV()
    {
        String path = "Classeur1.csv";
        BufferedReader br = null;
        String line ="";
        String csvSplit = ";";
        int y = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null)
            {
                String splitTable[]=  line.split(csvSplit);
                for(int i = 0; i<13;i++)
                {
                    tableString[y][i]=splitTable[i];
                    tableNoeud[y][i]= new Noeud(0, Integer.parseInt(splitTable[i]),y,i,"0");
                }
                y++;                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Partie1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Partie1.class.getName()).log(Level.SEVERE, null, ex);
        }
        show("à la case : "+tableNoeud[0][1].cout);
    }
 
   
   
   public void greedySearch()
   {
       ArrayList<String> queue = new ArrayList<>();
       ArrayList<Noeud> enfant = new ArrayList<>();
       
       
       Boolean firstPass = true;
       Boolean goal = false;
       
       int depart = 1;
       int arrivee = 13;
       int profondeur= 0;
       queue.add(Integer.toString(depart));
       while(!goal && queue.size()>0)
       {
           if(firstPass)
           {
            enfant= findChildrenGreedySearch(depart,arrivee);
            for(int x=0; x<enfant.size();x++)
            {
                queue.add(queue.get(0)+"/"+enfant.get(x).getNom());
            }
            queue.remove(0);
            show("éléments de la queue");
            for(int y =0 ; y<queue.size(); y++ )
            {
                show(queue.get(y));
            }
            firstPass=false;
            
        }
        
        else
        {
            String tempTab[] = null;
            for(int dec = queue.size(); dec>0 ; dec -- )
            {
                tempTab = queue.get(dec-1).split("/");
                
                if(queue.get(dec-1).equals(""+arrivee))
                {
                    goal=true;
                }

            }
            if(!goal)
            {
            show(tempTab[tempTab.length-1]);
            enfant= findChildrenGreedySearch(Integer.parseInt(tempTab[tempTab.length-1]),arrivee);
            int varPos = 1;
            //show("Est ce "+tempTab[tempTab.length-1]+" == "+arrivee+" ?");
            if(!tempTab[tempTab.length-1].equals(Integer.toString(arrivee)))
            {
                for(int x = 1; x<enfant.size()+1;x++)
                {

                    HashSet<String> check = new HashSet<>();
                    for(int z = 0; z<tempTab.length; z++)
                    {
                      check.add(tempTab[z]);
                    }
                   if(check.add(enfant.get(x-1).getNom()))
                    {
                        queue.add(varPos,queue.get(0)+"/"+enfant.get(x-1).getNom());
                        //queue.add(x, "bite");
                        //show(enfant.get(x-1));
                        varPos++;
                    }
                   
                }
                queue.remove(0);
                show(profondeur+" ème passage");
                 for(int lol = 0; lol<queue.size();lol++)
                {
                    show(queue.get(lol));
                }
            }
            else
            {
                goal=true;
            }
        }
        }
        profondeur++;
       }
       show("le chemin meilleur chemin trouvé par greedy search est : "+queue.get(0));
       show("le chemin a été trouvé en "+profondeur+" coups ");
       
   }
   
   public int heuristique(int dep, int fin)
   {
       int heuristique = (int)sqrt(((dep*2)*(dep*3))+((2*fin)*(3*fin)));
       return heuristique;
   }
   
   public int randomNum(int range)
   {
       Random rand = new Random();
       int nbr = rand.nextInt(range+1);
       return nbr;
   }
   public ArrayList findChildrenGreedySearch(int dep, int fin)
   {
       ArrayList<Noeud> enfant = new ArrayList<>();
       for(int i = 0 ; i<13; i++)
       {
           if(tableNoeud[dep-1][i].getCout()!= -1)
           {
               tableNoeud[dep-1][i].setHeuristique(heuristique(i, fin));
               tableNoeud[dep-1][i].setNom(Integer.toString(i+1));
              /* show("cout : "+tableNoeud[dep-1][i].getCout());
               show("heuristique : "+tableNoeud[dep-1][i].getHeuristique());
               show("X : "+tableNoeud[dep-1][i].getX());
               show("Y : "+tableNoeud[dep-1][i].getY());*/
               enfant.add(tableNoeud[dep-1][i]);
              
               
           } 
       }
        Collections.sort(enfant);         
       /* show("contenu des la liste d'enfant");
        for(Noeud str : enfant)
        {
            System.out.println(str.getNom());
        }
       */
       return enfant;
   }
   
   public void showCsv()
   {
       
       for(int i = 0; i<13 ; i++)
       {
           for(int j = 0; j<13 ; j++)
           {
               if(j>11)
               {
                   show(" ");
               }
               System.out.print(" "+tableNoeud[i][j].getCout()+" ");
           } 
       }
   }
   public ArrayList findChildren(int dep)
   {
       ArrayList<String> enfant = new ArrayList<>();
       for(int i = 0 ; i<13; i++)
       {
           if(!tableString[dep-1][i].equals("-1"))
           {
               if(debugEnfant)
               {
                show(" ");
                show("passe par le if");
                show((i+1)+" a un cout de "+tableString[dep-1][i]);  
               }
               
               enfant.add(Integer.toString(i+1));
           }
           else
           {
               if(debugEnfant)
               {
                show(" ");
                show("passe pas par le if");
                show((i+1)+" a un cout de "+tableString[dep-1][i]);  
               }
           }
           if(debugEnfant)
           {
               show("contenu des la liste d'enfant");
               enfant.stream().forEach((enfant1) -> {
                   show(enfant1);
               });
           }
           
       }
       return enfant;
   }
   
  
   public void nds()
   {
       ArrayList<String> queue = new ArrayList<>();
       ArrayList<String> enfant = new ArrayList<>();
       
       
       Boolean firstPass = true;
       Boolean goal = false;
       
       int depart = 1;
       int arrivee = 13;
       

       queue.add(Integer.toString(depart));
       while(!goal && queue.size()>0)
       {
           if(firstPass)
           {
            enfant= findChildren(depart);


            for(int x=0; x<enfant.size();x++)
            {

             if(debugDfs)
             {
                show(x+"eme element de la liste enfant sur "+enfant.size());
                show(enfant.get(x));
             }


             queue.add(queue.get(0)+"/"+enfant.get(x));


            }
            if(debugDfs)
            {
                show("nombre d'élément de queue : "+queue.size());
                show("affiche les éléments de la queue: ");
                for(int y =0; y<queue.size() ;y++)
                {
                    show(queue.get(y));
                }

            }
           queue.remove(0);
           firstPass=false;
        }
        
        else
        {
            String tempTab[] = null;
            for(int dec = queue.size(); dec>0 ; dec -- )
            {
                tempTab = queue.get(dec-1).split("/");
                
                if(queue.get(dec-1).equals(""+arrivee))
                {
                    goal=true;
                }
            }
            if(!goal)
            {
            enfant= findChildren(Integer.parseInt(tempTab[tempTab.length-1]));
            int varPos ;
            //show("Est ce "+tempTab[tempTab.length-1]+" == "+arrivee+" ?");
            if(!tempTab[tempTab.length-1].equals(Integer.toString(arrivee)))
            {
                String firstElement= queue.get(0);
                queue.remove(0);
                for(int x = 1; x<enfant.size()+1;x++)
                {

                    HashSet<String> check = new HashSet<>();
                    
                    for(int z = 0; z<tempTab.length; z++)
                    {
                      check.add(tempTab[z]);
                    }
                    
                   if(check.add(enfant.get(x-1)))
                    {
                        varPos = randomNum(queue.size());
                        show ("nbr aléatoire "+ varPos );
                        queue.add(varPos,firstElement+"/"+enfant.get(x-1));
                    }  
                }
                 for(int lol = 0; lol<queue.size();lol++)
                {
                    show(queue.get(lol));
                }
            }
            else
            {
                goal=true;
            }
        }
        }
           
       }
       show ("le chemin trouvé est : "+queue.get(0));
       
   }
   
   
   public void dfs()
   {
       ArrayList<String> queue = new ArrayList<>();
       ArrayList<String> enfant = new ArrayList<>();
       
       
       Boolean firstPass = true;
       Boolean goal = false;
       
       int depart = 1;
       int arrivee = 13;
       int profondeur= 0;
       queue.add(Integer.toString(depart));
       while(!goal && queue.size()>0)
       {
           if(firstPass)
           {
            enfant= findChildren(depart);


            for(int x=0; x<enfant.size();x++)
            {

             if(debugDfs)
             {
                show(x+"eme element de la liste enfant sur "+enfant.size());
                show(enfant.get(x));
             }


             queue.add(queue.get(0)+"/"+enfant.get(x));


            }
            if(debugDfs)
            {
                show("nombre d'élément de queue : "+queue.size());
                show("affiche les éléments de la queue: ");
                for(int y =0; y<queue.size() ;y++)
                {
                    show(queue.get(y));
                }

            }
           queue.remove(0);
           firstPass=false;
        }
        
        else
        {
            String tempTab[] = null;
            for(int dec = queue.size(); dec>0 ; dec -- )
            {
                tempTab = queue.get(dec-1).split("/");
                
                if(queue.get(dec-1).equals(""+arrivee))
                {
                    goal=true;
                }
            }
            if(!goal)
            {
            enfant= findChildren(Integer.parseInt(tempTab[tempTab.length-1]));
            int varPos = 1;
            //show("Est ce "+tempTab[tempTab.length-1]+" == "+arrivee+" ?");
            if(!tempTab[tempTab.length-1].equals(Integer.toString(arrivee)))
            {
                for(int x = 1; x<enfant.size()+1;x++)
                {

                    HashSet<String> check = new HashSet<>();
                    for(int z = 0; z<tempTab.length; z++)
                    {
                      check.add(tempTab[z]);
                    }
                   if(check.add(enfant.get(x-1)))
                    {
                        queue.add(varPos,queue.get(0)+"/"+enfant.get(x-1));
                        //queue.add(x, "bite");
                        //show(enfant.get(x-1));
                        varPos++;
                    }
                   
                }
                queue.remove(0);
                show(profondeur+" ème passage");
                 for(int lol = 0; lol<queue.size();lol++)
                {
                    show(queue.get(lol));
                }
            }
            else
            {
                goal=true;
            }
        }
        }
        profondeur++;
       }
       show("le chemin meilleur chemin trouvé par DFS est : "+queue.get(0));
       show("le chemin a été trouvé en "+profondeur+" coups ");
       
   }
   
   public void show(String txt)
   {
       System.out.println(txt);
   }
   
   public void show(int txt)
   {
       System.out.println(txt);
   }
}
