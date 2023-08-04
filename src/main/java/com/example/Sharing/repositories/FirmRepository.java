package com.example.Sharing.repositories;

import com.example.Sharing.model.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Integer> {

    Firm findById(int id);

    Firm findByNameIgnoreCase(String name);
}
