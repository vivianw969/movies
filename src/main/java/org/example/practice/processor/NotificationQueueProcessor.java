/*package org.example.practice.processor;

import org.example.practice.entity.Movie;
//import org.example.practice.service.NotificationService;
import org.example.practice.service.EmailQueueConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationQueueProcessor {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    //@Autowired
    //private NotificationService notificationService;
    private static final String QUEUE_NAME = "notificationQueue";//使用常量方便复用和修改

    @Scheduled(fixedRate = 5000)
    public void processQueue(){
        Map<String,Object> task;
        while((task = (Map<String,Object>) redisTemplate.opsForList().rightPop(QUEUE_NAME))!=null){
            String action = (String) task.get("action");
            Movie movie = (Movie) task.get("movie");
            if(action.equals("UPDATE")){
                EmailQueueConsumer.sendNotification(movie,action);
            }
        }
    }
}
*/