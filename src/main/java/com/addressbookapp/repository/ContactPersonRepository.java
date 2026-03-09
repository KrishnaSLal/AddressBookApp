package com.addressbookapp.repository;

import com.addressbookapp.model.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long> {
}