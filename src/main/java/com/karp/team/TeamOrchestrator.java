package com.karp.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamOrchestrator {

    @Autowired private TeamDao teamDao;

    public boolean getThing() {
        return teamDao.getThing();
    }

    public String getTeam(String team) {
        return teamDao.getTeam(team);
    }

    public boolean setThing(String stuff) {
        return teamDao.setThing(stuff);
    }
}
