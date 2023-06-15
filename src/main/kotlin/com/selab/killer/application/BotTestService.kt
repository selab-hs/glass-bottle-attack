package com.selab.killer.application

import com.selab.killer.client.BotClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import kotlin.system.measureNanoTime

@Service
class BotTestService(
    private val botClient: BotClient
) {
    suspend fun test() {
        //     `네트워크 처리 성능 비교`()
        // test2()
        // test3()

        println(test4())
    }

    suspend fun test4() = coroutineScope {
        botClient.health()
    }


    suspend fun test3() {
        val a = CoroutineScope(Dispatchers.IO).async {
            botClient.health()
        }

        println(a.await())
    }


    // ???????????? 뭐해야하지 -> 이상함
    suspend fun test2() {
        val a = coroutineScope {
            val response = async {
                botClient.health()
            }

            response.await()
        }

        println(a)
    }


    suspend fun `네트워크 처리 성능 비교`() {
        val io = test1WithContextWithDispatcherIO()
        val default = test1WithContextWithDispatcherDefault()
        val main = test1WithContextWithDispatcherMain()

        println("io : $io / default : $default / main : $main / 차이는 ${default - io}")
    }

    suspend fun test1WithContextWithDispatcherIO(): Long {
        return withContext(Dispatchers.IO) {
            measureNanoTime {
                (0..250).forEach {
                    println("1 - $it")
                    botClient.health()
                }
            }
        }
    }

    suspend fun test1WithContextWithDispatcherDefault(): Long {
        return withContext(Dispatchers.Default) {
            measureNanoTime {
                (0..250).forEach {
                    println("2 - $it")
                    botClient.health()
                }
            }
        }
    }

    suspend fun test1WithContextWithDispatcherMain(): Long {
        return withContext(Dispatchers.Main) {
            measureNanoTime {
                (0..250).forEach {
                    println("2 - $it")
                    botClient.health()
                }
            }
        }
    }
}
