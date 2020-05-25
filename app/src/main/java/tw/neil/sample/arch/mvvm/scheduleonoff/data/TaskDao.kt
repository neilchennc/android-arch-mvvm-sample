package tw.neil.sample.arch.mvvm.scheduleonoff.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY id ASC, day_of_week ASC")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task ORDER BY id ASC, day_of_week ASC")
    fun getAllLiveData(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE day_of_week=:dayOfWeek AND screen_on=:screenOnOff LIMIT 1")
    fun get(dayOfWeek: Int, screenOnOff: Boolean): Task

    @Query("SELECT * FROM task WHERE day_of_week=:dayOfWeek AND screen_on=:screenOnOff LIMIT 1")
    fun getLiveData(dayOfWeek: Int, screenOnOff: Boolean): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg tasks: Task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(tasks: Iterable<Task>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: Task)

    @Query("UPDATE task SET enabled=:enabled")
    fun enableAll(enabled: Boolean)

    @Query("UPDATE task SET enabled=:enabled WHERE day_of_week=:dayOfWeek")
    fun enableWeek(dayOfWeek: Int, enabled: Boolean)

    @Query("UPDATE task SET enabled=:enabled WHERE screen_on=1")
    fun enableScreenOn(enabled: Boolean)

    @Query("UPDATE task SET enabled=:enabled WHERE screen_on=0")
    fun enableScreenOff(enabled: Boolean)

    @Update
    fun update(task: Task): Int

    @Delete
    fun delete(task: Task): Int

}