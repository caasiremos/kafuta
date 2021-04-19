package com.kafuta.Kafuta.web.fuelstation;


import com.kafuta.Kafuta.web.district.District;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "stations")
public class FuelStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Station name is required")
    private String name;
    @NotBlank(message = "manager name is required")
    private String manager;
    @NotBlank(message = "manager phone number is required")
    private String telephone;
    @NotBlank(message = "Fuel station location address is required")
    private String address;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private District district;

    public FuelStation() {
    }

    public FuelStation(int id, String name, String manager, String telephone, String address, int districtId) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.telephone = telephone;
        this.address = address;
        this.district = new District(districtId, "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "FuelStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", district=" + district +
                '}';
    }
}
