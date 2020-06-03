data class Tarefa(
        val contactId: Int,
        var contactName: String,
        var contactPhoneNumber: String,
        var contactEmail: String? = null,
        var description: String? = null
)