# dogs-we-love

## troubleshooting

[issue](https://issuetracker.google.com/issues/174695268?pli=1#comment13)

> If you are using macOS with chip M1 and running with emulator comment the follow lines on `build.gradle`.

```
     implementation 'androidx.room:room-runtime:2.3.0'
     implementation "androidx.room:room-ktx:2.3.0"
     kapt "androidx.room:room-compiler:2.3.0"
```

> And uncomment the follow lines on `build.gradle`.
```
     /*implementation "androidx.room:room-ktx:2.4.0-beta01"
     implementation "androidx.room:room-runtime:2.4.0-beta01"
     kapt 'androidx.room:room-compiler:2.4.0-beta01'*/
```

