package com.activiti.controller;

import com.activiti.common.aop.ApiAnnotation;
import com.activiti.common.utils.CommonUtil;
import com.activiti.pojo.schedule.ScheduleDto;
import com.activiti.pojo.user.StudentWorkInfo;
import com.activiti.pojo.user.User;
import com.activiti.service.JudgementService;
import com.activiti.service.ScheduleService;
import com.activiti.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 12490 on 2017/8/1.
 */
@RequestMapping("/user")
@RestController
public class UserController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private JudgementService judgementService;
    @Autowired
    private ScheduleService scheduleService;

    /*
     *  根据Email获取用户信息
     */

    @RequestMapping("/getUserInfo")
    @ResponseBody
    @ApiAnnotation
    public User getUserInfo(@RequestParam(value = "email", required = true) String email) throws Exception {
        return userService.findUserInfo(email);
    }

    /**
     * 提交作业
     *
     * @param studentWorkInfo courseCode,emailAddress,workDetail
     * @return
     */
    @RequestMapping("/commitWork")
    @ResponseBody
    @ApiAnnotation
    public Object commitWork(@RequestParam(value = "StudentWorkInfo") StudentWorkInfo studentWorkInfo) {
        User user = new User(commonUtil.getRandomUserName(), studentWorkInfo.getEmailAddress());
        userService.insertUser(user);
        studentWorkInfo.setLastCommitTime(new Date());
        userService.insertUserWork(studentWorkInfo);
        return studentWorkInfo;
    }

    /**
     * 查询学生提交的作业
     *
     * @param email
     * @param courseCode
     * @return
     */
    @RequestMapping("/selectStudentWorkInfo")
    @ResponseBody
    @ApiAnnotation
    public Object selectStudentWorkInfo(@RequestParam(value = "email", required = true) String email,
                                        @RequestParam(value = "courseCode", required = true) String courseCode) {
        StudentWorkInfo studentWorkInfo = new StudentWorkInfo();
        studentWorkInfo.setCourseCode(courseCode);
        studentWorkInfo.setEmailAddress(email);
        return userService.selectStudentWorkInfo(studentWorkInfo);
    }

    @RequestMapping("/selectWorkListToJudge")
    @ResponseBody
    @ApiAnnotation
    public Object selectWorkListToJudge(@RequestParam(value = "email", required = true) String email,
                                        @RequestParam(value = "courseCode", required = true) String courseCode) {
        StudentWorkInfo studentWorkInfo = new StudentWorkInfo();
        studentWorkInfo.setCourseCode(courseCode);
        int studentId = judgementService.selectChaosId(email);
        int countWork = judgementService.countAllWorks(courseCode);
        int judgeTimes = scheduleService.selectScheduleTime(courseCode).getJudgeTimes();
        int[] initIdList={studentId+1,studentId+2,studentId+3};
        List<StudentWorkInfo>workInfoList=new ArrayList<>();
//        for (int id:initIdList){
//            if (id>countWork)id=id-countWork;
//            studentWorkInfo.setEmailAddress(userService);
//            workInfoList.add(userService.selectStudentWorkInfo())
//        }
        return null;
    }
}

