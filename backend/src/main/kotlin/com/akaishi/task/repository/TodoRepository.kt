package com.akaishi.task.repository

import com.akaishi.task.entity.TodoEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

interface TodoRepository: JpaRepository<TodoEntity, Long> {}