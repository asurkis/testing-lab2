import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.min

fun doubleEquals(a: Double, b: Double, epsilon: Double) = abs(a - b) <= epsilon * min(abs(a), abs(b))
fun Double.pow(n: Int): Double {
    if (n == 0) return 1.0
    if (n < 0) return 1.0 / this.pow(-n)
    val t = this.pow(n / 2)
    return t * t * if (n % 2 == 0) 1.0 else this
}

class Algebra(val epsilon: Double) {
    fun cos(x: Double): Double {
        val sqx = x * x
        var result = 1.0
        var k = 1.0
        var i = 1
        do {
            k *= -sqx / (i * (i + 1))
            i += 2
            val old = result
            result += k
        } while (!doubleEquals(result, old, epsilon))
        return result
    }

    fun ln(x: Double): Double {
        val z = (x - 1) / (x + 1)
        val sqr = z * z
        var result = z
        var k = z
        var i = 1
        do {
            k *= sqr
            i += 2
            val old = result
            result += k / i
        } while (!doubleEquals(result, old, epsilon))
        return 0.0
    }

    fun sin(x: Double) = cos(PI - x)
    fun sec(x: Double) = 1.0 / sin(x)
    fun csc(x: Double) = 1.0 / cos(x)
    fun tan(x: Double) = sin(x) / cos(x)
    fun log(x: Double, base: Double) = ln(x) / ln(base)
    fun log_2(x: Double) = log(x, 2.0)
    fun log_5(x: Double) = log(x, 5.0)
    fun log_10(x: Double) = log(x, 10.0)

    //x <= 0 : (((((((((sec(x) + sec(x)) - tan(x)) + cos(x)) - tan(x)) ^ 2) + sec(x)) +
    // + (tan(x) + (((csc(x) + (sec(x) / tan(x))) / csc(x)) + tan(x)))) / (csc(x) ^ 2)) -
    // - ((((csc(x) * (csc(x) - cos(x))) + sin(x)) * ((cos(x) - (cos(x) ^ 2)) +
    // + ((csc(x) / (csc(x) * sec(x))) ^ 3))) * (csc(x) ^ 3)))
    //x > 0 : (((((log_2(x) / ln(x)) - log_5(x)) ^ 3) + ln(x)) / (log_2(x) / log_10(x)))
    fun superFun(x: Double, epsilon: Double): Double {
        if (x <= 0) {
            return (
                    (
                            (
                                    (
                                            (
                                                    (
                                                            (
                                                                    (
                                                                            (
                                                                                    sec(x) + sec(x)
                                                                                    ) - tan(x)
                                                                            ) + cos(x)
                                                                    ) - tan(x)
                                                            ).pow(2)
                                                    ) + sec(x)
                                            ) + (
                                            tan(x) + (
                                                    (
                                                            (
                                                                    csc(x) + (
                                                                            sec(x) / tan(x)
                                                                            )
                                                                    ) / csc(x)
                                                            ) + tan(x)
                                                    )
                                            )
                                    ) / (
                                    csc(x).pow(2)
                                    )
                            ) - (
                            (
                                    (
                                            (
                                                    csc(x) * (
                                                            csc(x) - cos(x)
                                                            )
                                                    ) + sin(x)
                                            ) * (
                                            (
                                                    cos(x) - (
                                                            cos(x).pow(2)
                                                            )
                                                    ) + (
                                                    (
                                                            csc(x) / (
                                                                    csc(x) * sec(x)
                                                                    )
                                                            ).pow(3)
                                                    )
                                            )
                                    ) * (
                                    csc(x).pow(3)
                                    )
                            )
                    )
        } else {
            return (
                    (
                            (
                                    (
                                            (
                                                    log_2(x) / ln(x)
                                                    ) - log_5(x)
                                            ).pow(3)) + ln(x)
                            ) / (
                            log_2(x) / log_10(x)
                            )
                    )
        }
    }
}
