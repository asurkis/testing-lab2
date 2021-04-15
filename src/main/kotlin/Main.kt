import java.io.PrintWriter

fun main() {
    val algebra = Algebra(0.01)
    val powers = 0..3
    PrintWriter("output.csv").use {
        it.print("x")
        it.print(", f(x)")
        for (n in powers) {
            it.print(", x^$n")
        }
        it.print(", cos(x)")
        it.print(", sin(x)")
        it.print(", sec(x)")
        it.print(", csc(x)")
        it.print(", tan(x)")
        it.print(", ln(x)")
        it.print(", log_2(x)")
        it.print(", log_5(x)")
        it.print(", log_10(x)")
        it.println()

        for (i in -1000..1000) {
            val x = 0.1 * i
            it.print("$x")
            it.print(", ${algebra.superFun(x)}")
            for (n in powers) {
                it.print(", ${x.pow(n)}")
            }
            it.print(", ${algebra.cos(x)}")
            it.print(", ${algebra.sin(x)}")
            it.print(", ${algebra.sec(x)}")
            it.print(", ${algebra.csc(x)}")
            it.print(", ${algebra.tan(x)}")
            it.print(", ${algebra.ln(x)}")
            it.print(", ${algebra.log_2(x)}")
            it.print(", ${algebra.log_5(x)}")
            it.print(", ${algebra.log_10(x)}")
            it.println()
        }
    }
}
