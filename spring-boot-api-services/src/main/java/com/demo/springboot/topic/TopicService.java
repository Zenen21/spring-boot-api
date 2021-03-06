package com.demo.springboot.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TopicService {

    Logger logger = Logger.getLogger(TopicService.class.getName());

    private List<Topic> topics = new ArrayList<>(
            Arrays.asList(new Topic("Spring", "Spring Framework"), new Topic("Services", "Spring boot")));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String name) {
        return topics.stream().filter(t -> t.getName().equals(name)).findFirst().get();
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void addTopicFromJsonFile(MultipartFile json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            InputStream inputStream = json.getInputStream();

            Topic topic = objectMapper.readValue(inputStream, Topic.class);

            topics.add(topic);

            logger.info("New Topic with name = " + topic.getName());
            logger.info("New Topic with description  = " + topic.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
