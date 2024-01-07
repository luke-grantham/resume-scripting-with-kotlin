package org.example.sections

import java.time.YearMonth

class Job(
    val title: String,
    val company: String,
    val from: YearMonth,
    val to: YearMonth?,
    val bullets: List<String>
)