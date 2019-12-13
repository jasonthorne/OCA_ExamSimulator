package com.android;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Period{
	
	//----------------------------------------------
	private final static List<Block>blocks = Arrays.asList(Block.values());
	private final static List<Year>years = Arrays.asList(Year.values());
	
	private static ListIterator<Block>blocksIterator; 
	private static ListIterator<Year>yearsIterator;
	
	public static List<Period> getPeriods(Period first, Period last){
		
		
	
		
	
		
		blocksIterator = blocks.listIterator(); //set blocks iterator
		yearsIterator = years.listIterator(); //set years iterator
		Year currYear = null;
		Block currBlock = null;
		Period currPeriod = null;
		
		
		
		//currYear = yearsIterator.next();
		
		
		//loop through block and year lists and grab relevant periods ++++++++++++++++++
		while (yearsIterator.hasNext()) { //((!last.year.equals(currYear))) {
			
			
			/*
			if(blocksIterator.hasNext()) {
				currBlock = blocksIterator.next(); //move to next block
				System.out.println("current block is: " + currBlock);
				System.out.println("current year is: " + currYear);
				
				
			}else {
				blocksIterator = blocks.listIterator(); //reset blocks
				currYear = yearsIterator.next(); //move to next year
				
			}
			*/
			
			blocksIterator = blocks.listIterator(); //reset blocks
			currYear = yearsIterator.next(); //move to next year
			
			while(blocksIterator.hasNext()) {
				currBlock = blocksIterator.next(); //move to next block
				System.out.println("current block is: " + currBlock);
				System.out.println("current year is: " + currYear);
			}
			
			
		}
		
		
		
		
		

		/*
		while (yearsIterator.hasNext()) { //loop through years length
			
			System.out.println("current block is: " + currBlock);
			System.out.println("current year is: " + currYear); //+++++++++++++++
		
			if(blocksIterator.hasNext()) { //if next block:
				currBlock = blocksIterator.next(); //move to next block
				
			}else {
				blocksIterator = blocks.listIterator(); //reset blocks iterator
				currYear = yearsIterator.next(); //move to next year
			}
			
			//System.out.println("current block is: " + currBlock);
			//System.out.println("current year is: " + currYear); //+++++++++++++++
			
		}
		*/
		
		
		
		
		return null;
	}
	
	
	//----------------------------------------------
	
	private Block block;
	private Year year;
	
	public enum Block{
		EARLY("Early"), 
		MID("Mid"), 
		LATE("Late");
		private String block; //name of chosen block
		private Block(String block) { //constructor
			this.block = block; //assign name of block
		}
		@Override //override toString
		public String toString() {
			return block; //return name of chosen block
		}
	}
	
	public enum Year{
		NINETEEN_FORTY("1940"),
		NINETEEN_FORTY_ONE("1941"),
		NINETEEN_FORTY_TWO("1942"),
		NINETEEN_FORTY_THREE("1943"),
		NINETEEN_FORTY_FOUR("1944"),
		NINETEEN_FORTY_FIVE("1945");
		private String year; //name of chosen year
		private Year(String year) { //constructor
			this.year = year; //assign name of year
		}
		@Override //override toString
		public String toString() {
			return year; //return name of chosen year
		}
	}
	
	//constructor:
	Period(Block block, Year year){ 
		this.year = year;
		this.block = block;
	}


	@Override
	public String toString() {
		return "Period: [" + block + " " + year + "]";
	}
	
	
}
