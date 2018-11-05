package com.lay.redis.service;

import java.util.List;

import com.lay.redis.entity.Person;

public interface PersonService {
    //获取单个用户
    public Person getPerson(Long id);
    
    //新增用户
    public Person insertPerson(Person person);
    
    //更新用户名
    public Person updatePerson(Long id, String personName);
    
    //获取全部用户
    public List<Person> getAllPersons();
    
    //删除用户
    public int deletePerson(Long id);
    
}
