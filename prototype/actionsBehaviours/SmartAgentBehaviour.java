package sma.actionsBehaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import prolog.CallsSmart;
import prolog.PrologCallsThink;
import sma.AbstractAgent;
import sma.actionsBehaviours.LegalActions.LegalAction;
import sma.actionsBehaviours.LegalActions.Orientation;
import sma.agents.SmartAgent;
import sma.agents.ThinkAgent;

public class SmartAgentBehaviour extends TickerBehaviour {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -31619351753275981L;
	private int observeTimeout = 9;
	private int observecpt = 0;
	private SmartAgent myagent; 
	
	
	public SmartAgentBehaviour(final AbstractAgent a) {
		super(a, 200);
		myagent = (SmartAgent)a;
		if (myagent.getLocalName().equals("Player1"))
			CallsSmart.player = myagent;
		else
			CallsSmart.enemy = myagent;
		
		//myagent.stopMove();
		
	}

	@Override
	protected void onTick() {
		// TODO Auto-generated method stub
		checkObserveTimeout(); //On drop les connaissances tous les observeTimeout ticks et l'agent s'arête de bouger
		CallsSmart.computeProlog(myagent.getLocalName());

	}
	
	private synchronized void checkObserveTimeout(){
		if(observecpt >= observeTimeout-1){
			myagent.dropMapSitu();
			//myagent.stopMove();
			observecpt = 0;
		}
		else{
			observecpt++;
		}
	}
	


}
