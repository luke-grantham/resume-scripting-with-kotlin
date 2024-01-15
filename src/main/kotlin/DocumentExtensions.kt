package org.example

import com.itextpdf.layout.Document
import org.example.sections.*
import org.example.ResumeMaker.LINE_SEPARATOR

fun Document.add(contactInfo: ContactInfo) {
    this.add(contactInfo.contactInfoParagraph)
}
fun Document.add(nameHeader: NameHeader) {
    this.add(nameHeader.header)
}

fun Document.add(skillSection: SkillSection) {
    this.add(skillSection.header)
    this.add(skillSection.lineSeparator.setMarginBottom(4f))
    skillSection.skills.forEach { skillLine ->
        this.add(skillLine)
    }
}

fun Document.add(experienceSection: ExperienceSection) {
    this.add(experienceSection.header)
    this.add(experienceSection.lineSeparator)
    experienceSection.jobs.forEach { job ->
        this.add(job)
    }
}

fun Document.add(job: Job) {
    this.add(job.header)
    this.add(job.bullets)
}

fun Document.add(summarySection: SummarySection) {
    this.add(summarySection.header)
    this.add(summarySection.lineSeparator.setMarginBottom(4f))
    this.add(summarySection.summary)
}


fun Document.add(extraSection: ExtraSection) {
    this.add(extraSection.header)
    this.add(LINE_SEPARATOR.setMarginBottom(4f))
    this.add(extraSection.bullets)
}