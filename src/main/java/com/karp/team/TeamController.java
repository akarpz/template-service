package com.karp.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired private TeamOrchestrator teamOrchestrator;

//    @RequestMapping(path = "/api/v1/thing", method = RequestMethod.POST)
//    public boolean setThing(@RequestBody String stuff) {
//        return teamOrchestrator.setThing(stuff);
//    }

    @RequestMapping(path = "/api/v1/teams/{team}", method = RequestMethod.GET)
    public String getTeam(@PathVariable String team) {
        return teamOrchestrator.getTeam(team);
    }

}
