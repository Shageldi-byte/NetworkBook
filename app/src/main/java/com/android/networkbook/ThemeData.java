package com.android.networkbook;

public class ThemeData {
    private Integer id;
    private String title;
    private String short_desc;
    private String desc;
    private String book_name;
    private Integer book_start_position;
    private Integer book_end_position;
    private Integer category_id;
    private String class_number;

    public ThemeData(Integer id, String title, String short_desc, String desc, String book_name, Integer book_start_position, Integer book_end_position, Integer category_id, String class_number) {
        this.id = id;
        this.title = title;
        this.short_desc = short_desc;
        this.desc = desc;
        this.book_name = book_name;
        this.book_start_position = book_start_position;
        this.book_end_position = book_end_position;
        this.category_id = category_id;
        this.class_number = class_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Integer getBook_start_position() {
        return book_start_position;
    }

    public void setBook_start_position(Integer book_start_position) {
        this.book_start_position = book_start_position;
    }

    public Integer getBook_end_position() {
        return book_end_position;
    }

    public void setBook_end_position(Integer book_end_position) {
        this.book_end_position = book_end_position;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getClass_number() {
        return class_number;
    }

    public void setClass_number(String class_number) {
        this.class_number = class_number;
    }
}
