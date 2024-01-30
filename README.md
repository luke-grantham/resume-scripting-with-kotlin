# Kotlin Resume Scripting
Have you ever wanted to write your resumes in Kotlin? Well now you can!

Through the power of Kotlin Scripting and DSLs we can turn something like the below code into a beatiful resume:
```
ResumeBuilder(fileName = "example.pdf") {

    heading {
        name("Luke Grantham")
    }

    contactInfo {
        telephone(555.toString() + " 555 5555")
        email("asdf@gmail.com")
        location("Atlanta, GA")
    }

    experience {
        header("EXPERIENCE")
        job {
            title("Software Engineer")
            company("Company Two Inc.")
            from("January 2023")
            to("Presnet")
            bullet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
        }
        job {
            title("Software Engineer")
            company("Company One Inc.")
            from("January 2020")
            to("December 2023")
            bullet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
        }
    }

    education {
        header("EDUCATION")
        edu {
            school("University of Georgia")
            degree("Bachelor of Computer Science")
            from("May 2013")
            to("May 2015")
            bullet("Algorithms")
        }
    }
}
```

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
 - One really clean format (and a bunch I would never use).
 - Easy to rearrange sections without breaking everything.
 - Easy to change formatting like spacing between bullets.
 - Export to PDF.

I use Kotlin at my day job and wanted to learn more about Kotlin DSLs and Kotlin scripting. 
It turns out that the hierarchical nature of building documents translates very well to a DSL.

In the end, I learned a bit and saved myself $70 a year.

# Licence
I used a PDF library called itext, which is under the AGPLv3 licence. So, this project will fall under the same license. See the full licence text at `licence.txt`