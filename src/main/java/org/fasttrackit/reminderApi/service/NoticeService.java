package org.fasttrackit.reminderApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.reminderApi.domain.Notice;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.persistence.NoticeRepository;
import org.fasttrackit.reminderApi.transfer.Notice.CreateNoticeRequest;
import org.fasttrackit.reminderApi.transfer.Notice.UpdateNoticeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(NoticeService.class);

    private final ObjectMapper objectMapper;
    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(ObjectMapper objectMapper, NoticeRepository noticeRepository) {
        this.objectMapper = objectMapper;
        this.noticeRepository = noticeRepository;
    }


    public Notice createNotice(CreateNoticeRequest request) {
        LOGGER.info("Creating notice for reminder {}", request);
        Notice notice = objectMapper.convertValue(request, Notice.class);
        return noticeRepository.save(notice);
    }

    public Notice getNotice(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving notice for reminder {}", id);
        return noticeRepository.findById(id)
                // Optional and lambda expression
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notice " + id + " not found"));
    }

    public Notice updateNotice(long id, UpdateNoticeRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating notice {}, {}", id, request);
        Notice notice = getNotice(id);

        BeanUtils.copyProperties(request, notice);

        return noticeRepository.save(notice);
    }

    public void deleteReminder(long id) {
        LOGGER.info("Deleting notice {}", id);
        noticeRepository.deleteById(id);
        LOGGER.info("Deleted notice {}", id);
    }


}
