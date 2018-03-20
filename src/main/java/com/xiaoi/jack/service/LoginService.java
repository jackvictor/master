package com.xiaoi.jack.service;

import com.xiaoi.jack.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: User
 * @create: 2018/03/08 13:09
 * @description:
 **/
public interface LoginService {
    /**
     * 增加用户
     *
     * @param userName
     * @param passWorld
     * @param email
     * @return
     */
    public boolean addNewUser(String userName,String passWorld,String email) throws Exception;
    /**
     * 实现登录
     *
     * @author jack
     * @param userName
     * @param password
     * @param session
     * @return
     */
    public boolean login(String userName,String password,HttpSession session);

    /**
     * 查询所有用户
     *
     * @param session
     * @return
     */
    public List<User> getAllUsers(HttpSession session);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean deleteUser(String userId) throws Exception;


}
