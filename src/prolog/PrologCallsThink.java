package prolog;

import java.util.Map;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

import com.jme3.math.Vector3f;

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
	public static Vector3f playerDestination = null;
	public static Vector3f enemyDestination = null;
	
	/***
	 * Prologs functions
	 * */
		
	public static boolean check(String nom){//Vérifier si ennemi à protée
		System.out.println("Dans Check");
		boolean result = false;
		if(nom.equals("Player1") && enemyInView){
			result = true;
					
		}else if(nom.equals("Player2") && playerInView){
			result = true;
		}
		return result;
	}
	
	public static boolean view(String nom){ //Si besoin ?, vérification du remplissage des tables de Situation
		System.out.println("Dans view");
		boolean result = true;
		return result;
	}
	
	public static boolean shoot(String nom){//Tir
		System.out.println("Dans shoot");
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
	
	public static boolean move(String nom){ //Déplacement vers le point le plus haut observé
		System.out.println("Dans Move : "+nom);
		boolean result = false;

		if(nom.equals("Player1")){
			Vector3f currentpos  = player.getCurrentPosition();
			Vector3f dest = player.getDestination();
			if (dest==null || approximativeEqualsCoordinates(currentpos, dest)){
				player.moveTo(playerDestination);
			}
			result = true;	
		}else if(nom.equals("Player2")){
			Vector3f currentpos  = player.getCurrentPosition();
			Vector3f dest = player.getDestination();
			if (dest==null || approximativeEqualsCoordinates(currentpos, dest)){
				enemy.moveTo(enemyDestination);
			}result = true;
		}
		
		return result;
	}
	
	private static boolean approximativeEqualsCoordinates(Vector3f a, Vector3f b) {
		return approximativeEquals(a.x, b.x) && approximativeEquals(a.z, b.z);
	}
	private static boolean approximativeEquals(float a, float b) {
		return b-2.5 <= a && a <= b+2.5;
	}
	public static void computeProlog(String nom){
		if(orientationEnemy != null && orientationPlayer != null && player != null && enemy != null){
			System.out.println("Dans compute");
			//loading the pl file
			String plFile = "consult('ressources/prolog/test/pltest.pl')";
			if (!Query.hasSolution(plFile)) {
				System.out.println(plFile + " failed loading pl file");
				System.exit(1);
			}

			//Passge du nom de l'agent en paramètre prolog puis triatement du think
		    Atom atom = new Atom(nom);
			Term arg[] = { atom };
			Query    q = new Query("think",arg);
			q.hasSolution();
			
			//q.close();
			
		}else{
			System.out.println("il manque des données pour le prolog");
			
		}
		System.out.println("orientationEnemy : " + orientationEnemy
				+"\norientationPlayer : "+orientationPlayer
				+"\nplayer : "+player
				+"\nenemy : "+enemy);
		
	}
	
	/*******************************************/
	
	
}
