package org.fasttrackit.reminderApi.persistence;

import org.fasttrackit.reminderApi.domain.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ReminderRepository extends CrudRepository<Reminder, Long>{

    Page<Reminder> findReminderByLevelOfImportance(
        Enum levelofimportance, Pageable pageable);

}
