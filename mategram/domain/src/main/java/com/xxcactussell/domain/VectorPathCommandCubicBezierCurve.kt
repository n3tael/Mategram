package com.xxcactussell.domain

data class VectorPathCommandCubicBezierCurve(
    val startControlPoint: Point,
    val endControlPoint: Point,
    val endPoint: Point
) : VectorPathCommand
