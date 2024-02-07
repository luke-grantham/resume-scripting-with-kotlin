package tech.lukegrantham.sections

import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Paragraph

class SkillSection(
    val header: Paragraph,
    val skills: kotlin.collections.List<Paragraph>
)