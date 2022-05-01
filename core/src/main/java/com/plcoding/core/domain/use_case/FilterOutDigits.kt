package com.plcoding.core.domain.use_case

class FilterOutDigits {
    //use case performs a single action in this case filters out a number from text
    //can change the use case here and it will be applied to every part that uses it
    //when using a use case provide it to dagger-hilt
    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}