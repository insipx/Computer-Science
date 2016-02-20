/*  This is a solution for Assignment 1 in CMPS 250 for Spring 2016
    
     I, Andrew Plaza, am the developer of this work.
 	 Worked Alone
 	 
 	 Not yet aware of any flaws, maybe uses too much memory?
 	 the terminate function may also be a little redundant, but I like
 	 the ability of adding up as many UUI's as my heart desires
 	 
*/

public class UUI {
	String number;
	boolean terminate;

	// UUI Constructor
	public UUI(String string) {
		//set the number for the specific UUI
		number = string;
	}

	// Definitely "not equal to"
	public boolean NE(UUI val) {
		char[] numberCharArray = this.number.toCharArray();
		char[] valCharArray = val.number.toCharArray();
		//if the lengths are not the same, they cannot be equal
		if (numberCharArray.length == valCharArray.length) {
			//another method for readability and modularity
			if (checkEquality(numberCharArray, valCharArray) == true) {
				//return false that it IS equal
				return false;
			} else {
				//return true that it is NOT equal
				return true;
			}
		} else {
			return true;
		}
	}

	// add two UUI's
	public void add(UUI val) {
		
		//a simple terminate mechanism
		if(terminate(val) == true){
			terminate = true;
		}
		
		//get the string with the largest size, so that we can add zeroes to the smaller number to make them the same size
		int size;
		if (this.number.length() > val.number.length()) {
			size = this.number.length();
		} else if (val.number.length() > this.number.length()) {
			size = val.number.length();
		} else {
			size = val.number.length();
		}
		
		//parses the strings, ignoring any characters that are not numbers, making them arrays, and spitting them back at us
		char[] numberCharArray = parseString(this.number, size);
		char[] valCharArray = parseString(val.number, size);
		
		//since the arrays are the same size, we only have to worry about one length. valCharArray and numberCharArray.length are interchangeable
		for (int i = valCharArray.length - 1; i >= 0; i--) {
			//make a temp val t o store what each number adds up to
			int tmp = Character.getNumericValue(numberCharArray[i]) + Character.getNumericValue(valCharArray[i]);
			//if it's greater than ten we have to add one to the preceding number. the "addTen" method does this recursively
			//if it's < 10, that's easy and we can just add one without worry
			if (tmp >= 10) {
				numberCharArray = addTen(numberCharArray, i, tmp);
				valCharArray[i] = '0';
			} else {
				numberCharArray[i] = Integer.toString(tmp).charAt(0);
				valCharArray[i] = '0';
			}

		}
		//endCalc gets rid of all the zeroes we don't need anymore
		this.number=endCalc(numberCharArray);
		//this checks if the user wants to stop adding up numbers
		if(terminate == true){
			val.number = "0";
		}else{
			//val.number doesn't get to change
		}
		
		System.out.println("Enter 'exit' to terminate program, keep entering UUI's to add them together");
	}	
	

//a simple terminate function. if the user enters 'terminate' it just sets val.number="0", because UUIAccumulator ends the program when value==Sentinel
	private boolean terminate(UUI val) {
		String tempVal = val.number.toLowerCase();
		String terminate = "exit";
		if(tempVal.length() == terminate.length() && terminate.equals(tempVal)){
			return true;
		}else{
		return false;
		}
	}
	//unformated toString method
	public String toString() {
		return number;
	}
	
	private static String formatCommas(String str) {
		//if the string is less than four than no commas need to be added anymore, cause, no commas in 333
		if(str.length() < 4){
	        return str;
	    }
		//by itself this statement would just put a comma three away from the end, but recursively, the string keeps getting smaller by 3,
		//so it keeps adding commas 
		//until the str.length < 4
		//and the recursion will stop
		//this took me forever to figure out 
	    return formatCommas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
	}	
	//our beautifully formatted string :D
	public String toStringf() {
		String temp = number;
		temp = formatCommas(temp);
		return temp;
	}
	//check equality method, takes two character arrays and compares each element. if each ele is equal, true, else false
	private boolean checkEquality(char[] charA, char[] charB) {
		for (int i = 0; i < charA.length; i++) {
			int a, b;
			a = Character.getNumericValue(charA[i]);
			b = Character.getNumericValue(charB[i]);
			if (a == b) {
			} else {
				return false;
			}

		}
		return true;
	}

	//parses the Strings. size is needed because makeArray is used in this method
	private char[] parseString(String str, int size) {
	
		String result = str;
		//make sure each element is a digit
		for(int i = 0; i < result.length(); i ++){
			if(Character.isDigit(result.charAt(i))){
				
			}else{
				//if it's not a digit, remove it, and make the string smaller
				String temp = result.substring(0, i) + result.substring(i+1);
				result = temp;
			}
		}
		//we need to do this before creating a char array, because arrays are static
	
		//make a char array using makeArray and return that
		char[] charArr = makeArray(result, size);
		
		return charArr;
	}

	//makes an array of characters of the passed size. This ensures both arrays are the same size, makes for easier addition
	private char[] makeArray(String str, int size) {
		//get the actual size of the string
		int strSize = str.length();
		char[] charArr = new char[size + 1];
		//the first character will always be zero, just in case we get something like 99 + 99, we have an extra bit reserved
		charArr[0] = '0';
	
		//if the array is smaller, add zeros at the beginning
		for (int i = 1; i < (size - strSize) + 1; i++) {
			charArr[i] = '0';
		}
		//add the rest of the string after the zeros
		int j = 0;
		for (int i = (size - strSize + 1); i < charArr.length; i++) {
			char tmp = str.charAt(j);
			charArr[i] = tmp;
			j++;
		}

		return charArr;
	}
	//the method when the tmp variable in add is greater than 10
	private char[] addTen(char[] charArr, int j, int tmp) {
		//if adding one to the number before the one that is already ten, we have to keep using addTen until
		//we come accross a number that does not equal ten
		//if it's 99 + 99
		//then this is when the extra 0 comes in hand, because, obviously, 0+1 will never = 10
		if ((Character.getNumericValue(charArr[j - 1]) + 1) >= 10) {
			//use mod to attribute the value to the current space, so, if we are adding one because 3+9 = 12, 12%10 = 2 and we add the one to the extra bit, which becomes 12
			charArr[j] = Integer.toString(tmp % 10).charAt(0);
			tmp = Character.getNumericValue(charArr[j - 1]) + 1;
			j--;
			addTen(charArr, j, tmp);
		} else {
			//if it doesnt add to or greater than 10, all is swell in this world
			charArr[j - 1] = Integer.toString(Character.getNumericValue(charArr[j - 1]) + 1).charAt(0);
			charArr[j] = Integer.toString(tmp % 10).charAt(0);
		}

		return charArr;
	}
	
	private String endCalc(char[] charArr){
		
		// get rid of all those useless zeros...
		// Look et alllll thooooooose zeeeroooooos
		// https://youtu.be/F-X4SLhorvw?t=25s
	
		String endCalc = "";
		//get rid of all the zeroes until we get to a number that is not zero
		if (charArr[0] == '0') {
			int i = 0;
			//if the number is all zero, we want at least one zero left
			while (Character.getNumericValue(charArr[i]) == 0 && i < charArr.length - 1 ) {
				i++;
			}
			//add the rest to endCalc
			for (; i < charArr.length; i++) {
				endCalc += charArr[i];
			}
			//if the first char is already not zero, no worries here
		} else {
			for (int i = 0; i < charArr.length; i++) {
				endCalc += charArr[i];
			}
		}
		return endCalc;
	}
}
