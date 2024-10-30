package entry.dsm.equusrecruitment.domain.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import entry.dsm.equusrecruitment.domain.global.error.exception.RecruitmentException
import entry.dsm.equusrecruitment.domain.global.exception.InternalServerError
import io.sentry.Sentry
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.nio.charset.StandardCharsets
import kotlin.jvm.Throws

class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            Sentry.captureException(e)
            when (e) {
                is RecruitmentException -> writerErrorCode(response, e)
                else -> writerErrorCode(response, InternalServerError)
            }
        }
    }

    @Throws(IOException::class)
    private fun writerErrorCode(
        response: HttpServletResponse,
        exception: RecruitmentException
    ) {
        val errorResponse = ErrorResponse(exception.statusCode, exception.message)
        response.status = exception.statusCode
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}
