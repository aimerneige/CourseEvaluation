package com.aimerneige.course_evaluation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aimerneige.course_evaluation.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findById(long id);

    Iterable<Admin> findByName(String name);

    Admin findByUsername(String username);
}
