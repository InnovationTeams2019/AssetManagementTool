package com.factory.ecommerce.assetmanagementtool.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.internal.ParcelableSparseArray;

import java.util.Date;

public class User implements Parcelable {

    private String name;
    private String enterpriseId;
    private Date assignmentDate;
    private Date returnDate;

    public User(){}

    /**
     * Use when reconstructing User object from parcel
     * This will be used only by the 'CREATOR'
     * @param in a parcel to read this object
     */
    private User(Parcel in) {
        this.name = in.readString();
        this.enterpriseId = in.readString();
        this.assignmentDate = (java.util.Date) in.readSerializable();
        this.returnDate = (java.util.Date) in.readSerializable();
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
        dest.writeString(name);
        dest.writeString(enterpriseId);
        dest.writeSerializable(assignmentDate);
        dest.writeSerializable(returnDate);
    }

    /**
     * This field is needed for Android to be able to
     * create new objects, individually or as arrays
     *
     * If you don’t do that, Android framework will through exception
     * Parcelable protocol requires a Parcelable.Creator object called CREATOR
     */
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
     

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }


    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", enterpriseId='" + enterpriseId + '\'' +
                ", assignmentDate=" + assignmentDate +
                ", returnDate=" + returnDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User toCompare = (User) obj;
            return (this.enterpriseId.equalsIgnoreCase(toCompare.getEnterpriseId()));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (this.getName()+this.getEnterpriseId()).hashCode();
    }
}
