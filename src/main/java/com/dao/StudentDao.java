package com.dao;

import com.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class StudentDao {
    @Autowired
    HibernateTemplate hibernateTemplate;

    public StudentEntity findById(int id){
        StudentEntity studentEntity = hibernateTemplate.get(StudentEntity.class, id);
        return studentEntity;
    }
    @Transactional
    public void update(StudentEntity studentEntity){
        hibernateTemplate.saveOrUpdate(studentEntity);
    }

}
