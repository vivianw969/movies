package org.example.practice.service.impl;


import org.example.practice.entity.Movie;
import org.example.practice.service.MovieCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovieCacheServiceImpl implements MovieCacheService {
    //将 Spring 中配置好的 RedisTemplate 自动注入到当前的服务类中。
    //RedisTemplate 是 Spring Data Redis 提供的一个模板类，用于执行 Redis 的各种操作，如 set、get、delete 等。
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String MOVIE_CACHE_PREFIX = "movie:"; //设置前缀可防止键冲突+模拟空间隔离
    @Override
    public void cacheMovieInfo(String movieId, Movie movie) {
        redisTemplate.opsForValue().set(MOVIE_CACHE_PREFIX+movieId, movie);
    }

    @Override
    public Movie getMovieFromCache(String movieId) {
        return (Movie) redisTemplate.opsForValue().get(MOVIE_CACHE_PREFIX+movieId);
    }

    @Override
    public void evictMovieCache(String movieId) {
        redisTemplate.delete(MOVIE_CACHE_PREFIX+movieId);
    }

}
