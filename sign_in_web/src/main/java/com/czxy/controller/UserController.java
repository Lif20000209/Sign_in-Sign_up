package com.czxy.controller;

import com.czxy.domain.User;
import com.czxy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询性别
     * @return
     */
    @GetMapping("/findUserBySex")
    public ResponseEntity<List<User>>findUserBySex() {
        try {
            List<User> userBySex = userService.findUserBySex();
            return ResponseEntity.ok(userBySex);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 校验邮箱
     * @param email
     * @param user
     * @return
     */
    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<Boolean> findUserByEmail(@PathVariable("email") String email, User user) {
        try {
            Boolean userByEmail = userService.findUserByEmail(email);
            if (userByEmail) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 校验手机号
     * @param phone
     * @param user
     * @return
     */
    @GetMapping("/findUserByPhone/{phone}")
    public ResponseEntity<Boolean> findUserByPhone(@PathVariable("phone") String phone, User user) {
        try {
            Boolean userByPhone = userService.findUserByPhone(phone);
            if (userByPhone) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /**
     * 退出
     */
    @GetMapping("/loginOut")
    public ResponseEntity<Void> logOut(HttpSession session) {
        try {
            session.removeAttribute("loginU");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 注册
     *
     * @param user
     * @param myFile
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(User user, MultipartFile myFile) throws IOException {
        try {
            //1.在mFile不为null的情况下 获取mFile的文件名 并把 该文件存储到 服务器的指定位置
            if (myFile != null) {
                String filename = myFile.getOriginalFilename();
                File file = new File("E:\\develop\\tmp\\img", filename);
                myFile.transferTo(file);
                //2.把user对象的img 属性值 设置为mFile的文件名
                user.setImg(filename);
            }

            Boolean register = userService.register(user);
            if (register) {
                //3.把user对象 存储到 数据库中 完成添加
                return ResponseEntity.ok(true);
            } else {
                //否则提示错误
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    /**
     * 登录
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(User user, HttpSession session) {
        try {
            User loginU = userService.login(user);
            if (loginU != null) {
                session.setAttribute("loginU", loginU);
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
