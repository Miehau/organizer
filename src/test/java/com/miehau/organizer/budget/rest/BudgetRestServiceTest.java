package com.miehau.organizer.budget.rest;

import com.miehau.organizer.budget.entity.BudgetItem;
import com.miehau.organizer.budget.service.BudgetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BudgetRestServiceTest {

    @MockBean
    BudgetService budgetService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnAllBudgetItems() throws Exception {
        //given
        BudgetItem item1 = new BudgetItem();
        BudgetItem item2 = new BudgetItem();
        item1.setName("Maslo");
        item2.setName("Orzechy");
        when(budgetService.getAllItems()).thenReturn(Arrays.asList(item1, item2));

        //when
        ResultActions response = mockMvc.perform(get("/budget/allItems"));

        //then
        response.andExpect(status().isOk())//
                .andExpect(jsonPath("[0].name").value("Maslo"))//
                .andExpect(jsonPath("[1].name").value("Orzechy"));
    }

    public void shouldAddItem(){
        //given
        BudgetItem item1 = new BudgetItem();
    }
}
