package com.bluesquarehub.microcam;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.VideoView;

public class ReviewActivity extends Activity {
    private Button mUseButton;
    private Button mCancelButton;
    private Button mReplayButton;
    private VideoView mVideoView;
    private String filePath;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_review);
        filePath = getIntent().getStringExtra("FILE_PATH");
        mUseButton = (Button) findViewById(R.id.useButton);
        mUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("VALIDATED", true);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        mCancelButton = (Button) findViewById(R.id.cancelButton);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("VALIDATED", false);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        mReplayButton = (Button) findViewById(R.id.replayButton);
        mReplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
            }
        });

        mVideoView = (VideoView) findViewById(R.id.videoView);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playVideo();
            }
        }, 1000);

    }

    public void playVideo() {
        String path = filePath;
        Uri u = Uri.parse(path);
        mVideoView.setVideoURI(u);
        mVideoView.start();
    }

}
