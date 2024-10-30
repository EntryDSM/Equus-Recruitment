package entry.dsm.equusrecruitment.domain.application.exception

import entry.dsm.equusrecruitment.domain.global.error.exception.ErrorCode
import entry.dsm.equusrecruitment.domain.global.error.exception.RecruitmentException

object ApplicationAlreadyExistException : RecruitmentException(ErrorCode.APPLICATION_ALREADY_EXIST)
