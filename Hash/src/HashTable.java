
public class HashTable {
	private int p,size;
    private LinkedList[] items;

    public HashTable(int p) {
        this.p = p;
        this.items = new LinkedList[p];
        for (int i = 0; i < p; i++) {
            items[i] = new LinkedList();
        }
    }
    
    int hash(String nom) {
        int hash = 0;
        for (int c : nom.toCharArray()) {
            hash = (hash * 31 + c) % p;
        }
        return hash;
    }

    public void add(String nom) {
        if (!contains(nom)) {
            int index = hash(nom);
            items[index].add(nom);
            size++;
        }
    }
    
    public boolean contains(String nom) {
        int index = hash(nom);
        return items[index].contains(nom);
    }
    
    public boolean remove(String nom) {
        if (contains(nom)) {
            int index = hash(nom);
            items[index].remove(nom);
            size--;
            return true; 
        } else {
            return false; 
        }
    }
    

    public int size() {
        return size;
    }


    public int getCapacity() {
        return p;
    }

    public LinkedList[] getItems() {
        return items;
    }
    public void print() {
        for (int i = 0; i < p; i++) {
            System.out.println(i + ": " + items[i]);
        }
    }
	  
}

