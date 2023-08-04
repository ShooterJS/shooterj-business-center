package com.shooterj.sys.api;

import com.shooterj.common.annotation.MyRequestBody;
import com.shooterj.sys.domain.model.course.CourseDto;
import com.shooterj.sys.domain.model.course.CourseDto2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ShooterJ
 * @description
 * @date: 2023/7/31 17:39
 */
@Slf4j
@RestController
@RequestMapping("/myRequestBody")
public class MyRequestBodyController {

    @PostMapping("/list")
    public void list(@MyRequestBody CourseDto courseDto,@MyRequestBody CourseDto2 courseDto2) {
        System.out.println(courseDto);
        System.out.println(courseDto2);
    }

}
