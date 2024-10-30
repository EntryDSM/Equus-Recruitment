package entry.dsm.equusrecruitment.domain.application.exception

import entry.dsm.equusrecruitment.domain.global.error.exception.EntryBlogException
import entry.dsm.equusrecruitment.domain.global.error.exception.ErrorCode

object ApplicationAlreadyExistException : EntryBlogException(ErrorCode.APPLICATION_ALREADY_EXIST)