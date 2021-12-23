package com.example.sixthtask

interface InterfaceClick {
    interface ClickedItemHandler {
        fun openContactDetails(contact: Person)
        fun updateContact(contact: Person)
    }
}