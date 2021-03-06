package com.activiti.service;

import com.activiti.mapper.UserMapper;
import com.activiti.pojo.user.StudentWorkInfo;
import com.activiti.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 12490 on 2017/8/1.
 */
public interface UserService {

    /**
     * 查询学生基础信息
     * @param emailAddr
     * @return
     */
    User findUserInfo(String emailAddr);

    /**
     * 插入学生信息
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 插入学生作业信息
     * @param studentWorkInfo
     * @return
     */
    int insertUserWork(StudentWorkInfo studentWorkInfo);

    /**
     * 查询学生作业信息
     * @param studentWorkInfo
     * @return
     */
    StudentWorkInfo selectStudentWorkInfo(StudentWorkInfo studentWorkInfo);

    /**
     * 查询没有分数的学生d的email
     * @param courseCode
     * @return
     */
    List<String> selectNoGradeUser(String courseCode);

    /**
     * 查询没有参加完互评的学生
     * @param courseCode
     * @return
     */
    List<String> selectUnFinishJudgeUser(String courseCode);

    /**
     * 打乱学生信息表
     * @return
     */
    int chaosUserInfo();

    /**
     * 删除混序学生表
     * @return
     */
    int deleteChaosUserInfo();
}
