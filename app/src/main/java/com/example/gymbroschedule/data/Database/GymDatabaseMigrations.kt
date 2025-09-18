package com.example.gymbroschedule.data.Database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise1R2` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise1R3` TEXT NOT NULL DEFAULT ''")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE weight_records (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                scheduleId INTEGER NOT NULL,
                weight REAL NOT NULL,
                date INTEGER NOT NULL,
                FOREIGN KEY(scheduleId) REFERENCES `Gym-Schedule`(id) ON DELETE CASCADE
            )
            """
        )
    }
}

val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE weight_records ADD COLUMN dayOfWeek TEXT NOT NULL DEFAULT 'Unknown'")
    }
}

val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE user_preferences (
                id INTEGER PRIMARY KEY NOT NULL,
                selectedSchedule TEXT NOT NULL
            )
            """
        )
    }
}

val MIGRATION_9_10 = object : Migration(9, 10) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra1 TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra2 TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra3 TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra4 TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN Extra5 TEXT NOT NULL DEFAULT ''")
    }
}

val MIGRATION_10_11 = object : Migration(10, 11) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `Gym-Schedule` RENAME COLUMN Exercise5r2 TO `Exercise5R2`")
    }
}

val MIGRATION_11_12 = object : Migration(11, 12) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise7` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise7R2` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise7R3` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise8` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise8R2` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise8R3` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise9` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise9R2` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise9R3` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise10` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise10R2` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise10R3` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise11` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise11R2` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise11R3` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise12` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise12R2` TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE `Gym-Schedule` ADD COLUMN `gym-exercise12R3` TEXT NOT NULL DEFAULT ''")
    }
}

val MIGRATION_12_13 = object : Migration(12, 13) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create a temporary table with the correct schema
        database.execSQL("""
            CREATE TABLE `Gym-Schedule_temp` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `gym-day` TEXT NOT NULL,
                `gym-workout` TEXT NOT NULL,
                `gym-exercise1` TEXT NOT NULL,
                `gym-exercise1R2` TEXT NOT NULL,
                `gym-exercise1R3` TEXT NOT NULL,
                `gym-exercise2` TEXT NOT NULL,
                `gym-exercise2R2` TEXT NOT NULL,
                `gym-exercise2R3` TEXT NOT NULL,
                `gym-exercise3` TEXT NOT NULL,
                `gym-exercise3R2` TEXT NOT NULL,
                `gym-exercise3R3` TEXT NOT NULL,
                `gym-exercise4` TEXT NOT NULL,
                `gym-exercise4R2` TEXT NOT NULL,
                `gym-exercise4R3` TEXT NOT NULL,
                `gym-exercise5` TEXT NOT NULL,
                `gym-exercise5R2` TEXT NOT NULL,
                `gym-exercise5R3` TEXT NOT NULL,
                `gym-exercise6` TEXT NOT NULL,
                `gym-exercise6R2` TEXT NOT NULL,
                `gym-exercise6R3` TEXT NOT NULL,
                `gym-exercise7` TEXT NOT NULL,
                `gym-exercise7R2` TEXT NOT NULL,
                `gym-exercise7R3` TEXT NOT NULL,
                `gym-exercise8` TEXT NOT NULL,
                `gym-exercise8R2` TEXT NOT NULL,
                `gym-exercise8R3` TEXT NOT NULL,
                `gym-exercise9` TEXT NOT NULL,
                `gym-exercise9R2` TEXT NOT NULL,
                `gym-exercise9R3` TEXT NOT NULL,
                `gym-exercise10` TEXT NOT NULL,
                `gym-exercise10R2` TEXT NOT NULL,
                `gym-exercise10R3` TEXT NOT NULL,
                `gym-exercise11` TEXT NOT NULL,
                `gym-exercise11R2` TEXT NOT NULL,
                `gym-exercise11R3` TEXT NOT NULL,
                `gym-exercise12` TEXT NOT NULL,
                `gym-exercise12R2` TEXT NOT NULL,
                `gym-exercise12R3` TEXT NOT NULL,
                `extra-exercise` TEXT NOT NULL,
                `extra1` TEXT NOT NULL,
                `extra2` TEXT NOT NULL,
                `extra3` TEXT NOT NULL,
                `extra4` TEXT NOT NULL,
                `extra5` TEXT NOT NULL
            )
        """.trimIndent())

        // Copy data from the old table to the new table
        database.execSQL("""
            INSERT INTO `Gym-Schedule_temp` (
                `id`, `gym-day`, `gym-workout`,
                `gym-exercise1`, `gym-exercise1R2`, `gym-exercise1R3`,
                `gym-exercise2`, `gym-exercise2R2`, `gym-exercise2R3`,
                `gym-exercise3`, `gym-exercise3R2`, `gym-exercise3R3`,
                `gym-exercise4`, `gym-exercise4R2`, `gym-exercise4R3`,
                `gym-exercise5`, `gym-exercise5R2`, `gym-exercise5R3`,
                `gym-exercise6`, `gym-exercise6R2`, `gym-exercise6R3`,
                `gym-exercise7`, `gym-exercise7R2`, `gym-exercise7R3`,
                `gym-exercise8`, `gym-exercise8R2`, `gym-exercise8R3`,
                `gym-exercise9`, `gym-exercise9R2`, `gym-exercise9R3`,
                `gym-exercise10`, `gym-exercise10R2`, `gym-exercise10R3`,
                `gym-exercise11`, `gym-exercise11R2`, `gym-exercise11R3`,
                `gym-exercise12`, `gym-exercise12R2`, `gym-exercise12R3`,
                `extra-exercise`, `extra1`, `extra2`, `extra3`, `extra4`, `extra5`
            )
            SELECT
                `id`, `gym-day`, `gym-workout`,
                `gym-exercise1`, `gym-exercise1R2`, `gym-exercise1R3`,
                `gym-exercise2`, `gym-exercise2R2`, `gym-exercise2R3`,
                `gym-exercise3`, `gym-exercise3R2`, `gym-exercise3R3`,
                `gym-exercise4`, `gym-exercise4R2`, `gym-exercise4R3`,
                `gym-exercise5`, `Exercise5R2`, `Exercise5R3`,
                '' AS `gym-exercise6`, '' AS `gym-exercise6R2`, '' AS `gym-exercise6R3`,
                `gym-exercise7`, `gym-exercise7R2`, `gym-exercise7R3`,
                `gym-exercise8`, `gym-exercise8R2`, `gym-exercise8R3`,
                `gym-exercise9`, `gym-exercise9R2`, `gym-exercise9R3`,
                `gym-exercise10`, `gym-exercise10R2`, `gym-exercise10R3`,
                `gym-exercise11`, `gym-exercise11R2`, `gym-exercise11R3`,
                `gym-exercise12`, `gym-exercise12R2`, `gym-exercise12R3`,
                `extra-exercise`, `Extra1`, `Extra2`, `Extra3`, `Extra4`, `Extra5`
            FROM `Gym-Schedule`
        """.trimIndent())

        // Drop the old table
        database.execSQL("DROP TABLE `Gym-Schedule`")

        // Rename the temporary table to the original name
        database.execSQL("ALTER TABLE `Gym-Schedule_temp` RENAME TO `Gym-Schedule`")
    }
}