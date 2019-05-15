package org.fasttrackit.reminderApi.steps;
import org.fasttrackit.reminderApi.domain.DatetoString;
import org.fasttrackit.reminderApi.domain.Notice;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.service.NoticeService;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.transfer.Notice.CreateNoticeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fasttrackit.reminderApi.domain.LevelOfImportance.MEDIUM;

@Component
public class NoticeSteps {

    @Autowired
    private NoticeService noticeService;

    private DatetoString datetoString;

    public Notice createNotice() throws ParseException {
        CreateNoticeRequest request = new CreateNoticeRequest();

        SimpleDateFormat index = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = index.parse("16-02-2019");
        Date date2 = index.parse("01-02-2019");

        request.setRemindDate(date1);
        request.setTitle("Day off");
        request.setDetails("Saint Patrick's Day");
        request.setCreatedBy("Alex");
        request.setLevelOfImportance(MEDIUM);
        request.setReminderCreatedDate(date2);
        return noticeService.createNotice(request);
    }
}
