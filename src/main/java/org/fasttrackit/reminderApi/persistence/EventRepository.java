package org.fasttrackit.reminderApi.persistence;
import org.fasttrackit.reminderApi.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Page<Event> findEventByTitleAndAndEventDateGreaterThan(
            String searchTitle, long daysOverdue, Pageable pageable);

    Page<Event> findEventByTitleAndAndEventDateLessThan(
            String searchTitle, long daysLeft, Pageable pageable);

}
