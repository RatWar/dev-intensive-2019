package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return when (fullName) {
            "", " ", null -> Pair(null, null)
            else -> {
                val parts: List<String>? = fullName.split(" ")
                val firstName = parts?.getOrNull(0)
                val lastName = parts?.getOrNull(1)
                firstName to lastName
            }
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first= oneLetter(firstName)
        val last = oneLetter(lastName)
        when {
            (first == null) and (last == null) -> return null
            else -> when {
                first == null -> return last
                last == null -> return first
                else -> return first + last
            }
        }
    }

    private fun oneLetter(src: String?) : String? {
        return when (src) {
            "", " ", null -> null
            else -> src[0].toUpperCase().toString()
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val first = payload.substringBefore(" ").decapitalize()
        val last = payload.substringAfter(" ").decapitalize()
        return replace(first).capitalize() + divider + replace(last).capitalize()
    }

    private fun replace(src: String) = run {
        src.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюя]")) {
            when (it.value) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh'"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                else -> it.value
            }
        }
    }
}