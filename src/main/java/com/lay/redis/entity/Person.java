package com.lay.redis.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.lay.redis.enumeration.SexEnum;

@Alias("person")
public class Person implements Serializable {
    
    private static final long serialVersionUID = 3172000990909338544L;
    
    private Long id = null;
    
    private String personName = null;
    
    //性别枚举，这里需要使用typeHandler进行转换
    private SexEnum sex = null;//枚举
    
    private String note = null;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public SexEnum getSex() {
        return sex;
    }
    
    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public String getPersonName() {
        return personName;
    }
    
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
