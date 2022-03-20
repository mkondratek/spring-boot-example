package com.kondratek.app;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final Long userId;
    private final Long gameId;

    @JsonProperty("action")
    private final ActionType actionType;

    public Action(){
        id = null;
        userId = null;
        gameId = null;
        actionType = null;
    }

    public Action(Long id, Long userId, Long gameId, ActionType actionType) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.actionType = actionType;
    }

    // todo: use Lombok
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public ActionType getActionType() {
        return actionType;
    }
}