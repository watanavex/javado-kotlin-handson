package jp.watanave.githubsample.practice3

//`関数を定義してみる`
//fun hello(name: String ) : String {
//    return "Hello, $name"
//}


// デフォルト引数を定義してみる`
//fun hello(name: String = "World") : String {
//    return "Hello, $name"
//}

// 単一式関数を試してみる
fun hello(name: String = "World") = "Hello, " + name

fun Int.includeTax() = (this * 1.08).toInt()