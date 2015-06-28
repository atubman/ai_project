package ravensproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AnswerFilter {
	public static final String SHAPE = "shape";
	public static final String FILL = "fill";
	public static final String SIZE = "size";
	HashMap<String, MiniRavensObject> shapeLibrary;
	private HashMap<String, RavensFigure> itsProblems;
	private ArrayList<RavensFigure> itsAnswers; 
	public AnswerFilter(){
		 shapeLibrary = new HashMap<>();
	}
	public ArrayList<RavensFigure> FilterAnsweers
			(ArrayList<RavensFigure> origAnswers, 
			HashMap<String, RavensFigure> problems ){
		
		setData(origAnswers, problems);
		
		RemoveIncorrectObjNumber();
		removeIntroducedShapes();
		ArrayList<RavensFigure> itsAccum = accumulateTrack();
		System.out.println("THERE ARE STILL " + itsAccum.size() +" REMOVED!" );
		if(itsAccum.size() != 0){
			itsAnswers = itsAccum;
		}
		return itsAnswers;
	}
	public void setData(ArrayList<RavensFigure> origAnswers, 
			HashMap<String, RavensFigure> problems ){
		itsAnswers = origAnswers;
		itsProblems = problems;
		
	}
	
	//The theory here is if C and F or G and H have the
	//same amount of figures, the Answer should have that amount as well.
	public void RemoveIncorrectObjNumber(){
		int expectedSize = -1;
		if(itsProblems.get("C").getObjects().size() == 
				itsProblems.get("F").getObjects().size()){
			expectedSize = itsProblems.get("C").getObjects().size();
		}
		else if(itsProblems.get("G").getObjects().size() == 
				itsProblems.get("H").getObjects().size()){
			expectedSize = itsProblems.get("G").getObjects().size();
		}
		if (expectedSize != -1){
			for(Iterator<RavensFigure> ans = itsAnswers.iterator(); ans.hasNext();){
				RavensFigure tempy = ans.next();
				if(tempy.getObjects().size() != expectedSize){
					ans.remove();
				}
			}
		}
	}
	//If the given frames are accumulating the same objects, 
	//we can remove any answers with the wrong number of those objects
	public ArrayList<RavensFigure> accumulateTrack(){
		
		ArrayList<RavensFigure>accumAnswers = new ArrayList<>(itsAnswers);
		RavensFigure cFig = itsProblems.get("C");
		RavensFigure fFig = itsProblems.get("F");

		RavensFigure gFig = itsProblems.get("G");
		RavensFigure hFig = itsProblems.get("H");

		
		//if c and f are all the same object, and theres an accumulation
		if(cFig.getObjects().size() == fFig.getObjects().size() ||
				gFig.getObjects().size() == hFig.getObjects().size()){
			return accumAnswers;
		} 
		//Hashmap < attribute, value> , number hits.
		HashMap<String, Integer>  ObjectLibC = getClones(cFig);
		HashMap<String, Integer>  ObjectLibF = getClones(fFig);
		HashMap<String, Integer>  ObjectLibG = getClones(gFig);
		HashMap<String, Integer>  ObjectLibH = getClones(hFig);
		
		HashMap<String, Integer> accumCF = getAccumulations(ObjectLibC, ObjectLibF);
		HashMap<String, Integer> accumGH = getAccumulations(ObjectLibG, ObjectLibH);
		
		for (String key : shapeLibrary.keySet()){
			System.out.println(key + ":" 
		+ shapeLibrary.get(key).getItsShape() + ":" + shapeLibrary.get(key).getItsSize());
		}
		
	   for (Iterator<RavensFigure> it = accumAnswers.iterator(); it.hasNext();){
		   RavensFigure potAnswer = it.next();
		   boolean expectedAccum1 = ExpectedAccumulation(potAnswer, accumCF,ObjectLibF);
		   boolean expectedAccum2 = ExpectedAccumulation(potAnswer, accumGH,ObjectLibH);
		   
		   if(!expectedAccum1 && ! expectedAccum2){
			   it.remove();
		   }
	   }
	   
		return accumAnswers;
		
	}
	
	
	
	private boolean ExpectedAccumulation(RavensFigure potAnswer,
			HashMap<String, Integer> accumExpected, HashMap<String, Integer> prevFig) {
		
		HashMap<String, Integer> answerClones = getClones(potAnswer);
		boolean expected = true;
		for (String accumKey : accumExpected.keySet()){
			if(!answerClones.containsKey(accumKey)){
				return false;
			}
			if(answerClones.get(accumKey) != (prevFig.get(accumKey) + accumExpected.get(accumKey))){
				return false;
			}
			
		}
		return expected;
	}
	private HashMap<String, Integer> getAccumulations(
			HashMap<String, Integer> objectLibA,
			HashMap<String, Integer> objectLibB) {

		HashMap<String, Integer> accumulation = new HashMap<>();
		for (String aObj : objectLibA.keySet()){
			if(objectLibB.containsKey(aObj)){
				accumulation.put(aObj, objectLibB.get(aObj) - objectLibA.get(aObj) );
			}
		}
		
		return accumulation;
	}
	public HashMap<String, Integer> getClones(RavensFigure fig) {
		HashMap<String, Integer> itsCount = new HashMap<>();
		for (RavensObject obj : fig.getObjects().values()) {
			// does the object exist in our lib?
			boolean libFound = false;
			String foundName = "";
			MiniRavensObject iterObj = new MiniRavensObject(obj);
			for (String libby : shapeLibrary.keySet()) {
				MiniRavensObject tempMRO = shapeLibrary.get(libby);
				if (iterObj.equalsOther(tempMRO)) {
					libFound = true;
					foundName = libby;
					break;
				}

			}
			if (libFound) {
				// Shape is in library, tally it.
				if (itsCount.containsKey(foundName)) {
					int oldCount = itsCount.get(foundName);
					itsCount.put(foundName, oldCount + 1);

				} else {
					itsCount.put(foundName, 1);
				}
			} else {
				String elemNum = shapeLibrary.size() + "";
				shapeLibrary.put(elemNum, iterObj);
				itsCount.put(elemNum, 1);
			}

		}
//		for (String key : itsCount.keySet()) {
//			System.out.println(key + ":" + itsCount.get(key));
//
//		}
//		System.out.println("-----------------");
		return itsCount;
	}
	//The answers will never add a new shape that hasn't been seen before
	public void removeIntroducedShapes(){
		PopulateLib();
		for(Iterator<RavensFigure> ansIter = itsAnswers.iterator(); ansIter.hasNext(); ){
			RavensFigure answerFig = ansIter.next();
			for(RavensObject  answerObj : answerFig.getObjects().values()){
				MiniRavensObject tempMiniAnsObj = new MiniRavensObject(answerObj);
				boolean found = false;
				for(MiniRavensObject libObj : shapeLibrary.values()){
					if(libObj.equalsOtherNoSize(tempMiniAnsObj)){
						found = true;
						break;
					}
				}
				if(!found){
					System.out.println("Found an extra shape in answer " + answerFig.getName() + "--removed");
					ansIter.remove();
					break;
				}
			}
		}
	}
	
	public void PopulateLib(){
		for(RavensFigure theFig : itsProblems.values()){
			for(RavensObject theObj : theFig.getObjects().values()){
				MiniRavensObject tempRaven = new MiniRavensObject(theObj);
				boolean found = false;
				for(MiniRavensObject libObj : shapeLibrary.values()){
					try{
					if(libObj.equalsOther(tempRaven)){
						//i's already libbed.
						found = true;
						break;
					}
					} catch(Exception ex){
						System.out.println(ex.getStackTrace());
					}
				}
				if(!found){
					shapeLibrary.put(shapeLibrary.size() +"" , tempRaven);
				}
				
			}
		}
	}
}
