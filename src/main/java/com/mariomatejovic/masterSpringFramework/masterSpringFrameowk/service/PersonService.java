package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.service;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Person;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Roles;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.PersonRepository;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {


    @Autowired
    private PersonRepository personRepository;


    @Autowired
    private RolesRepository rolesRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName("STUDENT");


        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person  = personRepository.save(person);
        if (null != person && person.getPersonId() > 0){
            isSaved = true;
        }
        return  isSaved;
    }
}
