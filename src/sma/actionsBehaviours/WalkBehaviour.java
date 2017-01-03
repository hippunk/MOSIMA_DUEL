package sma.actionsBehaviours;

import com.jme3.math.Vector3f;

import env.jme.PlayerControl;
import jade.core.behaviours.TickerBehaviour;
import sma.AbstractAgent;
import sma.agents.BasicAgent;


public class WalkBehaviour extends TickerBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WalkBehaviour(final AbstractAgent myagent) {
		// TODO Auto-generated constructor stub
		super(myagent, 200);
	}

	@Override
	protected void onTick() {
		// TODO Auto-generated method stub
		Vector3f currentpos  =  ((AbstractAgent) this.myAgent).getCurrentPosition();
		Vector3f dest = ((AbstractAgent) this.myAgent).getDestination();
		( (BasicAgent) this.myAgent).majnbIterationBloque();

		if (dest==null || approximativeEqualsCoordinates(currentpos, dest) || (((BasicAgent)this.myAgent).getnbIterationBloque() >= 20)) {
			((AbstractAgent)this.myAgent).randomMove();
			String enemy = "";
			if (this.myAgent.getLocalName().equals("Player1")) {
				enemy = "Player2";
			}
			else {
				enemy = "Player1";
			}
			
			System.out.println("OOOOOOOOOOOOOOOOOOOKKKKKKKKKKKKKKKKKKKKKKKKKK");

			((AbstractAgent)this.myAgent).randomAction(enemy);
			
			//((AbstractAgent)(this.myAgent)).observeAgents();
		}
		//PrologCalls.main(null);

		
	}
	
	private boolean approximativeEqualsCoordinates(Vector3f a, Vector3f b) {
		return approximativeEquals(a.x, b.x) && approximativeEquals(a.z, b.z);
	}
	
	private boolean approximativeEquals(float a, float b) {
		return b-2.5 <= a && a <= b+2.5;
	}

}
