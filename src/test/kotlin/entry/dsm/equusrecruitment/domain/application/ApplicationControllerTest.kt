package entry.dsm.equusrecruitment.domain.application

import com.fasterxml.jackson.databind.ObjectMapper
import entry.dsm.equusrecruitment.domain.application.domain.enum.Experience
import entry.dsm.equusrecruitment.domain.application.domain.enum.Major
import entry.dsm.equusrecruitment.domain.application.presentation.controller.ApplicationController
import entry.dsm.equusrecruitment.domain.application.presentation.dto.request.InternApplicationRequest
import entry.dsm.equusrecruitment.domain.application.service.InternApplicationService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ApplicationController::class)
@AutoConfigureMockMvc(addFilters = false)
class ApplicationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var internApplicationService: InternApplicationService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `return 400 status bad phoneNumber`() {
        val request = InternApplicationRequest(
            name = "홍길동",
            schoolNumber = 1204,
            phoneNumber = "01012345678",
            introduce = "저는 자신감이 넘치는 1학년 홍길동입니다",
            interestingMajor = Major.BACKEND,
            motivation = "백엔드를 누구보다 잘하기 위해",
            experience = Experience.HIGH
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/application")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf())
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.message")
                .value("올바른 전화번호 형식이 아닙니다. ex): 010-1234-5678"))
    }
}