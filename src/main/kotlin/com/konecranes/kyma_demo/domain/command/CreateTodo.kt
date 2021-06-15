package com.konecranes.kyma_demo.domain.command

import com.konecranes.kyma_demo.domain.model.Todo

data class CreateTodo(val description: String) {
    fun asTodo(): Todo {
        return Todo(this.description)
    }
}
