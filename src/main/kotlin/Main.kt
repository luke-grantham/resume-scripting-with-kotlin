package org.example

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.*
import com.itextpdf.layout.properties.TextAlignment
import org.example.ResumeMaker.Companion.addSkillSection


// https://api.itextpdf.com/iText/java/8.0.2/
fun main() {
    val DEST: String = "output/hello.pdf";
    val resumeMaker: ResumeMaker = ResumeMaker()

    val pdf = PdfDocument(PdfWriter(DEST))
    val document: Document = Document(pdf)

    document.topMargin = 36.0f;
    val summaryText = "This is the text for the summary's it's the text with summary and only the text with summary and it ca also have numbers and stuff like 123 and @#$%^&%$#!@#$%^&*()_+{}|<>?/.;' but mostly just text"


    document.add( nameHeading("Porkchop") )
    document.add( contactInfo("(555) 555 5555", "porkchop@gmail.com", "Atlanta, US") )
    summarySection("SUMMARY", summaryText).forEach {  it: BlockElement<IElement> ->
        document.add(it)
    }

    document.addSkillSection(
        resumeMaker.createSkillsSection(
            headerText = "SKILLS",
            skillLines = listOf(
                listOf("Loafing, Eating, Napping"),
                listOf("Being Cute, Catching Toys, Annoying my siblings"),
            ).toTypedArray(),
        )
    )
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
        .setFontSize(14.0f)
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
        //sectionHeader as BlockElement<T>,
        //separator as BlockElement<T>
    )

}





