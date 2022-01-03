package lv.groupOne.linkedinhelper;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import lv.groupOne.linkedinhelper.adapter.ParseItemAdapter;
import lv.groupOne.linkedinhelper.data.model.ParseItemModel;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "";
    private RecyclerView recyclerView;
    private ParseItemAdapter parseItemAdapter;
    private final List<ParseItemModel> parseItemModelList = new ArrayList<>();
    Elements data;
    Document document;

    boolean debug = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(debug){
            startActivity(new Intent(this, PrimaryActivity.class));
            setContentView(R.layout.activity_primary);
        }else{
            startActivity(new Intent(this, AuthActivity.class));
            setContentView(R.layout.activity_auth);
        }

        //recyclerView = findViewById(R.id.recyclerView_id);

        //Content content = new Content();
       //content.execute();

    }

    /* private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            parseItemAdapter = new ParseItemAdapter((ArrayList<ParseItemModel>) parseItemModelList, MainActivity.this);
            recyclerView.setAdapter(parseItemAdapter);
            parseItemAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String temp = "";
                String url = "https://www.linkedin.com/pub/dir?firstName=Roberts&lastName=&trk=people-guest_people-search-bar_search-submit";
                document = Jsoup.connect(url).get();
                data = document.select("li.pserp-layout__profile-result-list-item");
//              Log.e(TAG, "Get Data: "+data.toString() );

                int size = data.size();
                for (int i =0; i <size; i++){

                    String title = data.select("h3.base-search-card__title")
                            .eq(i)
                            .select("a").text();

                    String role = data.select("h4.base-search-card__subtitle")
                            .eq(i)
                            .text();

                    String metadata = data.select("div.base-search-card__metadata")
                            .eq(i)
                            .text();

                    String imagelink = data.select("div.search-entity-media")
                            .eq(i).select("img").attr("src");

                    String postLink = data.select("h3.base-search-card__title").eq(i).select("a").attr("href");

//                  Log.e(TAG, "Get Data: "+title.toString() );

                    parseItemModelList.add(new ParseItemModel(title,role,metadata,imagelink));

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    } */
}