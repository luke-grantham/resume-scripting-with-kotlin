package tech.lukegrantham.sections

import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Paragraph

class ExperienceSection(
    val header: Paragraph,
    val lineSeparator: LineSeparator,
    val jobs: List<Job>
)