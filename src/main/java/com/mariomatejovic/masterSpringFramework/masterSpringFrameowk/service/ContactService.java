package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.service;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Contact;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.ContactRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        // Handed to auditawareimpl
//        contact.setCreatedBy("ANONYMOUS");
//        contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
        if (null != savedContact && savedContact.getContactID() > 0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {

        return  contactRepository.findByStatus("OPEN");
    }



    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        Optional<Contact> byId = contactRepository.findById(contactId);
        byId.ifPresent(
                contact -> {
                    contact.setStatus("CLOSE");
//                    contact.setUpdatedBy(updatedBy);
//                    contact.setUpdatedAt(LocalDateTime.now());

                }
        );

        Contact updatedContact = contactRepository.save(byId.get());

        if(null != updatedContact && updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
