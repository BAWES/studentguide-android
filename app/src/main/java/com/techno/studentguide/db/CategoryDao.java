package com.techno.studentguide.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CATEGORY.
*/
public class CategoryDao extends AbstractDao<Category, Long> {

    public static final String TABLENAME = "CATEGORY";

    /**
     * Properties of entity Category.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Category_id = new Property(1, String.class, "category_id", false, "CATEGORY_ID");
        public final static Property Parent_category_id = new Property(2, String.class, "parent_category_id", false, "PARENT_CATEGORY_ID");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Count = new Property(4, String.class, "count", false, "COUNT");
        public final static Property Category_name_en = new Property(5, String.class, "category_name_en", false, "CATEGORY_NAME_EN");
        public final static Property Category_name_ar = new Property(6, String.class, "category_name_ar", false, "CATEGORY_NAME_AR");
        public final static Property Filter_by = new Property(7, Boolean.class, "filter_by", false, "FILTER_BY");
        public final static Property Category_created_datetime = new Property(8, String.class, "category_created_datetime", false, "CATEGORY_CREATED_DATETIME");
        public final static Property Category_updated_datetime = new Property(9, String.class, "category_updated_datetime", false, "CATEGORY_UPDATED_DATETIME");
    };


    public CategoryDao(DaoConfig config) {
        super(config);
    }
    
    public CategoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CATEGORY' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'CATEGORY_ID' TEXT," + // 1: category_id
                "'PARENT_CATEGORY_ID' TEXT," + // 2: parent_category_id
                "'NAME' TEXT," + // 3: name
                "'COUNT' TEXT," + // 4: count
                "'CATEGORY_NAME_EN' TEXT," + // 5: category_name_en
                "'CATEGORY_NAME_AR' TEXT," + // 6: category_name_ar
                "'FILTER_BY' INTEGER," + // 7: filter_by
                "'CATEGORY_CREATED_DATETIME' TEXT," + // 8: category_created_datetime
                "'CATEGORY_UPDATED_DATETIME' TEXT);"); // 9: category_updated_datetime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CATEGORY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Category entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String category_id = entity.getCategory_id();
        if (category_id != null) {
            stmt.bindString(2, category_id);
        }
 
        String parent_category_id = entity.getParent_category_id();
        if (parent_category_id != null) {
            stmt.bindString(3, parent_category_id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String count = entity.getCount();
        if (count != null) {
            stmt.bindString(5, count);
        }
 
        String category_name_en = entity.getCategory_name_en();
        if (category_name_en != null) {
            stmt.bindString(6, category_name_en);
        }
 
        String category_name_ar = entity.getCategory_name_ar();
        if (category_name_ar != null) {
            stmt.bindString(7, category_name_ar);
        }
 
        Boolean filter_by = entity.getFilter_by();
        if (filter_by != null) {
            stmt.bindLong(8, filter_by ? 1l: 0l);
        }
 
        String category_created_datetime = entity.getCategory_created_datetime();
        if (category_created_datetime != null) {
            stmt.bindString(9, category_created_datetime);
        }
 
        String category_updated_datetime = entity.getCategory_updated_datetime();
        if (category_updated_datetime != null) {
            stmt.bindString(10, category_updated_datetime);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Category readEntity(Cursor cursor, int offset) {
        Category entity = new Category( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // category_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // parent_category_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // count
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // category_name_en
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // category_name_ar
            cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0, // filter_by
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // category_created_datetime
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // category_updated_datetime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Category entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCategory_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setParent_category_id(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCount(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCategory_name_en(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCategory_name_ar(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFilter_by(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0);
        entity.setCategory_created_datetime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCategory_updated_datetime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Category entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Category entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
