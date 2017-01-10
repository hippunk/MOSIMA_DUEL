package prolog;

import java.util.Map;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import sma.actionsBehaviours.LegalActions;
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
		
	public static boolean check(String nom){//V�rifier si ennemi � prot�e
		boolean result = false;
		if(nom.equals("Player1") && enemyInView){
			result = true;
					
		}else if(nom.equals("Player2") && playerInView){
			result = true;
		}
		return result;
	}
	
	public static boolean view(String nom){ //Si besoin ?, v�rification du remplissage des tables de Situation
		boolean result = false;
		return result;
	}
	
	public static boolean shoot(String nom){//Tir
		boolean result = true;
		
		if(nom.equals("Player1")){
			player.shoot(enemy.getLocalName());
			result = true;
					
		}else if(nom.equals("Player2")){
			enemy.shoot(player.getLocalName());
			result = true;
		}
		return result;
	}
	
	public static boolean move(String nom){ //D�placement vers le point le plus haut observ�
		boolean result = false;
		
		if(nom.equals("Player1")){
			player.cardinalMove(LegalActions.LookToMove(LegalActions.OrientationToLook(orientationPlayer)));
			result = true;
					
		}else if(nom.equals("Player2")){
			enemy.cardinalMove(LegalActions.LookToMove(LegalActions.OrientationToLook(orientationEnemy)));
			result = true;
		}
		
		return result;
	}
			
	public static void computeProlog(String nom){
		if(orientationEnemy != null && orientationPlayer != null && player != null && enemy != null){
			
			//loading the pl file
			String plFile = "consult('ressources/prolog/test/pltest.pl')";
			if (!Query.hasSolution(plFile)) {
				System.out.println(plFile + " failed loading pl file");
				System.exit(1);
			}

			//Passge du nom de l'agent en param�tre prolog puis triatement du think
		    Atom atom = new Atom(nom);
			Term arg[] = { atom };
			Query    q = new Query("think",arg);
			
			
			q.close();
			
		}else{
			System.out.println("il manque des donn�es pour le prolog");
			
		}
		System.out.println("orientationEnemy : " + orientationEnemy
				+"\norientationPlayer : "+orientationPlayer
				+"\nplayer : "+player
				+"\nenemy : "+enemy);
		
	}
	
	/*******************************************/
	
	
}
