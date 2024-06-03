package com.akaishi.task.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class UserEntity (
    @Id
    val id: String = "",
    val password: String = ""
)
