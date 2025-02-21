package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2024.day_19.Task1
import year_2024.day_19.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 19: Linen Layout")
class Day19Test {

    val input = """
        r, wr, b, g, bwu, rb, gb, br
        
        brwrr
        bggr
        gbbr
        rrbgbr
        ubwu
        bwurrg
        brgr
        bbrgwb
    """.trimIndent()

    fun transform(input: String): Pair<List<String>, List<String>> {
        val (patterns, designs) = input.split("\n\n")
        return patterns.split(", ") to designs.lines()
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val (patterns, designs) = transform(input)
            assertEquals(6, Task1.solve(patterns, designs))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 19).readText()
            val (patterns, designs) = transform(input)
            assertEquals(338, Task1.solve(patterns, designs))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val (patterns, designs) = transform(input)
            assertEquals(16, Task2.solve(patterns, designs))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 19).readText()
            val (patterns, designs) = transform(input)
            assertEquals(841533074412361, Task2.solve(patterns, designs))
        }
    }
}