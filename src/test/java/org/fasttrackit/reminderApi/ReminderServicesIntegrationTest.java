package org.fasttrackit.reminderApi;

import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.transfer.CreateReminderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
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

        System.out.println("Reminders created in table: " + services.getAllReminders());
        System.out.println("Deleting reminder - " + reminder1.getTitle());
        services.removeReminder(reminder1);

        System.out.println("Returning deleted reminder.");
        services.saveReminder(reminder1);


    }
}
