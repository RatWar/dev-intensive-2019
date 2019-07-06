package ru.skillbranch.devintensive.utils

object Utils {

    private val translitMap = mapOf(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z",
        'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l", 'м' to "m", 'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r",
        'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh",
        'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya"
    )
//    fun parseFullName(fullName: String?): Pair<String?, String?> {
//        return when (fullName) {
//            "", " ", null -> Pair(null, null)
//            else -> {
//                val parts: List<String>? = fullName.split(" ")
//                val firstName = parts?.getOrNull(0)
//                val lastName = parts?.getOrNull(1)
//                firstName to lastName
//            }
//        }
//    }

    private fun String.replaceAll(oldValue: String, newValue: String): String {
        var result = this
        while (result.contains(oldValue)) {
            result = result.replace(oldValue, newValue)
        }
        return result
    }

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.replaceAll("  ", " ")?.split(" ")

        val firstName = parts?.notEmptyOrNullAt(0)
        val lastName = parts?.notEmptyOrNullAt(1)

        return firstName to lastName
    }

    private fun List<String>.notEmptyOrNullAt(index: Int) = getOrNull(index).let {
        if ("" == it) null
        else it
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

//    fun toInitials(firstName: String?, lastName: String?): String? = when {
//        firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
//        !firstName.isNullOrBlank() && lastName.isNullOrBlank() -> firstName[0].toUpperCase().toString()
//        firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> lastName[0].toUpperCase().toString()
//        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> firstName[0].toUpperCase() + lastName[0].toUpperCase().toString()
//        else -> throw IllegalStateException("Incorrect state in 'when' expression")
//    }

    fun transliteration(payload: String, divider: String = " ") = buildString {
        payload.forEach {
            append(
                when {
                    it == ' ' -> divider
                    it.isUpperCase() -> translitMap[it.toLowerCase()]?.capitalize() ?: it.toString()
                    else -> translitMap[it] ?: it.toString()
                }
            )
        }
    }

}