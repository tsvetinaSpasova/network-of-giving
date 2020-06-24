package com.vmware.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class Charity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String image;

    private Double amountRequired;
    private Double amountCollected;

    public Charity(){

    }
    public Charity(@NotBlank String title, @NotBlank String description, String image, Double amountRequired, Double amountCollected) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.amountRequired = amountRequired;
        this.amountCollected = amountCollected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "amount_required")
    public Double getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(Double amountRequired) {
        this.amountRequired = amountRequired;
    }

    @Column(name = "amount_collected")
    public Double getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(Double amountCollected) {
        this.amountCollected = amountCollected;
    }

    @Override
    public String toString(){
        return this.title + this.description + this.image
                + this.amountCollected.toString() + this.amountRequired.toString();
    }
}