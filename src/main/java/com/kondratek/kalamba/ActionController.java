package com.kondratek.kalamba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class ActionController {

    private final ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping(path = "/action")
    public ResponseEntity<String> addAction(@RequestBody Action action) {
        actionService.addAction(action);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/actions")
    public ResponseEntity<String> getActions() {
        Iterable<Action> actions = actionService.getActions();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(actions);
            return ResponseEntity.ok(jsonString);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
