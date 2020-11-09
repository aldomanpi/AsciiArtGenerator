package signature

import java.io.File
import java.util.*


fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val name = scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()
    var nameSize = 0
    var statusSize = 0
    for (char in name) {
        nameSize += chars("large", char, 0).toInt()
    }
    for (char in status.toLowerCase()) {
        statusSize += chars("medium", char, 0).toInt()
    }
    nameSize--
    statusSize--
    var nameSpaces = 0
    var statusSpaces = 0
    val size: Int
    if (statusSize > nameSize) {
        size = statusSize
        nameSpaces = size - nameSize
    } else {
        size = nameSize
        statusSpaces = size - statusSize
    }
    println("8".repeat(size + 9))
    printStr("large", nameSpaces, name)
    printStr("medium", statusSpaces, status.toLowerCase())
    println("8".repeat(size + 9))
}
fun printStr(size: String, spaces: Int, str: String) {
    val row = if (size == "medium") 3 else 10
    for (index in 0 until row) {
        print("88")
        print(" ".repeat(spaces / 2 + 2))
        for (char in str) {
            print(chars(size, char, index + 1))
        }
        print(" ".repeat(spaces / 2 + spaces % 2 + 2))
        println("88")
    }
}

fun chars(size: String, char: Char, index: Int): String {
    if (char == ' ') {
        return if (size == "large") {
            if (index == 0) "10" else "          "
        } else {
            if (index == 0) "5" else "     "
        }
    }
    val file: Scanner = Scanner(File("C:/ReallyCoolStuff/${if (size == "large") "roman" else "medium"}.txt"))
    file.nextLine()
    var infoLine: String = file.nextLine()
    while (infoLine[0] != char) {
        repeat(if (size == "large") 10 else 3) {
            file.nextLine()
        }
        infoLine = file.nextLine()
    }
    return if (index == 0) {
        infoLine.substringAfter(" ")
    } else {
        repeat(index - 1) {
            file.nextLine()
        }
        file.nextLine()
    }
}