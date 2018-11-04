package com.miehau.organizer.budget;

import com.miehau.organizer.budget.dao.BudgetDao;
import com.miehau.organizer.budget.dao.BudgetItemDaoImpl;
import com.miehau.organizer.budget.service.BudgetService;
import com.miehau.organizer.budget.service.BudgetServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BudgetConfiguration {

    @Bean
    public BudgetDao getBudgetItemDao() {
        return new BudgetItemDaoImpl();
    }

    @Bean
    public BudgetService getBudgetService() {
        return new BudgetServiceImpl(getBudgetItemDao());
    }

}
