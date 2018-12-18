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
            ret[i][j] = temp[j].toInt() - "0".toInt()
        }
    }
    return ret
}

fun intArraysToString(array: Array<IntArray>): String {
    var builder = StringBuilder()
    builder.append('[')
    for(item in array.iterator()) {
        intArrayToString(item, builder)
    }
    builder.append(']')
    return builder.toString()
}

fun intArrayToString(array: IntArray, vararg builders: StringBuilder): String {
    var builder = if(builders.isEmpty()) StringBuilder() else builders[0]
    builder.append('[')
    for(i in 0 until array.size) {
        builder.append(array[i])
        if(i == array.size - 1) break
        builder.append(",")
    }
    builder.append(']')
    return builder.toString()
}