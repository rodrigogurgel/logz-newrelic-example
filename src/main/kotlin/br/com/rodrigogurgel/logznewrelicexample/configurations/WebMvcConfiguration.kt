package br.com.rodrigogurgel.logznewrelicexample.configurations

import br.com.rodrigogurgel.logznewrelicexample.configurations.interceptors.RequestIdInterceptor
import org.springframework.stereotype.Component

import org.springframework.web.servlet.config.annotation.InterceptorRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Component
class WebMvcConfiguration : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(RequestIdInterceptor())
    }
}