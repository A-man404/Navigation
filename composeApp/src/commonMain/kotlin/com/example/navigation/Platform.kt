package com.example.navigation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform