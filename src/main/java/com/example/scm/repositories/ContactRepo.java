package com.example.scm.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.scm.entities.Contacts;
import com.example.scm.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contacts,String>{

    // find by user 
    Page<Contacts> findByUser(User user, Pageable pageable);

    @Query("SELECT c FROM Contacts c WHERE c.user.id = :userId")
    List<Contacts> findByUserId(@Param("userId") String userId);

    Page<Contacts> findByUserAndNameContaining(User user, String nameKeyword, Pageable pageable);
    Page<Contacts> findByUserAndEmailContaining(User user, String emailKeyword, Pageable pageable);
    Page<Contacts> findByUserAndPhoneNumberContaining(User user, String phoneNumberKeyword, Pageable pageable);
}
