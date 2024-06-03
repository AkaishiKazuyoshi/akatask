package com.akaishi.task.repository

import com.akaishi.task.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LoginRepository : JpaRepository<UserEntity, String>