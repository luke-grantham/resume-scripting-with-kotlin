package tech.lukegrantham

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document

class Formatting {
    var textSize       : Float = 10.0f
    var headerSize     : Float = 13.0f
    var nameHeaderSize : Float = 28.0f
    var contactInfoSize: Float = 11f
    var cellPadding    : Float = 0f
    var linkSize       : Float = 7.5f

    fun textSize(s: Number) { textSize = s.toFloat() }
    fun headerSize(s: Number) { headerSize = s.toFloat() }
    fun nameHeaderSize(s: Number) { nameHeaderSize = s.toFloat() }
    fun contactInfoSize(s: Number) { contactInfoSize = s.toFloat() }
    fun cellPadding(s: Number) { cellPadding = s.toFloat() }
    fun linkSize(s: Number) { linkSize = s.toFloat() }
}
fun ResumeBuilder.formatting(inner: Formatting.() -> Unit) {
    val formatting = Formatting()
    formatting.inner()
    this.formatting = formatting
}


class ResumeBuilder(
    val document: Document,
    var formatting: Formatting
)
interface SectionDSL {
    fun addToDocument(document: Document, formatting: Formatting)
}

/*
*   In order to expose the DSL, each SectionDSL implementor needs a corresponding function with ResumeBuilder as the receiver.
*   That function needs to run the DSL function inside it, then add the section to the document.
*
*   This Generic function can be used to create that functionality for any given SectionDSL implementor
*/
fun <T: SectionDSL> ResumeBuilder.initSectionPart(section: T, inner: T.() -> Unit) {
    section.inner()
    section.addToDocument(this.document, this.formatting)
}


class HeadingDSL : SectionDSL {
    var name: String = ""
    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createNameHeader(this, formatting))
    }

    fun name(s: String ) { name = s }
}

fun ResumeBuilder.heading(inner: HeadingDSL.() -> Unit) = initSectionPart(HeadingDSL(), inner)



class ContactInfoDSL : SectionDSL {
    var telephone: String = ""
    var email: String = ""
    var location: String = ""

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createContactInfo(this, formatting))
    }

    fun telephone(s: String) { telephone = s }
    fun email(s: String) { email = s }
    fun location(s: String) { location = s }
}

fun ResumeBuilder.contactInfo(inner: ContactInfoDSL.() -> Unit) = initSectionPart(ContactInfoDSL(), inner)




class LinksSectionDSL : SectionDSL {
    val links: MutableList<String> = mutableListOf()

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createLinksSection(this, formatting))
    }

    fun link(s: String) = links.add(s)
}

fun ResumeBuilder.links(inner: LinksSectionDSL.() -> Unit) = initSectionPart(LinksSectionDSL(), inner)


class SkillSectionDSL : SectionDSL {
    var header: String = "SKILLS"
    var skills: MutableList<List<String>> = mutableListOf()

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createSkillsSection(this, formatting))
    }

    fun header (s: String) { header = s }
    fun skills(vararg s: String ) { skills.add(s.toList()) }
}

fun ResumeBuilder.skillSection(inner: SkillSectionDSL.() -> Unit) = initSectionPart(SkillSectionDSL(), inner)





class SummarySectionDSL : SectionDSL {
    var header: String = "SUMMARY"
    var text: String = ""

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createSummarySection(this, formatting))
    }

    fun header(s: String) { header = s }
    fun text(s: String ) { text = s }
}

fun ResumeBuilder.summary(inner: SummarySectionDSL.() -> Unit) = initSectionPart(SummarySectionDSL(), inner)






class ExtraSectionDSL : SectionDSL {
    var header: String = "CERTIFICATIONS"
    var bullets: MutableList<String> = mutableListOf()

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createExtraSection(this, formatting))
    }

    fun header(s: String) { header = s }
    fun bullet(s: String ) = bullets.add(s)
}

fun ResumeBuilder.section(inner: ExtraSectionDSL.() -> Unit) = initSectionPart(ExtraSectionDSL(), inner)





class ExperienceSectionDSL : SectionDSL {
    var header: String = "Experience"
    var jobs: MutableList<JobDSL> = mutableListOf()

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createExperienceSection(this, formatting))
    }

    fun header(s: String) { header = s }
}

fun ResumeBuilder.experience(inner: ExperienceSectionDSL.() -> Unit) = initSectionPart(ExperienceSectionDSL(), inner)
fun ResumeBuilder.education(inner: ExperienceSectionDSL.() -> Unit) = initSectionPart(ExperienceSectionDSL(), inner)




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
fun ExperienceSectionDSL.job(inner: JobDSL.() -> Unit) {
    // Create the DSL Object
    // Run the function inside the object
    // Add it to the ExperienceSection
    val jobDSL = JobDSL()

    jobDSL.inner()

    this.jobs.add(jobDSL)
}

fun ExperienceSectionDSL.edu(customFunction: JobDSL.() -> Unit) {
    job(customFunction)
}


fun ResumeBuilder(inner: ResumeBuilder.() -> Unit) {

    val resumeBuilder = ResumeBuilder(
        document = Document(PdfDocument(PdfWriter("output/hello.pdf"))),
        formatting = Formatting()
    )

    resumeBuilder.document.topMargin = 30.0f;

    resumeBuilder.inner()

    resumeBuilder.document.close()
    println("Awesome PDF just got created.")
}