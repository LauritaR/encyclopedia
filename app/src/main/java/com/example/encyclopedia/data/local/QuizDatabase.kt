package com.example.encyclopedia.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Question::class, Answer::class], version = 1)
abstract class QuizDatabase: RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao

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
                )
            )
        }
    }
}
