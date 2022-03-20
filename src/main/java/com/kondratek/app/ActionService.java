package com.kondratek.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private final ActionRepository actionRepository;

    @Autowired
    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public void addAction(Action action) {
        actionRepository.save(action);
    }

    public Iterable<Action> getActions() {
        return actionRepository.findAll();
    }
}
