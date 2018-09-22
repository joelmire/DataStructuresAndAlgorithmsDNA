
public class DNAAnalysis {
	
	private static final String ENZYME = "gaattc";
	private static final String SPLICEE = "tttttttttttttttttttttttt";
	private static final int NUM_TRIALS = 10;
		
	public static void test() {
		for (int b = 1; b < 80000000; b *= 2){
			StringBuilder dna = new StringBuilder();
			for (int i = 0; i < b; i++) 
			    dna.append(ENZYME);
			while (dna.length() < 10000000){
				dna.append("x");
			}
//			IDnaStrand strand = new StringStrand(dna.toString());
//			IDnaStrand strand = new StringBuilderStrand(dna.toString());
			IDnaStrand strand = new LinkStrand(dna.toString());
			double totalTime = 0;
			for (int trial = 0; trial < NUM_TRIALS; trial++) {
			    double start = System.nanoTime();
			    strand.cutAndSplice(ENZYME, SPLICEE);
			    totalTime += (System.nanoTime() - start) / 1e9;
			}
			totalTime /= NUM_TRIALS;
			System.out.printf("%6d\t%6.4f\n", b, totalTime);
		}
	}
	
	public static void main(String[] args){
		test();
	}

}
