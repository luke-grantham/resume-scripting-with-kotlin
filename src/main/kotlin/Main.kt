package org.example

import com.itextpdf.io.font.constants.StandardFonts
import com.itextpdf.kernel.colors.DeviceGray
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.*
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import org.example.ResumeMaker.Companion.SMALL_TEXT_SIZE
import org.example.sections.ExtraSection

//import org.example.ResumeMaker.Companion.addSkillSection



// https://api.itextpdf.com/iText/java/8.0.2/
fun main() {
    val DEST: String = "output/hello.pdf";
    val resumeMaker: ResumeMaker = ResumeMaker()

    val pdf = PdfDocument(PdfWriter(DEST))
    val document: Document = Document(pdf)

    document.topMargin = 30.0f;
    val summaryText = "This is the text for the summary's it's the text with summary and only the text with summary and it ca also have numbers and stuff like 123 and @#$%^&%$#!@#$%^&*()_+{}|<>?/.;' but mostly just text"


    document.add( resumeMaker.createNameHeader("Porkchop") )
    document.add( resumeMaker.createContactInfo("(555) 555 5555", "porkchop@gmail.com", "Atlanta, US") )
    document.addSummarySection( resumeMaker.createSummarySection("SUMMARY", summaryText))
    /*summarySection("SUMMARY", summaryText).forEach {  it: BlockElement<IElement> ->
        document.add(it)
    }*/

    document.addSkillSection(
        resumeMaker.createSkillsSection(
            headerText = "SKILLS",
            skillLines = listOf(
                listOf("Loafing, Eating, Napping"),
                listOf("Being Cute, Catching Toys, Annoying my siblings"),
            ).toTypedArray(),
        )
    )

    document.addExperienceSection(
        resumeMaker.createExperienceSection(
            headerText = "EXPERIENCE",
            jobs = listOf(
                resumeMaker.createJob(
                    jobTitle = "House Cat",
                    company = "Meow Meow Inc.",
                    from = "July 2020",
                    to = "Present",
                    bullets = listOf(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                        "Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit.",
                        "Integer vestibulum gravida ex eu lobortis. In hac habitasse platea dictumst. Praesent vehicula placerat quam, non dapibus lorem posuere ut.",
                        "Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit.",
                        "Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit."
                    )
                ),
                resumeMaker.createJob(
                    jobTitle = "Kitten",
                    company = "PetCo",
                    from = "January 2019",
                    to = "July 2020",
                    bullets = listOf(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                        "Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit.",
                        "Integer vestibulum gravida ex eu lobortis. In hac habitasse platea dictumst. Praesent vehicula placerat quam, non dapibus lorem posuere ut.",
                        "Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit.",
                        "Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit. Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit. Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit. Duis at ligula at augue sodales congue nec quis risus. Aenean sollicitudin nisl vel erat vehicula suscipit."
                    )
                )
            )
        )
    )

    document.addExtraSection(

        resumeMaker.createExtraSection(
            headerText = "CERTIFICATIONS & AWARDS",
            bullets = listOf(
                "AWS Certified Solutions Architect Associate",
                "R&D Excellence Award given for my work on an ETL project at NCR"
            )
        )
    )

    document.addExperienceSection(
        resumeMaker.createExperienceSection(
            headerText = "EDUCATION",
            jobs = listOf(
                resumeMaker.createJob(
                    jobTitle = "University of Georgia",
                    company = "Bachelor of Computer Science",
                    from = "Aug '13",
                    to = "May '15",
                    bullets = emptyList()

                )
            )
        )
    )

    // 612 972
   /* val columnWidths = floatArrayOf(204f, 204f, 204f)
    val table = Table(columnWidths)
    table.setBorder(Border.NO_BORDER)


    val cell = Cell(1, 1)
        .add(Paragraph("cell 1"))
        .setFontSize(10f)
        .setBold()
        .setFontColor(DeviceRgb.BLUE)
        .setTextAlignment(TextAlignment.LEFT)
        .setBorder(Border.NO_BORDER)

    val cell2 = Cell(1, 1)
        .add(Paragraph("cell 2"))
        .setFontSize(10f)
        .setBold()
        .setTextAlignment(TextAlignment.CENTER)
        .setBorder(Border.NO_BORDER)


    val cell3 = Cell(1, 1)
        .add(Paragraph("cell 3"))
        .setFontSize(10f)
        .setBold()
        .setTextAlignment(TextAlignment.RIGHT)
        .setBorder(Border.NO_BORDER)*/

    //table.addHeaderCell(cell)
  //  table.addCell(cell)
//    table.addCell(cell2)
    //table.addCell(cell3)

    //document.add( sectionHeader("Summary"))



    //document.add( List(   ).add("item1").add("item2").setListSymbol("•") )
    //document.add(separator)
    //document.add(Paragraph(wording).setTextAlignment(TextAlignment.CENTER)).setFontSize(24.0f)




    document.close()

    println("Awesome PDF just got created.")
}

fun nameHeading(name: String) : Paragraph {
    return Paragraph(name)
        .setTextAlignment(TextAlignment.CENTER)
        .setFontSize(28.0f)
}

fun contactInfo(vararg infos: String): Paragraph {
    val contactInfo = infos.reduce() { it1, it2 -> "$it1   |   $it2" }
    return Paragraph(contactInfo)
        .setTextAlignment(TextAlignment.CENTER)
        .setBold()
        .setFontSize(SMALL_TEXT_SIZE)
}


// Paragraph extends BlockElement<Paragraph>
// LineSeparator extends BlockElement<LineSeparator>

//fun <T: IElement> sectionHeader(s: String): kotlin.collections.List<BlockElement<T>> {
fun summarySection(s: String, text: String): kotlin.collections.List<BlockElement<IElement>> {
    val line = SolidLine(1.0f)
    val separator = LineSeparator(line)
    val sectionHeader = Paragraph(s)
        .setTextAlignment(TextAlignment.LEFT)
        .setFontSize(14.0f)

    val summary = Paragraph(text)
        .setTextAlignment(TextAlignment.LEFT)
        .setFontSize(10.0f) as BlockElement<IElement>

    return listOf(
        sectionHeader as BlockElement<IElement>,
        separator as BlockElement<IElement>,
        summary
    )

}





