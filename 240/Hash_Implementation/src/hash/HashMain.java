package hash;

public class HashMain{
	
		public static void main(String[] args){
			Hash hash = new Hash();
			hash.insert(1);
			hash.insert(5);
			hash.insert(28);
			hash.delete(5);
			hash.insert(15);
			hash.insert(8);
			hash.dumpHash();
			
		}
	
}