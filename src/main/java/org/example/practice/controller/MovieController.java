package org.example.practice.controller;

import org.example.practice.entity.Movie;
import org.example.practice.service.MovieCacheService;
import org.example.practice.service.MovieService;
import org.example.practice.service.NotificationQueueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Resource
    private MovieService movieService;

    @Resource
    private MovieCacheService movieCacheService;

    @GetMapping("/all")
    public List<Movie> selectAll(){
        return movieService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable String id) {
        Movie movie = movieCacheService.getMovieFromCache(id);
        if (movie == null) {
            movie = movieService.getById(id);
            if (movie != null){ //如果原数据库内有记录 再存入redis
                movieCacheService.cacheMovieInfo(id,movie);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie found with id: " + id);
            }
        }
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/add")
   // public int insertMovie(@RequestBody Movie movie){//@RequestBody 用于将 HTTP 请求体中的内容转换为 Movie 对象，
        // 并将其作为参数传递给控制器方法
    //    return movieService.insertMovie(movie);
    //}
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.insertMovie(movie);
        return ResponseEntity.ok("Movie added successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie, NotificationQueueService notificationQueueService) {
        movieCacheService.evictMovieCache(String.valueOf(movie.getId()));
        boolean updateResult = movieService.updateMovie(movie);
        if (updateResult) {
            notificationQueueService.enqueueNotificationTask(movie, "UPDATE");
            return ResponseEntity.ok("Movie updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update movie");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id){
        movieCacheService.evictMovieCache(String.valueOf(id));
        boolean deleteResult = movieService.deleteMovieById(id);
        if (deleteResult){
            return ResponseEntity.ok("Movie deleted successfully");
        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete movie");
        }
    }

}
