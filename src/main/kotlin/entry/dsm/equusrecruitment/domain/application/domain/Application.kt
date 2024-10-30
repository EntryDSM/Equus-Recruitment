package entry.dsm.equusrecruitment.domain.application.domain

import entry.dsm.equusrecruitment.domain.application.domain.enum.Experience
import entry.dsm.equusrecruitment.domain.application.domain.enum.Major
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "application_tbl")
class Application(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(columnDefinition = "varchar(30)", nullable = false)
    val name: String,
    @Column(columnDefinition = "int(4)", nullable = false, unique = true)
    val schoolNumber: Int,
    @Column(columnDefinition = "varchar(20)", nullable = false)
    val phoneNumber: String,
    @Column(columnDefinition = "TEXT", nullable = false)
    val introduce: String,
    @Column(columnDefinition = "varchar(15)", nullable = false)
    @Enumerated(value = EnumType.STRING)
    val interestingMajor: Major,
    @Column(columnDefinition = "TEXT", nullable = false)
    val motivation: String,
    @Column(columnDefinition = "varchar(30)", nullable = false)
    @Enumerated(value = EnumType.STRING)
    val experience: Experience,
)
