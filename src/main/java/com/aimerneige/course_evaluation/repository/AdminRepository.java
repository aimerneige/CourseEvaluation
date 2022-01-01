package com.aimerneige.course_evaluation.repository;

import com.aimerneige.course_evaluation.entity.Admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findById(long id);

    Iterable<Admin> findByName(String name);

    Admin findByUsername(String username);
}
