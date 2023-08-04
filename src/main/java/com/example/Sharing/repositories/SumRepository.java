package com.example.Sharing.repositories;

import com.example.Sharing.model.Sum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SumRepository extends JpaRepository<Sum, Long> {

    Sum findById(long id);
}
