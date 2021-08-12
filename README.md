# PSMeter
## A password strength meter for Android.

![](https://github.com/YoussefSeddik/PSMeter/blob/master/PsMetertPreview.gif)

[![](https://jitpack.io/v/YoussefSeddik/PSMeter.svg)](https://jitpack.io/#YoussefSeddik/PSMeter)

### Quick Start:

1-  Add import PSMeter to your project.
```sh
implementation "com.github.YoussefSeddik:PSMeter:${VERSION}"

```
2- Add PSMeterView to your XML

```sh
    <com.baianat.psmeter.PsMeterView
        android:id="@+id/psMeterView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/passwordEditText"
        />
```

3- attach your password editText with PSMeterView

```sh
[EditText].addTextChangedListener {
            attachPsStrengthEstimator(it.toString())
}
```
        
> Note:
`attachPsStrengthEstimator` should be called whenever the password text is changed to update the indicator appopriately.
### Customization:

#### View Customization:
You can customize PSMeter for these attributes:


| Attribute | Definition | Default value | 
| ------ | ------ | ------ |
| psBackgroundProgressColor | The background color of the strength bar | android.R.color.white |
| psLabel | The title across the strength value | "Strength" |
| psTextColor | The text color for the title | android.R.color.black |
| psFont | The font for the strength labels | system font with 12sp size |

```sh
    <com.baianat.psmeter.PsMeterView
        android:id="@+id/psMeterView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/passwordEditText"
        app:psBackgroundProgressColor="@android:color/holo_green_light"
        app:psLabel="empty"
        app:psFont="@font/app_font_bold"
        app:psTextColor="@android:color/holo_green_dark"
        />
```

#### States Customization:
You can also customize text, text color, progress color for each of the 6 strength states.

These attributes are defined using PsStateDecorator class as follows...
```sh
PsStateDecorator(
            psLabel = R.string.very_week,
            pasTextColor = R.color.psColorVeryWeek,
            psProgressColor = R.color.psColorVeryWeek
        )
```
To customize all the stated you only have to set the six decorators for the six different states (empty, very weak, weak, fair, strong, very strong).

This can easily be achieved using `PsMeterView.builder()`, the object combining all the decorators.

##### Example: The default PsStateDecorator is implemented as follows...

```sh
val psMeterBuilder = PsMeterView.builder()
            .setEmptyStateDecorator(
                PsStateDecorator(
                    psLabel = R.string.empty,
                    psTextColor = android.R.color.black,
                    psProgressColor = android.R.color.black,
                )
            ).setVeryWeekStateDecorator(
                PsStateDecorator(
                    psLabel = R.string.very_week,
                    pasTextColor = R.color.psColorVeryWeek,
                    psProgressColor = R.color.psColorVeryWeek
                )
            ).setWeakStateDecorator(
                PsStateDecorator(
                    psLabel = R.string.weak,
                    pasTextColor = R.color.psColorWeek,
                    psProgressColor = R.color.psColorWeek
                )
            ).setFairStateDecorator(
                PsStateDecorator(
                    psLabel = R.string.fair,
                    pasTextColor = R.color.psColorFair,
                    psProgressColor = R.color.psColorFair
                )
            ).setStrongStateDecorator(
                PsStateDecorator(
                    psLabel = R.string.strong,
                    pasTextColor = R.color.psColorStrong,
                    psProgressColor = R.color.psColorStrong
                )
            ).setVeryStrongStateDecorator(
                PsStateDecorator(
                    psLabel = R.string.very_strong,
                    pasTextColor = R.color.psColorVeryStrong,
                    psProgressColor = R.color.psColorVeryStrong
                )
            ).build()
        mPsMeterView.configurePsMeter(psMeterBuilder)
```
> Note:
don't forget to attach PSMeterStrengthEstimator with passwordEditText after that using

`[EditText].addTextChangedListener {
            attachPsStrengthEstimator(it.toString())
}`
        
### Callback:
You can observe for whenever the password strength changes, just use the following.
```sh  
[PsMeterView].onPsMeterScoreChanged { passStrengthEnum, score, strengthMessage ->
            Toast.makeText(this, "passStrengthEnum: $passStrengthEnum", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "score: $score", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "passStrengthMessage: $strengthMessage", Toast.LENGTH_SHORT).show()
}
```
Alternatively

1- you can get the current password strength phase by:
```sh  
[PsMeterView].passStrengthEnum
```
2- you can get the current password strength score by:
```sh  
[PsMeterView].passStrengthEnum.score
```
3- you can get the current password strength message by:
```sh  
[PsMeterView].passStrengthMessage
```
> Note: passStrengthEnum contains 6 values as follows...
```sh  
enum class PassStrengthEnum(val score: Int) {
        EMPTY(0),
        VERY_WEAK(1),
        Weak(2),
        FAIR(3),
        STRONG(4),
        VERY_STRONG(5),
}
```

### Password Strength Algorithm:
##### Default Algorithm:
The default algorithm for determining password strength is based on 
[GoSimpleLLC/nbvcxz](https://github.com/GoSimpleLLC/nbvcxz)

##### Custom Algorithm:
You can easily use your owen custom password estimator by implement the `PsMeterEstimator`
Example:

```sh  
class CustomPassEstimation : PsMeterEstimator {
        override fun estimatePassword(pass: String): PsMeterStrengthCategoryEnum {
            return when (pass.length) {
                0 -> PsMeterStrengthCategoryEnum.EMPTY
                1 -> PsMeterStrengthCategoryEnum.VERY_WEAK
                2 -> PsMeterStrengthCategoryEnum.Weak
                3 -> PsMeterStrengthCategoryEnum.FAIR
                4 -> PsMeterStrengthCategoryEnum.STRONG
                else -> PsMeterStrengthCategoryEnum.VERY_STRONG
            }
        }
    }
```
And then attach your custom defenestion to PassEstimator view by:

`[PsMeterView].configurePsMeterEstimator(CustomPassEstimation())`

### Dependency.
PSMeter uses [GoSimpleLLC/nbvcxz](https://github.com/GoSimpleLLC/nbvcxz), rxkotlin, rxAndroid under its hood, they gets automatically installed with library.
