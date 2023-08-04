package com.example.Sharing.repositories;

import com.example.Sharing.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    Type findById(int id);

    Type findByNameIgnoreCase(String name);
}