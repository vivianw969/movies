package org.example.practice.service.impl;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.practice.service.EmailService;
import org.example.practice.utils.SendEmailUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.transform.Result;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

    @Resource
    private SendEmailUtils sendEmailUtils;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
