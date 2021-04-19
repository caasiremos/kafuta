package com.kafuta.Kafuta.web.user;

import com.kafuta.Kafuta.web.district.District;
import com.kafuta.Kafuta.web.fuelstation.FuelStation;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "First name required")
    private String firstName;
    @NotBlank(message = "Last name required")
    private String lastName;
    @NotBlank(message = "telephone required")
    private String telephone;
    @NotBlank(message = "Provide a temporary password for the user that can be edited later")
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
    @ManyToOne
    @JoinColumn(name = "fuel_station_id", nullable = false)
    private FuelStation fuelStation;

    public User(){}

    public User(String firstName, String lastName, String telephone, String password, int districtId, int fuelStationId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.password = password;
        this.district = new District(districtId, "");
        this.fuelStation = new FuelStation(fuelStationId, "", "", "","", 0);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public FuelStation getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
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
}
