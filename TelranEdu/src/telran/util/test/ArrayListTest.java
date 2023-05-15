package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Array;
import java.lang.reflect.Executable;

import org.junit.jupiter.api.BeforeEach;

import telran.util.*;
import telran.util.test.Comparators.EvenOddComparator;
import telran.util.test.Comparators.PersonsAgeComparator;
import telran.util.test.Models.Person;

import org.junit.jupiter.api.Test;



class ArrayListTest extends ListTest {

	@Override
	protected <T> List<T> getList() {
		return new ArrayList<>();
	}

}
