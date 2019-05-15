package org.fasttrackit.reminderApi;

import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.domain.Notice;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.steps.EventSteps;
import org.fasttrackit.reminderApi.steps.NoticeSteps;
import org.fasttrackit.reminderApi.transfer.Reminder.SaveReminderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReminderServiceIntegrationTests {

	@Autowired
	private ReminderServices reminderServices;

	@Autowired
	private EventSteps eventSteps;

	@Autowired
	private NoticeSteps noticeSteps;

	@Test
	public void testAddEventsToReminder_whenValidRequest_thenReturnCart() throws ResourceNotFoundException, ParseException {
		Event event = eventSteps.createEvent();
		Notice notice = noticeSteps.createNotice();

		SaveReminderRequest request = new SaveReminderRequest();
		request.setNoticeId(notice.getId());
		request.setEventIds(Collections.singleton(event.getId()));

		Reminder reminder = reminderServices.addEventToReminder(request);

		assertThat(reminder, notNullValue());
		assertThat(reminder.getId(), is(notice.getId()));

		assertThat(reminder.getEvents(), notNullValue());
		assertThat(reminder.getEvents(), hasSize(1));
	}

}