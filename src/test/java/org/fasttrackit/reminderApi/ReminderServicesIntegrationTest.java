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
        CreateReminderRequest request = new CreateReminderRequest();
        String stringDate = "16-02-2019";
        SimpleDateFormat index = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = index.parse(stringDate);
        request.setRemindDate(date1);
        request.setTitle("Day off");
        Reminder reminder = services.createReminder(request);

        assertThat(reminder, notNullValue());
        assertThat(reminder.getId(), greaterThan(0L));
    }
}
