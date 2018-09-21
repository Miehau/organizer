package com.miehau.organizer.budget.service;

import com.miehau.organizer.budget.dao.BudgetDao;
import com.miehau.organizer.budget.dao.BudgetItemDaoImpl;
import com.miehau.organizer.budget.entity.BudgetItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BudgetServiceTest {
    BudgetDao dao = mock(BudgetItemDaoImpl.class);
    BudgetService budgetService = new BudgetServiceImpl(dao);

    @Test
    public void shouldReturnBudgetItemsBetweenDates() {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 5, 10).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        when(dao.getItemsBetweenDates(startDate, endDate)).thenReturn(Arrays.asList(item1));
        //when
        Collection<BudgetItem> result = budgetService.getBudgetItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(1, result.size());
        assertEquals(item1, result.iterator().next());
    }

    @Test
    public void shouldReturnEmptyListIfNoMatchingElements() {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        when(dao.getItemsBetweenDates(startDate, endDate)).thenReturn(Arrays.asList());

        //when
        Collection<BudgetItem> result = budgetService.getBudgetItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnAllBudgetItems() {
        //given
        when(dao.getAll()).thenReturn(Arrays.asList(new BudgetItem()));
        //when
        Collection<BudgetItem> result = budgetService.getAllBudgetItems();

        //then
        assertEquals(1, result.size());
    }

    @Test
    public void shouldReturnItemAtStartDate() {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        when(dao.getItemsBetweenDates(startDate, endDate)).thenReturn(Arrays.asList(item1));
        //when
        Collection<BudgetItem> result = budgetService.getBudgetItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(1, result.size());
        assertEquals(item1, result.iterator().next());
    }

    @Test
    public void shouldReturnItemAtEndDate() {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        when(dao.getItemsBetweenDates(startDate, endDate)).thenReturn(Arrays.asList(item1));
        //when
        Collection<BudgetItem> result = budgetService.getBudgetItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(1, result.size());
        assertEquals(item1, result.iterator().next());
    }

    @Test
    public void shouldNotReturnItemsIfDatesDoesntMatch() {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        when(dao.getItemsBetweenDates(startDate, endDate)).thenReturn(Arrays.asList());
        //when
        Collection<BudgetItem> result = budgetService.getBudgetItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(0, result.size());
    }

    public void shouldAddNewItem(){

    }
    public void shouldNotAddElementIfExists(){}
    public void shouldDeleteElement(){}
    public void shouldThrowExceptionIfElementsDoesntExist(){}
    public void shouldUpdateElement(){}
    public void shouldThrowExceptionIfElementDoesntExist(){}


}
