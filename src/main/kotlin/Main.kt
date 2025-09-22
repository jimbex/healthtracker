import controllers.UserStore  // Import the User class from models package
import models.User
import utils.checkGender

val userStore = UserStore()// Create a global User object to store user details

fun main(){
    println("Welcome to Health Tracker")
    runApp()  // Start the main application loop
}

fun menu(): Int{
    // Display menu options and read user input
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List User
        |  0. Exit
        |Please enter your option: """.trimMargin())
    return readlnOrNull()?.toIntOrNull() ?: -1  // Return entered option or -1 if invalid
}

fun runApp(){
    var input: Int
    // Keep running until user chooses 0 (Exit)
    do{
        input = menu()
        when (input) {
            1 -> addUser()               // Option 1: Add user details
            2 -> listUser()              // Option 2: List user details
            in(3..6) -> println("feature coming soon") // Placeholder for future features
            0 -> print("Bye...")         // Exit message
            else -> print("Invalid option") // Handle invalid input
        }
    }while (input != 0)  // Exit loop when input is 0
}

fun addUser(){
    val user = User()
    println("Please enter the following for the user: ")

    // Take user details from console input
    print("     Name: ")
    user.name = readln()
    print("     Email: ")
    user.email = readln()

    // Keep asking until gender is valid (M/F/O)
    do{
        print("     Enter gender (M/F/O):  ")
        user.gender = readlnOrNull()?.firstOrNull()?: ' '
    }while(checkGender(user.gender) == 1)

    // Take numeric ID, fallback to -1 if invalid
    print("     Id: ")
    user.id = readlnOrNull()?.toIntOrNull() ?: -1
    print("     Weight: ")
    user.weight = readlnOrNull()?.toDoubleOrNull() ?: -1.0
    print("     Height: ")
    user.height = readlnOrNull()?.toFloatOrNull() ?: -1.0f

    userStore.create(user)
}

fun listUser(){
    // Display user details using User's toString() implementation
    println("The user details are: ${userStore.findAll()}")
}
