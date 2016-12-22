package prolog;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.Query;
import org.jpl7.Term;

public class PrologCalls_vWill {

	
	public static boolean hooked(String x,String y){
		//Term pecheur = new Atom(x);
		//Term poisson = new Atom(y);
		Term pecheur = new Atom(x);
		Term poisson = new Atom(y);
		Term peche = new Compound("fishing", new Term[]{pecheur});
		Query q1 = new Query("consult", new Term[] {new Atom("./ressources/prolog/test/fishing.pl")} );	
		if (!q1.hasSolution()){
			System.out.println("fichier pas chargé");
		}
		
	
		
		if (new Query("fish",poisson).hasSolution() && new Query("fisherman",pecheur).hasSolution() &&  new Query("free", poisson).hasSolution() &&  new Query(peche).hasSolution()){
			int rand = (int) (Math.random()*100);
			System.out.println(rand);
			if (rand < 20){
				return true;
				
			}
		
		}
		
		return false;
	}
	
	
	public static void main (String args[]){
		PrologCalls_vWill a = new PrologCalls_vWill();
		hooked("tom", "maurice");
	}
	
}
