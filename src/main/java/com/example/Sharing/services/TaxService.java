package com.example.Sharing.services;

import com.example.Sharing.model.Tax;
import com.example.Sharing.repositories.TaxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TaxService {

    private final TaxRepository taxRepository;

    public TaxService(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    public Iterable<Tax> findAll() {
        return taxRepository.findAll();
    }

    public Tax findById(long id) {
        return taxRepository.findById(id);
    }

    @Transactional
    public void delete(Tax tax) {
        taxRepository.delete(tax);
    }

    @Transactional
    public void save(Tax tax) throws Exception{
        taxRepository.save(tax);
    }
}