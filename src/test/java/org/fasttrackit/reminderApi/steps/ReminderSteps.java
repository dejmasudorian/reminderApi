package org.fasttrackit.reminderApi.steps;
import org.fasttrackit.reminderApi.domain.DatetoString;
import org.fasttrackit.reminderApi.domain.LevelOfImportance;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.transfer.Reminder.CreateReminderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import java.util.Date;

import static org.fasttrackit.reminderApi.domain.LevelOfImportance.MEDIUM;

@Component
public class ReminderSteps {

    @Autowired
    private ReminderServices reminderServices;

    private DatetoString datetoString;

    public Reminder createReminder() throws ParseException {
        CreateReminderRequest request = new CreateReminderRequest();

        Date date1 = datetoString.converter("16-02-2019");
        Date date2 = datetoString.converter("01-02-2019");

        request.setRemindDate(date1);
        request.setTitle("Day off");
        request.setDetails("Saint Patrick's Day");
        request.setCreatedBy("Alex");
        request.setLevelOfImportance(MEDIUM);
        request.setReminderCreatedDate(date2);
        return reminderServices.createReminder(request);
    }
}
