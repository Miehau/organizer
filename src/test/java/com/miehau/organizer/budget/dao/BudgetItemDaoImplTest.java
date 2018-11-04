package com.miehau.organizer.budget.dao;


import com.miehau.organizer.budget.entity.BudgetItem;
import com.miehau.organizer.budget.exception.ItemAlreadyExists;
import com.miehau.organizer.budget.exception.ItemNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class BudgetItemDaoImplTest {
    @Autowired
    BudgetDao dao;


    @Test
    @Rollback
    public void shouldAddBudgetItem() throws ItemNotFoundException, ItemAlreadyExists {
        //given
        BudgetItem input = new BudgetItem("Milk", "Goat", new BigDecimal("1"), new Date());

        //when
        dao.save(input);

        //then
        assertEquals(3, dao.getAll().size());
        assertEquals(input, dao.getOne(input.getId()));
    }

    @Test
    @Rollback
    public void shouldGetZeroItemsIfEmpty() {
        //given
        dao.deleteAll();
        //when
        Collection<BudgetItem> itemsRetrieved = dao.getAll();

        //then
        assertEquals(0, itemsRetrieved.size());
    }

    @Test(expected = ItemNotFoundException.class)
    @Rollback
    public void shouldThrowExceptionIfRetrievingNonExistingItem() throws ItemNotFoundException {
        //when
        dao.getOne(999L);
    }

    @Test
    @Rollback
    public void shouldDeleteOneByIdIfExists() throws ItemNotFoundException {
        //given
        Long idToDelete = 100L;

        //when
        dao.delete(idToDelete);

        //then
        assertEquals(1, dao.getAll().size());
    }

    @Test(expected = ItemNotFoundException.class)
    @Rollback
    public void shouldThrowExceptionIfDeletesNonExistent() throws ItemNotFoundException {
        //when
        dao.delete(999L);
    }


    @Test
    @Rollback
    public void shouldUpdateExistingElement() throws ItemNotFoundException, ItemAlreadyExists {
        //given
        BudgetItem input = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), new Date());
        String newName = "Milk";
        //when
        dao.save(input);
        input.setName(newName);
        dao.update(input);
        Collection<BudgetItem> result = dao.getAll();

        //then
        assertEquals(true, result.contains(input));
    }


    @Test
    @Rollback
    public void shouldGetItemsInBetweenDates() throws ItemAlreadyExists {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 1, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 4, 1).build().getTime();
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 4, 1).build().getTime();
        Date creationDateItem2 = new Calendar.Builder().setDate(2018, 3, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        BudgetItem item2 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem2);
        dao.save(item1);
        dao.save(item2);

        //when
        Collection<BudgetItem> result = dao.getItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(true, result.contains(item1));
        assertEquals(true, result.contains(item2));
    }

    @Test
    @Rollback
    public void shouldNotGetItemsInBetweenDatesOuter() throws ItemAlreadyExists {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 4, 1).build().getTime();
        Date creationDateItem2 = new Calendar.Builder().setDate(2018, 3, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        BudgetItem item2 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem2);
        dao.save(item1);
        dao.save(item2);

        //when
        Collection<BudgetItem> result = dao.getItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(0, result.size());
    }

    @Test
    @Rollback
    public void shouldGetItemBetweenDatesOnStartDate() throws ItemAlreadyExists {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        dao.save(item1);

        //when
        Collection<BudgetItem> result = dao.getItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(1, result.size());
        assertEquals(item1, result.iterator().next());
    }

    @Test
    @Rollback
    public void shouldGetItemBetweenDatesOnEndDate() throws ItemAlreadyExists {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        dao.save(item1);

        //when
        Collection<BudgetItem> result = dao.getItemsBetweenDates(startDate, endDate);

        //then
        assertEquals(1, result.size());
        assertEquals(item1, result.iterator().next());
    }

    @Test(expected = ItemAlreadyExists.class)
    @Rollback
    public void shouldNotAddItemIfAlreadyExists() throws ItemAlreadyExists {
        //given
        Date startDate = new Calendar.Builder().setDate(2018, 5, 1).build().getTime();
        Date endDate = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        Date creationDateItem1 = new Calendar.Builder().setDate(2018, 6, 1).build().getTime();
        BudgetItem item1 = new BudgetItem("Porridge", "Tesco!", new BigDecimal("0.5"), creationDateItem1);
        dao.save(item1);
        dao.save(item1);

        //when
        Collection<BudgetItem> result = dao.getItemsBetweenDates(startDate, endDate);

        //then
    }
}
