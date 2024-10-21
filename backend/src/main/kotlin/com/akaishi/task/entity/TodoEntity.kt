package com.akaishi.task.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

//@Entity
//@Table(name = "TB_TODO")
data class TodoEntity(
    val id: String,
    val title: String = "",
    val content: String = ""
)