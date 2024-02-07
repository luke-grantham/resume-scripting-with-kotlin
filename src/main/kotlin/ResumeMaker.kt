package tech.lukegrantham

import com.itextpdf.kernel.colors.DeviceCmyk
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.*
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment
import tech.lukegrantham.sections.*

object ResumeMaker {

    private val SMALL_TEXT_COLOR = DeviceRgb(89, 84, 84)
    private val BLUE_TEXT_COLOR = DeviceCmyk(100, 19, 0, 5)
    private val HYPERLINK_COLOR = DeviceRgb(0, 102, 204)
    private const val BULLET_SYMBOL = "•"

    // todo: big | in contact info

    private fun createSectionHeader(headerText: String, headerTextSize: Float): Paragraph {
        return Paragraph(headerText)
            .setTextAlignment(TextAlignment.LEFT)
            .setFontSize(headerTextSize)
            .setMarginBottom(-4f) // Makes the LineSeparator closer to the header text
            .setPaddingTop(5f)
    }

    fun createNameHeader(headingDSL: HeadingDSL, formatting: Formatting) : NameHeader {
        return NameHeader(
            header = Paragraph(headingDSL.name)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(-2f) // Less empty space under the name header
                .setFontSize(formatting.nameHeaderSize)
        )
    }

    fun createContactInfo(contactInfoDSL: ContactInfoDSL, formatting: Formatting): ContactInfo {
        val contactInfo = "${contactInfoDSL.telephone}         |         ${contactInfoDSL.email}         |         ${contactInfoDSL.location}"
        val contactInfoParagraph = Paragraph(contactInfo)
            .setTextAlignment(TextAlignment.CENTER)
            .setFontSize(formatting.contactInfoSize)
            .setMarginBottom(-2f) // Less empty space under the contact info

        return ContactInfo(
            contactInfoParagraph = contactInfoParagraph
        )
    }

    fun createLinksSection(linksSectionDSL: LinksSectionDSL, formatting: Formatting): LinksSection {

        val links: Paragraph = linksSectionDSL.links.fold(Paragraph()) { init, acc -> init.add(Paragraph(acc).setUnderline()).add("            ")  }

        val linksParagraph = links
            .setTextAlignment(TextAlignment.CENTER)
            .setFontSize(formatting.linkSize)
            .setFontColor(HYPERLINK_COLOR)
            .setMarginBottom(-8f) // Less empty space under the links

        return LinksSection(
            linksParagraph = linksParagraph
        )
    }

    fun createSummarySection(summaryDSL: SummarySectionDSL, formatting: Formatting): SummarySection {

        val summary = Paragraph(summaryDSL.text)
            .setTextAlignment(TextAlignment.LEFT)
            .setFontSize(formatting.textSize)
            .setFontColor(SMALL_TEXT_COLOR)

        return SummarySection(
            header = createSectionHeader(summaryDSL.header, formatting.headerSize),
            summary = summary
        )
    }

    fun createSkillsSection(skillsDSL: SkillSectionDSL, formatting: Formatting): SkillSection {

        val skills = skillsDSL.skills.map { skillLine ->
            Paragraph(skillLine.reduce { skill1, skill2 -> "$skill1,  $skill2" })
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(formatting.textSize)

        }

        return SkillSection(
            header = createSectionHeader(skillsDSL.header, formatting.headerSize),
            skills = skills
        )
    }

    fun createJob(
        jobDSL: JobDSL,
        formatting: Formatting
    ): Job {


        val fromToString = "${jobDSL.from} - ${jobDSL.to ?: "Present"}"

        val columnWidths = floatArrayOf(204f, 204f, 204f)
        val headerTable = Table(columnWidths)
        headerTable
            .setBorder(Border.NO_BORDER)
            .setMargin(5f)


        val jobTitleCell = Cell(1, 1)
            .add(Paragraph(jobDSL.jobTitle))
            .setFontSize(formatting.textSize)
            
            .setFontColor(BLUE_TEXT_COLOR)
            .setTextAlignment(TextAlignment.LEFT)
            .setBorder(Border.NO_BORDER)

        val companyCell = Cell(1, 1)
            .add(Paragraph(jobDSL.company))
            .setFontSize(formatting.textSize)
            
            .setTextAlignment(TextAlignment.CENTER)
            .setBorder(Border.NO_BORDER)


        val fromToCell = Cell(1, 1)
            .add(Paragraph(fromToString))
            .setFontSize(formatting.textSize)
            
            .setTextAlignment(TextAlignment.RIGHT)
            .setBorder(Border.NO_BORDER)

        headerTable.addCell(jobTitleCell)
        headerTable.addCell(companyCell)
        headerTable.addCell(fromToCell)



        val bulletTable = Table(floatArrayOf(16F,602f))
        bulletTable.setMarginLeft(15f)

        // 612 972
        jobDSL.bullets.forEachIndexed { i, bulletText ->

            val bulletSymbolCell = Cell(i+1, 1)
                .add(Paragraph(BULLET_SYMBOL))
                .setFontSize(formatting.textSize)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setPadding(formatting.cellPadding)


            val bulletTextCell = Cell(i+1, 2)
                .add(Paragraph(bulletText))
                .setFontSize(formatting.textSize)
                .setFontColor(SMALL_TEXT_COLOR)
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setPadding(formatting.cellPadding)
                .setBorder(Border.NO_BORDER)


            bulletTable.addCell(bulletSymbolCell)
            bulletTable.addCell(bulletTextCell)
        }


        return Job(
            header = headerTable,
            bullets = bulletTable
        )


    }

    fun createExtraSection(
        extraSectionDSL: ExtraSectionDSL,
        formatting: Formatting
    ): ExtraSection {

        val bulletTable = Table(floatArrayOf(16F,602f))
        bulletTable.setMarginLeft(15f)

        // 612 972
        extraSectionDSL.bullets.forEachIndexed { i, bulletText ->


            val bulletSymbolCell = Cell(i+1, 1)
                .add(Paragraph(BULLET_SYMBOL))
                .setFontSize(formatting.textSize)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setPadding(formatting.cellPadding)


            val bulletTextCell = Cell(i+1, 2)
                .add(Paragraph(bulletText))
                .setFontSize(formatting.textSize)
                .setFontColor(SMALL_TEXT_COLOR)
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setPadding(formatting.cellPadding)
                .setBorder(Border.NO_BORDER)


            bulletTable.addCell(bulletSymbolCell)
            bulletTable.addCell(bulletTextCell)
        }


        return ExtraSection(
            header = createSectionHeader(extraSectionDSL.header, formatting.headerSize),
            bullets = bulletTable
        )


    }

    fun createExperienceSection(
        experienceDSL: ExperienceSectionDSL,
        formatting: Formatting
    ): ExperienceSection {

        return ExperienceSection(
            header = createSectionHeader(experienceDSL.header, formatting.headerSize),
            jobs = experienceDSL.jobs.map { jobsDSL -> createJob(jobsDSL, formatting) }
        )
    }


}