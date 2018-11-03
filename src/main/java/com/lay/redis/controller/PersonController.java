package com.lay.redis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lay.redis.entity.Person;
import com.lay.redis.enumeration.SexEnum;
import com.lay.redis.service.PersonService;

@Controller
@RequestMapping(value = "/person")
public class PersonController {
    @Autowired
    PersonService personService;
    
    @RequestMapping(value = "/getPerson")
    @ResponseBody
    public Person getPerson(@RequestParam("id") Long id) {
        return personService.getPerson(id);
    }
    
    @RequestMapping(value = "/insertPerson")
    @ResponseBody
    public Person inserPerson(String personName, String sex, String note) {
        Person person = new Person();
        person.setPersonName(personName);
        person.setSex(SexEnum.getEnumByName(sex));
        person.setNote(note);
        personService.insertPerson(person);
        return person;
    }
    
    @RequestMapping(value = "/updatePersonName")
    @ResponseBody
    public Map<String, Object> updatePerson(Long id, String personName) {
        Person person = personService.updatePerson(id, personName);
        boolean flag = person != null;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag, message);
    }
    
    @RequestMapping(value = "/getAllPerson")
    @ResponseBody
    public List<Person> getAllPerson() {
        return personService.getAllPersons();
    }
    
    @RequestMapping(value = "/deletePerson")
    @ResponseBody
    public Map<String, Object> inserPerson(Long id) {
        int result = personService.deletePerson(id);
        boolean flag = result == 1;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag, message);
    }
    
    private Map<String, Object> resultMap(boolean success, String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("message", message);
        return result;
    }
}
