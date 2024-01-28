# Kotlin Resume Scripting
Have you ever wanted to write your resumes in Kotlin? Well now you can!

# Setup

The scripting experience is most pleasant in IntelliJ where you'll get IDE features when writing the Kotlin scripts.
1. Download IntelliJ
2. File -> New -> Project From Version Control
3. Enter the project URL (`https://github.com/luke-grantham/resume-maker.git`) and press Clone:
4. Execute `./gradlew publishToMavenLocal`

Now in `src/main/scripts`, you should be able to run the example script `example.main.kts`.
Your resulting pdf will be in `src/main/scripts/output`

To make your own script simply create a new file with the suffix `.main.kts`

# Motivation
I was paying $70 for site where I could build my resume.

They had a few features I liked:
 - They had one really clean format (and a bunch I would never ever use).
 - Easy to rearrange sections without breaking everything.
 - Easy to change formatting like spacing between bullets.
 - Export to PDF.

I use Kotlin at my day job and wanted to learn more about Kotlin DSLs and Kotlin scripting. 
It turns out that the hierarchical nature of building documents translates very well to a DSL.

In the end, I learned a bit and saved myself $70 a year.

# Licence
I used a PDF library called itext, so to open source it I'm forced to use the ___ licence 