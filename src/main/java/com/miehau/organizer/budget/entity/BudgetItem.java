package com.miehau.organizer.budget.entity;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "budget_item")
public class BudgetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal amount;
    @Column(name = "creation_date")
    private Date creationDate;

    public BudgetItem(String name, String description, BigDecimal amount, Date creationDate) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetItem that = (BudgetItem) o;
        return  Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, amount, creationDate);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
