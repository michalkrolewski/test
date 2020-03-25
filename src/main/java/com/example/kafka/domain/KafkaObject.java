package com.example.kafka.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KafkaObject {
    public String name;

    @JsonProperty
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "'\"}";
    }
}
