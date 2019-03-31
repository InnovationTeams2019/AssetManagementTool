package com.factory.ecommerce.assetmanagementtool.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Asset implements Parcelable {

    private String id;
    private String name;
    private String description;
    private long qtyInStock;
    private long totalQty;
    private List<User> users;

    public Asset(){}

    private Asset(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.qtyInStock = in.readLong();
        this.totalQty = in.readLong();
        this.users = new ArrayList<>();
        in.readTypedList(this.users, User.CREATOR);

    }

    /**
     * Define the kind of object that you gonna parcel,
     * You can use hashCode() here
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Actual object serialization happens here, Write object content
     * to parcel one by one, reading should be done according to this write order
     * @param dest parcel
     * @param flags Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeLong(qtyInStock);
        dest.writeLong(totalQty);
        dest.writeTypedList(users);
    }

    /**
     * This field is needed for Android to be able to
     * create new objects, individually or as arrays
     *
     * If you don’t do that, Android framework will through exception
     * Parcelable protocol requires a Parcelable.Creator object called CREATOR
     */
    public static final Parcelable.Creator<Asset> CREATOR = new Parcelable.Creator<Asset>() {

        public Asset createFromParcel(Parcel in) {
            return new Asset(in);
        }

        public Asset[] newArray(int size) {
            return new Asset[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(long qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public long getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(long totalQty) {
        this.totalQty = totalQty;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", qtyInStock=" + qtyInStock +
                ", totalQty=" + totalQty +
                ", users=" + users +
                '}';
    }
}
