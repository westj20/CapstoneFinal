package sixsmobile.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SixSv26";
    public static final int DATABASE_VERSION = 1;


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //UPDATED
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String department = "CREATE TABLE department (id INTEGER PRIMARY KEY, department TEXT)";
        String zone = "CREATE TABLE zone (id INTEGER PRIMARY KEY, zone TEXT)";
        String audit = "CREATE TABLE audit (id INTEGER PRIMARY KEY, department TEXT, zone TEXT, date TEXT, sort1 INTEGER, sort2 INTEGER, sort3 INTEGER, sort4 INTEGER)";
        String auditResults = "CREATE TABLE auditResults (" +
                "_id INTEGER PRIMARY KEY, date TEXT, department TEXT, zone TEXT, sort1 INTEGER,sort2 INTEGER," +
                "sort3 INTEGER,sort4 INTEGER, set1 INTEGER, set2 INTEGER," +
                "set3 INTEGER, set4 INTEGER, sustain1 INTEGER, sustain2 INTEGER," +
                "sustain3 INTEGER, sustain4 INTEGER, standardize1 INTEGER, standardize2 INTEGER," +
                "standardize3 INTEGER, standardize4 INTEGER, shine1 INTEGER, shine2 INTEGER," +
                "shine3 INTEGER, shine4 INTEGER, safety1 INTEGER, safety2 INTEGER, safety3 INTEGER, safety4 INTEGER," +
                "sumSort INTEGER, sumSet INTEGER, sumSustain INTEGER, sumStandardize INTEGER, sumShine INTEGER, sumSafety INTEGER, totalScore INTEGER)";



        sqLiteDatabase.execSQL(department);
        sqLiteDatabase.execSQL(zone);
        sqLiteDatabase.execSQL(audit);
        sqLiteDatabase.execSQL(auditResults);


        Log.d("Database", "Tables created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void  department (String department){
        ContentValues cv = new ContentValues();
        cv.put("department", department);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("department", null, cv);
        db.close();
    }
    public void insertZone(String zone) {
        ContentValues cv = new ContentValues();
        cv.put("zone", zone);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("zone", null, cv);
        db.close();
    }

    public long insertAuditData(String department, String zone, String date) {
        ContentValues cv = new ContentValues();
        cv.put("department", department);
        cv.put("zone", zone);
        cv.put("date", date);

        SQLiteDatabase db = getWritableDatabase();
        long newRowId = -1;

        try {
            newRowId = db.insert("audit", null, cv);
            Log.d("Database", "Audit data inserted successfully. Row ID: " + newRowId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Database", "Error inserting audit data: " + e.getMessage());
        } finally {
            db.close();
        }

        return newRowId;
    }


    public void insertAuditResults(String date, String department, String zone, int sort1, int sort2, int sort3, int sort4, int set1, int set2, int set3, int set4, int sustain1, int sustain2, int sustain3, int sustain4, int standardize1, int standardize2, int standardize3, int standardize4, int shine1, int shine2, int shine3, int shine4, int safety1, int safety2, int safety3, int safety4) {

        int sumSort = sort1 + sort2 + sort3 + sort4;
        int sumSet = set1 + set2 + set3 + set4;
        int sumSustain = sustain1 + sustain2 + sustain3 + sustain4;
        int sumStandardize = standardize1 + standardize2 + standardize3 + standardize4;
        int sumShine = shine1 + shine2 + shine3 + shine4;
        int sumSafety = safety1 + safety2 + safety3 + safety4;
        int totalScore = sort1 + sort2 + sort3 + sort4 + set1 + set2 + set3 + set4 + sustain1 + sustain2 + sustain3 + sustain4 + standardize1 + standardize2 + standardize3 + standardize4 + shine1 + shine2 + shine3 + shine4 + safety1 + safety2 + safety3 + safety4;

        ContentValues cv = new ContentValues();

        cv.put("date", date);
        cv.put("department", department);
        cv.put("zone", zone);
        cv.put("sort1", sort1);
        cv.put("sort2", sort2);
        cv.put("sort3", sort3);
        cv.put("sort4", sort4);
        cv.put("set1", set1);
        cv.put("set2", set2);
        cv.put("set3", set3);
        cv.put("set4", set4);
        cv.put("sustain1", sustain1);
        cv.put("sustain2", sustain2);
        cv.put("sustain3", sustain3);
        cv.put("sustain4", sustain4);
        cv.put("standardize1", standardize1);
        cv.put("standardize2", standardize2);
        cv.put("standardize3", standardize3);
        cv.put("standardize4", standardize4);
        cv.put("shine1", shine1);
        cv.put("shine2", shine2);
        cv.put("shine3", shine3);
        cv.put("shine4", shine4);
        cv.put("safety1", safety1);
        cv.put("safety2", safety2);
        cv.put("safety3", safety3);
        cv.put("safety4", safety4);

        cv.put("sumSort", sumSort);
        cv.put("sumSet", sumSet);
        cv.put("sumSustain", sumSustain);
        cv.put("sumStandardize", sumStandardize);
        cv.put("sumShine", sumShine);
        cv.put("sumSafety", sumSafety);
        cv.put("totalScore", totalScore);

        SQLiteDatabase db = getWritableDatabase();
        long newRowId = -1;

        try {
            newRowId = db.insert("auditResults", null, cv);
            Log.d("Database", "Audit results inserted successfully. Row ID: " + newRowId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Database", "Error inserting audit results: " + e.getMessage());
        } finally {
            db.close();
        }
    }


    public List<String> getAllDepartments() {
        List<String> departments = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM department", null);

        int departmentIndex = cursor.getColumnIndex("department");

        if (cursor.moveToFirst()) {
            do {
                String department = cursor.getString(departmentIndex);
                departments.add(department);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return departments;
    }

    public List<String> getAllZones() {
        List<String> zones = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM zone", null);

        int zoneIndex = cursor.getColumnIndex("zone");
        if (cursor.moveToFirst()) {
            do {
                String zone = cursor.getString(zoneIndex);
                zones.add(zone);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return zones;
    }
    public String[] getAuditDataById(int auditId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] result = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM audit WHERE id = ?", new String[]{String.valueOf(auditId)});

            if (cursor.moveToFirst()) {
                int departmentIndex = cursor.getColumnIndex("department");
                int zoneIndex = cursor.getColumnIndex("zone");
                int dateIndex = cursor.getColumnIndex("date");

                // Check if column indices are valid
                if (departmentIndex != -1 && zoneIndex != -1 && dateIndex != -1) {
                    result = new String[]{
                            cursor.getString(departmentIndex),
                            cursor.getString(zoneIndex),
                            cursor.getString(dateIndex)
                    };
                } else {
                    // Log a warning if indices are not valid
                    Log.w("Database", "Invalid column indices for audit data");
                }
            } else {
                // Log a warning if no data is found
                Log.w("Database", "No data found for audit ID: " + auditId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Database", "Error retrieving audit data: " + e.getMessage());
        } finally {
            // Close the cursor in a finally block to ensure it is closed, even if an exception occurs
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

            // Always close the database in a finally block
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return result;
    }

    public Cursor getDataForCurrentMonth() {
        SQLiteDatabase db = getReadableDatabase();

        String currentMonth = getCurrentMonth();
        String currentYear = getCurrentYear();

        // Query to retrieve data for the current month
        String query = "SELECT department, zone, totalScore FROM auditResults " +
                "WHERE strftime('%Y-%m', date) = ?";

        String[] selectionArgs = new String[]{currentYear + "-" + currentMonth};

        return db.rawQuery(query, selectionArgs);
    }


    private String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    private String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        return monthFormat.format(calendar.getTime());
    }
    public String getDepartmentForZone(String zone) {
        SQLiteDatabase db = getReadableDatabase();
        String department = null;

        Cursor cursor = db.rawQuery("SELECT department FROM zone WHERE zone = ?", new String[]{zone});
        int departmentIndex = cursor.getColumnIndex("department");

        if (cursor.moveToFirst() && departmentIndex != -1) {
            department = cursor.getString(departmentIndex);
        }

        cursor.close();
        db.close();
        return department;
    }

    public List<String> getAllAuditResults() {
        List<String> auditResults = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM auditResults", null);

        int departmentIndex = cursor.getColumnIndex("department");
        int zoneIndex = cursor.getColumnIndex("zone");
        int totalScoreIndex = cursor.getColumnIndex("totalScore");

        if (cursor.moveToFirst()) {
            do {
                String department = cursor.getString(departmentIndex);
                String zone = cursor.getString(zoneIndex);
                int totalScore = cursor.getInt(totalScoreIndex);

                // Create a string representation of the data
                String result = "Department: " + department + ", Zone: " + zone + ", Total Score: " + totalScore;
                auditResults.add(result);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return auditResults;
    }

    public int getTotalScoreForDepartmentAndZone(String department, String zone) {
        SQLiteDatabase db = getReadableDatabase();
        int totalScore = 0;

        String query = "SELECT totalScore FROM auditResults WHERE department = ? AND zone = ?";
        String[] selectionArgs = new String[]{department, zone};

        Cursor cursor = db.rawQuery(query, selectionArgs);
        int totalScoreIndex = cursor.getColumnIndex("totalScore");

        if (cursor.moveToFirst() && totalScoreIndex != -1) {
            totalScore = cursor.getInt(totalScoreIndex);
        }

        cursor.close();
        db.close();
        return totalScore;
    }
}
