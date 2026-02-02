package com.campusdwar.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campusdwar.backend.entity.Faculty;
import com.campusdwar.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByUser(User user);

    List<Faculty> findByDepartment(String department);
    
    Optional<Faculty> findByUserEmail(String email);
}
