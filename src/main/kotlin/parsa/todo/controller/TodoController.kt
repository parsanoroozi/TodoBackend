package parsa.todo.controller

import jakarta.validation.Valid
import parsa.todo.models.Todo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import parsa.todo.services.TodoService

@RestController
@RequestMapping("/v1/api")
class TodoController {
    @Autowired
    lateinit var todoService : TodoService

    @PostMapping("/todo")
    fun saveTodo(@Valid @RequestBody todo: Todo): Todo = todoService.saveTodo(todo)

    @GetMapping("/todo")
    fun allTodo(): List<Todo>? = todoService.retrieveAllTodo()

    @PutMapping("/todo/{id}")
    fun updateTodo(
        @PathVariable("id") todoId : Long,
        @Valid @RequestBody todo: Todo
    ): Todo = todoService.updateTodo(todo, todoId)

    @DeleteMapping("/todo/{id}")
    fun deleteTodo(@PathVariable("id") todoId : Long): ResponseEntity<Map<String, String>> = todoService.deleteTodoById(todoId)
}