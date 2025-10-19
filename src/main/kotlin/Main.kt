import controllers.UserStore  // Import the User class from models package
import models.User
import utils.checkGender
import io.github.oshai.kotlinlogging.KotlinLogging
import utils.Conversion

val userStore = UserStore()// Create a global User object to store user details
private val logger = KotlinLogging.logger {}

fun main(){
    logger.info{"Welcome to Health Tracker"}

    //Some Temporary Test Data
    userStore.create((User(1, "Homer Simpson", "homer@simpson.com", 178.0, 2.0, 'M')))
    userStore.create((User(2, "Marge Simpson", "marge@simpson.com", 140.0, 1.6, 'F')))
    userStore.create((User(3, "Lisa Simpson", "lisa@simpson.com", 100.0, 1.2, 'F')))
    userStore.create((User(4, "Bart Simpson", "bart@simpson.com", 80.0, 1.0, 'M')))
    userStore.create((User(5, "Maggie Simpson", "maggie@simpson.com", 50.0, 0.7, 'F')))

    runApp()
}

fun menu(): Int{
    // Display menu options and read user input
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List User
        |  3. Search by Id
        |  4. Delete by Id
        |  5. Update user
        |  6. Search by Gender
        |  7. User Report
        |  8. Users (imperial)
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
            3 -> searchById()
            4 -> deleteUser()
            5 -> updateUser()
            6 -> searchByGender() // Placeholder for future features
            7 -> userReport()
            8 -> imperial()
            0 -> print("Bye...")         // Exit message
            else -> print("Invalid option") // Handle invalid input
        }
    }while (input != 0)  // Exit loop when input is 0
}

fun addUser(){
    userStore.create(getUserDetails())
}

fun updateUser() {
    listUser()
    val foundUser = getUserById()
    if (foundUser == null)
        println("No user found")
    else {
        val user = getUserDetails()

        if (userStore.update(foundUser.id, user))
            println("User updated")
        else
            println("User not updated")
        //TODO: using the id from foundUser and the details read from the console, update the user.
    }

}


fun getUserDetails() : User{
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

    print("     Weight: ")
    user.weight = readlnOrNull()?.toDoubleOrNull() ?: -1.0
    print("     Height: ")
    user.height = readlnOrNull()?.toDoubleOrNull() ?: -1.0

    return user
}

fun getUserById() : User?{
    print("Enter the id of the user: ")
    return  userStore.findOne(readlnOrNull()?.toIntOrNull() ?: -1)
}

fun getUserByGender(): List <User> {
    print("Enter the gender of the user: ")
    val genderInp = readlnOrNull()?.firstOrNull() ?: ' '
    return  userStore.findGender(genderInp).sortedBy {it.name }
}

fun searchById() {
    val user = getUserById()
    if (user == null)
        logger.info{"Search - no user found"}
    else
        println(user)
}

fun searchByGender() {
    val user = getUserByGender().forEach{println(it)}
    if (user == null)
        logger.info{"Search - no user found"}
    else
        println(user)
}



fun listUser(){
    // Display user details using User's toString() implementation
    println("The user details are:")
    userStore.findAll()
        .sortedBy { it.name }
        .forEach{println(it)}
}

fun deleteUser() {
    val user = getUserById()
    if (user == null)
        println("No user found")
    else {
        userStore.delete(user.id)
        println("User ${user.name} deleted")
    }
}

fun userReport() {
    println("------------------------------")
    println("         USERS REPORT         ")
    println("------------------------------\n")
    println("Number of Users: ${userStore.findAll().size}")
    println("Gender Breakdown: ${userStore.findAll().groupingBy{it.gender}.eachCount()}")
    println("Average Weight: ${userStore.findAll().map { it.weight }.average()} kg")
    println("Min Weight: ${userStore.findAll().minOfOrNull { it.weight }} kg")
    println("Max Weight: ${userStore.findAll().maxOfOrNull { it.weight }} kg")
    println("Average Height: ${userStore.findAll().map { it.height }.average()} metres")
    println("Min Height: ${userStore.findAll().minOfOrNull { it.height }} metres")
    println("Max Height: ${userStore.findAll().maxOfOrNull { it.height }} metres")
    println("\n------------------------------")
}

fun imperial() {
    println("The user details (imperial) are: ")
    userStore.findAll()
        .sortedBy { it.name }
        .forEach{println( "User: " + it.name + "(" +
                it.email + "), " +
                Conversion.convertKGtoPounds(it.weight, 1.0) + " pounds, " +
                Conversion.convertMetresToInches(it.height, 1.0) + " inches."
            )}

}


