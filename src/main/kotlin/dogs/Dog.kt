package dogs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    @SerialName("id") val id: Int,
    @SerialName("dog_name") val dogName: String,
    @SerialName("age") val age: Int,
    @SerialName("breed") val breed: String,
    @SerialName("color") val color: String,
    @SerialName("weight") val weight: Double
)