package pl.rynski.todo_rest_for_react_app.service;

import org.springframework.stereotype.Service;
import pl.rynski.todo_rest_for_react_app.model.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();
    private static Long idCounter = 0L;

    public TodoService() {
        todos.add(new Todo(++idCounter, "michu", "jakis opis", new Date(), false));
        todos.add(new Todo(++idCounter, "michu", "jakis opis nr 2", new Date(), false));
        todos.add(new Todo(++idCounter, "michu", "jakis opis nr 3", new Date(), false));
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo deleteById(Long id) {
        Todo todo = findById(id);
        if(todo == null) {
            return null;
        } else {
            todos.remove(todo);
            return todo;
        }
    }

    public Todo findById(Long id) {
        Optional<Todo> todoFound = todos.stream().filter(todo -> todo.getId() == id).findFirst();
        if(todoFound.isPresent())
            return todoFound.get();
        else
            return null;
    }

    public Todo save(String username, Todo todo) {
        if(todo.getId()==null || todo.getId() == -1L) {
            todo.setId(++idCounter);
            todo.setUsername(username);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }
}
