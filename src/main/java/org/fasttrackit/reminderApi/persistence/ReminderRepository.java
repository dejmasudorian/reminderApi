package org.fasttrackit.reminderApi.persistence;

import org.fasttrackit.reminderApi.domain.Reminder;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReminderRepository extends PagingAndSortingRepository<Reminder, Long> {

}
