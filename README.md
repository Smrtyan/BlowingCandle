# BlowingCandle

## function

### (Your should set the app as a device administrator on which the first reference below gives a solution towards it, otherwise it may cash)

Blow the microphone to extinguish the fire on the candle.
If the noise is up to 70 db, the fire will be put out,then the screen will be locked.

## previews


<div align='center' >
  <img align='center' width="250"  src="https://github.com/Smrtyan/BlowingCandle/blob/master/WechatIMG5%201.jpeg"/>
  <img align='center' width="250"  src="https://github.com/Smrtyan/BlowingCandle/blob/master/WechatIMG6.jpeg"/>
  <img align='center' width="250"  src="https://github.com/Smrtyan/BlowingCandle/blob/master/WechatIMG7.jpeg"/>
</div>

## how it works


<pre>
The app start in a while loop, trying to calculate how much noise currently.

  noise < 50 db , set a pic of full flame candle to the ImageView,
50db<=noise<70db, set a pic of half flame candle to the ImageView,
    else        , set a pic of candle with no flame to the  ImageView
</pre>


## references

<a href = 'https://blog.csdn.net/u011913612/article/details/77822280'>Android息屏与亮屏</a>

<a href = 'https://developer.android.com/reference/android/media/AudioRecord'>AudioRecord doc</a>
