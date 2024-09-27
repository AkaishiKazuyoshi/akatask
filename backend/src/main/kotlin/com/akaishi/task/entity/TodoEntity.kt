package com.akaishi.task.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "TB_TODO")
class TodoEntity(
    @Id
    val id: Int = 0,
    val title: String = ""
)