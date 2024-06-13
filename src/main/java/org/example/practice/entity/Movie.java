package org.example.practice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("Movies")
public class Movie {
    @TableId("Movie_ID")
    private int id;
    @TableField("Movie_Title")
    private String title;
    @TableField("Movie_Release_Year")
    private int year;
    @TableField("Movie_Rating")
    private Double rating;

}

