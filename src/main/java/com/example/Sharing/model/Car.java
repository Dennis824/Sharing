package com.example.Sharing.model;


import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "firm")
    private Firm firm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type")
    private Type type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmission")
    private Transmission transmission;

    @Column(name = "insurance")
    private int insurance;

    @Column(name = "cost_per_day")
    private int costPerDay;

    private String number;

    @Override
    public String toString() {
        return number+", "+firm.getName()+", "+type.getName()+", "+transmission.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
