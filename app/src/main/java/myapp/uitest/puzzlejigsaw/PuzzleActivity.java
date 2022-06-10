package myapp.uitest.puzzlejigsaw;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PuzzleActivity extends AppCompatActivity implements Runnable, View.OnTouchListener {
    PuzzleLayout puzzleLayout;
    TextView tvTips;
    Button counter;
    Button mainMenu;
    Button next;
    ImageView ivTips;
    int squareRootNum = 2;
    int score = 0;
    int drawableId = R.mipmap.pic_02;
    CountDownTimer cTimer = null;
    GlobalPreferences globalPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        globalPreferences = new GlobalPreferences(this);
        ivTips = (ImageView) findViewById(R.id.iv_tips);
        ivTips.setImageResource(drawableId);
        tvTips = (TextView) findViewById(R.id.tv_tips);
        next = (Button) findViewById(R.id.next);
        mainMenu = (Button) findViewById(R.id.mainMenu);
        counter = (Button) findViewById(R.id.timer);
        if (globalPreferences.getTimeStatus()) {
            startTimer();
            counter.setVisibility(View.VISIBLE);
        } else {
            counter.setVisibility(View.INVISIBLE);

        }
        tvTips.setOnTouchListener(this);
        puzzleLayout = (PuzzleLayout) findViewById(R.id.activity_swipe_card);
        puzzleLayout.setImage(drawableId, squareRootNum);
        puzzleLayout.setOnCompleteCallback(new PuzzleLayout.OnCompleteCallback() {
            @Override
            public void onComplete() {
                score++;
                Toast.makeText(PuzzleActivity.this, "completed", Toast.LENGTH_LONG).show();
                puzzleLayout.postDelayed(PuzzleActivity.this, 800);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawableId++;
                puzzleLayout.postDelayed(PuzzleActivity.this, 800);
                if (globalPreferences.getTimeStatus()) {
                    cancelTimer();
                    startTimer();
                }

            }
        });
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PuzzleActivity.this.finish();
            }
        });

    }

    @Override
    public void run() {
        squareRootNum++;
        drawableId++;
        if (squareRootNum > 10) {
            Toast.makeText(PuzzleActivity.this, R.string.next, Toast.LENGTH_SHORT).show();
            showDialog();
        } else {
            try {
                ivTips.setImageResource(drawableId);
                puzzleLayout.setImage(drawableId, squareRootNum);

            } catch (Resources.NotFoundException e) {
                Log.e("TAG", "run: " + e.getLocalizedMessage());
            }

        }
    }

    private void showDialog() {
        new AlertDialog.Builder(PuzzleActivity.this)
                .setTitle(R.string.success)
                .setMessage(R.string.restart)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                squareRootNum = 2;
                                drawableId = R.mipmap.pic_02;
                                ivTips.setImageResource(drawableId);
                                puzzleLayout.setImage(drawableId, squareRootNum);
                            }
                        }).setNegativeButton(R.string.exit,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ivTips.setVisibility(View.VISIBLE);
                break;
            default:
                ivTips.setVisibility(View.GONE);
        }
        return true;
    }

    void startTimer() {
        cTimer = new CountDownTimer((long) globalPreferences.getMinutes() * 60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long durationSeconds = millisUntilFinished / 1000;
                String aaa = String.format("%02d:%02d:%02d", durationSeconds / 3600,
                        (durationSeconds % 3600) / 60, (durationSeconds % 60));
                counter.setText(aaa);
            }

            public void onFinish() {
                Toast.makeText(PuzzleActivity.this, "Time is up", Toast.LENGTH_LONG).show();
                PuzzleActivity.this.finish();

            }
        };
        cTimer.start();
    }


    //cancel timer
    void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        globalPreferences.storeScore(score);
        cancelTimer();
    }
}
