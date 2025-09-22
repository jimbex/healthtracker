package utils

fun checkGender(g:Char): Int{
    // Validate gender input, return 0 if valid, 1 if invalid
    if (g in setOf('M', 'F', 'O')){
        return 0
    }else{return 1}
}
