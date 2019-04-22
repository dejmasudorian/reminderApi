package org.fasttrackit.reminderApi;

import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.transfer.Reminder.CreateReminderRequest;
import org.fasttrackit.reminderApi.transfer.Reminder.UpdateReminderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReminderServicesIntegrationTest {

    @Autowired
    ReminderServices services;

    @Test
    public void testCreateReminder() throws ParseException {
        CreateReminderRequest createReminder1 = new CreateReminderRequest();
        CreateReminderRequest createReminder2 = new CreateReminderRequest();

        String stringDate = "16-02-2019";
        SimpleDateFormat index = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = index.parse(stringDate);
        createReminder1.setRemindDate(date1);
        createReminder1.setTitle("Day off");
        createReminder1.setDescription("Saint Patrick's Day");

        String stringDate2 = "18-04-2019";
        SimpleDateFormat index2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = index2.parse(stringDate2);
        createReminder2.setRemindDate(date2);
        createReminder2.setTitle("Work");
        createReminder2.setDescription("No comment");

        Reminder reminder1 = services.createReminder(createReminder1);
        Reminder reminder2 = services.createReminder(createReminder2);

        assertThat(reminder1, notNullValue());
        assertThat(reminder1.getId(), greaterThan(0L));
        assertThat(reminder2, notNullValue());
        assertThat(reminder2.getId(), greaterThan(0L));

        System.out.println("Deleting reminder - " + reminder1.getTitle());
        services.deleteReminder(reminder1.getId());

    }

    public Reminder createReminder() throws ParseException {
        CreateReminderRequest request = new CreateReminderRequest();
        String stringDate = "16-02-2019";
        SimpleDateFormat index = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = index.parse(stringDate);
        request.setRemindDate(date1);
        request.setTitle("Day off");
        request.setDescription("Saint Patrick's Day");
        return services.createReminder(request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProduct_whenReminderNotFound_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        //productService.getProduct(0);
        services.getReminder(0);
    }

    @Test
    public void testGetProduct_whenExistingId_thenReturnMatchingProduct() throws ResourceNotFoundException, ParseException {
        Reminder reminder = createReminder();

        Reminder retrievedReminder = services.getReminder(reminder.getId());

        assertThat(retrievedReminder.getId(), is(reminder.getId()));
        assertThat(retrievedReminder.getTitle(), is(reminder.getTitle()));

        }
/*
    @Test
    public void testUpdateProduct_whenValidRequestWithAllFields_thenReturnUpdatedProduct() throws ResourceNotFoundException, ParseException {
        Reminder createdReminder = createReminder();

        UpdateReminderRequest request = new UpdateReminderRequest();
        request.setTitle(createdReminder.getTitle() + "_Edited");
        request.setDescription(createdReminder.getTitle() + "_Edited");
        request.setRemindDate(createdReminder.getRemindDate() );


        Reminder updatedReminder =
                services.updateReminder(createdReminder.getId());

        assertThat(updatedReminder.getTitle(), is(request.getTitle()));
        assertThat(updatedReminder.getTitle(), not(is(createdReminder.getTitle())));

        assertThat(updatedReminder.getDescription(), is(request.getDescription()));
        assertThat(updatedReminder.getRemindDate(), is(request.getRemindDate()));

        assertThat(updatedReminder.getId(), is(createdReminder.getId()));
    }
*/
    // todo: Implement negative tests for update and tests for update with some fields only

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteProduct_whenExistingId_thenProductIsDeleted() throws ResourceNotFoundException, ParseException {
        Reminder createdReminder = createReminder();

        services.deleteReminder(createdReminder.getId());

        services.getReminder(createdReminder.getId());
    }


}
