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
        return TaxRepository.findAll();
    }

    public Tax findById(long id) {
        return TaxRepository.findById(id);
    }

    @Transactional
    public void delete(Tax tax) {
        TaxRepository.delete(tax);
    }

    @Transactional
    public void save(Tax tax) throws Exception{
        TaxRepository.save(tax);
    }
}