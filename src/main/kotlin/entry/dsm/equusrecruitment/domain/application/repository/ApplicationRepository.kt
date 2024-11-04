package entry.dsm.equusrecruitment.domain.application.repository

import entry.dsm.equusrecruitment.domain.application.domain.Application
import org.springframework.data.repository.CrudRepository

interface ApplicationRepository : CrudRepository<Application, Long> {
    fun existsBySchoolNumber(schoolNumber: Int): Boolean
}
