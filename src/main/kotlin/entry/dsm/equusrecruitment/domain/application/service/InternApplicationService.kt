package entry.dsm.equusrecruitment.domain.application.service

import entry.dsm.equusrecruitment.domain.application.domain.Application
import entry.dsm.equusrecruitment.domain.application.exception.ApplicationAlreadyExistException
import entry.dsm.equusrecruitment.domain.application.presentation.dto.request.InternApplicationRequest
import entry.dsm.equusrecruitment.domain.application.repository.ApplicationRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class InternApplicationService(
    private val applicationRepository: ApplicationRepository,
) {
    @Transactional
    fun applicationRegister(request: InternApplicationRequest) {
        val application =
            applicationRepository.findBySchoolNumber(request.schoolNumber)?.let {
                throw ApplicationAlreadyExistException
            }
        request.run {
            applicationRepository.save(
                Application(
                    name = name,
                    schoolNumber = schoolNumber,
                    phoneNumber = phoneNumber,
                    introduce = introduce,
                    interestingMajor = interestingMajor,
                    motivation = motivation,
                    experience = experience,
                ),
            )
        }
    }
}
