package org.fasttrackit.reminderApi.persistence;

import org.fasttrackit.reminderApi.domain.Notice;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoticeRepository extends PagingAndSortingRepository<Notice, Long> {

    Page<Reminder> findReminderByLevelOfImportance(
            Enum levelofimportance, Pageable pageable);
}
