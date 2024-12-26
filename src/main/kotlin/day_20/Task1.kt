package day_20

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.PriorityQueue
import java.util.concurrent.atomic.AtomicInteger

class Task1 {

    companion object {

        private fun Set<Point>.findWalls(cur: Point): Set<Point> {
            return buildSet {
                Direction.entries
                    .map { cur + it.delta }
                    .filter { it in this@findWalls }
                    .forEach { add(it) }
            }
        }

        private fun passBoard(begin: Point, end: Point, walls: Set<Point>, processStep: (Point) -> Unit = {}): Int {
            val queue = PriorityQueue<Pair<Point, Int>>(compareBy { (_, time) -> time })
            queue.add(begin to 0)
            val visited = mutableSetOf<Point>()
            while (true) {
                val (cur, time) = queue.remove()
                if (cur == end) return time
                val next = Direction.entries
                    .map { cur + it.delta }
                    .filter { it !in walls }
                    .filter { it !in visited }
                next.forEach { processStep(it) }
                visited += next
                queue += next.map { it to time + 1 }
            }
        }

        fun solve(input: String, threshold: Int): Int {
            // transform input
            val data = input.lines()
            val height = data.size
            val width = data.first().length
            var begin: Point? = null
            var end: Point? = null
            val walls = buildSet {
                data.forEachIndexed { y, line ->
                    line.forEachIndexed { x, char ->
                        when (char) {
                            'S' -> begin = Point(y, x)
                            'E' -> end = Point(y, x)
                            '#' -> add(Point(y, x))
                        }
                    }
                }
            }
            // pass map without cheating, calc reference min time, collect all walls nearby
            val wallsNearby = mutableSetOf<Point>()
            val referenceTime = passBoard(begin!!, end!!, walls) { cur -> wallsNearby += walls.findWalls(cur) }

            val count = AtomicInteger(0)
            runBlocking(Dispatchers.Default) {
                wallsNearby
                    .filter { it.y in 1..<height-1 && it.x in 1..<width-1}
                    .forEach {
                        launch {
                            // remove each of nearby walls and calc new min times
                            val time = passBoard(begin!!, end!!, walls - it)
                            // count if current min time - reference min time >= threshold
                            if (referenceTime - time >= threshold) {
                                count.incrementAndGet()
                            }
                        }
                    }
            }
            return count.get()
        }
    }
}