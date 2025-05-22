package com.example.encyclopedia.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Question::class, Answer::class, User::class, QuizResult::class],
    version = 5)

abstract class QuizDatabase: RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
    abstract fun userDao(): UserDao
    abstract fun quizResultDao(): QuizResultDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun populateInitialData(context: Context) {
            val questionDao = getDatabase(context).questionDao()

            CoroutineScope(Dispatchers.IO).launch {
                questionDao.deleteAllQuestions()
                questionDao.insertQuestions(getPredefinedQuestions())
            }
        }

        private fun getPredefinedQuestions(): List<Question> {

            return listOf(
                Question(
                    category = "Mammals",
                    questionText = "Which animal is known as the king of the jungle?",
                    option1 = "Tiger",
                    option2 = "Lion",
                    option3 = "Elephant",
                    option4 = "Giraffe",
                    correctOption = 2
                ),
                Question(
                    category = "Mammals",
                    questionText = "What is the largest land animal?",
                    option1 = "Elephant",
                    option2 = "Rhinoceros",
                    option3 = "Giraffe",
                    option4 = "Hippo",
                    correctOption = 1
                ),
                Question(
                    category = "Mammals",
                    questionText = "Which mammal is known for having a pouch to carry its babies?",
                    option1 = "Elephant",
                    option2 = "Lion",
                    option3 = "Kangaroo",
                    option4 = "Tiger",
                    correctOption = 3
                ),
                Question(
                    category = "Mammals",
                    questionText = "Which mammal is capable of true flight?",
                    option1 = "Flying Squirrel",
                    option2 = "Bat",
                    option3 = "Kangaroo",
                    option4 = "Lemur",
                    correctOption = 2
                ),
                Question(
                    category = "Mammals",
                    questionText = "Which mammal lays eggs?",
                    option1 = "Platypus",
                    option2 = "Kangaroo",
                    option3 = "Elephant",
                    option4 = "Giraffe",
                    correctOption = 1
                ),
                Question(
                    category = "Mammals",
                    questionText = "Which mammal spends almost all of its life in water?",
                    option1 = "Horse",
                    option2 = "Dolphin",
                    option3 = "Dog",
                    option4 = "Bear",
                    correctOption = 2
                ),
                Question(
                    category = "Mammals",
                    questionText = "What is the only mammal capable of sustained flight?",
                    option1 = "Flying Squirrel",
                    option2 = "Bat",
                    option3 = "Owl",
                    option4 = "Parrot",
                    correctOption = 2
                ),
                Question(
                    category = "Mammals",
                    questionText = "Which mammal has the longest gestation period?",
                    option1 = "Blue Whale",
                    option2 = "Human",
                    option3 = "Elephant",
                    option4 = "Giraffe",
                    correctOption = 3
                ),
                Question(
                    category = "Mammals",
                    questionText = "Which marine mammal is known for its tusk?",
                    option1 = "Dolphin",
                    option2 = "Walrus",
                    option3 = "Narwhal",
                    option4 = "Seal",
                    correctOption = 3
                ),
                // Birds category
                Question(
                    category = "Birds",
                    questionText = "Which bird is known for its ability to mimic human speech?",
                    option1 = "Sparrow",
                    option2 = "Eagle",
                    option3 = "Owl",
                    option4 = "Parrot",
                    correctOption = 4
                ),
                Question(
                    category = "Birds",
                    questionText = "Which bird is the fastest flying bird?",
                    option1 = "Hummingbird",
                    option2 = "Eagle",
                    option3 = "Peregrine Falcon",
                    option4 = "Swan",
                    correctOption = 3
                ),
                Question(
                    category = "Birds",
                    questionText = "Which bird is known for its elaborate courtship dance?",
                    option1 = "Penguin",
                    option2 = "Peacock",
                    option3 = "Crow",
                    option4 = "Pelican",
                    correctOption = 2
                ),
                Question(
                    category = "Birds",
                    questionText = "What bird has the largest wingspan?",
                    option1 = "Bald Eagle",
                    option2 = "Albatross",
                    option3 = "Vulture",
                    option4 = "Stork",
                    correctOption = 2
                ),
                Question(
                    category = "Birds",
                    questionText = "Which bird is known to be flightless?",
                    option1 = "Penguin",
                    option2 = "Swan",
                    option3 = "Parrot",
                    option4 = "Falcon",
                    correctOption = 1
                ),
                Question(
                    category = "Birds",
                    questionText = "Which bird is famous for being nocturnal?",
                    option1 = "Eagle",
                    option2 = "Parrot",
                    option3 = "Owl",
                    option4 = "Hawk",
                    correctOption = 3
                ),
                Question(
                    category = "Birds",
                    questionText = "What is a group of crows called?",
                    option1 = "Flock",
                    option2 = "Pack",
                    option3 = "Murder",
                    option4 = "Swarm",
                    correctOption = 3
                ),
                Question(
                    category = "Birds",
                    questionText = "Which bird migrates the longest distance?",
                    option1 = "Sparrow",
                    option2 = "Arctic Tern",
                    option3 = "Stork",
                    option4 = "Pigeon",
                    correctOption = 2
                ),
                Question(
                    category = "Birds",
                    questionText = "Which bird lays the largest eggs?",
                    option1 = "Chicken",
                    option2 = "Emu",
                    option3 = "Ostrich",
                    option4 = "Penguin",
                    correctOption = 3
                ),
                // Reptiles category
                Question(
                    category = "Reptiles",
                    questionText = "Which reptile is known for its ability to change color?",
                    option1 = "Lizard",
                    option2 = "Snake",
                    option3 = "Crocodile",
                    option4 = "Chameleon",
                    correctOption = 4
                ),
                Question(
                    category = "Reptiles",
                    questionText = "Which reptile has the strongest bite force?",
                    option1 = "Alligator",
                    option2 = "Crocodile",
                    option3 = "Komodo Dragon",
                    option4 = "Gecko",
                    correctOption = 2
                ),
                Question(
                    category = "Reptiles",
                    questionText = "What is the largest species of turtle?",
                    option1 = "Green Sea Turtle",
                    option2 = "Box Turtle",
                    option3 = "Leatherback Turtle",
                    option4 = "Red-eared Slider",
                    correctOption = 3
                ),
                Question(
                    category = "Reptiles",
                    questionText = "Which reptile can detach its tail to escape predators?",
                    option1 = "Snake",
                    option2 = "Turtle",
                    option3 = "Lizard",
                    option4 = "Crocodile",
                    correctOption = 3
                ),
                Question(
                    category = "Reptiles",
                    questionText = "What kind of reptile is the Komodo Dragon?",
                    option1 = "Snake",
                    option2 = "Lizard",
                    option3 = "Turtle",
                    option4 = "Gecko",
                    correctOption = 2
                ),
                Question(
                    category = "Reptiles",
                    questionText = "Which reptile is known to live the longest?",
                    option1 = "Snake",
                    option2 = "Komodo Dragon",
                    option3 = "Tortoise",
                    option4 = "Alligator",
                    correctOption = 3
                ),
                Question(
                    category = "Reptiles",
                    questionText = "Which part of a snake's body detects heat from prey?",
                    option1 = "Eyes",
                    option2 = "Tongue",
                    option3 = "Nostrils",
                    option4 = "Pit organs",
                    correctOption = 4
                ),
                Question(
                    category = "Reptiles",
                    questionText = "What is the name of the largest living reptile?",
                    option1 = "Komodo Dragon",
                    option2 = "Green Anaconda",
                    option3 = "Saltwater Crocodile",
                    option4 = "Leatherback Turtle",
                    correctOption = 3
                ),
                Question(
                    category = "Reptiles",
                    questionText = "Which reptile is known for its frill that fans out when threatened?",
                    option1 = "Gecko",
                    option2 = "Frilled Lizard",
                    option3 = "Iguana",
                    option4 = "Monitor Lizard",
                    correctOption = 2
                ),
                // Fish category
                Question(
                    category = "Fish",
                    questionText = "Which fish is known for its ability to generate electric shocks?",
                    option1 = "Salmon",
                    option2 = "Goldfish",
                    option3 = "Shark",
                    option4 = "Electric Eel",
                    correctOption = 4
                ),
                Question(
                    category = "Fish",
                    questionText = "What is the largest species of fish?",
                    option1 = "Great White Shark",
                    option2 = "Whale Shark",
                    option3 = "Manta Ray",
                    option4 = "Stingray",
                    correctOption = 2
                ),
                Question(
                    category = "Fish",
                    questionText = "Which fish is famous for its vibrant colors and being a popular aquarium pet?",
                    option1 = "Tuna",
                    option2 = "Salmon",
                    option3 = "Clownfish",
                    option4 = "Bass",
                    correctOption = 3
                ),
                Question(
                    category = "Fish",
                    questionText = "Which fish can inflate itself as a defense mechanism?",
                    option1 = "Goldfish",
                    option2 = "Pufferfish",
                    option3 = "Tuna",
                    option4 = "Catfish",
                    correctOption = 2
                ),
                Question(
                    category = "Fish",
                    questionText = "What part of the body do fish use to breathe?",
                    option1 = "Lungs",
                    option2 = "Fins",
                    option3 = "Gills",
                    option4 = "Scales",
                    correctOption = 3
                ),
                Question(
                    category = "Fish",
                    questionText = "Which fish is known to have a symbiotic relationship with sea anemones?",
                    option1 = "Clownfish",
                    option2 = "Shark",
                    option3 = "Tuna",
                    option4 = "Eel",
                    correctOption = 1
                ),
                Question(
                    category = "Fish",
                    questionText = "Which fish can swim backwards?",
                    option1 = "Goldfish",
                    option2 = "Shark",
                    option3 = "Eel",
                    option4 = "Betta",
                    correctOption = 3
                ),
                Question(
                    category = "Fish",
                    questionText = "What kind of fish is a 'guppy'?",
                    option1 = "Freshwater",
                    option2 = "Saltwater",
                    option3 = "Deep-sea",
                    option4 = "Tropical reef",
                    correctOption = 1
                ),
                Question(
                    category = "Fish",
                    questionText = "Which fish has a flattened body and is often buried in sand?",
                    option1 = "Tuna",
                    option2 = "Catfish",
                    option3 = "Stingray",
                    option4 = "Mackerel",
                    correctOption = 3
                ),
            )
        }
    }
}
