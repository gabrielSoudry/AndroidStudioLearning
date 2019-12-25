package com.example.firstapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import org.bson.Document;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final StitchAppClient client =
                Stitch.initializeDefaultAppClient("androidapp-lfjxs");


        final RemoteMongoClient mongoClient =
                client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");

        final RemoteMongoCollection<Document> coll =
                mongoClient.getDatabase("mydb").getCollection("user");

        coll.findOne();
        Log.d("============", "onCreate:"+coll.count());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Task<StitchUser> stitchUserTask = client.getAuth().loginWithCredential(new AnonymousCredential());
        if(stitchUserTask.isSuccessful()) {
            Log.e("===============STITCH", "Login Sucessfull ");
        }
        else {
            Log.e("===============STITCH", "Login :'( !");
        }
    }


}


