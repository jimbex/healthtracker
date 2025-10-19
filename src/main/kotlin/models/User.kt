package models

// Data class representing a User with basic health and identity attributes
data class User (
    var id: Int = -1,                   // Unique ID for the user (default -1 means not set)
    var name: String = "no name yet",   // User's name (default placeholder)
    var email: String = "no email yet", // User's email address (default placeholder)
    var weight: Double = 0.0,           // User's weight in kilograms (default 0.0)
    var height: Double = 0.0,           // User's height in meters (default 0.0)
    var gender: Char = ' ',             // User's gender (M/F/O, default blank)
)
