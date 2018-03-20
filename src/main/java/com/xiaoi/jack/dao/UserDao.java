package com.xiaoi.jack.dao;

/**
 * @author jack
 */

import com.xiaoi.jack.model.User;
import java.util.List;

public interface UserDao {

    int insert(User userName);

    int delete(String userName);

    int update(User user);

    User selectByUserId(String userId);

    User selectByName(String userName);

    List<User> selectAll();

}
