package tech.lukegrantham.sections

import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Paragraph

class SummarySection(
    val header: Paragraph,
    val lineSeparator: LineSeparator,
    val summary: Paragraph
)