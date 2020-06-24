package com.vmware.backend.service;

import com.vmware.backend.model.Charity;

import java.util.List;

public interface ICharityService {
    Charity add(Charity charity);
    void update(Long id, Charity charity);
    void delete(Long id);
    List<Charity> getAll();
    Charity get(Long id);
    
}
