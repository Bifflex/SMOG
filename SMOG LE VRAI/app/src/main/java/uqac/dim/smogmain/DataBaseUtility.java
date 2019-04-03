package uqac.dim.smogmain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtility {

    private FirebaseDatabase database;
    private DatabaseReference mReference;
    private List<Film> filmList = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Film> films, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public DataBaseUtility() {
        database = FirebaseDatabase.getInstance();
        mReference = database.getReference("films");
    }

    public void ReadData(final DataStatus dataStatus) {
        mReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                filmList.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot KeyNode : dataSnapshot.getChildren()){
                    keys.add(KeyNode.getKey());
                    Film film = KeyNode.getValue(Film.class);
                    filmList.add(film);
                }
                dataStatus.DataIsLoaded(filmList,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
