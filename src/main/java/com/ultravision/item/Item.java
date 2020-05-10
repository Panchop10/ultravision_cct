package com.ultravision.item;

public class Item {
    private int itemID;
    private String title;
    private String UID;
    private int rentalPrice;

    private int yearReleased;
    private String genre;
    private String diskType;
    private String directorOrBand;
    private String itemType;
    private String itemStatus;

    //constructor for the database
    public Item(String... values){
        itemID = Integer.parseInt(values[0]);
        title = values[1];
        UID = values[2];
        rentalPrice = Integer.parseInt(values[3]);
        yearReleased = Integer.parseInt(values[4]);
        genre = values[5];
        diskType = values[6];
        directorOrBand = values[7];
        itemType = values[8];
        itemStatus = values[9];
    }

    //constructor for creation of item through the form
    public Item(int price, int year, String director, String title, String genre,
                String diskType, int itemType){
        this.itemID = ItemModel.createItem(title, price, year, genre, diskType, director, itemType);
        this.title = title;
        this.rentalPrice = price;
        this.yearReleased = year;
        this.directorOrBand = director;
        this.genre = genre;
        this.diskType = diskType;
    }

    //update data
    public void update(int price, int year, String director, String title, String genre, String diskType,
                       int itemType){
        ItemModel.updateItem(this.getItemID(), price, year, director, title, genre, diskType, itemType);
    }

    public int getItemID() {
        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public String getUID() {
        return UID;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public String getGenre() {
        return genre;
    }

    public String getDiskType() {
        return diskType;
    }

    public String getDirectorOrBand() {
        return directorOrBand;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemStatus() {
        return itemStatus;
    }

}
