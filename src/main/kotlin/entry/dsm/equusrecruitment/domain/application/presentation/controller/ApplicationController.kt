package entry.dsm.equusrecruitment.domain.application.presentation.controller

import entry.dsm.equusrecruitment.domain.application.presentation.dto.request.InternApplicationRequest
import entry.dsm.equusrecruitment.domain.application.service.InternApplicationService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/applications")
class ApplicationController(
    private val internApplicationService: InternApplicationService,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun internApplication(
        @RequestBody @Valid request: InternApplicationRequest,
    ) {
        internApplicationService.applicationRegister(request)
    }
}
