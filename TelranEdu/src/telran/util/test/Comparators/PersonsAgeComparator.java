package telran.util.test.Comparators;

import java.util.Comparator;

import telran.util.test.Models.Person;

public class PersonsAgeComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {
		
		return Integer.compare(person2.getAge(),
				person1.getAge());
	}

}
