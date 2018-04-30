package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti",
                R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko",
                R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu",
                R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa",
                R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka",
                R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka",
                R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku",
                R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta",
                R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e",
                R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha",
                R.drawable.number_ten, R.raw.number_ten));
        words.add(new Word("eleven", "wo'elutti",
                        R.drawable.number_ten, R.raw.number_ten));

//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(itemsAdapter);
//
//       LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        for (int i = 0; i < words.size(); i++) {
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }
        /**
         * Создаем {@link WordAdapter} источником данных которого является список
         * {@link Word} The adapter knows how to create list item views for each item in the list.
         */

        WordAdapter adapter = new WordAdapter (this, words, R.color.category_numbers);
//        Получите ссылку на ListView и присоедините адаптер к listView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);
//                Log.i("numbersActivity", "текущее слово: " + word);
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this,
                        word.getAudioResourceId());
                mMediaPlayer.start();
            }
        });

    }
}
