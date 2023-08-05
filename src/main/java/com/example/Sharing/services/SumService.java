package com.example.Sharing.services;

import com.example.Sharing.model.Sum;
import com.example.Sharing.repositories.SumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SumService{

    private final SumRepository sumRepository;

    public SumService(SumRepository sumRepository) {
        this.sumRepository = sumRepository;
    }


    public Iterable<Sum> findAll() {
        return sumRepository.findAll();
    }

    public Sum findById(int id) {
        return sumRepository.findById(id);
    }

    @Transactional
    public void delete(Sum sum) {
        sumRepository.delete(sum);
    }

    @Transactional
    public void save(Sum sum) throws Exception {
        sumRepository.save(sum);
    }
}
