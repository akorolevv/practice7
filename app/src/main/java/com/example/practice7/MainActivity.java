package com.example.practice7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Запуск сервиса для воспроизведения музыки
        Intent startIntent = new Intent(this, MusicService.class);
        startService(startIntent);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showConfirmDialog();
            }
        });

    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // Остановка сервиса и музыки при уничтожении активности
        Intent stopIntent = new Intent(this, MusicService.class);
        stopService(stopIntent);
    }

    private void showConfirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Перейти к следующей активности?")
                .setMessage("Вы уверены, что хотите перейти к следующей активности?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Переход к следующей активности
                        Intent intent = new Intent(MainActivity.this, NewActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Отмена перехода
                        dialog.cancel();
                    }
                })
                .show();
    }


}