package org.example

import com.itextpdf.layout.Document
import org.example.sections.ExperienceSection
import org.example.sections.Job
import org.example.sections.SkillSection
import org.example.sections.SummarySection

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


