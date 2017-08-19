/*
Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : activiti

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE IF NOT EXISTS JUDGE_STU_INFO (
  USER_ID       INT(11)     NOT NULL AUTO_INCREMENT,
  USER_NAME     VARCHAR(20)          DEFAULT NULL,
  EMAIL_ADDRESS VARCHAR(30) UNIQUE NOT NULL,
  ABILITY_LEVEL VARCHAR(10)          DEFAULT NULL,
 INDEX index_email_address (EMAIL_ADDRESS),
  PRIMARY KEY (USER_ID)
)
  ENGINE = MyISAM  -- 采用MyISAM防止主键id自增出现断层现象
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for scheduleTime
-- ----------------------------
CREATE TABLE IF NOT EXISTS JUDGE_SCHEDULE_TIME (
  COURSE_NAME      VARCHAR(50) NOT NULL,
  COURSE_CODE      VARCHAR(20) NOT NULL,
  START_TIME       DATETIME    NOT NULL,
  COMMIT_END_TIME  DATETIME    NOT NULL,
  JUDGE_START_TIME DATETIME    NOT NULL,
  JUDGE_END_TIME   DATETIME    NOT NULL,
  AUDIT_START_TIME DATETIME    NOT NULL,
  AUDIT_END_TIME   DATETIME    NOT NULL,
  PUBLISH_TIME     DATETIME    NOT NULL,
  judgeTimes       INT(8) DEFAULT 3,
  UNIQUE INDEX index_COURSE_CODE (COURSE_CODE),
  PRIMARY KEY (COURSE_CODE)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table for record student`s workInfo
-- ----------------------------
CREATE TABLE IF NOT EXISTS JUDGE_STU_WORK_INFO (
  COURSE_CODE      VARCHAR(20)   NOT NULL,
  EMAIL_ADDRESS    VARCHAR(30)   NOT NULL,
  WORK_DETAIL      VARCHAR(5000) NOT NULL,
  LAST_COMMIT_TIME DATE          NOT NULL,
  GRADE            DOUBLE      DEFAULT NULL,
  JUDGE_TYPE       VARCHAR(10) DEFAULT NULL,
  JUDGE_TIMES      INT(8)      DEFAULT NULL,
  INDEX index_COURSE_CODE(COURSE_CODE, EMAIL_ADDRESS),
  INDEX index_JUDGE_TIMES(JUDGE_TIMES)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table for record judgement_ls
-- ----------------------------
CREATE TABLE IF NOT EXISTS JUDGE_JUDGEMENT_LS (
  COURSE_CODE      VARCHAR(20) NOT NULL,
  JUDGER_EMAIL     VARCHAR(30) NOT NULL,
  NON_JUDGER_EMAIL VARCHAR(30) NOT NULL,
  GRADE            DOUBLE      NOT NULL,
  JUDGE_TIME       DATE        NOT NULL,
  JUDGEMENT        VARCHAR(100) DEFAULT NULL,
  INDEX index_judge(COURSE_CODE, JUDGER_EMAIL),
  INDEX index_non_judge(COURSE_CODE, NON_JUDGER_EMAIL)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;