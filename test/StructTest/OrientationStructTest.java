package StructTest;

import java.util.EnumMap;
import java.util.Map;

import env.jme.Situation;
import sma.actionsBehaviours.LegalActions;
import sma.agents.ThinkAgent;

public class OrientationStructTest {

	private static Map<LegalActions.Orientation,Integer> mapSitu;
	private static int observeTimeout = 20;
	private static int observecpt = 0;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 mapSitu = new EnumMap<LegalActions.Orientation,Integer>(LegalActions.Orientation.class);
		/*mapSitu.put(LegalActions.Orientation.LOOKTO_NORTH, 0);
		mapSitu.put(LegalActions.Orientation.LOOKTO_NORTHEAST, 0);
		mapSitu.clear();*/
		 
		 
			// TODO Auto-generated method stub
			for(int i = 0;i<100000;i++){ //Daemon test
				//Si timeout atteint clear map
				checkObserveTimeout();
				//Si pas complètement observé continuer
				fillMapSitu();
				
				System.out.println(mapSitu); //Print debug
			}
				
	}
	
	private static void fillMapSitu(){
		LegalActions.Orientation key = mapSituHasEmptyOrientation();
		if(key != null){
			mapSitu.put(key, 1);
		}
	}
	
	private static void checkObserveTimeout(){
		if(observecpt >= observeTimeout){
			dropMapSitu();
			observecpt = 0;
		}
		else{
			observecpt++;
		}
	}
	
	
	/***
	 * Clear the mapSitu enumMap
	 * */
	private static void dropMapSitu(){
		mapSitu.clear();
	}

	/***
	 * Return the first unset orientation key, null if all keys are set
	 * 
	 * */
	private static LegalActions.Orientation mapSituHasEmptyOrientation(){

		for(LegalActions.Orientation key : LegalActions.Orientation.values()){
			if(!mapSitu.containsKey(key))
				return key;
				
		}
		
		return null;
	}

}
