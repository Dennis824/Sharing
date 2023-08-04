package com.example.Sharing.services;

import com.example.Sharing.model.Firm;
import com.example.Sharing.repositories.FirmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FirmService {

    private final FirmRepository firmRepository;

    public FirmService(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }


    public Iterable<Firm> findAll() {
        return firmRepository.findAll();
    }

    public Firm findById(int id) {
        return firmRepository.findById(id);
    }

    @Transactional
    public void delete(Firm firm) {
        firmRepository.delete(firm);
    }

    @Transactional
    public void save(Firm firm) throws Exception {
        if(firmRepository.findByNameIgnoreCase(firm.getName())==null) {
            firm.setName(firm.getName());
            firmRepository.save(firm);
            return;
        }
        throw new Exception(firm.getName() + " already exist");
    }
}
