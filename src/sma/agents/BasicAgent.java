package sma.agents;

import com.jme3.math.Vector3f;

import env.jme.Environment;
import sma.AbstractAgent;
import sma.actionsBehaviours.RandomWalkBehaviour;
import sma.actionsBehaviours.WalkBehaviour;

public class BasicAgent extends AbstractAgent {
	/**
	 *
	 */
	private static final long serialVersionUID = 7545160765928961044L;
	
	/**
	 * True to create a friend, false otherwise 
	 */
	public boolean friendorFoe;
	
	public RandomWalkBehaviour randomWalk;
	public WalkBehaviour walk ;
	
	
	// Pour randomWalk
		private Vector3f positionPrecedente = null;
		private int nbIterationBloque = 0;
		
	
	
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

		randomWalk = new RandomWalkBehaviour(this);
		walk = new WalkBehaviour(this);
		
		//addBehaviour(randomWalk);
		addBehaviour(walk);
		
		System.out.println("the player "+this.getLocalName()+ " is started. Tag (0==enemy): " + friendorFoe);
		
	}
	

	
	public Vector3f getPositionPrecedente(){
		if (this.positionPrecedente == null){
			Vector3f a = getCurrentPosition().clone();
			setPositionPrecedente(a);
		}
		return this.positionPrecedente;
	}
	
	public void setPositionPrecedente(Vector3f pos){
		this.positionPrecedente = pos;
	}
	
	public int getnbIterationBloque(){
		return nbIterationBloque;
	}
	
	public void majnbIterationBloque(){

		System.out.println(approximativeEqualsCoordinates(getCurrentPosition(), getPositionPrecedente()));

		if (approximativeEqualsCoordinates(getCurrentPosition(), getPositionPrecedente()) ){
			nbIterationBloque ++;
		}
		if (nbIterationBloque > 20)
		{
			nbIterationBloque = 0;
			setPositionPrecedente(null);
		}
		System.out.println(nbIterationBloque);
		System.out.println(getPositionPrecedente());
		System.out.println(getCurrentPosition());
		
		setPositionPrecedente(getCurrentPosition().clone());

	}
	

	
	private boolean approximativeEqualsCoordinates(Vector3f a, Vector3f b) {
		return approximativeEquals(a.x, b.x) && approximativeEquals(a.z, b.z);
	}
	
	private boolean approximativeEquals(float a, float b) {
		return b-1 <= a && a <= b+1;
	}	
	
}
