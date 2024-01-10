package org.example

import com.itextpdf.kernel.colors.DeviceCmyk
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.*
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment
import org.example.sections.*

class ResumeMaker {

    // todo
    // todo: Druid Sans
    // todo: big | in contact info

    private fun createSectionHeader(headerText: String): Paragraph {
        return Paragraph(headerText)
            .setTextAlignment(TextAlignment.LEFT)
            .setFontSize(HEADER_TEXT_SIZE)
            .setMarginBottom(-4f) // Makes the LineSeparator closer to the header text
            .setPaddingTop(5f)
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
            
            .setFontSize(CONTACT_INFO_TEXT_SIZE)
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
        val headerTable = Table(columnWidths)
        headerTable
            .setBorder(Border.NO_BORDER)
            .setMargin(5f)


        val jobTitleCell = Cell(1, 1)
            .add(Paragraph(jobTitle))
            .setFontSize(SMALL_TEXT_SIZE)
            
            .setFontColor(BLUE_TEXT_COLOR)
            .setTextAlignment(TextAlignment.LEFT)
            .setBorder(Border.NO_BORDER)

        val companyCell = Cell(1, 1)
            .add(Paragraph(company))
            .setFontSize(SMALL_TEXT_SIZE)
            
            .setTextAlignment(TextAlignment.CENTER)
            .setBorder(Border.NO_BORDER)


        val fromToCell = Cell(1, 1)
            .add(Paragraph(fromToString))
            .setFontSize(SMALL_TEXT_SIZE)
            
            .setTextAlignment(TextAlignment.RIGHT)
            .setBorder(Border.NO_BORDER)

        headerTable.addCell(jobTitleCell)
        headerTable.addCell(companyCell)
        headerTable.addCell(fromToCell)



        val bulletTable = Table(floatArrayOf(16F,602f))
        bulletTable
            .setMarginLeft(15f)

        // 612 972
        bullets.forEachIndexed { i, bulletText ->

            val bulletSymbolCell = Cell(i+1, 1)
                .add(Paragraph(BULLET_SYMBOL))
                .setFontSize(BULLET_SYMBOL_SIZE)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setRelativePosition(0f, 0f, 0f, 1f)
                .setPadding(CELL_PADDING)


            val bulletTextCell = Cell(i+1, 2)
                .add(Paragraph(bulletText))
                .setFontSize(SMALL_TEXT_SIZE)
                .setFontColor(SMALL_TEXT_COLOR)
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setPadding(CELL_PADDING)
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
        headerText: String,
        bullets: List<String>
    ): ExtraSection {

        val bulletTable = Table(floatArrayOf(16F,602f))
        bulletTable.setMarginLeft(15f)

        // 612 972
        bullets.forEachIndexed { i, bulletText ->


            val bulletSymbolCell = Cell(i+1, 1)
                .add(Paragraph(BULLET_SYMBOL))
                .setFontSize(BULLET_SYMBOL_SIZE)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER)
                .setRelativePosition(0f, 0f, 0f, 1f)
                .setPadding(CELL_PADDING)


            val bulletTextCell = Cell(i+1, 2)
                .add(Paragraph(bulletText))
                .setFontSize(SMALL_TEXT_SIZE)
                .setFontColor(SMALL_TEXT_COLOR)
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setPadding(CELL_PADDING)
                .setBorder(Border.NO_BORDER)


            bulletTable.addCell(bulletSymbolCell)
            bulletTable.addCell(bulletTextCell)
        }


        return ExtraSection(
            header = createSectionHeader(headerText),
            bullets = bulletTable
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
        const val HEADER_TEXT_SIZE = 13.0f
        const val NAME_TEXT_SIZE = 28.0f
        const val BULLET_SYMBOL_SIZE = 14f
        const val CELL_PADDING = 0f
        const val CONTACT_INFO_TEXT_SIZE = 11f
        val SMALL_TEXT_COLOR = DeviceRgb(89, 84, 84)
        val BLUE_TEXT_COLOR = DeviceCmyk(100, 19, 0, 5)
        val LINE_SEPARATOR = LineSeparator(SolidLine(1.0f)).setMarginTop(2f)
        const val BULLET_SYMBOL = "•"


    }
}