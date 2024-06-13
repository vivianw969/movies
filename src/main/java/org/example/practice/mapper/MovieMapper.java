package org.example.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.practice.entity.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMapper extends BaseMapper<Movie> {
    @Select("SELECT * FROM Movies " +
            "WHERE (Movie_Title LIKE concat('%',#{title},'%') OR " +
            "Movie_Release_Year = #{year} OR " +
            "Movie_Rating >= #{minRating}) " +
            "ORDER BY Movie_ID")
    List<Movie> selectAll();

    @Select("SELECT * FROM Movies WHERE (Movie_ID = #{id})")
    Movie getById(Integer id);

    @Insert("INSERT INTO Movies(Movie_Title,Movie_Release_Year,Movie_Rating) values (#{title},#{year},#{rating})")
    int insert(Movie movie);

    @Update("UPDATE Movies SET Movie_Title= #{title}, Movie_Release_Year =#{year}, Movie_Rating =#{rating}" +
            "WHERE (Movie_ID = #{id})")
    int updateById(Movie movie);

    @Delete("DELETE from Movies where Movie_ID = #{id}")
    void deleteById(Integer id);
}
