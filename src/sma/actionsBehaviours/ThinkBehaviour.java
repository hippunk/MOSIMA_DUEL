package sma.actionsBehaviours;

import java.util.Map;
import java.util.Set;

import env.jme.Situation;
import jade.core.behaviours.TickerBehaviour;
import prolog.PrologCallsThink;
import sma.AbstractAgent;
import sma.actionsBehaviours.LegalActions.LegalAction;
import sma.actionsBehaviours.LegalActions.Orientation;
import sma.agents.ThinkAgent;

public class ThinkBehaviour extends TickerBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int observeTimeout = 20;
	private int observecpt = 0;
	private ThinkAgent myagent; 
	private boolean computed = false;
	
	public ThinkBehaviour(final AbstractAgent a) {
		super(a, 200);
		myagent = (ThinkAgent)a;
		
	}
	@Override
	protected void onTick() { /*Utilisation d'un graphe pour mémoriser des positions ?!*/
		// TODO Auto-generated method stub
		
		//Si timeout atteint clear map
		checkObserveTimeout();
		
		//Si pas complètement observé continuer
		fillMapSitu();
		
		//Print debug
		//debugPrintMap();
		
		//Si fillMapSitu plein : charger le Prolog Static
		
		//Compute : Avec le prolog :)
		PrologCallsThink.computeProlog(myagent.getLocalName());
		
	}
	
	private void computeDatas(){
		if (!computed){
			computed = true;
			float max = -10000000;
			Orientation res = null;
			for(Map.Entry<Orientation,Situation> entry : myagent.mapSitu.entrySet()) {
			    Orientation key = entry.getKey();
			    Situation value = entry.getValue();
			    
			    
			    // Agent dans champ de vision
			    if (!value.agents.isEmpty()){
			    	if (myagent.getLocalName().equals("player1")){
			    		PrologCallsThink.orientationPlayer = key;
			    	}
			    	else {
			    		PrologCallsThink.orientationEnemy = key;
			    	}
			    	break;
			    }
			    // Sinon, on prend l'altitude la plus haute
			    else {
			    	if (value.maxAltitude != null && max < value.maxAltitude.y){
			    		res = key;
			    		max = value.maxAltitude.y;
			    	}
			    	
			    	
			    }
			    
			}
			if (res != null){
				if (myagent.getLocalName().equals("player1")){
		    		PrologCallsThink.orientationPlayer = res;
		    		PrologCallsThink.enemyInView = true;
		    	}
		    	else {
		    		PrologCallsThink.orientationEnemy = res;
		    		PrologCallsThink.playerInView = true;
		    	}		
			}
					
			
		}
	}
	
	private synchronized void debugPrintMap(){

		for(Map.Entry<Orientation,Situation> entry : myagent.mapSitu.entrySet()) {
		    Orientation key = entry.getKey();
		    Situation value = entry.getValue();
		
		    // do what you have to do here
		    // In your case, an other loop.
		    System.out.println("Debug Print name : "+myagent.getLocalName()+" tick : "+observecpt+" key : "+key+" value "+value);
		}
	}
	
	private synchronized void fillMapSitu(){
		Orientation key = mapSituHasEmptyOrientation();
		
		if(key != null){
			LegalAction action = LegalActions.OrientationToLook(key);
			myagent.lookAt(action);
			try{
				myagent.mapSitu.put(key, myagent.observeAgents());
			}catch (Exception e){}
		}
	}
	
	private synchronized void checkObserveTimeout(){
		if(observecpt >= observeTimeout-1){
			dropMapSitu();
			myagent.moveTo(myagent.getCurrentPosition());
			observecpt = 0;
		}
		else{
			observecpt++;
		}
	}
	
	
	/***
	 * Clear the mapSitu enumMap
	 * */
	private synchronized void dropMapSitu(){
		System.out.println("Drop SituMap");
		myagent.mapSitu.clear();
		computed = false;
		
		//Drop dans la classe statique prolog pour le traitement des données
		if(myagent.getLocalName().equals("Player1")){
			PrologCallsThink.mapSituPlayer = null;
		}
		if(myagent.getLocalName().equals("Player2")){
			PrologCallsThink.mapSituEnemy = null;
		}
	}

	/***
	 * Return the first unset orientation key, null if all keys are set
	 * 
	 * */
	private synchronized LegalActions.Orientation mapSituHasEmptyOrientation(){

		for(LegalActions.Orientation key : LegalActions.Orientation.values()){
			if(!myagent.mapSitu.containsKey(key))
				return key;
				
		}
		
		return null;
	}
	
	
	
}
