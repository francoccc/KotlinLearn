package com

import java.util.*

/**
 *
 * @author Franco
 * running slowly???
 */
class Solution {

    var rows: Int = 0
    var cols: Int = 0
    val dirx: Array<Int> = arrayOf(0, 0, 1, -1)
    val diry: Array<Int> = arrayOf(1, -1, 0, 0)
    val uf: UnionFind by lazy {
        UnionFind(rows * cols + 1)
    }

    fun hitBricks(grid: Array<IntArray>, hits: Array<IntArray>): IntArray {
        Solution@rows = grid.size
        Solution@cols = grid[0].size
        var bricks = IntArray(hits.size)
        for(hit: IntArray in hits) if(grid[hit[0]][hit[1]] == 1) grid[hit[0]][hit[1]] = 2

        for(i in 0 until rows) {
            for(j in 0 until cols) {
                if(grid[i][j] == 1) {
                    //do unionAroundIt
                    unionAround(grid, i, j)
                }
            }
        }

        var brickLeft = uf.size[uf.find(0)]
        loop@ for(i in hits.size - 1 downTo 0) {
            if(grid[hits[i][0]][hits[i][1]] != 2) continue@loop
            grid[hits[i][0]][hits[i][1]] = 1
            unionAround(grid, hits[i][0], hits[i][1])
            val curBrickLeft = uf.size[uf.find(0)]
            bricks[i] = curBrickLeft - brickLeft - 1
            if(bricks[i] < 0) bricks[i] = 0
            brickLeft = curBrickLeft
        }
        return bricks
    }

    fun unionAround(grid: Array<IntArray>, x: Int, y: Int) {
        val curSerial = serialize(x, y)
        loop@ for(k in 0..3) {
            val tx = x + dirx[k]
            val ty = y + diry[k]
            if(tx < 0 || ty < 0 || tx >= rows || ty >= cols || grid[tx][ty] != 1) continue@loop
            uf.union(curSerial, serialize(tx, ty))
        }

        if(x == 0) uf.union(0, curSerial)
    }

    fun serialize(x: Int, y: Int): Int {
        return x * cols + y + 1
    }

    class UnionFind(capacity: Int) {

        var parents = Array(capacity + 1, {i -> i})
        var size = Array(capacity + 1, {1})

        fun find(x: Int): Int {
            if(parents[x] == x) {
                return x
            } else {
                var t = find(parents[x])
                parents[x] = t
                return t
            }
        }

        fun union(x: Int, y: Int) {
            val rootX = find(x)
            val rootY = find(y)
            if(rootX == rootY) return
            else if(size[rootX] < size[rootY]) {
                size[rootY] += size[rootX]
                parents[rootX] = rootY
            } else {
                size[rootX] += size[rootY]
                parents[rootY] = rootX
            }
        }

    }
}

fun main(args: Array<String>) {
    //val age: String? = null
    //val age2 = age!!.toInt()
    //print(age2)
    //print(uf.parents[1])
    //print(uf.size[2])
    //val str = "123"
    val sc = Scanner(System.`in`)
    val grid = stringTo2DimArray(sc.nextLine())
    val hits = stringTo2DimArray(sc.nextLine())
    print(intArrayToString(Solution().hitBricks(grid, hits)))
}