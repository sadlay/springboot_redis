package com.lay.redis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lay.redis.entity.Person;

@Mapper
public interface PersonDao {
    
    public Person getPerson(Long id);
    
    public int insertPerson(Person person);
    
    public int updatePerson(Person person);
    
    public List<Person> getAllPersons();
    
    public int deletePerson(Long id);
}
