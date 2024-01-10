package org.example

import com.itextpdf.layout.Document
import org.example.sections.*
import org.example.ResumeMaker.Companion.LINE_SEPARATOR

fun Document.addSkillSection(skillSection: SkillSection) {
    this.add(skillSection.header)
    this.add(skillSection.lineSeparator)
    skillSection.skills.forEach { skillLine ->
        this.add(skillLine)
    }
}

fun Document.addExperienceSection(experienceSection: ExperienceSection) {
    this.add(experienceSection.header)
    this.add(experienceSection.lineSeparator)
    experienceSection.jobs.forEach { job ->
        this.addJob(job)
    }
}

fun Document.addJob(job: Job) {
    this.add(job.header)
    this.add(job.bullets)
}

fun Document.addSummarySection(summarySection: SummarySection) {
    this.add(summarySection.header)
    this.add(summarySection.lineSeparator)
    this.add(summarySection.summary)
}


fun Document.addExtraSection(extraSection: ExtraSection) {
    this.add(extraSection.header)
    this.add(LINE_SEPARATOR)
    this.add(extraSection.bullets)
}