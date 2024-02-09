# Resume Build Scripts in Kotlin
Have you ever wanted to write your resumes in Kotlin? Well now you can!

Through the power of Kotlin Scripting and DSLs we can turn the below code into a beautiful resume:
```
ResumeBuilder(fileName = "readme.pdf") {

    heading {
        name("Luke Grantham")
    }

    contactInfo {
        telephone("555 555 5555")
        email("email@gmail.com")
        location("Atlanta, GA")
    }

    links {
        link("http://linkedin.com/in/luke-grantham")
        link("http://github.com/luke-grantham")
    }

    experience {
        job {
            title("Software Engineer")
            company("Company Inc.")
            from("April 2023")
            to("Present")
            bullet("aaaLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("bbbLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
        }
        job {
            title("Software Engineer")
            company("Company2 Inc.")
            from("January 2015")
            to("April 2023")
            bullet("aaaLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("bbbLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
        }
    }

    education {
        edu {
            school("University of Georgia")
            degree("Bachelor of Computer Science")
            from("May 2013")
            to("May 2015")
        }
    }
}
```

![alt text](./readme-resume.png?raw=true)


# Setup

The scripting experience is most pleasant in IntelliJ where you'll get IDE features when writing the Kotlin scripts.

You'll need JDK 8 or higher.
1. Download IntelliJ
2. File -> New -> Project From Version Control
3. Enter the project URL (`https://github.com/luke-grantham/resume-maker.git`) and press Clone:
4. Execute `./gradlew publishToMavenLocal`

Now in `src/main/scripts`, you should be able to run the example script `example.main.kts`.
Your resulting pdf will be in `src/main/scripts/output`

COMMENTS
1. Need to specify that JAVA_HOME env var needs to be set
2. Need to install gradle dependencies (i did it VS code so i guess i was missing everything)
3. Not sure how to run a kotlin script. kotlinc command via bash command line doesn't work
4. me dummy

### Make your own resumes
- To make your own script simply create a new file with the suffix `.main.kts`.
- All the sections inside the `ResumeBuilder` block are optional.
- Please refer to `example.main.kts` as documentation on the DSL itself. The example script uses all available functionality.


# Why I made this
I wanted to learn more about DSLs in Kotlin, and I realized that I was paying $70 for site where I could build my resume.

What I wanted to accomplish is:
 - Have one really clean format.
 - Easy to rearrange sections without breaking formatting.
 - Easy to change formatting like text size and spacing between bullets.
 - Generates a PDF.

It turns out that the hierarchical nature of building documents translates very well to a DSL.

In the end, I learned a bit, had fun, and saved myself $70 a year.

# Licence
I used a PDF library called itext, which is under the AGPLv3 licence. So, this project will fall under the same license. See the full licence text at `licence.txt`