package org.fasttrackit.reminderApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.persistence.ReminderRepository;
import org.fasttrackit.reminderApi.transfer.CreateReminderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReminderServices {



    private ReminderRepository repository;
    private final ObjectMapper objectMapper;
    private final ReminderRepository reminderRepository;



    public List<Reminder> getAllReminders(){
        return repository.findAll();
    }
    public Reminder getReminder(long id){
        return repository.getOne(id);
    }
    public Reminder updateReminder(Reminder reminder){
        return repository.saveAndFlush(reminder);
    }
    public void deleteReminder(Reminder reminder){
        repository.delete(reminder);
    }


    @Autowired
    public ReminderServices(ObjectMapper objectMapper, ReminderRepository reminderRepository) {
        this.objectMapper = objectMapper;
        this.reminderRepository = reminderRepository;
    }

    public Reminder createReminder(CreateReminderRequest request){
        Reminder reminder = objectMapper.convertValue(request, Reminder.class);

        return reminderRepository.save(reminder);
    }


}