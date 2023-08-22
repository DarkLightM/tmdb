package com.example.tmdbkotlinapp.ui.base

sealed class ErrorEvent: Event {
    class SendErrorToast(val text: String): ErrorEvent()
}