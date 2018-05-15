package org.template.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserOrchestrator {

    @Autowired private UserDao userDao;

    boolean createUser(User user) {
        return userDao.createUser(user);
    }

}
