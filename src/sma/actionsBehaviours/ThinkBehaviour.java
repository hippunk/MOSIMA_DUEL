package sma.actionsBehaviours;

import java.util.Map;
import java.util.Set;

import env.jme.Situation;
import jade.core.behaviours.TickerBehaviour;
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
		
	/*******************************************/
	
	
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
		
		//Compute : Avec le prolog :)
		
		
	}
	
	private void computeDatas(){
		
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
