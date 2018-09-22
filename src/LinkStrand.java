
public class LinkStrand implements IDnaStrand{
	
	private class Node {
	   	String info;
	   	Node next;
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	   }
	   private Node myFirst, myLast;
	   private long mySize;
	   private int myAppends;
	   
	public LinkStrand() {						//passes empty dna source as an empty string
		this("");
	}
	

	public LinkStrand(String string) {					//sends to the "real" constructor
		initialize(string);
	}


	@Override
	public long size() {						//returns size
		return mySize;
	}

	@Override
	public void initialize(String source) {				//constructs class variables around source
		Node dnaStrand = new Node(source);
		myFirst = dnaStrand;
		myLast = dnaStrand;
		mySize = source.length();
		myAppends = 0;
	}

	@Override
	public IDnaStrand getInstance(String source) {			//creates new object with input source
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		Node moreDna = new Node(dna);			//one line version in lecture slides from Nov 1
		myLast.next = moreDna;
		myLast = moreDna;
		mySize += dna.length();
		myAppends ++;
		return this;
	}
	
	public String toString() {					//returns string dna
		Node first = myFirst;
		String dnaString = "";
		while (first != null) {
			dnaString += first.info;
			first = first.next;
		}
		return dnaString;
	}

	@Override
	public IDnaStrand reverse() { 						//uses arrays to help reverse the dna strands then reconverts with a LinkStrand object
		Node last = myFirst;
		String[] strands = new String[myAppends + 1];
		for (int k = strands.length - 1; k >= 0; k--) {
			strands[k] = new StringBuilder(last.info).reverse().toString();
			last = last.next;
		}
		LinkStrand reversedDna = new LinkStrand(strands[0]);
		for (int k = 1; k < strands.length; k++)
			reversedDna.append(strands[k]);
		return reversedDna;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {						//effectively finds characters, even across differently sized strands
		Node first = myFirst;
		int currentIndex = 0;
		while (currentIndex + first.info.length() <= index) {
			currentIndex += first.info.length();
			first = first.next;
		}
		return first.info.charAt(index - currentIndex);
	}
}
