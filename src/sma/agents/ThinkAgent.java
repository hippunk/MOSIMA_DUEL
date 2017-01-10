package sma.agents;


import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import env.jme.Environment;
import env.jme.Situation;
import sma.AbstractAgent;
import sma.actionsBehaviours.LegalActions;
import sma.actionsBehaviours.LegalActions.Orientation;
import sma.actionsBehaviours.ThinkBehaviour;


public class ThinkAgent extends AbstractAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7469387968066432059L;
	public boolean friendorFoe;
	public ThinkBehaviour think;
	public Map<Orientation,Situation> mapSitu; //Contient les données de la dernière observation complète autour de l'agent
	public String type; // Agent or Enemy
	
	protected void setup(){
		super.setup();
		mapSitu = new EnumMap<Orientation,Situation>(Orientation.class);

		//get the parameters given into the object[]. In the current case, the environment where the agent will evolve
		final Object[] args = getArguments();
		if(args[0]!=null && args[1]!=null){
			
			this.friendorFoe = ((boolean)args[1]);
			
			if (friendorFoe) {
				deployAgent((Environment) args[0]);
				type = "Agent";
			} else {
				deployEnemy((Environment) args[0]);
				type = "Enemy";
			}
			
		}else{
			System.err.println("Malfunction during parameter's loading of agent"+ this.getClass().getName());
			System.exit(-1);
		}
		
		think = new ThinkBehaviour(this);
		
		addBehaviour(think);
		
		System.out.println("the player "+this.getLocalName()+ " is started. Tag (0==enemy): " + friendorFoe);		
	}

}
