package com.konecranes.kyma_demo.dao

import com.konecranes.kyma_demo.domain.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import javax.transaction.Transactional

interface TodoRepository: JpaRepository<Todo, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Todo t set t.description = ?1, t.isDone = ?2 where t.id = ?3")
    fun updateDescriptionAndIsDone(description: String, isDone: Boolean, id: Long ): Int

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from Todo t where t.id = ?1")
    fun deleteTodoById(id: Long): Int

}
