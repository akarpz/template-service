package org.template.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired private UserOrchestrator userOrchestrator;

    @RequestMapping(path = "/api/v1/users", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user) {
        return userOrchestrator.createUser(user);
    }

}
