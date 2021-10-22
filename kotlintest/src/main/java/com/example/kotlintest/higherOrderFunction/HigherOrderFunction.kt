package com.example.kotlintest.higherOrderFunction

/**
 * 高阶函数与方法成员引用练习
 * *表现：接收一个或多个过程为参数；或者以一个过程作为返回结果
 * 也可以理解为以其他函数(方法)作为参数或返回值的函数
 *
 * @author WangQingYi
 * @since  2021/2/1
 */

/**
 * Kotlin中的函数类型
 * 例：
 * (Int) -> Unit
 * Kotlin中的函数类型声明需要遵循以下几点：
 * 1.通过 -> 符号来组织参数类型和返回值，左边是参数类型，右边是返回值类型
 * 2.必须用一个括号来包裹参数类型
 * 3.返回值类型即使是Unit，也必须显示声明
 *
 * 如果是一个没有参数的函数类型，参数类型部分就用()来表示
 * () -> Unit
 *
 * 如果是多个参数就用,隔开
 * (Int, String) -> Unit
 *
 * 此外Kotlin还支持声明参数指定名字
 * (name :String, age :Int) -> Unit
 *
 * 高阶函数还支持返回另一个函数
 * (Int) -> ((Int) -> Unit)
 * 这表示传入一个类型为Int的参数，然后返回另一个类型为（Int）-> Unit的函数，简化它的表达，可以把后半段的括号省略
 * (Int) -> Int -> Unit
 *
 * 需要注意的是一下的函数类型则不同，它表示的是传入一个函数类型的参数再返回一个Unit
 * ((Int) -> Int) -> Unit
 */


/* 例：Shaw因为旅游喜欢上了地理，然后建立了一个所有国家的数据库。设计了一个CountryApp类来对国家数据进行操作。
 * Shaw偏好欧洲国家，于是设计了一个程序来获取欧洲所有的国家
 */

/**
 * 筛选国家用的数据类
 */
data class Country(
    val name: String,  // 国家名称
    val continent: String,  // 洲
    val population: Int  // 人口
)

val res = mutableListOf<Country>()

class CountryApp {
    /**
     * 过滤国家
     */
    fun filterCountries1(countries: List<Country>): List<Country> {
        for (c in countries) {
            if (c.continent == "EU") // EU代表欧洲
                res.add(c)
        }
        return res
    }

    /*
     * 后来，Shaw对非洲也产生了兴趣，于是他又改进了上述方法的实现，支持根据具体的州来筛选国家
     */
    fun filterCountries2(countries: List<Country>, continent: String): List<Country> {
        for (c in countries) {
            if (c.continent == continent) {
                res.add(c)
            }
        }
        return res
    }

    /*
     * 以上的程序都具备了一定的复用性。然而，随着Shaw的地理志是越来越丰富，他相对国家的特点做进一步的研究，
     * 比如筛选具有一定人口规模的国家，于是改变代码：
     */
    fun filterCountries3(
        countries: List<Country>,
        continent: String,
        population: Int
    ): List<Country> {
        for (c in countries) {
            if (c.continent == continent && c.population == population) {
                res.add(c)
            }
        }
        return res
    }

    /*
     * 新增一个population参数来代表人口 单位万。如果按照现有的设计，更多的筛选条件回座位方法参数不断累加，
     * 而且业务也高度耦合。
     * 解决问题的核心在于对filterCountries方法进行解耦，我们能否把筛选逻辑行为都抽象成一个参数
     */
    /**
     * 新建测试类
     */
    class CountryTest {
        fun isBigEuropeanCountry(country: Country): Boolean {
            return country.continent == "EU" && country.population > 10000
        }
    }

    /*
     * 调用isBigEuropeanCountry()方法就能判断一个国家是否是一个人口超过1亿的欧洲国家。然而要怎么将filterCountries
     * 方法变成一个参数?要先解决两个问题：
     * 1.方法作为参数传入，必须要想其他参数一样具备具体的类型信息
     * 2.需要把isBigEuropeanCountry()的方法引用当作参数传递给filterCountries。
     */
    // 重新定义filterCountries方法
    fun filterCountries4(
        country: List<Country>,
        test: (Country) -> Boolean
    ): List<Country> {
        for (c in country) {
            if (test(c)) {
                res.add(c)
            }
        }
        return res
    }
}
/**
 * 我们该如何把方法传递给filterCountries()？直接把isBigEuropeanCountry()当参数
 * 肯定不行，因为函数名并不是一个表达式，不具有类型信息，它在带上括号、被执行之后才存在价值，我们需要的是一个
 * 单纯的方法引用表达式，用它在filterCountries()内部调用参数
 */

/****************************** 方法和成员引用  **********************/

/**
 * Kotlin中存在一种特殊的语法，通过两个冒号来实现对于某个类的方法进行引用，以上面代码为例，假如我们有一个
 * CountryTest类的对象实例countryTest，如果要引用它的isBigEuropeanCountry()就可以这么写：
 * countryTest :: isBigEuropeanCountry()
 */
// 我们可以直接通过这种语法，来定义一个类的构造方法引用变量

class Book(val name: String)

fun main1() {
    val getBook = ::Book
    println(getBook("Kotlin核心编程").name)
}

/**
 * 可以发现getBook的类型为(name :String) -> String。当我们在对Book集合应用一些函数式API的时候，这回显得格外有用
 */
fun main() {
    val bookName = listOf(
        Book("疯狂Java讲义"),
        Book("Kotlin核心编程")
    ).map(Book::name)
    println(bookName)
}

fun filterCountries(
    country: List<Country>,
    test: (Country) -> Boolean
): List<Country> {
    for (c in country) {
        if (test(c)) {
            res.add(c)
        }
    }
    val countryApp = CountryApp()
    val countryTest = CountryApp.CountryTest()
    val countries = mutableListOf<Country>()
    countryApp.filterCountries4(countries, countryTest::isBigEuropeanCountry)
    return res
}

