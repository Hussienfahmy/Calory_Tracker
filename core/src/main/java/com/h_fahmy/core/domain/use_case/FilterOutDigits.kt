package com.h_fahmy.core.domain.use_case

class FilterOutDigits {

    operator fun invoke(string: String): String {
        return string.filter { it.isDigit() }
    }

}