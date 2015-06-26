package ravensproject;

import java.util.ArrayList;
import java.util.HashMap;


// Uncomment these lines to access image processing.
//import java.awt.Image;
//import java.io.File;
//import javax.imageio.ImageIO;

/**
 * Your Agent for solving Raven's Progressive Matrices. You MUST modify this
 * file.
 * 
 * You may also create and submit new files in addition to modifying this file.
 * 
 * Make sure your file retains methods with the signatures:
 * public Agent()
 * public char Solve(RavensProblem problem)
 * 
 * These methods will be necessary for the project's main method to run.
 * 
 */
public class Agent {
    /**
     * The default constructor for your Agent. Make sure to execute any
     * processing necessary before your Agent starts solving problems here.
     * 
     * Do not add any variables to this signature; they will not be used by
     * main().
     * 
     */
	
	RavensUtils itsUtils;
    public Agent() {
        itsUtils = new RavensUtils();
    }
    /**
     * The primary method for solving incoming Raven's Progressive Matrices.
     * For each problem, your Agent's Solve() method will be called. At the
     * conclusion of Solve(), your Agent should return a String representing its
     * answer to the question: "1", "2", "3", "4", "5", or "6". These Strings
     * are also the Names of the individual RavensFigures, obtained through
     * RavensFigure.getName().
     * 
     * In addition to returning your answer at the end of the method, your Agent
     * may also call problem.checkAnswer(String givenAnswer). The parameter
     * passed to checkAnswer should be your Agent's current guess for the
     * problem; checkAnswer will return the correct answer to the problem. This
     * allows your Agent to check its answer. Note, however, that after your
     * agent has called checkAnswer, it will *not* be able to change its answer.
     * checkAnswer is used to allow your Agent to learn from its incorrect
     * answers; however, your Agent cannot change the answer to a question it
     * has already answered.
     * 
     * If your Agent calls checkAnswer during execution of Solve, the answer it
     * returns will be ignored; otherwise, the answer returned at the end of
     * Solve will be taken as your Agent's answer to this problem.
     * 
     * @param problem the RavensProblem your agent should solve
     * @return your Agent's answer to this problem
     */
    public int Solve(RavensProblem problem) {
    	System.out.println("Solving " + problem.getName() +  problem.getProblemType());
    	System.out.println("================================");
    	if(problem.getName().contains("10")){
    		//for setting breakpoints based on problem name...
    		System.out.println("Breaky Breaky, eggs and Bakey");
    	}
    	int answer =0;
        if(problem.getProblemType().equals("3x3")) {
        	answer = solve3x3(problem);
		} else{
				answer= Solve2x2(problem);
				}
        System.out.println("================================");
        return answer;
        
    }
    private int solve3x3(RavensProblem problem) {
		// TODO Auto-generated method stub
    	
    	HashMap<String, RavensFigure> problems = new HashMap<>();
    	
    	problems.put("A", problem.getFigures().get("A"));
    	problems.put("B", problem.getFigures().get("B"));
    	problems.put("C", problem.getFigures().get("C"));
    	problems.put("D", problem.getFigures().get("D"));
    	problems.put("E", problem.getFigures().get("E"));
    	problems.put("F", problem.getFigures().get("F"));
    	problems.put("G", problem.getFigures().get("G"));
    	problems.put("H", problem.getFigures().get("H"));
        
        ArrayList<RavensFigure> answerList = new ArrayList<>();
        
        
        answerList.add(problem.getFigures().get("1"));
        answerList.add(problem.getFigures().get("2"));
        answerList.add(problem.getFigures().get("3"));
        answerList.add(problem.getFigures().get("4"));
        answerList.add(problem.getFigures().get("5"));
        answerList.add(problem.getFigures().get("6"));
        answerList.add(problem.getFigures().get("7"));
        answerList.add(problem.getFigures().get("8"));
        
        HashMap<String, RavensObject> objA = problems.get("A").getObjects();
        HashMap<String, RavensObject> objB = problems.get("B").getObjects();
        HashMap<String, RavensObject> objC = problems.get("C").getObjects();
        HashMap<String, RavensObject> objD = problems.get("D").getObjects();
        HashMap<String, RavensObject> objE = problems.get("E").getObjects();
        HashMap<String, RavensObject> objF = problems.get("F").getObjects();
        HashMap<String, RavensObject> objG = problems.get("G").getObjects();
        HashMap<String, RavensObject> objH = problems.get("H").getObjects();
        
       Transformation ABtrans= new Transformation(problems.get("A"), problems.get("B"));
        Transformation BCtrans= new Transformation(problems.get("H"), answerList.get(0));
//        Transformation GHtrans= new Transformation(problems.get("G"), problems.get("H"));
        ArrayList<HashMap<String, String>> result = ABtrans.compareTo(BCtrans);
        
        for(HashMap <String,String> theMap : result){
        	for(String key : theMap.keySet()){
        		System.out.println(key + ":" + theMap.get(key));
        	}
        	System.out.println("=================");
        }
        return 0;
    }
	public int Solve2x2(RavensProblem problem){
        
    	RavensFigure FigA = problem.getFigures().get("A");
        RavensFigure FigB = problem.getFigures().get("B");
        RavensFigure FigC = problem.getFigures().get("C");
        
        ArrayList<RavensFigure> answerList = new ArrayList<>();
        
        
        answerList.add(problem.getFigures().get("1"));
        answerList.add(problem.getFigures().get("2"));
        answerList.add(problem.getFigures().get("3"));
        answerList.add(problem.getFigures().get("4"));
        answerList.add(problem.getFigures().get("5"));
        answerList.add(problem.getFigures().get("6"));
        
//        
        //first, lest find the partners between A and B squares. 
        HashMap<String, RavensObject> objA = FigA.getObjects();
        HashMap<String, RavensObject> objB = FigB.getObjects();
        HashMap<String, RavensObject> objC = FigC.getObjects();
        
        //Lets take a good guess at which objects in each figure are partners by finding ones with the least differences.
        System.out.println("Finding partners between " + FigA.getName() + " and " + FigB.getName());
        HashMap<String,String > ABPpartners = FindPartners(objA,objB);
        HashMap<String,String > ACpartners = FindPartners(objA,objC);
        
        
        // now lets find partners for C and it's potential solutions.
        ArrayList<RavensFigurePartners> cPartners = new ArrayList<>();
        for(RavensFigure posSolution : answerList){
        	if(objA.size() == objB.size() && objA.size() == objC.size() && posSolution.getObjects().size() != objC.size()){
        		System.out.println("Solution " + posSolution.getName() + " Discarded due to wrong number of objects");
        		
        		//frames A-C have the same amount of objects. the answer should too.
        	} else
        	{
	        	System.out.println("Finding partners between " + FigC.getName() + " and " + posSolution.getName());
	        	RavensFigurePartners tempPartners = new RavensFigurePartners(FigC.getName(), posSolution.getName());
	        	tempPartners.setItsPartners(FindPartners(FigC.getObjects(), posSolution.getObjects()));
	        	cPartners.add(tempPartners);
        	}
        }
        //we are now looking for the same set of transitions between the two.
        HashMap<String, Integer> SolutionScore = new HashMap<>();
        for(RavensFigurePartners CPotPart : cPartners){
        	System.out.println("Answer " + CPotPart.getNameObj2());
        	int answerScore = 0;
        	//This is skipping any figures added to C
        	for(String AFigure : ACpartners.keySet()){
        		
        		String BFigure = ABPpartners.get(AFigure);
        		String CFigure = ACpartners.get(AFigure);
        		//the name of the object.
        		
        		String DFigure = CPotPart.getItsDiffs().get(CFigure);
        		if(CFigure.equals("added")){
        			DFigure = CPotPart.getItsDiffs().get(AFigure);
        			RavensObject dRavensObject = problem.getFigures().get(CPotPart.getNameObj2()).getObjects().get(DFigure);
        			System.out.println("Added " + objC.get(AFigure).getName()+ "-->" + dRavensObject.getName());
        			int diffSize = itsUtils.DiffRavensObjects(objC.get(AFigure), dRavensObject).getR().size();
        			answerScore +=diffSize;
        		}
        		else if(!DFigure.equals("removed") && !BFigure.equals("removed")){
        		RavensObject dRavensObject = problem.getFigures().get(CPotPart.getNameObj2()).getObjects().get(DFigure);
        		
        		System.out.println("Comparing " + AFigure + "-->" + BFigure + " To " + CFigure + "-->" +dRavensObject.getName());
        			HashMap<String, String> ABDiffs = itsUtils.DiffRavensObjects(objA.get(AFigure), objB.get(BFigure)).getR();
        			HashMap<String, String> CDDiffs = itsUtils.DiffRavensObjects(objC.get(CFigure), dRavensObject).getR();
        			answerScore += DiffTheDiffs(ABDiffs,CDDiffs);
        		}
        		else if((BFigure.equals("removed" ) && DFigure.equals("removed"))){
        			System.out.println("the object dissapeared!");
        		}else if(BFigure.equals("removed")){
        			System.out.println("The object only dissapeared from B!");
        			answerScore++;
        			
        		}else{
        			System.out.println("The object only dissapeared from solution!");
//        			answerScore +=2; //an object dissapearing is a major issue....
        		}
        	}
        	SolutionScore.put(CPotPart.getNameObj2(), answerScore);
        }
        int lowestScore =999;
        String solution ="";
        for(String ans : SolutionScore.keySet()){
        	System.out.println("Solution " + ans + "has a score of " +SolutionScore.get(ans));
        	if(SolutionScore.get(ans) < lowestScore){
        		lowestScore = SolutionScore.get(ans);
        		solution = ans;
        	}
        }
        int solInt = Integer.parseInt(solution);
        problem.setAnswerReceived(solInt);
        
        System.out.println("[Actual,Guess] : [" + solInt + "," + problem.checkAnswer(solInt) + "] " + problem.getCorrect());
        
        
		return solInt;
        
        
        
    }
    
