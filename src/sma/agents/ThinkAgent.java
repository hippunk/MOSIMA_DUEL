package sma.agents;


import env.jme.Environment;
import sma.AbstractAgent;
import sma.actionsBehaviours.RandomWalkBehaviour;
import sma.actionsBehaviours.ThinkBehaviour;

public class ThinkAgent extends AbstractAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7469387968066432059L;
	public boolean friendorFoe;
	public ThinkBehaviour think;

	
	protected void setup(){
		super.setup();
		
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
		
		think = new ThinkBehaviour(this);
		addBehaviour(think);
		
		System.out.println("the player "+this.getLocalName()+ " is started. Tag (0==enemy): " + friendorFoe);		
	}

}
