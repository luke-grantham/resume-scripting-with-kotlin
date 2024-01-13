package org.example

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document

class ResumeBuilder(
    val document: Document,
)

class HeadingDSL {
    var name: String = ""

    fun name(s: String ) { name = s }
}
fun ResumeBuilder.heading(customFunction: HeadingDSL.() -> Unit) {
    // Create the DSL Object
    // Run the function inside the object
    // Add it to the Document
    val resumeMaker = ResumeMaker()
    val headingDSL = HeadingDSL()

    headingDSL.customFunction()

    this.document.add(
        resumeMaker.createNameHeader(headingDSL)
    )

}

class ContactInfoDSL {
    var telephone: String = ""
    var email: String = ""
    var location: String = ""

    fun telephone(s: String) { telephone = s }
    fun email(s: String) { email = s }
    fun location(s: String) { location = s }
}



fun ResumeBuilder.contactInfo(customFunction: ContactInfoDSL.() -> Unit) {
    val contactInfoDSL = ContactInfoDSL()
    val resumeMaker = ResumeMaker()

    contactInfoDSL.customFunction()

    this.document.add(
        resumeMaker.createContactInfo(
            contactInfoDSL.telephone,
            contactInfoDSL.email,
            contactInfoDSL.location
        )
    )
}


class SkillSectionDSL {
    var header: String = "SKILLS"
    var skills: MutableList<List<String>> = mutableListOf()

    fun header (s: String) { header = s }
    fun skills(vararg s: String ) { skills.add(s.toList()) }
}
fun ResumeBuilder.skillSection(customFunction: SkillSectionDSL.() -> Unit) {
    // Create the DSL Object
    // Run the function inside the object
    // Add it to the Document
    val resumeMaker = ResumeMaker()
    val skillsDSL = SkillSectionDSL()

    skillsDSL.customFunction()

    this.document.addSkillSection(
        resumeMaker.createSkillsSection(skillsDSL)
    )

}

class SummarySectionDSL {
    var header: String = "SUMMARY"
    var text: String = ""

    fun header(s: String) { header = s }
    fun text(s: String ) { text = s }
}
fun ResumeBuilder.summary(customFunction: SummarySectionDSL.() -> Unit) {
    // Create the DSL Object
    // Run the function inside the object
    // Add it to the Document
    val resumeMaker = ResumeMaker()
    val summaryDSL = SummarySectionDSL()

    summaryDSL.customFunction()

    this.document.addSummarySection(
        resumeMaker.createSummarySection(summaryDSL)
    )

}


class ExtraSectionDSL {
    var header: String = "CERTIFICATIONS"
    var bullets: MutableList<String> = mutableListOf()

    fun header(s: String) { header = s }
    fun bullet(s: String ) = bullets.add(s)
}
fun ResumeBuilder.section(customFunction: ExtraSectionDSL.() -> Unit) {
    // Create the DSL Object
    // Run the function inside the object
    // Add it to the Document
    val resumeMaker = ResumeMaker()
    val extraDSL = ExtraSectionDSL()

    extraDSL.customFunction()

    this.document.addExtraSection(
        resumeMaker.createExtraSection(extraDSL)
    )

}


class ExperienceSectionDSL {
    var header: String = "Experience"
    var jobs: MutableList<JobDSL> = mutableListOf()

    fun header(s: String) { header = s }
}
fun ResumeBuilder.experience(customFunction: ExperienceSectionDSL.() -> Unit) {
    // Create the DSL Object
    // Run the function inside the object
    // Add it to the Document
    val resumeMaker = ResumeMaker()
    val experienceDSL = ExperienceSectionDSL()

    experienceDSL.customFunction()

    this.document.addExperienceSection(
        resumeMaker.createExperienceSection(experienceDSL)
    )

}

fun ResumeBuilder.education(customFunction: ExperienceSectionDSL.() -> Unit) {
    experience(customFunction)
}



class JobDSL {
    var jobTitle: String = ""
    var company: String = ""
    var from: String = ""
    var to: String? = ""
    var bullets: MutableList<String> = mutableListOf()

    fun title(s: String) { jobTitle = s }
    fun company(s: String) { company = s }

    fun school(s: String) = title(s)
    fun degree(s: String) = company(s)
    fun from(s: String) { from = s }
    fun to(s: String) { to = s }
    fun bullet(s: String) {  bullets.add(s) }
}
fun ExperienceSectionDSL.job(customFunction: JobDSL.() -> Unit) {
    // Create the DSL Object
    // Run the function inside the object
    // Add it to the ExperienceSection
    val jobDSL = JobDSL()

    jobDSL.customFunction()

    this.jobs.add(jobDSL)
}

fun ExperienceSectionDSL.edu(customFunction: JobDSL.() -> Unit) {
    job(customFunction)
}


fun ResumeBuilder(customFunction: ResumeBuilder.() -> Unit) {

    val resumeBuilder = ResumeBuilder(
        document = Document(PdfDocument(PdfWriter("output/hello.pdf"))),
    )
    resumeBuilder.document.topMargin = 30.0f;

    resumeBuilder.customFunction()

    resumeBuilder.document.close()
    println("Awesome PDF just got created.")
}