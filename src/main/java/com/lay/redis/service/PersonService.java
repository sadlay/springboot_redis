package com.lay.redis.service;

import java.util.List;

import com.lay.redis.entity.Person;

public interface PersonService {
    public Person getPerson(Long id);
    
    public Person insertPerson(Person person);
    
    public Person updatePerson(Long id, String personName);
    
    public List<Person> getAllPersons();
    
    public int deletePerson(Long id);
    
}
