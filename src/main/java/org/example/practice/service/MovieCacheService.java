package org.example.practice.service;

import org.example.practice.entity.Movie;

public interface MovieCacheService {
    void cacheMovieInfo(String movieId, Movie movie);
    Movie getMovieFromCache(String movieId);
    void evictMovieCache(String movieId);
}
