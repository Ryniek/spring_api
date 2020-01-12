package pl.rynski.todo_rest_for_react_app.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rynski.todo_rest_for_react_app.model.Todo;
import pl.rynski.todo_rest_for_react_app.service.TodoService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://michu-api.herokuapp.com/")
public class TodoApi {

    private TodoService todoService;

    @Autowired
    public TodoApi(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public ResponseEntity<List<Todo>> getAllTodos(@PathVariable String username) {
        return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String username, @PathVariable Long id) {
        return new ResponseEntity<>(todoService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> removeTodoById(@PathVariable String username, @PathVariable Long id) {
        Todo todo = todoService.deleteById(id);
        if(todo != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable Long id, @RequestBody Todo todo) {
        Todo todoUpdated = todoService.save(username, todo);
        return new ResponseEntity<>(todoUpdated, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Void> updateTodo(@PathVariable String username,  @RequestBody Todo todo) {
        Todo todoUpdated = todoService.save(username, todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
