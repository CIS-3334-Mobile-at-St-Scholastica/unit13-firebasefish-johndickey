package css.cis3334.fishlocatorfirebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cssuser on 4/20/2017.
 */

public class FishFirebaseData {
    DatabaseReference FishRef;
    public static final String FishDataTag = "Fish Data";

    public DatabaseReference open() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FishRef = database.getReference(FishDataTag);
        return FishRef;

        // Get an instance of the database and a reference to the fish data in it

    }

    public void close() {

    }

    public Fish createFish(String species, String weightInOz, String dateCaught) {           //Added String rating as a parameter
        String key = FishRef.child(FishDataTag).push().getKey(); // ---- Get a new database key for the vote

        Fish newFish = new Fish(key, species, weightInOz, dateCaught); // ---- set up the fish object

        FishRef.child(key).setValue(newFish); // ---- write the vote to Firebase

        return newFish;
    }

    public Fish createFish(String species, String weightInOz, String dateCaught, String locationLatitude, String locationLongitude) {           //Added String rating as a parameter
        String key = FishRef.child(FishDataTag).push().getKey();// ---- Get a new database key for the vote

        Fish newFish = new Fish(key, species, weightInOz, dateCaught, locationLatitude, locationLongitude); // ---- set up the fish object

        FishRef.child(key).setValue(newFish);// ---- write the vote to Firebase

        return newFish;
    }

    public void deleteFish(Fish fish) {
        String key = fish.getKey();
        FishRef.child(key).removeValue();

    }

    public List<Fish> getAllFish(DataSnapshot dataSnapshot) {
        List<Fish> fishList = new ArrayList<Fish>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Fish fish = data.getValue(Fish.class);
            fishList.add(fish);
        }
        return fishList;

    }
}


