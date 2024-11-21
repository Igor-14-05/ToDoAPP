package com.example.yandextodoapp.data

enum class Importance(val text: String){
    NORMAL("Обычный"),
    LOW("Низкий"),
    HIGH( "!! Высокий")
}

enum class ImportanceTask(val text: String){
    NORMAL("Обычный"),
    LOW("низкая"),
    HIGH( "срочная")
}