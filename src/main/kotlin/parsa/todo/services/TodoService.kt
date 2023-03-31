package parsa.todo.services

import parsa.todo.models.Todo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import parsa.todo.exceptions.DataNotFoundException
import parsa.todo.repository.TodoRepository

@Service
class TodoService {
    @Autowired
    lateinit var todoRepo: TodoRepository

    fun saveTodo(todoEntity: Todo): Todo = todoRepo.save(todoEntity)
    fun updateTodo(todoEntity: Todo, todoId: Long) : Todo {
        if(todoEntity.id == todoId) {
            return try {
                val newTodo: Todo = todoRepo.getReferenceById(todoId)
                newTodo.title = todoEntity.title
                newTodo.description = todoEntity.description
                newTodo.progress = todoEntity.progress
                todoRepo.save(newTodo)
            } catch ( e : Exception) {
                throw Exception("Unable to update data with id $todoId")
            }
        }
        throw DataNotFoundException("Id used not valid")

    }
    fun retrieveAllTodo(): List<Todo>? = todoRepo.findAll()
    fun deleteTodoById(todoId : Long) : ResponseEntity<Map<String, String>> {
        return try {
            todoRepo.getReferenceById(todoId)
            todoRepo.deleteById(todoId)
            val dataMessage : MutableMap<String,String> = HashMap()
            dataMessage["data"] = "Properly Removed"
            ResponseEntity.ok(dataMessage)
        } catch (e : EmptyResultDataAccessException) {
            throw DataNotFoundException("Unable to delete item, id not found")
        } catch (e : Exception) {
            throw e
        }
    }
}