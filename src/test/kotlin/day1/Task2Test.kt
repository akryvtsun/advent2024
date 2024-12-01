package day1

import com.akryvtsun.day1.Task2
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task 2 example`() {
        val list1: List<Long> = listOf(3, 4, 2, 1, 3, 3)
        val list2: List<Long> = listOf(4, 3, 5, 3, 9, 3)

        assertEquals(31, Task2.solve(list1, list2))
    }

    @Test
    fun `should successfully solve the real task 2`() {
        val list1 = mutableListOf<Long>()
        val list2 = mutableListOf<Long>()
        java.io.File("src/test/resources/day1/TaskData.txt").forEachLine { line ->
            val parts = line.split("   ")
            list1.add(parts[0].toLong())
            list2.add(parts[1].toLong())
        }
        println("Task 1 solving: ${Task2.solve(list1, list2)}")
    }
}