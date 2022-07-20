package br.com.rodrigogurgel.logznewrelicexample.controllers

import com.newrelic.api.agent.NewRelic
import java.util.UUID
import kotlin.math.abs
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class MathController {
    companion object {
        var logger: Logger = LoggerFactory.getLogger(MathController::class.java)
    }

    @GetMapping
    fun getOddOrEven(
        @RequestParam
        number: Int
    ): String {
        val result = if (isOdd(number)) "ODD" else "EVEN"

        NewRelic.addCustomParameter("IsOddOrEven", result)
        logger.info("Number $number is $result")

        return result
    }

    private fun isOdd(number: Int): Boolean {
        return number % 2 == 1
    }
}