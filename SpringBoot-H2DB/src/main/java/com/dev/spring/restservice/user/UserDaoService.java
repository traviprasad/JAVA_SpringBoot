package com.dev.spring.restservice.user;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<UserBean> usersList = new ArrayList<>();

    private static int userIdCount=4;

    static {
        usersList.add(new UserBean(1, "Jock", "London", new Date()));
        usersList.add(new UserBean(2, "Akos", "Swindon", new Date()));
        usersList.add(new UserBean(3, "cats", "Liverpool", new Date()));
        usersList.add(new UserBean(4, "neeraj", "noida", new Date()));
    }

    public List<UserBean> findAllUsers(){
        return usersList;
    }

    public UserBean saveUser(UserBean uBean){
        if(uBean.getId() == null){
            uBean.setId(++userIdCount);
        }
        usersList.add(uBean);
        return uBean;
    }

    public UserBean findUser(int userId){
        for(UserBean userBean:usersList){
            if(userBean.getId() == userId) {
                return userBean;
            }
        }
        return null;
    }

    public UserBean deleteUser(int userId){
        Iterator<UserBean> iterator = usersList.iterator();
        UserBean user = null;
        boolean userFoundFlag = false;
        while (iterator.hasNext()){
             user = iterator.next();
            if(user.getId() == userId) {
                iterator.remove();
                userFoundFlag = true;
                break;
            }
        }

        if(userFoundFlag == false){
            user = null;
        }
        return user;
    }
}
