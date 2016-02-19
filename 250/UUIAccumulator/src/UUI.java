
public class UUI {
	String number;
	//UUI Constructor
	public UUI(String string) {
		number = string;
	}
	//Definitely "not equal to"
	public boolean NE(UUI val) {
		char[] numberCharArray = this.number.toCharArray(); 
		char[] valCharArray =  val.number.toCharArray();
			
		if(numberCharArray.length == valCharArray.length){
				if (checkEquality(numberCharArray, valCharArray) == true){
					return false;
				}else{
					return true;
				}
		}else{
			return true;
		}
	}
	//add two UUI's
	public void add(UUI val) {
		int size;
		if(this.number.length() > val.number.length()){
			size = this.number.length();
		}else if(val.number.length() > this.number.length()){
			size = val.number.length();
		}else{
			size = val.number.length();
		}
		char[] numberCharArray = makeArray(this.number, size);
		char[] valCharArray = makeArray(val.number, size);

		if(numberCharArray.length == 2 && numberCharArray[1]=='0'){
		
			numberCharArray = valCharArray;
			
		}else if(numberCharArray.length > valCharArray.length){
			int j = numberCharArray.length - 1;
			for(int i = valCharArray.length-1; i >= 0; i-- ){
				 int tmp;
				 tmp =  Character.getNumericValue(numberCharArray[j]) + Character.getNumericValue(valCharArray[i]);

				 	
				 if(tmp > 10){
					numberCharArray = addTen(numberCharArray, j, tmp);
					valCharArray[i]= '0';
				 }else{
					numberCharArray[j] = Integer.toString(tmp).charAt(0); 
					valCharArray[i] = '0';
				 }
				 
				 j--;
			}
		//NUMBER is accumulator
		}else if(numberCharArray.length < valCharArray.length){
			int j = valCharArray.length - 1;
			
			for(int i = numberCharArray.length-1; i >= 0; i-- ){
				 
				 int tmp;
				 tmp =  Character.getNumericValue(numberCharArray[i]) + Character.getNumericValue(valCharArray[j]);
 
				 if(tmp >= 10){
					 numberCharArray = addTen(numberCharArray, i, tmp);
					 valCharArray[j] = '0';
				 }else{
					 numberCharArray[i] = Integer.toString(tmp).charAt(0);
					 valCharArray[j] = '0';
				 }
				 
				 j--;
			
			}
		}else{
			int j = numberCharArray.length -1;
			for(int i = valCharArray.length-1; i >= 0; i-- ){
				 int tmp;
				 tmp =  Character.getNumericValue(numberCharArray[j]) + Character.getNumericValue(valCharArray[i]);
				 System.out.println(tmp); 
				 if(tmp > 10){
					 numberCharArray = addTen(numberCharArray, j, tmp);
  					 valCharArray[i]= '0';
				 }else{
					numberCharArray[j] = Integer.toString(tmp).charAt(0); 
					valCharArray[i] = '0';
				 }
				 
				 j--;
			}
		}
		String endCalc = "";
		if(numberCharArray[0] == '0'){
			for(int i =1; i < numberCharArray.length; i++){
			endCalc += numberCharArray[i];
			}
		}else{
			for(int i = 0; i < numberCharArray.length; i++){
			endCalc += numberCharArray[i];
		}

		}

		
		this.number = endCalc;
	}
	//another String Method?
	public String toString(){
		return number;
	}
	public String toStringf() {
		return number;
	}
	
	private boolean checkEquality(char[] charA, char[] charB){
		for(int i = 0; i < charA.length; i++){
			int a, b;
			a = Character.getNumericValue(charA[i]);
			b = Character.getNumericValue(charB[i]);
			if(a == b){
				
			}else{
				return false;
			}
		}
		return true;
	
	}
	private char[] makeArray(String str, int size){
		int strSize = str.length();
		char[] charArr = new char[size + 1];
		charArr[0] = '0';
		
		for(int i = 1; i < (size-strSize) + 1; i ++){
			charArr[i] = '0';
		}
		int j = 0;
		for(int i = (size-strSize + 1); i < charArr.length; i++){
			char tmp = str.charAt(j);
			charArr[i] = tmp;
			j++;
		}

	return charArr;	
	}
	private char[] addTen(char[] charArr, int j, int tmp){
		
		if((Character.getNumericValue(charArr[j-1]) + 1)== 10){
			j--;
			addTen(charArr,j,tmp);
		}else{
		charArr[j-1] = Integer.toString(Character.getNumericValue(charArr[j-1]) + 1).charAt(0);  
		charArr[j] = Integer.toString(tmp%10).charAt(0);
		}

		return charArr;
	}
}
