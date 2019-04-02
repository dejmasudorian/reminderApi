package org.fasttrackit.reminderApi.persistence;

import org.fasttrackit.reminderApi.domain.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder,Long> {

}
