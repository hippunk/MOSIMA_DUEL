package prologTest;

import java.util.Hashtable;
import java.util.Map;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

public class AgentPrologDebug {
	
	public static boolean test(){
		System.out.println("Test passage");
		return false;
	}
	
	public static String me(){
		return "player";
	}
	
	public static String him(){
		return "enemy";
	}
	
	/*public static boolean check(String x,String y, String z){
		
	}
	
	public static boolean view(String x,String y, String z){
		
	}
	
	public static boolean hunt(String x,String y, String z){
		
	}
	
	public static boolean move(String z){
		
	}*/
		
	
	public static void main(String argv[]) {
		System.out.print("Loading File...");
		//loading the file
		String plFile = "consult('ressources/prolog/test/pltest.pl')";
		if (!Query.hasSolution(plFile)) {
			System.out.println(plFile + " failed");
			System.exit(1);
		}
		System.out.println("passed");
		
		System.out.println("**Test 5**");
	    Variable X = new Variable("X");
		Term arg[] = { X };
		Query    q = new Query("me",arg);
		
		System.out.println("var : "+X+" q : "+q);
		
		if(q.hasSolution()){
			q.open();
			Map<String, Term> soln = q.getSolution();
			for(Map.Entry<String, Term> entry : soln.entrySet()) {
			    String key = entry.getKey();
			    Term value = entry.getValue();
			    System.out.println("Key : "+key+" value "+value);
			}
		}
		else
			System.out.println("false");

		q.close();
		
		/*q.open();
		Map<String, Term>  solution = q.nextSolution();
		System.out.println( "X = " + solution.get("X"));*/

	}
}
