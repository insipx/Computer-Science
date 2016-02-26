package hash;

public class Hash<T> implements Hash_Interface<T>{
	private final static int ENTRY_SIZE = 10;
	Node<T> current;
	//will always be a number because it's the 'KEY' ie index so we don't need/can't to use generic
	LinkedList[] entries;
	public Hash(){
		entries = new LinkedList[ENTRY_SIZE];
	}
	
	public void insert(int val) {
		int idx = val % ENTRY_SIZE;
		entries[idx].insert(val);
	}
	public void delete(int val) {
		int idx = val % ENTRY_SIZE;
		entries[idx].delete(val);
	}
	public void dumpHash() {
		for(int i = 0; i < ENTRY_SIZE; i++){
			System.out.println("=======================");
			System.out.println("Index " + i + " of Hash");
			entries[i].dumpList();
			System.out.println("=======================");
		}
	}
}

