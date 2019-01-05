package controller;

import dto.UserDTO;
import entity.QiniuEntity;
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
import service.QiniuService;
import service.UserServiceImpl;
import util.QiniuUtil;
import util.RestResult;

/**
 * @author ACM-PC
 */
@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserServiceImpl service;
    private final QiniuService qiniuService;

    @Autowired
    public UserController(UserServiceImpl service, QiniuService qiniuService) {
        this.service = service;
        this.qiniuService = qiniuService;
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
        if (entity.getType().equals(UserType.TEACHER)) {
            dto = service.selectUserDTO(id);
        } else {
            dto = new UserDTO(entity);
        }
        logger.info(String.format("query user %d ==> %s", id, dto));
        return new RestResult.Builder(200).data(dto).build();
    }

    /**
     * 学生修改个人资料
     *
     * @param id    id
     * @param phone 联系方式
     * @param email 邮箱
     * @return success || fail
     */
    @RequestMapping(value = "/api/user/stu", method = RequestMethod.POST)
    @ResponseBody
    public RestResult updateStu(@RequestParam int id,
                                @RequestParam String phone,
                                @RequestParam String email) {
        logger.info(String.format("update stu{%d} %s %s", id, phone, email));
        UserEntity entity = service.selectOne(id);
        entity.setEmail(email);
        entity.setTelephone(phone);
        int res = service.update(entity);
        logger.info(String.format("update stu{%d}  --> %d", id, res));
        return new RestResult.Builder(200).message(res == 1 ? "success" : "fail").build();
    }

    /**
     * 教师修改个人资料
     *
     * @param id
     * @param phone
     * @param email
     * @param office
     * @return success || fail
     */
    @RequestMapping(value = "/api/user/tch", method = RequestMethod.POST)
    @ResponseBody
    public RestResult updateTch(@RequestParam int id,
                                @RequestParam String phone,
                                @RequestParam String email,
                                @RequestParam String office) {
        logger.info(String.format("update tch{%d} %s %s %s", id, phone, email, office));
        UserEntity entity = service.selectOne(id);
        entity.setEmail(email);
        entity.setTelephone(phone);
        entity.setOffice(office);
        int res = service.update(entity);
        logger.info(String.format("update tch{%d}  --> %d", id, res));
        return new RestResult.Builder(200).message(res == 1 ? "success" : "fail").build();
    }

    /**
     * 用户修改登陆密码
     *
     * @param id
     * @param oldPass
     * @param newPass
     * @param checkPass
     * @return success || fail
     */
    @RequestMapping(value = "/api/user/pwd", method = RequestMethod.POST)
    @ResponseBody
    public RestResult updatePassword(@RequestParam int id,
                                     @RequestParam String oldPass,
                                     @RequestParam String newPass,
                                     @RequestParam String checkPass) {
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
        return new RestResult.Builder(200).message(msg).build();
    }

    /**
     * 教师修改七牛云账号
     *
     * @param id
     * @param accessKey
     * @param secretKey
     * @param bucket
     * @return success || fail
     */
    @RequestMapping(value = "/api/qiniu", method = RequestMethod.POST)
    @ResponseBody
    public RestResult updateBucket(@RequestParam Integer id,
                                   @RequestParam String accessKey,
                                   @RequestParam String secretKey,
                                   @RequestParam String bucket) {
        logger.info(String.format("update bucket %d %s %s %s", id, accessKey, secretKey, bucket));
        QiniuEntity qiniuEntity = new QiniuEntity(secretKey, accessKey, bucket);
        String domain = QiniuUtil.queryDomain(qiniuEntity);
        logger.info(domain);
        if (!"http://".equals(domain)) {
            int qiniu = qiniuService.save(qiniuEntity);
            logger.info(String.format("save %s ==> %d", qiniuEntity, qiniu));
            if (qiniu > 0) {
                int res = service.updateQiniu(id, qiniu);
                return new RestResult.Builder(200).message(res == 1 ? "success" : "fail").build();
            }
        }
        return new RestResult.Builder(200).message("fail").build();
    }
}
