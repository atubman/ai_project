package ravensproject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;


// the old agent was getting very cluttered, starting fresh.
public class VisualAgent {
	RavensProblem itsProblem;
	VisualTools itsVisualTools;
	PatternFinder itsPatternFinder;
	HashMap<String, BufferedImage> figures;
	HashMap<String, BufferedImage> solutions;
	
	public VisualAgent(RavensProblem problem){
		itsProblem = problem;
		itsVisualTools = new VisualTools();
		itsPatternFinder = new PatternFinder();
		figures = new HashMap<>();
		solutions = new HashMap<>();
	}
		public int Solve3x3(){
		
	    	
	    	
	    	try {
			figures.put("A", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("A").getVisual()))));
	    	figures.put("B", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("B").getVisual()))));
	    	figures.put("C", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("C").getVisual()))));
	    	figures.put("D", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("D").getVisual()))));
	    	figures.put("E", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("E").getVisual()))));
	    	figures.put("F", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("F").getVisual()))));
	    	figures.put("G", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("G").getVisual()))));
	    	figures.put("H", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("H").getVisual()))));
	    		//DisplayImage.displayImage(figures.get("A"));
			

	    	
	    	solutions.put("1", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("1").getVisual()))));
	    	solutions.put("2", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("2").getVisual()))));
	    	solutions.put("3", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("3").getVisual()))));
	    	solutions.put("4", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("4").getVisual()))));
	    	solutions.put("5", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("5").getVisual()))));
	    	solutions.put("6", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("6").getVisual()))));
	    	solutions.put("7", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("7").getVisual()))));
	    	solutions.put("8", itsVisualTools.toBW(ImageIO.read(new File(itsProblem.getFigures().get("8").getVisual()))));
	    	
	    	
	    	
	    	
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	PixelRatioPattern();
	    	int ansRemoved = 8-solutions.size();
	    	System.out.println("Removed " + ansRemoved + " solutions" );
	    	if(ansRemoved >= 4){
	    		printSolutions();
	    		int BadGuess = (int) ((Math.random()*10)%solutions.size());
	    		System.out.println("BAD GUESS:" + BadGuess);
	    		return Integer.parseInt((String)solutions.keySet().toArray()[BadGuess]);
	    	}
	    	return 0;
	    	
		}
		
		//Identifys if the pixel ratio pattern applies, and remove any anwers it doesnt;
		public void PixelRatioPattern(){
			//row 1
			
			if(itsProblem.getName().contains("12")){
				//DisplayImage.displayImage(itsVisualTools.cropFrame(figures.get("H")));
				
			}
			float aRatio = itsVisualTools.BWRatio(itsVisualTools.cropFrame(figures.get("A")));
			float bRatio = itsVisualTools.BWRatio(itsVisualTools.cropFrame(figures.get("B")));
			float cRatio = itsVisualTools.BWRatio(itsVisualTools.cropFrame(figures.get("C")));
			
			float gRatio = itsVisualTools.BWRatio(itsVisualTools.cropFrame(figures.get("G")));
			float hRatio = itsVisualTools.BWRatio(itsVisualTools.cropFrame(figures.get("H")));
			boolean patternApplies = false;
			
			
			if(itsVisualTools.CompareWithin( aRatio, bRatio,5) == 0){
				if(itsVisualTools.CompareWithin( bRatio, cRatio,5) == 0){
					if(itsVisualTools.CompareWithin( gRatio, hRatio,5) == 0){
						//solution should have same ratio!
						Iterator<HashMap.Entry<String, BufferedImage>> iter = solutions.entrySet().iterator();
						for( ;iter.hasNext();){
							HashMap.Entry<String,BufferedImage> entry = iter.next();
							float solRatio = itsVisualTools.BWRatio(itsVisualTools.cropFrame(entry.getValue()));
							if(itsVisualTools.CompareWithin( hRatio, solRatio,5) != 0){
								iter.remove();
							}
						}
						
					}
					
				}
			}

		}

	public void printSolutions() {
		System.out.print("Remaining sol: ");
		for (String key : solutions.keySet()) {
			System.out.print(key + " ");

		}
		System.out.println();
	}
		
}
