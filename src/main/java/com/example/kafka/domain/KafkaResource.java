package com.example.kafka.domain;


import com.example.kafka.util.KafkaAdminService;
import com.example.kafka.util.KafkaPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@RestController
//@RequestMapping("/kafka")
public class KafkaResource {

    public static final String TOPIC_ATTRIBUTE = "topicName";

    public static final String TOPIC_PATH = "/topic/{" + TOPIC_ATTRIBUTE + "}";
    public static final String EVENT_PATH = "/event";

    @Autowired
    KafkaRepository repository;


    @PostMapping(EVENT_PATH)
    public void sendOnTopic(@RequestParam(TOPIC_ATTRIBUTE) String topicName, @RequestBody KafkaObject object) {
        Objects.requireNonNull(topicName);

        KafkaPublisherService publisher = KafkaPublisherService.getINSTANCE();
        publisher.sendOnTopic(topicName, object);
    }

    @GetMapping(EVENT_PATH)
    public Collection<KafkaObject> getFromTopic(@RequestParam(TOPIC_ATTRIBUTE) String topicName) {
        Objects.requireNonNull(topicName);

        return repository.getObjectsFromTopic(topicName);
    }

    @GetMapping(TOPIC_PATH)
    public Set<String> getTopics() {
        KafkaAdminService adminService = KafkaAdminService.getINSTANCE();
        return adminService.getTopics();
    }

    @PostMapping(TOPIC_PATH)
    public void createTopic(@PathVariable(TOPIC_ATTRIBUTE) String topicName) {
        Objects.requireNonNull(topicName);

        KafkaAdminService adminService = KafkaAdminService.getINSTANCE();
        adminService.createTopic(topicName);
    }

    @DeleteMapping(TOPIC_PATH)
    public void deleteTopic(@PathVariable(TOPIC_ATTRIBUTE) String topicName) {
        Objects.requireNonNull(topicName);

        KafkaAdminService adminService = KafkaAdminService.getINSTANCE();
        adminService.deleteTopic(topicName);
    }

}
