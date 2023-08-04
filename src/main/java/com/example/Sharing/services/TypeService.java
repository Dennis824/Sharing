package com.example.Sharing.services;

import com.example.Sharing.model.Type;
import com.example.Sharing.repositories.TypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TypeService{
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    public Type findById(int id) {
        return typeRepository.findById(id);
    }

    @Transactional
    public void delete(Type type) {
        typeRepository.delete(type);
    }
    @Transactional
    public void save(Type type) throws Exception {
        if(typeRepository.findByNameIgnoreCase(type.getName())==null) {
            type.setName(type.getName());
            typeRepository.save(type);
            return;
        }
        throw new Exception("type "+type.getName()+" already exist");
    }
}