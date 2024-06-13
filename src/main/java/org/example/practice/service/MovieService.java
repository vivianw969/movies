package org.example.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.practice.entity.Movie;

import java.util.List;

public interface MovieService extends IService<Movie> {
    List<Movie> selectAll();
    Movie getById(Integer id);
    int insertMovie(Movie movie);
    boolean updateMovie(Movie movie);
    boolean deleteMovieById(Integer id);
}

