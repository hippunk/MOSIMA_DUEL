package prolog;

import java.util.Map;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import env.jme.Situation;
import jade.core.Agent;
import sma.AbstractAgent;
import sma.actionsBehaviours.LegalActions.Orientation;
import sma.agents.ThinkAgent;

public class PrologCallsThink {
	
	public static String nomActif = null;
	public static ThinkAgent player = null;
	public static ThinkAgent enemy = null;
	public static Map<Orientation,Situation> mapSituPlayer = null;
	public static Map<Orientation,Situation> mapSituEnemy = null;
	public static Orientation orientationPlayer = null;
	public static Orientation orientationEnemy = null;
	public static boolean playerInView = false;
	public static boolean enemyInView = false;
	
	/***
	 * Prologs functions
	 * */
	
	//Debug me/him for testing
	public static String me(){
		return "player";
	}
	
	public static String him(){
		return "enemy";
	}
	
	public static boolean check(String nom){//Vérifier si ennemi à protée
		boolean result = true;
		return result;
	}
	
	public static boolean view(String nom){ //Si besoin ?, vérification du remplissage des tables de Situation
		boolean result = true;
		return result;
	}
	
	public static boolean shoot(String nom){//Tir
		boolean result = true;
		return result;
	}
	
	public static boolean move(String nom){ //Déplacement vers le point le plus haut observé
		boolean result = true;
		return result;
	}
		
	/*******************************************/
	
	public static void computeProlog(String nom){
		if(mapSituEnemy != null && mapSituPlayer != null && player != null && enemy != null){
			
			//loading the pl file
			String plFile = "consult('ressources/prolog/test/pltest.pl')";
			if (!Query.hasSolution(plFile)) {
				System.out.println(plFile + " failed loading pl file");
				System.exit(1);
			}
			//System.out.println("passed");
			
			//System.out.println("**Test 5**");
		    Atom atom = new Atom(nom);
			Term arg[] = { atom };
			Query    q = new Query("think",arg);
			
			//System.out.println("var : "+atom+" q : "+q);
			
			/*if(q.hasSolution()){
				q.open();
				Map<String, Term> soln = q.getSolution();
				for(Map.Entry<String, Term> entry : soln.entrySet()) {
				    String key = entry.getKey();
				    Term value = entry.getValue();
				    System.out.println("Key : "+key+" value "+value);
				}
			}
			else
				System.out.println("false");
			 */
			
			q.close();
			
		}else{
			System.out.println("il manque des données pour le prolog");
		}
		
	}

	
}
