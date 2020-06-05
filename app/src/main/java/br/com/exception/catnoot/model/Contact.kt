package  br.com.exception.catnoot.model

data class Contact(
        var name: String,
        var phoneNumber: String,
        var email: String? = null,
        val id: Long? = null,
        var onDelete: ((Contact)->Unit)? = null
)