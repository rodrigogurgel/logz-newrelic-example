package br.com.rodrigogurgel.logznewrelicexample.configurations.interceptors

import br.com.rodrigogurgel.logznewrelicexample.configurations.Headers
import com.newrelic.api.agent.NewRelic
import java.util.UUID
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.web.servlet.HandlerInterceptor


class RequestIdInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestId = getRequestId(request)
        MDC.put(Headers.X_REQUEST_ID, requestId)
        NewRelic.addCustomParameter(Headers.X_REQUEST_ID, requestId)
        response.setHeader(Headers.X_REQUEST_ID, requestId)
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        MDC.remove(Headers.X_REQUEST_ID)
        MDC.remove("X-User-ID")
    }

    private fun getRequestId(request: HttpServletRequest) = request.getHeader(Headers.X_REQUEST_ID) ?: UUID.randomUUID().toString()
}