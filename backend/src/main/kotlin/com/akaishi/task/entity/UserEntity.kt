package com.akaishi.task.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "TB_USERS")
class UserEntity (
    @Id
    val id: String = "",
    val password: String = ""
)
