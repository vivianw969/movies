package org.example.practice.service;

import org.example.practice.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationQueueService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String QUEUE_NAME = "notificationQueue";

    public void enqueueNotificationTask(Movie movie, String action) {
        if (movie == null || action == null || action.isEmpty()) {
            throw new IllegalArgumentException("Movie and action must not be null or empty");
        }
        Map<String, Object> task = new HashMap<>();
        task.put("action", action);
        task.put("movie", movie);
        redisTemplate.opsForList().leftPush(QUEUE_NAME, task);

    }


            }

