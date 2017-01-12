package sma.agents;

import java.util.EnumMap;
import java.util.Map;

import env.jme.Environment;
import env.jme.Situation;
import prolog.CallsSmart;
import prolog.PrologCallsThink;
import sma.AbstractAgent;
import sma.actionsBehaviours.ThinkBehaviour;
import sma.actionsBehaviours.LegalActions.LegalAction;
import sma.actionsBehaviours.LegalActions.Orientation;
import sma.actionsBehaviours.LegalActions;
import sma.actionsBehaviours.SmartAgentBehaviour;

public class SmartAgent extends AbstractAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7229813360504204925L;
	public boolean friendorFoe;
	public SmartAgentBehaviour smart;
	public Map<Orientation,Situation> mapSitu; //Contient les données de la dernière observation complète autour de l'agent

	
	protected void setup(){
		super.setup();
		mapSitu = new EnumMap<Orientation,Situation>(Orientation.class);

		//get the parameters given into the object[]. In the current case, the environment where the agent will evolve
		final Object[] args = getArguments();
		if(args[0]!=null && args[1]!=null){
			
			this.friendorFoe = ((boolean)args[1]);
			
			if (friendorFoe) {
				deployAgent((Environment) args[0]);
			} else {
				deployEnemy((Environment) args[0]);
			}
			
		}else{
			System.err.println("Malfunction during parameter's loading of agent"+ this.getClass().getName());
			System.exit(-1);
		}
		
		smart = new SmartAgentBehaviour(this);
		
		addBehaviour(smart);
		
		System.out.println("the player "+this.getLocalName()+ " is started. Tag (0==enemy): " + friendorFoe);		
	}
	
	public synchronized boolean fillMapSitu(){ //Retourne vrai si la table est pleine ou si un ennemi a été trouvé.
		boolean result = false;
		Orientation key = mapSituHasEmptyOrientation();
		
		if(key != null){
			LegalAction action = LegalActions.OrientationToLook(key);
			lookAt(action);
			try{
				mapSitu.put(key, observeAgents());
			}catch (Exception e){}
			if(!mapSitu.get(key).agents.isEmpty()){
				result = true;
				if(this.getLocalName().equals("Player1")){
					CallsSmart.playerInView = true;
				}
				else{
					CallsSmart.enemyInView = true;
				}
			}
		}else{
			result = true;
		}
		return result;
	}
	
	public synchronized void debugPrintMap(){

		for(Map.Entry<Orientation,Situation> entry : mapSitu.entrySet()) {
		    Orientation key = entry.getKey();
		    Situation value = entry.getValue();
		
		    // do what you have to do here
		    // In your case, an other loop.
		    System.out.println("Debug Print name : "+getLocalName()+" key : "+key+" value "+value);
		}
	}
	
	private synchronized LegalActions.Orientation mapSituHasEmptyOrientation(){

		for(LegalActions.Orientation key : LegalActions.Orientation.values()){
			if(!mapSitu.containsKey(key))
				return key;
				
		}
		
		return null;
	}
	
	public synchronized void dropMapSitu(){
		System.out.println("Drop SituMap");
		mapSitu.clear();
		//stopMove();
		
		//Drop dans la classe statique prolog pour le traitement des données
		//Toute la classe statique lol
		if(getLocalName().equals("Player1")){
			CallsSmart.dropPlayerDatas();
			//PrologCallsThink.orientationPlayer = null;
		}
		if(getLocalName().equals("Player2")){
			CallsSmart.dropEnemyDatas();
			//PrologCallsThink.orientationEnemy = null;
		}
	}

}
