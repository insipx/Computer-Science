package hash;

public class HashMain{
	
		public static void main(String[] args){
			LinkedList list = new LinkedList();
			list.insert(3);
			list.insert(44);
			list.insert(4);
			list.delete(3);
			list.dumpList();

			System.out.println("SDFSDFDSF");

			Hash hash = new Hash();
			hash.insert(1);
			hash.insert(5);
			hash.insert(28);
			hash.delete(5);
			hash.insert(15);
			hash.insert(8);
			hash.dumpHash();
			hash.delete(1);
			hash.insert(18);
			hash.insert(25);
			hash.delete(33);
			
		}
 	
}