package com.example.ledgefy.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction_table ORDER BY date DESC")
    fun getAllTransactions(): LiveData<List<com.example.ledgefy.model.Transaction>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transaction: com.example.ledgefy.model.Transaction)

    @Delete
    suspend fun delete(transaction: com.example.ledgefy.model.Transaction)
}
