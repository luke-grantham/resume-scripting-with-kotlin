@file:DependsOn("tech.lukegrantham:resume-maker:1.0")

import tech.lukegrantham.*

ResumeBuilder(fileName = "example.pdf") {

    formatting {// optional
        textSize(10.0)
        headerSize(13.0)
        nameHeaderSize(28.0f)
        contactInfoSize(11)
        cellPadding(0)
        linkSize(7.5)
    }

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

    summary {
        header("SUMMARY")
        text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla. Integer id mi eget arcu pulvinar varius. Vestibulum congue urna in risus pulvinar, id eleifend ex malesuada.")
    }

    skillSection {
        header("SKILLS")
        skills("Java", "SQL", "Skill1")
        skills("Databases", "Skill3", "Skill4")
        skills("Skill5", "Skill6", "Skill7", "Skill8")
    }

    experience {
        header("EXPERIENCE")
        job {
            title("Software Engineer")
            company("Company Inc.")
            from("April 2023")
            to("Present")
            bullet("aaaLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("bbbLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("cccLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
        }
        job {
            title("Software Engineer")
            company("Company2 Inc.")
            from("January 2015")
            to("April 2023")
            bullet("aaaLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("bbbLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
            bullet("cccLorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac vehicula nulla.")
        }
    }

    section {
        header("CERTIFICATIONS")
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
