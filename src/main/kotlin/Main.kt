import io.finnhub.api.models.Quote
import io.finnhub.api.models.StockSymbol
import okhttp3.*
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

fun main(args: Array<String>) {
    val token = "c9aj2eiad3i8qngr305g"

    val log = HttpLoggingInterceptor()
    log.setLevel(HttpLoggingInterceptor.Level.BODY)


    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(log)
        .build()

    val request = Request.Builder()
        .url("wss://ws.finnhub.io?token=$token")
        .build()

    val ws = okHttpClient.newWebSocket(request, WsListner())
    ws.send("{\"type\":\"subscribe\",\"symbol\":\"AAPL\"}")
}

class WsListner: WebSocketListener() {
    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        println("[onClosed] $code")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        println("[onMessage] $text")
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        println("[onOpen]")
    }
}