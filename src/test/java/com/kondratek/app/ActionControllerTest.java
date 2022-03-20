package com.kondratek.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ActionControllerTest {

    @Mock
    private ActionService actionService;

    private final ObjectMapper objectMapper = Mockito.spy(new ObjectMapper());

    private ActionController actionController;

    @BeforeEach
    public void setup() {
        this.actionController = new ActionController(actionService, objectMapper);

    }

    @Test
    void getActions_shouldReturnListAnd200WhenResultIsNonEmpty() throws JsonProcessingException {
        // given
        Action action = new Action(0L, 1L, 2L, ActionType.LEAVE);
        Set<Action> getActionsResult = Collections.singleton(action);
        Mockito.when(actionService.getActions()).thenReturn(getActionsResult);
        Mockito.when(objectMapper.writeValueAsString(getActionsResult)).thenCallRealMethod();

        // when
        ResponseEntity<String> actions = actionController.getActions();

        // then
        Assertions.assertEquals("[{\"id\":0,\"userId\":1,\"gameId\":2,\"action\":\"LEAVE\"}]", actions.getBody());
        Assertions.assertEquals(HttpStatus.OK, actions.getStatusCode());
    }

    @Test
    void getActions_shouldReturnEmptyListAnd200WhenResultIsEmpty() throws JsonProcessingException {
        // given
        List<Action> getActionsResult = Collections.emptyList();
        Mockito.when(actionService.getActions()).thenReturn(getActionsResult);
        Mockito.when(objectMapper.writeValueAsString(getActionsResult)).thenCallRealMethod();

        // when
        ResponseEntity<String> actions = actionController.getActions();

        // then
        Assertions.assertEquals("[]", actions.getBody());
        Assertions.assertEquals(HttpStatus.OK, actions.getStatusCode());
    }

    @Test
    void getActions_shouldErrorMessageAnd500WhenObjectMapperThrows() throws JsonProcessingException {
        // given
        String errorMsg = "Something went wrong!";
        JsonProcessingException jpe = new MyJsonProcessingException(errorMsg);
        List<Action> getActionsResult = Collections.emptyList();
        Mockito.when(actionService.getActions()).thenReturn(getActionsResult);
        Mockito.when(objectMapper.writeValueAsString(getActionsResult)).thenThrow(jpe);

        // when
        ResponseEntity<String> actions = actionController.getActions();

        // then
        Assertions.assertEquals(errorMsg, actions.getBody());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actions.getStatusCode());
    }

    static class MyJsonProcessingException extends JsonProcessingException {
        protected MyJsonProcessingException(String msg) {
            super(msg);
        }
    }
}