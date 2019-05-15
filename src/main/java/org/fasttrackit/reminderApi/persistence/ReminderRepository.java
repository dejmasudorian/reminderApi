package org.fasttrackit.reminderApi.persistence;

import org.fasttrackit.reminderApi.domain.Reminder;
import org.springframework.data.repository.CrudRepository;

public interface ReminderRepository extends CrudRepository<Reminder, Long>{


}
