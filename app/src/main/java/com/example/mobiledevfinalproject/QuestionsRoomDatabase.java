package com.example.mobiledevfinalproject;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Questions.class}, version = 2)
public abstract class QuestionsRoomDatabase extends androidx.room.RoomDatabase {
    private static QuestionsRoomDatabase INSTANCE;


    public abstract QuestionsDao wordDao();

    public static synchronized QuestionsRoomDatabase getInstance(final Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuestionsRoomDatabase.class, "questions_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuestionsDao wordDao;

        private PopulateDbAsyncTask(QuestionsRoomDatabase db) {
            wordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.insert(new Questions(" Android is -", "an operating system", "a web browser", "a web server", "Apple's system", "an operating system"));
            wordDao.insert(new Questions(" For which of the following Android is mainly developed? ", "Servers", "Desktops", "Laptops", "Mobile devices", "Mobile devices"));
            wordDao.insert(new Questions(" Which of the following is the first mobile phone released that ran the Android OS?", "HTC Hero", "iPhone 4", "T - Mobile G1", "Google gPhone", "T - Mobile G1"));
            wordDao.insert(new Questions(" APK stands for - ", "Android Phone Kit", "Android Page Kit", "Advance Power Kit", "Android Package Kit", "Android Package Kit"));

            wordDao.insert(new Questions("What two words every programmer learned to code first?", "Stack Overflow", "Hello, world.", "pip install", "Text Editors", "Hello, world."));
            wordDao.insert(new Questions("What is the most popular programming problem?", "Unit Test", "Print Statements", "Memory Error", "Missing a Semicolon", "Missing a Semicolon"));
            wordDao.insert(new Questions("How many Infinity Stones are there?", "6", "Infinity ", "5", "8", "6"));
            wordDao.insert(new Questions("Which section of Boston is home to Mike's Pastry, La Dolce Vita, and The Improv Asylum?", "Beacon Hill", "South End", "North End", "Fenway", "North End"));
            wordDao.insert(new Questions("In what year was Fenway Park opened? ", "1988", "1912", "2003", "1956", "1912"));
            wordDao.insert(new Questions("Boston was the first city with:", "Seaport", "Subway System", "McDonalds", "Zoo", "Subway System"));
            return null;
        }
    }

}
