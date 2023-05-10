class Test2
{
}

fun main()
{
    fun testH(arg:(Int) -> Boolean):()->String
    {
        val result = if (arg(10))
        {
            "valid"
        }
        else
        {
            "invalid"
        }
        return {"testH result 확인 : $result"}
    }

    //고차함수 사용예제
    val result16 = testH({ no -> no > 0 })
    println(result16())
}