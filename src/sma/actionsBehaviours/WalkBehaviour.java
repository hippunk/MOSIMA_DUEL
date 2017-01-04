package sma.actionsBehaviours;

import com.jme3.math.Vector3f;

import env.jme.PlayerControl;
import jade.core.behaviours.TickerBehaviour;
import sma.AbstractAgent;
import sma.actionsBehaviours.LegalActions.LegalAction;
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
		((BasicAgent)this.myAgent).setSitu(((AbstractAgent) this.myAgent).observeAgents());
		// TODO Auto-generated method stub
		
		Vector3f currentpos  =  ((AbstractAgent) this.myAgent).getCurrentPosition();
		Vector3f dest = ((AbstractAgent) this.myAgent).getDestination();
		( (BasicAgent) this.myAgent).majnbIterationBloque();
		System.out.println("DESSSSSSST" + dest);
		
		
		
		if (dest==null || approximativeEqualsCoordinates(currentpos, dest) || (((BasicAgent)this.myAgent).getnbIterationBloque() >= 20)) {
			String enemy = "";
			if (this.myAgent.getLocalName().equals("Player1")) {
				enemy = "Player2";
			}
			else {
				enemy = "Player1";
			}
			/**
			if ( approximativeEqualsCoordinates(currentpos, dest) || (((BasicAgent)this.myAgent).getnbIterationBloque() >= 20))
			((BasicAgent)this.myAgent).setWatchAndChooseDirection(true);
			else
				((BasicAgent)this.myAgent).setWatchAndChooseDirection(false);
**/
			
			if (((BasicAgent)this.myAgent).getWatchAndChooseDirection()){

				((BasicAgent)this.myAgent).watchAndChooseDirection();
				System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

				}
				else {
					
					
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
					dest = ((BasicAgent)this.myAgent).getMaxAltitudeObservee();
					//((AbstractAgent)this.myAgent).cardinalMove(   ((BasicAgent)this.myAgent).getChooseDirection() );
					((AbstractAgent)this.myAgent).moveTo( dest);
				}

			//((AbstractAgent)this.myAgent).randomAction(enemy);

			
		}
		
		
		
		
		System.out.println("/////////////////////" +  ((BasicAgent)this.myAgent).getMaxAltitudeObservee()  );
		System.out.println("/////////////////////" +  ((BasicAgent)this.myAgent).getWatchAndChooseDirection()  );

		//PrologCalls.main(null);
		
		
		
		
/*     PARTIE POUR RANDOM MOVE
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
*/
		
	}
	
	private boolean approximativeEqualsCoordinates(Vector3f a, Vector3f b) {
		return approximativeEquals(a.x, b.x) && approximativeEquals(a.z, b.z);
	}
	
	private boolean approximativeEquals(float a, float b) {
		return b-2.5 <= a && a <= b+2.5;
	}

}
