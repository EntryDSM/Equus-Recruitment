package entry.dsm.equusrecruitment.domain.application

import entry.dsm.equusrecruitment.domain.application.domain.Application
import entry.dsm.equusrecruitment.domain.application.domain.enum.Experience
import entry.dsm.equusrecruitment.domain.application.domain.enum.Major
import entry.dsm.equusrecruitment.domain.application.exception.ApplicationAlreadyExistException
import entry.dsm.equusrecruitment.domain.application.presentation.dto.request.InternApplicationRequest
import entry.dsm.equusrecruitment.domain.application.repository.ApplicationRepository
import entry.dsm.equusrecruitment.domain.application.service.InternApplicationService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import io.mockk.verify
import kotlin.test.assertFailsWith

class InternApplicationServiceTest {
    private val applicationRepository: ApplicationRepository = mockk()
    private val internApplicationService:InternApplicationService =  InternApplicationService(applicationRepository)

    @Test
    fun `if exist same school number, Exception is thrown`() {
        val request = InternApplicationRequest(
            name = "홍길동",
            schoolNumber = 1204,
            phoneNumber = "010-1234-5678",
            introduce = "저는 자신감이 넘치는 1학년 홍길동입니다",
            interestingMajor = Major.BACKEND,
            motivation = "백엔드를 누구보다 잘하기 위해",
            experience = Experience.HIGH
        )

        every { applicationRepository.findBySchoolNumber(request.schoolNumber) } returns Application(
            name = "김사또",
            schoolNumber = 1204,
            phoneNumber = "010-9876-5432",
            introduce = "저는 자신감이 넘치는 1학년 김사또입니다.",
            interestingMajor = Major.FRONTEND,
            motivation = "프론트엔드를 누구보다 잘하기 위해",
            experience = Experience.LOW
        )

        println("테스트 시작")

        val exception = assertFailsWith<ApplicationAlreadyExistException> {
            internApplicationService.applicationRegister(request)
        }

        println("예외 발생 확인: ${exception.message}")

        verify(exactly = 1) { applicationRepository.findBySchoolNumber(request.schoolNumber) }
    }



    @Test
    fun `if not exist same schoolNumber, Exception isn't thrown`() {
        val request = InternApplicationRequest(
            name = "홍길동",
            schoolNumber = 1204,
            phoneNumber = "010-1234-5678",
            introduce = "저는 자신감이 넘치는 1학년 홍길동입니다",
            interestingMajor = Major.BACKEND,
            motivation = "백엔드를 누구보다 잘하기 위해",
            experience = Experience.HIGH
        )

        every { applicationRepository.findBySchoolNumber(request.schoolNumber) } returns null
        every { applicationRepository.save(any()) } returns Application(
            name = request.name,
            schoolNumber = request.schoolNumber,
            phoneNumber = request.phoneNumber,
            introduce = request.introduce,
            interestingMajor = request.interestingMajor,
            motivation = request.motivation,
            experience = request.experience
        )

         internApplicationService.applicationRegister(request)

        verify(exactly = 1) { applicationRepository.findBySchoolNumber(request.schoolNumber) }
        verify(exactly = 1) { applicationRepository.save(any()) }

        println("테스트 성공, 예외 없이 정상적으로 수행됨")
    }
}
