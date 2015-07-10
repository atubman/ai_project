package ravensproject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class VisualTools {
	public static final int WHITEHEX = 0xFFFFFFFF;
	public BufferedImage toBW(BufferedImage input){
	    BufferedImage orginalImage = input;

	    BufferedImage bwImage = new BufferedImage(
	        orginalImage.getWidth(), orginalImage.getHeight(),
	        BufferedImage.TYPE_BYTE_BINARY);
	    
	    Graphics2D graphics = bwImage.createGraphics();
	    graphics.drawImage(orginalImage, 0, 0, null);
	    graphics.dispose();
	    return bwImage;

	}
	//returns ratio of black to white pixles
	public float BWRatio(BufferedImage image){
		
		int black = 0;
		int width = image.getWidth();
		int height = image.getHeight();
		int white = 0;
		for (int i = 0; i < width; i++) 
		{
			for (int j = 0; j < height; j++) 
			{
				if (image.getRGB(i, j) < WHITEHEX)
				{
					black++;
				}
				else
				{
					white++;
				}
			}
		}
//		System.out.println("W:" + white + " B:" + black);
		if(white > 0){
		return (float) black / (float) (white);
		}
		return (float) black;
//		return toReturn;
	}
	//returns ratio of Black to all pixels
	public float BlackRatio(BufferedImage image){
		
		int black = 0;
		int width = image.getWidth();
		int height = image.getHeight();
		int area = width*height;
		for (int i = 0; i < width; i++) 
		{
			for (int j = 0; j < height; j++) 
			{
				if (image.getRGB(i, j) < WHITEHEX)
				{
					black++;
				}

			}
		}
//		System.out.println("W:" + white + " B:" + black);
			return (float) black / (float) (area);
//		return toReturn;
	}
	
	
	//crops the frame to the first instance of black.
	public BufferedImage cropFrame(BufferedImage image){
		
		int width = image.getWidth();
		int height = image.getHeight();
	  	int left = -1;
    	int right = -1;
    	int top = -1;
    	int bottom = -1;
    	
    	
    	//in each dir, look for firs pixel of black. then crop to that
    	for(int i = 0 ; i < width ; i++)
    	{
    		for( int j = 0; j < height && left == -1; j ++)
    		{
    			if(image.getRGB(i, j) < WHITEHEX){
    				left = i;
    				break;
    			}
    		}
    	}
    	
    	for(int i = width-1 ; i >= 0 && right == -1; i--)
    	{
    		for( int j = 0; j < height; j ++)
    		{
    			if(image.getRGB(i, j) < WHITEHEX){
    				right = i;
    				break;
    			}
    		}
    	}
    	
    	for(int j = 0 ; j < height && top == -1; j++)
    	{
    		for( int i = 0; i < width; i ++)
    		{
    			if(image.getRGB(i, j) < WHITEHEX){
    				top = j;
    				break;
    			}
    		}
    	}
    	for(int j = height-1 ; j >= 0 && bottom == -1; j--)
    	{
    		for( int i = 0; i < width; i ++)
    		{
    			if(image.getRGB(i, j) < WHITEHEX){
    				bottom = j;
    				break;
    			}
    		}
    	}
    	

    	return image.getSubimage(left, top, right - left + 1, bottom - top + 1);
    	
    	
	}
	
	
	//returns 0 if a == b +/- percent, -1 if A is larger, 1 if B is larger
	// all within the given percent
	
	public int CompareWithin(float aVal, float bVal,int percent){
		if(aVal == bVal){
			return 0;
		}
		float ans = 0.0f;
		boolean aIsLarger = false;
		if(aVal > bVal){
			aIsLarger = true;
			ans = (aVal-bVal)/aVal;
			
		}else{
			ans = (bVal-aVal)/bVal;
		}
		ans = ans * 100;
		if(ans <= percent){
			return 0;
		} 
		if(aIsLarger){
			return -1;
		}
		return 1;
	}
	
	
	
	
	
}
