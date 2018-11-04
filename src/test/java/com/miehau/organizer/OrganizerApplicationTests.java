package com.miehau.organizer;

import com.miehau.organizer.budget.dao.BudgetItemDaoImplTest;
import com.miehau.organizer.budget.rest.BudgetRestServiceImpl;
import com.miehau.organizer.budget.rest.BudgetRestServiceTest;
import com.miehau.organizer.budget.service.BudgetServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Suite.class)
@Suite.SuiteClasses({BudgetItemDaoImplTest.class,//
        BudgetServiceTest.class, //
        BudgetRestServiceTest.class})
public class OrganizerApplicationTests {

}
