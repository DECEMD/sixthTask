package com.example.sixthtask

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Person(
    var id: Int,
    var avatarId: Int,
    var name: String?,
    var secondName: String?,
    var phone: String?
) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(avatarId)
        parcel.writeString(name)
        parcel.writeString(secondName)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}

