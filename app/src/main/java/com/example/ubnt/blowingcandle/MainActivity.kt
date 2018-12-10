package com.example.ubnt.blowingcandle

import android.Manifest
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    var  mLock = Object()
    val SAMPLE_RATE_IN_HZ = 8000
    val BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
            AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT)
    lateinit var imageView_candle:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView_candle = findViewById(R.id.imageView_candle)

        //imageView_candle.background = resources.getDrawable(R.drawable.candle_2)
        val permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        if (permission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    0
            )
        }
        var recorder = AudioRecord(MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT,
                AudioFormat.ENCODING_PCM_16BIT, BUFFER_SIZE)

        Thread(Runnable {
            recorder.startRecording();


            var buffer = ShortArray(BUFFER_SIZE);
            while (true) {
                //r是实际读取的数据长度，一般而言r会小于buffersize
                var r:Int = recorder.read(buffer, 0, buffer.size);
                var v:Long = 0;
                // 将 buffer 内容取出，进行平方和运算
                for (i in buffer) {
                    v += i * i;
                }
                // 平方和除以数据总长度，得到音量大小。
                var mean: Double = (v /  r).toDouble();
                var volume:Double = 10 * Math.log10(mean);
                Log.d("myAudioTest", "分贝值:" + volume);
                if(volume<=50){
                    imageView_candle.post {
//                        imageView_candle.setImageResource(R.drawable.candle)

                        imageView_candle.background = resources.getDrawable(R.drawable.candle)
                    }


                }else if(volume>50&&volume<=70){
                    imageView_candle.post {
                       // imageView_candle.setImageResource(R.drawable.candle_2)
                        imageView_candle.background = resources.getDrawable(R.drawable.candle_2)

                    }
                }else {
                    imageView_candle.post {
//                        imageView_candle.setImageResource(R.drawable.candle_3)

                        imageView_candle.background = resources.getDrawable(R.drawable.candle_3)
                        val manager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
                        manager.lockNow()
                    }
                    break
                }

                // 大概一秒十次
                synchronized (mLock) {
                    try {
                        mLock.wait(100);

                    } catch (e:InterruptedException ) {
                        e.printStackTrace();
                    }
                }
            }
            recorder.stop();
            recorder.release();
           // recorder = null;
        }).start()


    }
}
