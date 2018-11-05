package com.lay.redis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lay.redis.entity.Person;

@Mapper
public interface PersonDao {
    //获取单个用户
    public Person getPerson(Long id);
    
    //新增用户
    public int insertPerson(Person person);
    
    //更新用户
    public int updatePerson(Person person);
    
    //获取全部用户
    public List<Person> getAllPersons();
    
    //删除用户
    public int deletePerson(Long id);
}
