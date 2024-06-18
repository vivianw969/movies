package org.example.practice.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.practice.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailQueueConsumer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private NotificationService notificationService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  // 忽略未知字段;

    @Scheduled(fixedRate = 10000) // 每隔10秒执行一次
    public void consumeEmailQueue() {
        System.out.println("Scheduled task running...");
        String taskJson = stringRedisTemplate.opsForList().rightPop("notificationQueue");
        if (taskJson != null) {
            try {
                System.out.println("Consuming task: " + taskJson);
                Map<String, Object> task = objectMapper.readValue(taskJson, Map.class);
                String action = (String) task.get("action");
                Movie movie = objectMapper.convertValue(task.get("movie"), Movie.class);
                notificationService.sendNotification(movie, action);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No task found in queue");
        }
    }
}

