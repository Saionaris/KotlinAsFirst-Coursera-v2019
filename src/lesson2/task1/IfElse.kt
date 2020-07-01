@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when (age) {
    1, 21, 31, 41, 51, 61, 71, 81, 91, 101, 121, 131, 141, 151, 161, 171, 181, 191 -> "$age год"
    in 2..4, in 22..24, in 32..34, in 42..44, in 52..54, in 62..64, in 72..74, in 82..84, in 92..94, in 102..104, in 122..124, in 132..134, in 142..144, in 152..154, in 162..164, in 172..174, in 182..184, in 192..194 -> "$age года"
    in 5..20, in 25..30, in 35..40, in 45..50, in 55..60, in 65..70, in 75..80, in 85..90, in 95..100, in 105..120, in 125..130, in 135..140, in 145..150, in 155..160, in 165..170, in 175..180, in 185..190, in 195..199 -> "$age лет"
    else -> "столько не живут $age"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = v1*t1
    val s2 = v2*t2
    val s3 = v3*t3
    val s = (v1*t1 + v2*t2 + v3*t3)/2
    if (s <= s1) return s / v1
    if (s <= s1+s2) return t1 + (s-s1)/v2
    return t1 + t2 + (s-s1-s2)/v3
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    if ((kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2)) return 3
    if (kingX == rookX1 || kingY == rookY1) return 1
    if (kingX == rookX2 || kingY == rookY2) return 2
    return 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    if ((kingX == rookX || kingY == rookY) && (abs(bishopX - kingX) == abs(bishopY - kingY))) return 3
    if (kingX == rookX || kingY == rookY) return 1
    if (abs(bishopX - kingX) == abs(bishopY - kingY)) return 2
    return 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if (a >= b + c || b >= a + c || c >= a + b) return -1
    if (sqr(a) == sqr(b) + sqr(c) || sqr(b) == sqr(a) + sqr(c) || sqr(c) == sqr(a) + sqr(b)) return 1
    if (sqr(a) > sqr(b) + sqr(c) || sqr(b) > sqr(a) + sqr(c) || sqr(c) > sqr(a) + sqr(b)) return 2
    return 0
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if ((b >= c) && (c >= a) && (d >= b) && (d >= a)) return abs(b - c)
    if ((b >= c) && (c >= a) && (b >= d) && (d >= a)) return abs(d - c)
    if ((a >= c) && (d >= a) && (b >= d) && (b >= c)) return abs(d - a)
    if ((a >= c) && (b >= c) && (d >= a) && (d >= b)) return abs(b - a)
    return -1
}
