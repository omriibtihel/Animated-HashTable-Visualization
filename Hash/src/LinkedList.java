public class LinkedList {
	    public Node head;
	    public int size;

	    public LinkedList() {
	        head = null;
	        size = 0;
	    }
	    
	    @Override
	    public String toString() {
	    	StringBuilder sb=new StringBuilder();
	    	Node c=head;
	    	boolean first=true;
	    	while(c!=null) {
	    		if(first) {
	    			first=false;
	    		}else {
	    			sb.append(",");
	    		}
	    		sb.append(c.data);
	    		c=c.next;
	    	}
	    	return sb.toString();
	    }
	    
	    class Node {
	        String data;
	        Node next;

	        public Node(String data) {
	            this.data = data;
	            this.next = null;
	        }
	    }

	    public void add(String nom) {
	        Node n = new Node(nom);
	        if (head == null) {
	            head = n;
	        } else {
	            Node current = head;
	            while (current.next != null) {
	                current = current.next;
	            }
	            current.next = n;
	        }
	        size++;
	    }

	    public boolean contains(String nom) {
	        Node current = head;
	        while (current != null) {
	            if (current.data.equals(nom)) {
	                return true;
	            }
	            current = current.next;
	        }
	        return false;
	    }

	    public void remove(String nom) {
	        if (head == null) {
	            return;
	        }
	        if (head.data.equals(nom)) {
	            head = head.next;
	            size--;
	            return;
	        }
	        Node current = head;
	        while (current.next != null) {
	            if (current.next.data.equals(nom)) {
	                current.next = current.next.next;
	                size--;
	                return;
	            }
	            current = current.next;
	        }
	    }

		public boolean isEmpty() {
			return size == 0;
		}

		public int getSize() {
			return size;
		}

		 // Méthodes d'accès pour la classe interne Node
		 public Node getHead() {
			return head;
		}
	
		public Node getNext(Node node) {
			return node.next;
		}
	
		public String getData(Node node) {
			return node.data;
		}


	    public void printt() {
	        Node current = head;
	        while (current != null) {
	            System.out.print("["+ current.data + ",");
	            current = current.next;
	        }
	        System.out.println();
	    }

	  
	

}