	private int DiffTheDiffs(HashMap<String, String> aBDiffs,
			HashMap<String, String> cDDiffs) {
		int diffAttributes = 0;
		for(String atrib : aBDiffs.keySet()){
			if(cDDiffs.containsKey(atrib)){
				if(!aBDiffs.get(atrib).equals(cDDiffs.get(atrib))){
					System.out.println(atrib + ": " + aBDiffs.get(atrib) + "!=" +cDDiffs.get(atrib));
					diffAttributes++;
					
				}
			} else{ // an attribute was removed, add a difference.
				diffAttributes++;
			}
			
		}
		for(String attrib : cDDiffs.keySet()){
			if(!aBDiffs.containsKey(attrib)){
				diffAttributes++;
			}
		}
		return diffAttributes;
	}
	private HashMap<String, String> FindPartners(
			HashMap<String, RavensObject> objA,
			HashMap<String, RavensObject> objB) {

		HashMap<String, String> partners = new HashMap<>();
		for(String aName  : objA.keySet()){
			HashMap<String, Integer> potentialMatch = new HashMap<>();
			//record all the diffs
			for(String bName : objB.keySet()){
				HashMap<String, String > diffs = itsUtils.DiffRavensObjects(objA.get(aName), objB.get(bName)).getR();
				potentialMatch.put(bName,diffs.size() );
				
			}
			//find the least differences.
			//We will use the first one found in most cases, not the best method, so it will lower our confidence
			int minDiff = 9999;
			String potentialPotential = "";
			for(String potent : potentialMatch.keySet()){
				if(potentialMatch.get(potent) < minDiff){
					potentialPotential = potent;
					minDiff = potentialMatch.get(potent);
				}
			}
			if(partners.values().contains(potentialPotential)){
				//someone has already been paired with this one
				//is there a second potential with same amount of diffs?
				//Hashmaps arn't gurenteed ordered, so we're pushing our luck, it will have to do for now.
				//TODO: reduce confidence!
				int count = 0;
				for( int val : potentialMatch.values()){
					if(val == minDiff){
						count++;
					}
				}
				if (count >1){
					//we have multiple matches! pick the second one.
					for(String newMatch : potentialMatch.keySet()){
						if(newMatch.compareTo(potentialPotential) != 0 && potentialMatch.get(newMatch) == minDiff){
							partners.put(aName, newMatch);
							System.out.println("*Partners [ " + aName + "," + newMatch + "]");
							break;
						}
					}
				} else{
					partners.put(aName, "removed");
					System.out.println("Partners [ " + aName + ", removed]");
				}
			}else 
			{//no matches already
				partners.put(aName, potentialPotential);
				System.out.println("Partners [ " + aName + "," + potentialPotential + "]");
			}
			
			
			
			
			
			
		}
		//if any figures from B were not matched, we can guess that they were added and dont have a partner.
		for (String bObj : objB.keySet()){
			if(!partners.values().contains(bObj)){
				System.out.println("Partners [ " + bObj + ",added]");
				partners.put(bObj, "added");
			}
		}
		return partners;
	}
	
}
