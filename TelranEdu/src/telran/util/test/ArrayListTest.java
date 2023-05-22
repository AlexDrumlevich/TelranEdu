package telran.util.test;
import telran.util.*;


class ArrayListTest extends ListTest {

	@Override
	protected <T> List<T> getList() {
		return new ArrayList<>();
	}

}
