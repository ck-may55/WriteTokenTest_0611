package com.example.chie.notifitest0429;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.chie.notifitest0429.MainActivity.flag;
import static com.example.chie.notifitest0429.MainActivity.uid;

/**
 * Created by chie on 2017/04/29.
 */

public class MyFirebaseInstanceIdservice extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIdservice";
    public  static String token;
    private long createdAt;
    private long endedAt;

    //ユーザIDの指定
    private static String userId = "AB0012-6";

    private List<User> hoge1;


    @Override
    public void onTokenRefresh() {
        //アプリ初起動時にFCMトークンを生成
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "RefreshedToken = " + token);
        createdAt = new Date().getTime() /1000L;
        endedAt = 0;

        //TODO
        //生成されたトークンをmain_activityのtoken_viewに指定する

        //トークンの値をサーバーへ送信
        submit();
    }

    private void submit() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d(TAG, "getInstance");

        // 2017/6/3　変更部分
        // token,createdAt,endedAtを,usersTokenの子として登録



        //TODO
        //ユーザIDに対してデータが見つかればendedAtを更新
        //endedAt = new Date().getTime()/1000L;

/*
        DatabaseReference userRef = database.getReference("/usersTokens/" + userId);
        userRef.push();
        userRef.child("token").setValue(token);
        userRef.child("createdAt").setValue(createdAt);
        userRef.child("endedAt").setValue(endedAt);
*/


        //DBに書き込むユーザについての個人設定
        User user = new User(createdAt,endedAt,token);
        DatabaseReference refUser = database.getReference("/usersTokens_test/" + userId);
        refUser.push().setValue(user);

/*          refHoge.child("hoge1").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<User> users = new ArrayList<User>();
                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            users.add(child.getValue(User.class));
                        }
                    }
                });

*/
        Log.d(TAG, "setValue");

        //setValue失敗時→ログにpermission denied の表示

    }

}
