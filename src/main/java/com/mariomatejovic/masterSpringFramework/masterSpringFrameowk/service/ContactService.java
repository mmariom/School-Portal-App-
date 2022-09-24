package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.service;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Contact;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.ContactRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Data
//@ApplicationScope
public class ContactService {

@Autowired
private ContactRepository contactRepository;



    //
//    private int counter = 0 ;
//
//
//    public ContactService() {
//        System.out.println("Contact Service Bran created!");
//    }

    public boolean saveMessageDetails(Contact contact) {

        boolean isSaved = false;
        contact.setStatus("OPEN");
        contact.setCreatedBy("ANONYMOUS");
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if (result>0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {

        return  contactRepository.findMsgsWithOpenStatus("OPEN");
    }

    public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId,"CLOSE", updatedBy);
        if(result>0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}