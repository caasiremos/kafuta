package com.kafuta.Kafuta.web.customer;

import com.kafuta.Kafuta.web.district.District;
import com.kafuta.Kafuta.web.fuelstation.FuelStation;
import com.kafuta.Kafuta.web.stage.Stage;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "First name required")
    private String firstName;
    @NotBlank(message = "Last name required")
    private String lastName;
    @NotBlank(message = "Telephone required")
    private String telephone;
    @NotBlank(message = "Plate number required")
    private String plateNumber;
    @NotBlank(message = "Date of birth required")
    private String dateOfBirth;
    private String secretCode;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;
    @ManyToOne
    @JoinColumn(name = "fuel_station_id")
    private FuelStation fuelStation;
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Customer(){}

    public Customer(int id, String firstName,
                    String lastName, String telephone, String dateOfBirth, String plateNumber,
                    String secretCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.dateOfBirth = dateOfBirth;
        this.plateNumber = plateNumber;
        this.secretCode = secretCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public FuelStation getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
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
        return "Customer{" +
                "Id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", secretCode='" + secretCode + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", stageId=" + stage +
                ", fuelStationId=" + fuelStation +
                ", district=" + district +
                '}';
    }
}
