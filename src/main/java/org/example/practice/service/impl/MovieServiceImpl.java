package org.example.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.practice.entity.Movie;
import org.example.practice.mapper.MovieMapper;
import org.example.practice.service.MovieService;
import org.example.practice.service.NotificationQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private NotificationQueueService notificationQueueService;

    @Override
    public List<Movie> selectAll() {
        return movieMapper.selectAll();
    }
    @Override
    public Movie getById(Integer id){
        return movieMapper.getById(id);
    }
    @Override
    public int insertMovie(Movie movie) {
        return movieMapper.insert(movie);
    }
    @Override
    public boolean updateMovie(Movie movie) {
        int rowsAffected = movieMapper.updateById(movie);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to update movie");
        }

        notificationQueueService.enqueueNotificationTask(movie, "update");
        return movieMapper.updateById(movie) > 0;
    }
    @Override
    public boolean deleteMovieById(Integer id) {
        movieMapper.deleteById(id);
        return true;
    }
}
