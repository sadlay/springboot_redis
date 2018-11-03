package com.lay.redis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lay.redis.dao.PersonDao;
import com.lay.redis.entity.Person;
import com.lay.redis.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
    
    @Autowired
    PersonDao personDao;
    
    //获取id，取参数id缓存用户
    @Override
    @Transactional
    @Cacheable(value = "redisCache", key = "'redis_person_'+#id")
    public Person getPerson(Long id) {
        return personDao.getPerson(id);
    }
    
    //插入用户,最后mybatis会回填id，取结果id缓存用户
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.REQUIRES_NEW)
    @CachePut(value = "redisCache", key = "'redis_person_'+#result.id")
    public Person insertPerson(Person person) {
        System.out.println("===========sex==========" + person.getSex().getId());
        personDao.insertPerson(person);
        return person;
    }
    
    //更新数据后，更新缓存，如果condition配置项使结果返回为Null,不缓存
    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result!='null'", key = "'redis_person_'+#id")
    public Person updatePerson(Long id, String personName) {
        Person person = this.getPerson(id);
        if (person == null) {
            return null;
        }
        person.setPersonName(personName);
        personDao.updatePerson(person);
        return person;
    }
    
    @Override
    public List<Person> getAllPersons() {
        
        return personDao.getAllPersons();
    }
    
    //移除缓存
    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_person_'+#id", beforeInvocation = false)
    public int deletePerson(Long id) {
        return personDao.deletePerson(id);
    }
    
}
