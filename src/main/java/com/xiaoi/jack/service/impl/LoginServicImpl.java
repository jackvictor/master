package com.xiaoi.jack.service.impl;

import com.xiaoi.jack.constant.SessionConstant;
import com.xiaoi.jack.dao.UserDao;
import com.xiaoi.jack.model.User;
import com.xiaoi.jack.service.LoginService;
import com.xiaoi.jack.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LoginServicImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    public boolean addNewUser(String userName, String passWorld, String email)  throws Exception{
        //验证是否有同名
        User user = userDao.selectByName(userName);
        if(user!=null){
            //有同名
            return false;
        }
        String userId = UUID.randomUUID().toString().replace("-","");
        user.setUserId(userId);
        user.setUserName(userName);
        String salt = UUID.randomUUID().toString().replace("-","");
        user.setSalt(salt);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addDate = df.format(new Date());
        user.setAddTime(addDate);
        String encryptedPassWord = Md5Utils.GetMD5Code(passWorld+salt);
        user.setPassword(encryptedPassWord);
        user.setEmail(email);
        user.setUserStatus("1");
        return true;
    }

    public boolean login(String userName, String password, HttpSession session) {
        User user = userDao.selectByUserId(userName);
        if(user == null || user.getUserStatus() == "")
        {
            return false;
        }
        String salt = user.getSalt();
        String correctPassword = user.getPassword();
        String verifyPassword = Md5Utils.GetMD5Code(password+salt);
        if(correctPassword.equals(verifyPassword)){
            session.setAttribute(SessionConstant.SESSION_IS_LOGIN, true);
            session.setAttribute(SessionConstant.SESSION_USER_ID, user.getUserId());
            session.setAttribute(SessionConstant.SESSION_USER_NAME,user.getUserName());
            return true;
        }
        return false;
    }
    public boolean changePassword(String old_password, String new_password, HttpSession session) throws Exception {
        return false;
    }

    public List<User> getAllUsers(HttpSession session) {
        List<User> users = new ArrayList<User>();
        users = userDao.selectAll();
        return users;
    }

    public boolean deleteUser(String username) throws Exception {
        //先查询数据库是否有对应的user
        User user = userDao.selectByName(username);
        if(user == null)
            return false;
        //有对应的user
        String id = user.getUserId();
        //删除user
        userDao.delete(id);
        return true;
    }

}
