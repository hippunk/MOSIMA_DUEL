package prolog;


import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;


public class PrologCalls {

	public static int succesRate=50;
	
//	public PrologCalls(){
//		super();
//		System.out.println("Instance created!");
//	};
	
	public static void test(){
		System.out.println("Test called :)");
	}
	
	/**
	 * 
	 * @param fisherman Name of the fisherman
	 * @param fish Name of the fish
	 * @return true if the fisherman hooked the fish. 
	 */
	public static boolean hooked(String fisherman,String fish){
		Random r=new Random();
		//theoretically, the function should check in the environment that the conditions for the fish to be hooked are met.  
		int x=r.nextInt(100);
		System.out.println("Hooked function triggered; succesRate = "+succesRate+"; v= "+x);
		return (x<succesRate) ? true: false;
	}
	
	
	public static boolean shoot( String x, String y){
		return true;
	}
	
	public static void main(String []args){
	 
		System.out.println("Trigerring Java-JPL-Java loop");
		//System.out.println(""+System.getProperty("user.dir"));
		
		//unexplicit loading of the file
		String query = "consult('./ressources/prolog/test/fishing.pl')";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		System.out.println("**Test 1**");
		query="fish(tom)";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		System.out.println("**Test 2**");
		query="fish(maurice)";
		System.out.println(query+" ?: "+Query.hasSolution(query));

		System.out.println("**Test 3**");
		query="caught(maurice,tom)";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		System.out.println("**Test 4**");
		query="caught(tom,maurice)";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		
		System.out.println("**Test 5**");
	    Variable X = new Variable("X");
		Term arg[] = { X };
		Query    q = new Query("fish", arg);
		q.open();
		Map<String, Term> soln = q.getSolution();
		for(Map.Entry<String, Term> entry : soln.entrySet()) {
		    String key = entry.getKey();
		    Term value = entry.getValue();
		    System.out.println("Key : "+key+" value "+value);
		}
		System.out.println(q.getSolution());
		System.out.println(soln);
		q.close();

	
		System.out.println("passed");
		
		/*System.out.println("/////////////////////////////////////////");
		
		query = "consult('./ressources/prolog/test/regle.pl')";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		
		System.out.println("**Test 1**");
		query="vivant(player)";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		System.out.println("**Test 2**");
		query="mort(player)";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		System.out.println("**Test 3**");
		query="victoire(player,enemy)";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		System.out.println("**Test 4**");
		query="blesse(player)";
		System.out.println(query+" ?: "+Query.hasSolution(query));
		
		System.out.println("**Test 5**");
		query="blesse(enemy)";
		System.out.println(query+" ?: "+Query.hasSolution(query));*/
		
		
	}
//
//		
//		Query q1 =
//	            new Query(
//	                "consult",
//	                new Term[] {new Atom("test.pl")}
//	            );
//	    
//	    
//	}    
	
}
