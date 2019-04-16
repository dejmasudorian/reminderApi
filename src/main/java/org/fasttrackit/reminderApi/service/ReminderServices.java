package org.fasttrackit.reminderApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.persistence.ReminderRepository;
import org.fasttrackit.reminderApi.transfer.CreateReminderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Service
public class ReminderServices {



    private ReminderRepository repository;
    private final ObjectMapper objectMapper;
    private final ReminderRepository reminderRepository;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Reminder> getAllReminders(){
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Reminder getReminder (long id){
        return repository.getOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Reminder updateReminder(Reminder reminder){
        return repository.saveAndFlush(reminder);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
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