package tw.neil.sample.arch.mvvm.scheduleonoff.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    private class TaskDatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                GlobalScope.launch {
                    populateDefaultData(database)
                }
            }
        }

        fun populateDefaultData(database: TaskDatabase) {
            val taskDao = database.taskDao()
            var index = 0L // table's primary key

            // create default data for a week, each day have the only two tasks/times
            // one is start time (screen on), another is end time (screen off)
            val tasks = arrayListOf(
                Task(++index, Calendar.SUNDAY, 7, 0, true, false),
                Task(++index, Calendar.SUNDAY, 7, 0, false, false),
                Task(++index, Calendar.MONDAY, 7, 0, true, false),
                Task(++index, Calendar.MONDAY, 19, 59, false, false),
                Task(++index, Calendar.TUESDAY, 7, 0, true, false),
                Task(++index, Calendar.TUESDAY, 19, 59, false, false),
                Task(++index, Calendar.WEDNESDAY, 7, 0, true, false),
                Task(++index, Calendar.WEDNESDAY, 19, 59, false, false),
                Task(++index, Calendar.THURSDAY, 7, 0, true, false),
                Task(++index, Calendar.THURSDAY, 19, 59, false, false),
                Task(++index, Calendar.FRIDAY, 7, 0, true, false),
                Task(++index, Calendar.FRIDAY, 19, 59, false, false),
                Task(++index, Calendar.SATURDAY, 7, 0, true, false),
                Task(++index, Calendar.SATURDAY, 19, 59, false, false)
            )

            // populate to database
            taskDao.insertAll(tasks)
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    DB_NAME
                )
                    .addCallback(TaskDatabaseCallback())
                    .build()
                    .also { INSTANCE = it }
            }
        }

        private const val DB_NAME = "task_database"
    }
}