package telran.perfomance;

public abstract class PerfomanceTest {

	//properties
	private String testName;
	private int nRuns;
	
	//initializer
	public PerfomanceTest(String testName, int nRuns) {
		this.testName = testName;
		this.nRuns = nRuns;
	}
	
	//methods
	public void run() {
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < nRuns; i ++) {
			runTest();
		}
		long finishTime = System.currentTimeMillis();
		System.out.println("Test name: " + testName + ", count of running test: " + nRuns + ", " + "spent time: " + (finishTime - startTime) + " ms.");  
	}
	
	abstract void runTest();
}
