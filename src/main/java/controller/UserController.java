package controller;

import dto.UserDTO;
import entity.UserEntity;
import entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserServiceImpl;
import util.RestResult;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/user")
    public String getUserProfilePage() {
        return "user";
    }

    /**
     * 通过 userId 获取用户信息 个人资料
     *
     * @param id 用户 Id
     * @return UserDTO
     */
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getUser(@RequestParam Integer id) {
        UserEntity entity = service.selectOne(id);
        UserDTO dto;
        if (entity.getType() == UserType.TEACHER) {
            dto = service.selectUserDTO(id);
        } else {
            dto = new UserDTO(entity);
        }
        logger.info(String.format("query user{%d} %s", id, entity));
        return new RestResult.Builder(200).data(entity).build();
    }

    /**
     * 学生修改个人资料
     *
     * @param id       id
     * @param phone    联系方式
     * @param email    邮箱
     * @param response post 请求 跨域 开发用
     * @return success || fail
     */
    @RequestMapping(value = "/api/user/stu", method = RequestMethod.POST)
    @ResponseBody
    public RestResult updateStu(@RequestParam int id,
                                @RequestParam String phone,
                                @RequestParam String email,
                                HttpServletResponse response) {
        logger.info(String.format("update stu{%d} %s %s", id, phone, email));
        UserEntity entity = service.selectOne(id);
        entity.setEmail(email);
        entity.setTelephone(phone);
        int res = service.update(entity);
        logger.info(String.format("update stu{%d}  --> %d", id, res));
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        return new RestResult.Builder(200).message(res == 1 ? "success" : "fail").build();
    }

    /**
     * 教师修改个人资料
     *
     * @param id
     * @param phone
     * @param email
     * @param office
     * @param response post 请求 跨域 开发用
     * @return success || fail
     */
    @RequestMapping(value = "/api/user/tch", method = RequestMethod.POST)
    @ResponseBody
    public RestResult updateTch(@RequestParam int id,
                                @RequestParam String phone,
                                @RequestParam String email,
                                @RequestParam String office,
                                HttpServletResponse response) {
        logger.info(String.format("update tch{%d} %s %s %s", id, phone, email, office));
        UserEntity entity = service.selectOne(id);
        entity.setEmail(email);
        entity.setTelephone(phone);
        entity.setOffice(office);
        int res = service.update(entity);
        logger.info(String.format("update tch{%d}  --> %d", id, res));
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new RestResult.Builder(200).message(res == 1 ? "success" : "fail").build();
    }

    /**
     * 用户修改登陆密码
     *
     * @param id
     * @param oldPass
     * @param newPass
     * @param checkPass
     * @param response  post 请求 跨域 开发用
     * @return success || fail
     */
    @RequestMapping(value = "/api/user/pwd", method = RequestMethod.POST)
    @ResponseBody
    public RestResult updatePassword(@RequestParam int id,
                                     @RequestParam String oldPass,
                                     @RequestParam String newPass,
                                     @RequestParam String checkPass,
                                     HttpServletResponse response) {
        logger.info(String.format("update password {%d} %s %s", id, oldPass, newPass));
        UserEntity entity = service.selectOne(id);
        String msg = "fail";
        if (oldPass.equals(entity.getPassword()) && newPass.equals(checkPass)) {
            entity.setPassword(newPass);
            int res = service.update(entity);
            if (res > 0) {
                msg = "success";
            }
        }
        logger.info(String.format("update password {%d} --> %s", id, msg));
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new RestResult.Builder(200).message(msg).build();
    }

    /**
     * 教师修改七牛云账号
     *
     * @param id
     * @param accessKey
     * @param secretKey
     * @param bucket
     * @param response  post 请求 跨域 开发用
     * @return success || fail
     */
    @RequestMapping(value = "/api/qiniu", method = RequestMethod.POST)
    @ResponseBody
    public RestResult updateBucket(@RequestParam Integer id,
                                   @RequestParam String accessKey,
                                   @RequestParam String secretKey,
                                   @RequestParam String bucket,
                                   HttpServletResponse response) {
        logger.info(String.format("update bucket %d %s %s %s", id, accessKey, secretKey, bucket));
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new RestResult.Builder(200).message("success").build();
    }
}
