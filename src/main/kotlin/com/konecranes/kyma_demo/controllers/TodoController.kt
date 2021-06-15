package com.konecranes.kyma_demo.controllers

import com.konecranes.kyma_demo.dao.TodoRepository
import com.konecranes.kyma_demo.domain.command.CreateTodo
import com.konecranes.kyma_demo.domain.command.UpdateTodo
import com.konecranes.kyma_demo.domain.model.Todo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/todos"])
class TodoController(private val repository: TodoRepository) {

    @GetMapping
    fun getAll(): MutableList<Todo> {
        return repository.findAll()
    }

    @GetMapping(path= ["/{id}"])
    fun getOne(@PathVariable id: Long): ResponseEntity<Todo> {
        return repository.findById(id)
            .map { ResponseEntity(it, HttpStatus.OK) }
            .orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @PostMapping()
    fun addOrder(@RequestBody createTodo: CreateTodo): Todo {
        return repository.save(createTodo.asTodo())
    }

    @PutMapping(path= ["/{id}"])
    fun updateTodo(@PathVariable id: Long, @RequestBody updateTodo: UpdateTodo): Int {
        return repository.updateDescriptionAndIsDone(updateTodo.description, updateTodo.isDone, id)
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteTodo(@PathVariable id: Long): Int {
        return repository.deleteTodoById(id)
    }

}
