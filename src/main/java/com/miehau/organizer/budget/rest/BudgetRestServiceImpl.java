package com.miehau.organizer.budget.rest;

import com.miehau.organizer.budget.entity.BudgetItem;
import com.miehau.organizer.budget.service.BudgetService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
@RestController
@RequestMapping("/budget")
public class BudgetRestServiceImpl {
    private BudgetService budgetService;

    public BudgetRestServiceImpl(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/allItems")
    public Collection<BudgetItem> getAllItems() {
        return budgetService.getAllItems();
    }



}