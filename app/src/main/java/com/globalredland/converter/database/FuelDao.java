package com.globalredland.converter.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FuelDao {

    @Query("SELECT * FROM combustibles WHERE clase = 5")
    List<FuelEntry> loadAllFuels();

    @Query("SELECT * FROM combustibles WHERE clase = :clase")
    List<FuelEntry> loadFuelByClass(int clase);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllFuels(FuelEntry... fuelEntries);

    @Insert
    void insertFuel(FuelEntry fuelEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFuel(FuelEntry fuelEntry);

    @Delete
    void deleteFuel(FuelEntry fuelEntry);

}