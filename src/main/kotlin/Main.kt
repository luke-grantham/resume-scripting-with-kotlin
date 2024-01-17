package org.example

// https://api.itextpdf.com/iText/java/8.0.2/
fun main() {



    ResumeBuilder {

        formatting {// optional
            textSize(10.0)
            headerSize(13.0)
            nameHeaderSize(28.0f)
            contactInfoSize(11)
            cellPadding(0)
        }

        heading {
            name("Porkchop")
        }

        contactInfo {
            telephone(555.toString() + " 555 5555")
            email("porkchop@gmail.com")
            location("Atlanta, GA")
        }

        summary {
            header("SUMMARY")
            text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla. Integer id mi eget arcu pulvinar varius. Vestibulum congue urna in risus pulvinar, id eleifend ex malesuada.")
        }

        skillSection {
            header("SKILLZZZ")
            skills("Meowing", "Hoop Jumping", "Fighting")
            skills("Eating", "Sleeping")
            skills("meow", "meow")
        }

        experience {
            header("EXPERIENCE")
            job {
                title("Mascot")
                company("PetCo")
                from("April 2023")
                to("December 2023")
                bullet("aaaLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
                bullet("bbbLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
                bullet("cccLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            }
            job {
                title("House Cat")
                company("Forever Home")
                from("December 2023")
                to("Present")
                bullet("aaaLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
                bullet("bbbLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
                bullet("cccLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            }
        }

        section {
            header("CERTIFICATIONSSSS")
            bullet("aaaLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("bbbLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("cccLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
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

}





