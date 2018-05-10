package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mOnCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMediaPlayer();
                }
            };

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    //метод onCreateView() фрагментов является public, в отличии от protected для активности
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        //Для активности было так:
        // mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //Fragment не имеет доступа к системным службам, в отличии от Activity
        //поэтому сначала создаем экземпляр объекта Activity (getActivity()).
        // Это будет NumbersActivity, который включает текущий фрагмент NumbersFragment.
        // Затем вызовите getSystemService (String) в этом объекте Activity.
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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

        //для активности было:
        // WordAdapter adapter = new WordAdapter (this, words, R.color.category_numbers);
        //фрагмент не является допустимым контекстом для для этого конструктора
        //поэтому передаем ссылку на Activity(getActivity(),
        // которая включает этот фрагмент в качестве контекста

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        //Для активности нужно писать так: ListView listView = (ListView) findViewById(R.id.list);
        //НО для фрагментов нет метода findViewById, поэтому вызываем этот метод у rootView,
        //который должен содержать дочерние представления, такие как ListView:
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //для активности было: mMediaPlayer =
                    // MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    //для фрагментов сначала получаем активность getActivity()
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });
        return rootView;
    }

    @Override
    //метод onStop() фрагментов является public, в отличии от protected для активности
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
