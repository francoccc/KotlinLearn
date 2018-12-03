package com

fun stringTo2DimArray(str: String): Array<IntArray> {
    val strs = str.substring(1, str.lastIndex - 1)
            .replace("[", "")
            .split("],")
    val rows = strs.size
    val cols = strs[0].split(",").size
    val ret: Array<IntArray> = Array(rows, { i -> IntArray(cols)})
    for(i in 0 until rows) {
        val temp = strs[i].split(",")
        for(j in 0 until cols) {
            ret[i][j] = temp[j].toInt() - "0".toInt();
        }
    }
    return ret
}