package org.example

import com.itextpdf.io.font.constants.FontStyles
import com.itextpdf.io.font.constants.StandardFonts
import com.itextpdf.kernel.colors.CalGray
import com.itextpdf.kernel.colors.Color
import com.itextpdf.kernel.colors.DeviceCmyk
import com.itextpdf.kernel.colors.DeviceGray
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.*
import com.itextpdf.layout.properties.TextAlignment
import org.example.sections.ExperienceSection
import org.example.sections.Job
import org.example.sections.SkillSection
import org.example.sections.SummarySection
import java.awt.color.ColorSpace
import java.time.YearMonth

class ResumeMaker {

    private fun createSectionHeader(headerText: String): Paragraph {
        return Paragraph(headerText)
            .setTextAlignment(TextAlignment.LEFT)
            .setFontSize(HEADER_TEXT_SIZE)
            .setMarginBottom(-4f) // Makes the LineSeparator closer to the header text
    }

    fun createNameHeader(name: String) : Paragraph {
        return Paragraph(name)
            .setTextAlignment(TextAlignment.CENTER)
            .setFontSize(NAME_TEXT_SIZE)
    }

    fun createContactInfo(vararg infos: String): Paragraph {
        val contactInfo = infos.reduce() { it1, it2 -> "$it1   |   $it2" }
        return Paragraph(contactInfo)
            .setTextAlignment(TextAlignment.CENTER)
            .setBold()
            .setFontSize(SMALL_TEXT_SIZE)
    }

    fun createSummarySection(headerText: String, summaryText: String): SummarySection {

        val summary = Paragraph(summaryText)
            .setTextAlignment(TextAlignment.LEFT)
            .setFontSize(SMALL_TEXT_SIZE)
            .setFontColor(SMALL_TEXT_COLOR)

        return SummarySection(
            header = createSectionHeader(headerText),
            lineSeparator = LINE_SEPARATOR,
            summary = summary
        )
    }

    fun createSkillsSection(headerText: String, vararg skillLines: kotlin.collections.List<String>): SkillSection {

        val skills = skillLines.map { skillLine ->
            Paragraph(skillLine.reduce { skill1, skill2 -> "$skill1, $skill2" })
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(SMALL_TEXT_SIZE)
                .setBold()
        }

        return SkillSection(
            header = createSectionHeader("SKILLS"),
            lineSeparator = LINE_SEPARATOR,
            skills = skills
        )
    }

    fun createJob(
        jobTitle: String,
        company: String,
        from: String,
        to: String?,
        bullets: List<String>
    ): Job {


        val fromToString = "$from - ${to ?: "Present"}"

        val columnWidths = floatArrayOf(204f, 204f, 204f)
        val table = Table(columnWidths)
        table.setBorder(Border.NO_BORDER)


        val jobTitleCell = Cell(1, 1)
            .add(Paragraph(jobTitle))
            .setFontSize(SMALL_TEXT_SIZE)
            .setBold()
            .setFontColor(DeviceRgb.BLUE)
            .setTextAlignment(TextAlignment.LEFT)
            .setBorder(Border.NO_BORDER)

        val companyCell = Cell(1, 1)
            .add(Paragraph(company))
            .setFontSize(SMALL_TEXT_SIZE)
            .setBold()
            .setTextAlignment(TextAlignment.CENTER)
            .setBorder(Border.NO_BORDER)


        val fromToCell = Cell(1, 1)
            .add(Paragraph(fromToString))
            .setFontSize(SMALL_TEXT_SIZE)
            .setBold()
            .setTextAlignment(TextAlignment.RIGHT)
            .setBorder(Border.NO_BORDER)

        table.addCell(jobTitleCell)
        table.addCell(companyCell)
        table.addCell(fromToCell)

        val bulletList = com.itextpdf.layout.element.List()
            .setListSymbol(BULLET_SYMBOL)
            .setFontSize(SMALL_TEXT_SIZE)



        bullets.forEach { bullet -> bulletList.add(bullet) }

        return Job(
            header = table,
            bullets = bulletList
        )


    }

    fun createExperienceSection(
        headerText: String,
        jobs: kotlin.collections.List<Job>
    ): ExperienceSection {

        return ExperienceSection(
            header = createSectionHeader(headerText),
            lineSeparator = LINE_SEPARATOR,
            jobs = jobs
        )
    }



    companion object {
        const val SMALL_TEXT_SIZE = 10.0f
        const val HEADER_TEXT_SIZE = 14.0f
        const val NAME_TEXT_SIZE = 28.0f
        val SMALL_TEXT_COLOR = DeviceRgb(89, 84, 84)
        val LINE_SEPARATOR = LineSeparator(SolidLine(1.0f))
        const val BULLET_SYMBOL = "• "


    }
}