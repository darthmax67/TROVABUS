package android.battistim.it.busfinder.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by m.battisti on 03/04/2017.
 * Implements Builder and Static Factory Pattern
 * Implements interface Parcelable
 */

public final class BusStop implements Parcelable
{

    /**
     * Constant that identify an existing data
     */
    private static final byte PRESENT = 1;

    /**
     * Constant that identify a NON existing data
     */
    private static final byte NOT_PRESENT = 0;



    public final String id;
    public final String name;
    public final String direction;
    public final float latitude;
    public final float longitude;


    private BusStop(String id, String name, String direction, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private BusStop(Parcel in) {
        id = in.readString();
        name = in.readString();
        boolean present = in.readByte() == PRESENT;
        if (present){
            direction = in.readString();
        } else {
            direction = null;
        }
        present = in.readByte() == PRESENT;
        if (present){
            latitude = in.readFloat();
            longitude = in.readFloat();
        } else {
            latitude = 0.0f;
            longitude = 0.0f;
        }
    }

    /**
     * Implementation of a CREATOR for the creation of the instance
     */
    public static final Parcelable.Creator<BusStop> CREATOR = new Parcelable.Creator<BusStop>() {
        public BusStop createFromParcel(Parcel in) {
            return new BusStop(in);
        }

        public BusStop[] newArray(int size) {
            return new BusStop[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        if (direction != null) {
            dest.writeByte(PRESENT);
            dest.writeString(direction);
        } else {
            dest.writeByte(NOT_PRESENT);
        }
        if (latitude != 0.0f) {
            dest.writeByte(PRESENT);
            dest.writeFloat(latitude);
            dest.writeFloat(longitude);
        } else {
            dest.writeByte(NOT_PRESENT);
        }
    }

    public interface Keys
    {
        String ID = "id";
        String NAME = "name";
        String DIRECTION = "direction";
        String LATITUDE = "latitude";
        String LONGITUDE = "longitude";
    }

    public static class Builder
    {
        private String mId;
        private String mName;
        private String mDirection;
        private float mLatitude;
        private float mLongitude;


          private Builder(final String id, final String name)
          {
              this.mId = id;
              this.mName = name;
          }

          public static Builder create(final String id, final String name)
          {
              return new Builder(id, name);
          }

        public Builder withDirection(final String direction)
        {
            this.mDirection = direction;
            return this;
        }


        public Builder withLocation(final float latitude, final float longitude)
          {
            this.mLatitude = latitude;
            this.mLatitude = longitude;

            return this;

          }


            public BusStop build()
            {
                return new BusStop(mId,mName, mDirection, mLatitude, mLongitude);
            }

     }
}
