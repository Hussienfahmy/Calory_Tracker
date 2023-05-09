package com.h_fahmy.core.domain.use_case

class FilterOutDigits {

    operator fun invoke(age: String): String {
        return age.filter { it.isDigit() }
    }

}