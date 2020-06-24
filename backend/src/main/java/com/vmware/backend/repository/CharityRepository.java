package com.vmware.backend.repository;

import com.vmware.backend.model.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {
    //Charity findByTitle(String title);
}
