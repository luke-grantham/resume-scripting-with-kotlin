package tech.lukegrantham

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import java.io.File

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
fun ResumeBuilder.formatting(block: Formatting.() -> Unit) {
    val formatting = Formatting()
    formatting.block()
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
fun <T: SectionDSL> ResumeBuilder.initSectionPart(section: T, block: T.() -> Unit) {
    section.block()
    section.addToDocument(this.document, this.formatting)
}


class HeadingDSL : SectionDSL {
    var name: String = ""
    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createNameHeader(this, formatting))
    }

    fun name(s: String ) { name = s }
}

fun ResumeBuilder.heading(block: HeadingDSL.() -> Unit) = initSectionPart(HeadingDSL(), block)



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

fun ResumeBuilder.contactInfo(block: ContactInfoDSL.() -> Unit) = initSectionPart(ContactInfoDSL(), block)




class LinksSectionDSL : SectionDSL {
    val links: MutableList<String> = mutableListOf()

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createLinksSection(this, formatting))
    }

    fun link(s: String) = links.add(s)
}

fun ResumeBuilder.links(block: LinksSectionDSL.() -> Unit) = initSectionPart(LinksSectionDSL(), block)


class SkillSectionDSL : SectionDSL {
    var header: String = "SKILLS"
    var skills: MutableList<List<String>> = mutableListOf()

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createSkillsSection(this, formatting))
    }

    fun header (s: String) { header = s }
    fun skills(vararg s: String ) { skills.add(s.toList()) }
}

fun ResumeBuilder.skillSection(block: SkillSectionDSL.() -> Unit) = initSectionPart(SkillSectionDSL(), block)





class SummarySectionDSL : SectionDSL {
    var header: String = "SUMMARY"
    var text: String = ""

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createSummarySection(this, formatting))
    }

    fun header(s: String) { header = s }
    fun text(s: String ) { text = s }
}

fun ResumeBuilder.summary(block: SummarySectionDSL.() -> Unit) = initSectionPart(SummarySectionDSL(), block)






class ExtraSectionDSL : SectionDSL {
    var header: String = "CERTIFICATIONS"
    var bullets: MutableList<String> = mutableListOf()

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createExtraSection(this, formatting))
    }

    fun header(s: String) { header = s }
    fun bullet(s: String ) = bullets.add(s)
}

fun ResumeBuilder.section(block: ExtraSectionDSL.() -> Unit) = initSectionPart(ExtraSectionDSL(), block)





class ExperienceSectionDSL(
    var header: String = "EXPERIENCE",
    var jobs: MutableList<JobDSL> = mutableListOf()
) : SectionDSL {

    override fun addToDocument(document: Document, formatting: Formatting) {
        document.add(ResumeMaker.createExperienceSection(this, formatting))
    }

    fun header(s: String) { header = s }
}

fun ResumeBuilder.experience(block: ExperienceSectionDSL.() -> Unit) = initSectionPart(ExperienceSectionDSL(), block)
fun ResumeBuilder.education(block: ExperienceSectionDSL.() -> Unit) = initSectionPart(ExperienceSectionDSL(header = "EDUCATION"), block)




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
fun ExperienceSectionDSL.job(block: JobDSL.() -> Unit) {
    // Create the DSL Object
    // Run the function inside the object
    // Add it to the ExperienceSection
    val jobDSL = JobDSL()

    jobDSL.block()

    this.jobs.add(jobDSL)
}

fun ExperienceSectionDSL.edu(customFunction: JobDSL.() -> Unit) {
    job(customFunction)
}


fun ResumeBuilder(fileName:String, block: ResumeBuilder.() -> Unit) {

    File("output").mkdirs()
    val out = "output/$fileName"

    val resumeBuilder = ResumeBuilder(
        document = Document(PdfDocument(PdfWriter(out))),
        formatting = Formatting()
    )

    resumeBuilder.document.topMargin = 30.0f;

    resumeBuilder.block()

    resumeBuilder.document.close()
    println("PDF Resume created at $out")
}