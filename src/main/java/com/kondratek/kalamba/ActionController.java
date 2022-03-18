package com.kondratek.kalamba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok().body("ok");
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
