package com.xiaoi.jack;


import com.xiaoi.jack.dao.UserDao;
import com.xiaoi.jack.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.xiaoi.jack.dao.UserDao;


import com.xiaoi.jack.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


    /**
     * @author jack
     */
    public class Test {
        private static ApplicationContext ac;
        static {
            ac = new ClassPathXmlApplicationContext("/*.xml");
        }

        public static void main(String[] args) {
            UserDao mapping = (UserDao) ac.getBean("userDao");
            System.out.println(mapping);
            System.out.println("=========================");
            System.out.println("获取jack");
            User user = mapping.selectByName("victor");
            System.out.println(user);
            System.out.println(user.getUserId()+":"+"username:"+user.getUserName());
            System.out.println("password:"+user.getPassword());

        }

}
