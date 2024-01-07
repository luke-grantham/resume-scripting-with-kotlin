package org.example

import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.TextAlignment
import org.example.sections.Job
import org.example.sections.SkillSection
import java.time.YearMonth

class ResumeMaker {

    fun createSkillsSection(headerText: String, vararg skillLines: kotlin.collections.List<String>): SkillSection {

        val separator = LineSeparator(SolidLine(1.0f))

        val sectionHeader = Paragraph(headerText)
            .setTextAlignment(TextAlignment.LEFT)
            .setFontSize(14.0f)

        val skills = skillLines.map { skillLine ->
            Paragraph(skillLine.reduce { skill1, skill2 -> "$skill1, $skill2" })
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(SMALL_TEXT_SIZE)
                .setBold()
        }

        return SkillSection(
            header = sectionHeader,
            lineSeparator = separator,
            skills = skills
        )
    }

    fun createJob(title: String,
                  company: String,
                  from: YearMonth,
                  to: YearMonth?,
                  bullets: List<String>): Job {




    }

    fun createExperienceSection(headerText: String, vararg jobs: kotlin.collections.List<Job>): SkillSection {

    }



    companion object {
        const val SMALL_TEXT_SIZE = 8.0f
        const val HEADER_TEXT_SIZE = 14.0f

        fun Document.addSkillSection(skillSection: SkillSection) {
            this.add(skillSection.header)
            this.add(skillSection.lineSeparator)
            skillSection.skills.forEach { skillLine ->
                this.add(skillLine)
            }
        }
    }
}