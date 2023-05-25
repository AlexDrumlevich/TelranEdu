package telran.util.test.Models;

import java.util.Objects;

public class Person implements Comparable<Person>{
	
	private long id;
	private int age;
	private String name;
	public Person(long id, int age, String name) {
		this.id = id;
		this.age = age;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}
	@Override
	public int compareTo(Person person) {
		
		return Long.compare(this.id, person.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(age, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && id == other.id && Objects.equals(name, other.name);
	}
}
