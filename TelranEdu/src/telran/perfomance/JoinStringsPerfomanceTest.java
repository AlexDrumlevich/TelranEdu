package telran.perfomance;

public class JoinStringsPerfomanceTest extends PerfomanceTest {

	//properties
	private String[] strings;
	private JoinStrings joinString;

	//initializer
	public JoinStringsPerfomanceTest(String testName, int nRuns, String[] strings, JoinStrings joinString) {
		super(testName, nRuns);
		this.strings = strings;
		this.joinString = joinString;
	}

	//methods
	@Override
	protected void runTest() {
		joinString.join(strings, " -next_string- ");
	}

}
