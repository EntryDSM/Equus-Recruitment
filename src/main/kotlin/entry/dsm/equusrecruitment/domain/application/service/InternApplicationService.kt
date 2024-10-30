package entry.dsm.equusrecruitment.domain.application.service

import entry.dsm.equusrecruitment.domain.application.domain.Application
import entry.dsm.equusrecruitment.domain.application.exception.ApplicationAlreadyExistException
import entry.dsm.equusrecruitment.domain.application.presentation.dto.request.InternApplicationRequest
import entry.dsm.equusrecruitment.domain.application.repository.ApplicationRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class InternApplicationService (
    private val applicationRepository: ApplicationRepository
) {
    @Transactional
    fun application(request: InternApplicationRequest) {
        val application = applicationRepository.findBySchoolNumber(request.schoolNumber)
        if (application != null) throw ApplicationAlreadyExistException

        request.run {
            applicationRepository.save(
                Application(
                    name = request.name,
                    schoolNumber = request.schoolNumber,
                    phoneNumber = request.phoneNumber,
                    introduce = request.introduce,
                    interestingMajor = request.interestingMajor,
                    motivation = request.motivation,
                    experience = request.experience
                )
            )
        }
    }
}