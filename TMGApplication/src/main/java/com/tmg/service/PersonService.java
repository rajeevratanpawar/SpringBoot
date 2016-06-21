package com.tmg.service;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.tmg.model.Person;

@Service
public class PersonService {

	Hashtable<String, Person> person = new Hashtable<String, Person>();

	public PersonService() {
		Person p = new Person();
		p.setId("1");
		p.setFirstName("Rajeev");
		p.setLastName("Pawar");
		p.setAge(23);
		person.put("1", p);

		p = new Person();
		p.setId("2");
		p.setFirstName("Kumar");
		p.setLastName("Pawar");
		p.setAge(23);
		person.put("2", p);

	}

	public Person getPerson(String id) {
		if (person.containsKey(id))
			return person.get(id);
		else
			return null;
	}

	public Hashtable<String, Person> getAll() {
		return person;
	}
}
