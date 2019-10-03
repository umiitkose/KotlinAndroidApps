package com.example.quiz.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quiz.model.Question

@Database(entities = arrayOf(Question::class), version = 1)
abstract class QuestionDB: RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    companion object{
        @Volatile
        var INSTANCE: QuestionDB? = null

        @Synchronized
        fun getInstance(context: Context): QuestionDB{
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionDB::class.java,
                    "question_db"
                )
                    .addCallback(roomDBCallback)
                    .build()
            }
            return INSTANCE as QuestionDB
        }

        private val roomDBCallback = object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsyncTask(INSTANCE).execute()
            }

        }

        class PopulateAsyncTask(private val db: QuestionDB?): AsyncTask<Void,Void,Void>() {

            private val dao: QuestionDao? by lazy {
                db?.questionDao()
            }

            override fun doInBackground(vararg p0: Void?): Void? {
                var question = Question(
                    question ="Android uygulama yazabilmek için hangi programlama dilini bilgmek gerekir.",
                    optionA ="Java",
                    optionB ="Kotlin",
                    optionC ="Kotlin",
                    optionD = "Hepsi",
                    answer ="Hepsi"
                )
                dao?.addQuestion(question)
                var question1 = Question(
                    question ="Android uygulama yazabilmek için hangi programlama dilini bilgmek gerekir.",
                    optionA ="Java",
                    optionB ="Kotlin",
                    optionC ="Kotlin",
                    optionD = "Hepsi",
                    answer ="Hepsi"
                )
                dao?.addQuestion(question1)
                var question2 = Question(
                    question ="Android uygulama yazabilmek için hangi programlama dilini bilgmek gerekir.",
                    optionA ="Java",
                    optionB ="Kotlin",
                    optionC ="Kotlin",
                    optionD = "Hepsi",
                    answer ="Hepsi"
                )
                dao?.addQuestion(question2)
                var question3 = Question(
                    question ="Android uygulama yazabilmek için hangi programlama dilini bilgmek gerekir.",
                    optionA ="Java",
                    optionB ="Kotlin",
                    optionC ="Kotlin",
                    optionD = "Hepsi",
                    answer ="Hepsi"
                )
                dao?.addQuestion(question3)
                return null
            }
        }
    }
}