package com.miehau.organizer.budget.service;

import com.miehau.organizer.budget.dao.BudgetDao;
import com.miehau.organizer.budget.entity.BudgetItem;
import com.miehau.organizer.budget.exception.ItemAlreadyExists;
import com.miehau.organizer.budget.exception.ItemNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BudgetServiceTest {
    @Mock
    BudgetDao dao;
    @InjectMocks
    BudgetServiceImpl budgetService;

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
        Collection<BudgetItem> result = budgetService.getAllItems();

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

    @Test
    public void shouldAddNewItem() throws ItemAlreadyExists {
        //given
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        when(dao.getAll()).thenReturn(Arrays.asList(item1));
        //when
        budgetService.addItem(item1);
        //then
        verify(dao).save(item1);


    }

    @Test(expected = ItemAlreadyExists.class)
    public void shouldNotAddElementIfExists() throws ItemAlreadyExists {
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        doThrow(new ItemAlreadyExists()).when(dao).save(any());
        //when
        budgetService.addItem(item1);
    }


    @Test
    public void shouldDeleteElement() throws ItemNotFoundException {
        //given
        Long id = new Long(3);
        //when
        budgetService.deleteItem(id);
        //then
        verify(dao).delete(id);
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldThrowExceptionOnDeleteElementIfNotExistent() throws ItemNotFoundException {
        //given
        Long id = new Long(1);
        doThrow(new ItemNotFoundException()).when(dao).delete(id);
        //when
        budgetService.deleteItem(id);
    }

    @Test
    public void shouldUpdateElementIfExists() throws ItemNotFoundException {
        //given
        BudgetItem item = new BudgetItem();
        //when
        budgetService.update(item);
        //then
        verify(dao).update(item);
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldThrowExceptionIfElementDoesntExistOnUpdate() throws ItemNotFoundException {
        //given
        BudgetItem item = new BudgetItem();
        doThrow(new ItemNotFoundException()).when(dao).update(item);

        //when
        budgetService.update(item);

    }


}
