package  br.com.exception.catnoot.model

import java.io.Serializable

data class Contact(
        var name: String,
        var phoneNumber: String,
        var email: String? = null,
        val id: Long? = null
)