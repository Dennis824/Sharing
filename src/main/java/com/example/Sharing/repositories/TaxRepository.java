package com.example.Sharing.repositories;

import com.example.Sharing.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {

    Tax findById(long id);
}
