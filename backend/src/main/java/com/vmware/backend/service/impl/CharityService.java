package com.vmware.backend.service.impl;

import com.vmware.backend.model.Charity;
import com.vmware.backend.repository.CharityRepository;
import com.vmware.backend.service.ICharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharityService implements ICharityService {

    @Autowired
    private CharityRepository charityRepository;


    @Override
    public Charity add(Charity charity) {
        return charityRepository.saveAndFlush(charity);
    }

    @Override
    public void update(Long id, Charity charity) {
        Optional<Charity> charityOptional = charityRepository.findById(id);
        if(charityOptional.isPresent()) {
            charity.setId(id);
            charityRepository.save(charity);
        }
    }

    @Override
    public void delete(Long id) {
        if(charityRepository.findById(id).isPresent()) {
            charityRepository.deleteById(id);
        }
    }

    @Override
    public List<Charity> getAll() {
        return charityRepository.findAll();
    }

    @Override
    public Charity get(Long id) {
        return charityRepository.findById(id).orElse(null);
    }
}
