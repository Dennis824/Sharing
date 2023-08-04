package com.example.Sharing.services;

import com.example.Sharing.model.Transmission;
import com.example.Sharing.repositories.TransmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TransmissionService {
    private final TransmissionRepository transmissionRepository;

    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public Iterable<Transmission> findAll() {
        return transmissionRepository.findAll();
    }

    public Transmission findById(int id) {
        return transmissionRepository.findById(id);
    }

    @Transactional
    public void delete(Transmission transmission) {
        transmissionRepository.delete(transmission);
    }

    @Transactional
    public void save(Transmission transmission) throws Exception {
        if(transmissionRepository.findByNameIgnoreCase(transmission.getName())==null) {
            transmission.setName(transmission.getName());
            transmissionRepository.save(transmission);
            return;
        }
        throw new Exception("Transmission "+transmission.getName()+" already exist");
    }
}
