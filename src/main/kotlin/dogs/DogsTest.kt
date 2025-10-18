package dogs

fun main() {
    DogRepository.getInstanceDogRepository("qwertyu").dogs.forEach (::println)
}