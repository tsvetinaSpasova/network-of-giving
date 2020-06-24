package com.vmware.backend.controller;

import com.vmware.backend.model.Charity;
import com.vmware.backend.service.impl.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/charities", method = {RequestMethod.GET, RequestMethod.POST})
@CrossOrigin(origins = "http://localhost:4200")
public class CharityController {
    @Autowired
    private CharityService charityService;

    @PostMapping
    public Charity add(@RequestBody Charity charity) {
        return charityService.add(charity);
    }

    @GetMapping("/all")
    public List<Charity> getAll() {
        return charityService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getCharity(@PathVariable Long id) {
        return Optional
                .ofNullable(charityService.get(id))
                .map(charity -> ResponseEntity.ok().body(charity))
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        this.charityService.delete(id);
    }

    @PutMapping("/put/{id}")
    public void update(@PathVariable Long id, @RequestBody Charity charity) {
        charityService.update(id, charity);
    }

}
