package com.kondratek.kalamba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long userId;
    public Long gameId;
    public ActionType action;

    public Action(){}

    public Action(Long userId, Long gameId, ActionType action) {
        this.userId = userId;
        this.gameId = gameId;
        this.action = action;
    }
}